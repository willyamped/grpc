package com.gitlab.techschool.pcbook.service;

import com.gitlab.techschool.pcbook.pb.CreateLaptopRequest;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponse;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponseOrBuilder;
import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.pb.LaptopServiceGrpc;
import com.gitlab.techschool.pcbook.sample.Generator;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LaptopServerTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private LaptopStore store;
    private LaptopServer server;
    private ManagedChannel channel;

    @BeforeEach
    void setUp() throws IOException {
        // uses in process server vs server builder in LaptopServer.java
        String serverName = InProcessServerBuilder.generateName();
        InProcessServerBuilder serverBuilder = InProcessServerBuilder.forName(serverName).directExecutor();

        store = new InMemoryLaptopStore();
        server = new LaptopServer(serverBuilder, 0, store);
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

        Laptop found = store.Find(response.getId());
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

        Laptop found = store.Find(response.getId());
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
            store.Save(laptop);
            CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();

            LaptopServiceGrpc.LaptopServiceBlockingStub stub = LaptopServiceGrpc.newBlockingStub(channel);
            CreateLaptopResponse response = stub.createLaptop(request);
        });
    }
}
