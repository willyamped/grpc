package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.CreateLaptopRequest;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponse;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponseOrBuilder;
import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.pb.LaptopServiceGrpc;
import com.gitlab.techschool.pcbook.pb.RateLaptopRequest;
import com.gitlab.techschool.pcbook.pb.RateLaptopResponse;
import com.gitlab.techschool.pcbook.sample.Generator;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LaptopServerTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private LaptopStore laptopStore;
    private ImageStore imageStore;
    private RatingStore ratingStore;
    private LaptopServer server;
    private ManagedChannel channel;

    @BeforeEach
    void setUp() throws IOException {
        // uses in process server vs server builder in LaptopServer.java
        String serverName = InProcessServerBuilder.generateName();
        InProcessServerBuilder serverBuilder = InProcessServerBuilder.forName(serverName).directExecutor();

        laptopStore = new InMemoryLaptopStore();
        imageStore = new DiskImageStore("tmp");
        ratingStore = new InMemoryRatingStore();

        server = new LaptopServer(serverBuilder, 0, laptopStore, imageStore, ratingStore);
        server.start();

        channel = grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build());
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        server.stop();
    }

    @Test
    public void createLaptopWithValidID() {
        Generator generator = new Generator();
        Laptop laptop = generator.NewLaptop();
        CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();

        LaptopServiceGrpc.LaptopServiceBlockingStub stub = LaptopServiceGrpc.newBlockingStub(channel);
        CreateLaptopResponse response = stub.createLaptop(request);
        assertNotNull(response);
        assertEquals(laptop.getId(), response.getId());

        Laptop found = laptopStore.Find(response.getId());
        assertNotNull(found);
    }

    @Test
    public void createLaptopWithEmptyID() {
        Generator generator = new Generator();
        Laptop laptop = generator.NewLaptop().toBuilder().setId("").build();
        CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();

        LaptopServiceGrpc.LaptopServiceBlockingStub stub = LaptopServiceGrpc.newBlockingStub(channel);
        CreateLaptopResponse response = stub.createLaptop(request);
        assertNotNull(response);
        assertFalse(response.getId().isEmpty());

        Laptop found = laptopStore.Find(response.getId());
        assertNotNull(found);
    }

    @Test
    public void createLaptopWithAnInvalidID() {
        assertThrows(StatusRuntimeException.class, () -> {
            Generator generator = new Generator();
            Laptop laptop = generator.NewLaptop().toBuilder().setId("invalid").build();
            CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();

            LaptopServiceGrpc.LaptopServiceBlockingStub stub = LaptopServiceGrpc.newBlockingStub(channel);
            CreateLaptopResponse response = stub.createLaptop(request);
        });
    }

    @Test
    public void createLaptopWithAnExistingID() {
        assertThrows(StatusRuntimeException.class, () -> {
            Generator generator = new Generator();
            Laptop laptop = generator.NewLaptop();
            laptopStore.Save(laptop);
            CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();

            LaptopServiceGrpc.LaptopServiceBlockingStub stub = LaptopServiceGrpc.newBlockingStub(channel);
            CreateLaptopResponse response = stub.createLaptop(request);
        });
    }

    @Test
    public void rateLaptop() throws Exception {
        Generator generator = new Generator();
        Laptop laptop = generator.NewLaptop();
        laptopStore.Save(laptop);

        LaptopServiceGrpc.LaptopServiceStub stub = LaptopServiceGrpc.newStub(channel);
        RateLaptopResponseStreamObserver responseObserver = new RateLaptopResponseStreamObserver();
        StreamObserver<RateLaptopRequest> requestObserver = stub.rateLaptop(responseObserver);

        double[] scores = {8, 7.5, 10};
        double[] averages = {8, 7.75, 8.5};
        int n = scores.length;

        for (int i = 0; i < n; i++) {
            RateLaptopRequest request = RateLaptopRequest.newBuilder()
                    .setLaptopId(laptop.getId())
                    .setScore(scores[i])
                    .build();
            requestObserver.onNext(request);
        }
        requestObserver.onCompleted();
        assertNull(responseObserver.err);
        assertTrue(responseObserver.completed);
        assertEquals(n, responseObserver.responses.size());

        int idx = 0;
        for (RateLaptopResponse response : responseObserver.responses) {
            assertEquals(laptop.getId(), response.getLaptopId());
            assertEquals(idx + 1, response.getRatedCount());
            assertEquals(averages[idx], response.getAverageScore(), 1e-9);
            idx ++;
        }
    }

    private class RateLaptopResponseStreamObserver implements StreamObserver<RateLaptopResponse> {
        public List<RateLaptopResponse> responses;
        public Throwable err;
        public boolean completed;

        public RateLaptopResponseStreamObserver() {
            responses = new LinkedList<>();
        }

        @Override
        public void onNext(RateLaptopResponse response) {
            responses.add(response);
        }

        @Override
        public void onError(Throwable t) {
            err = t;
        }

        @Override
        public void onCompleted() {
            completed = true;
        }
    }
}
