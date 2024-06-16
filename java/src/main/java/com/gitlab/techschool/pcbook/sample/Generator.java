package com.gitlab.techschool.pcbook.sample;

import com.gitlab.techschool.pcbook.pb.CPU;
import com.gitlab.techschool.pcbook.pb.GPU;
import com.gitlab.techschool.pcbook.pb.Keyboard;
import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.pb.Memory;
import com.gitlab.techschool.pcbook.pb.Screen;
import com.gitlab.techschool.pcbook.pb.Storage;
import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class Generator {
    private Random rand;

    public Generator() {
        rand = new Random();
    }

    public Keyboard NewKeyboard() {
        return Keyboard.newBuilder()
                .setLayout(randomKeyboardLayout())
                .setBacklit(rand.nextBoolean())
                .build();
    }

    public CPU NewCPU() {
        String brand = randomCPUBrand();
        String name = randomCPUName(brand);

        int numberCores = randomInt(2, 8);
        int numberThreads = randomInt(numberCores, 12);

        double minGhz = randomDouble(2.0, 3.5);
        double maxGhz = randomDouble(minGhz, 5.0);

        return CPU.newBuilder()
                .setBrand(brand)
                .setName(name)
                .setNumberCores(numberCores)
                .setNumberThreads(numberThreads)
                .setMinGhz(minGhz)
                .setMaxGhz(maxGhz)
                .build();
    }

    public GPU NewGPU() {
        String brand = randomGPUBrand();
        String name = randomGPUName(brand);

        double minGhz = randomDouble(1.0, 1.5);
        double maxGhz = randomDouble(minGhz, 2.0);

        Memory memory = Memory.newBuilder()
                .setValue(randomInt(2, 6))
                .setUnit(Memory.Unit.GIGABYTE)
                .build();
        return GPU.newBuilder()
                .setBrand(brand)
                .setName(name)
                .setMinGhz(minGhz)
                .setMaxGhz(maxGhz)
                .setMemory(memory)
                .build();
    }

    public Memory NewRAM() {
        return Memory.newBuilder()
                .setValue(randomInt(4, 64))
                .setUnit(Memory.Unit.GIGABYTE)
                .build();
    }

    public Storage NewSSD() {
        Memory memory = Memory.newBuilder()
                .setValue(randomInt(128, 1024))
                .setUnit(Memory.Unit.GIGABYTE)
                .build();
        return Storage.newBuilder()
                .setDriver(Storage.Driver.SSD)
                .setMemory(memory)
                .build();
    }

    public Storage NewHDD() {
        Memory memory = Memory.newBuilder()
                .setValue(randomInt(1, 6))
                .setUnit(Memory.Unit.TERABYTE)
                .build();
        return Storage.newBuilder()
                .setDriver(Storage.Driver.HDD)
                .setMemory(memory)
                .build();
    }

    public Screen NewScreen() {
        int height = randomInt(1080, 4320);
        int width = height * 16 / 9;

        Screen.Resolution resolution = Screen.Resolution.newBuilder()
                .setHeight(height)
                .setWidth(width)
                .build();

        return Screen.newBuilder()
                .setSizeInch(randomFloat(13, 17))
                .setResolution(resolution)
                .setPanel(randomScreenPanel())
                .setMultitouch(rand.nextBoolean())
                .build();
    }

    public Laptop NewLaptop() {
        String brand = randomLaptopBrand();
        String name = randomLaptopName(brand);

        double weightKg = randomDouble(1.0, 3.0);
        double priceUsd = randomDouble(1500, 3500);

        int releaseYear = randomInt(2015, 2019);

        return Laptop.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setBrand(brand)
                .setName(name)
                .setCpu(NewCPU())
                .setRam(NewRAM())
                .addGpus(NewGPU())
                .addStorages(NewSSD())
                .addStorages(NewHDD())
                .setScreen(NewScreen())
                .setKeyboard(NewKeyboard())
                .setWeightKg(weightKg)
                .setPriceUsd(priceUsd)
                .setReleaseYear(releaseYear)
                .setUpdatedAt(timestampNow())
                .build();
    }

    private Timestamp timestampNow() {
        Instant now = Instant.now();
        return Timestamp.newBuilder()
                .setSeconds(now.getEpochSecond())
                .setNanos(now.getNano())
                .build();
    }

    private String randomLaptopName(String brand) {
        switch (brand) {
        case "Apple":
            return randomStringFromSet("Macbook Air", "Macbook Pro");
        case "Dell":
            return randomStringFromSet("Latitude", "Vostro");
        default:
            return randomStringFromSet("ThinkPad X1", "Thinkpad P1");
        }
    }

    private String randomLaptopBrand() {
        return randomStringFromSet("Apple", "Dell");
    }

    private Screen.Panel randomScreenPanel() {
        if (rand.nextBoolean()) {
            return Screen.Panel.IPS;
        }
        return Screen.Panel.OLED;
    }

    private float randomFloat(float min, float max) {
        return min + rand.nextFloat() * (max - min);
    }

    private String randomGPUName(String brand) {
        if (brand.equals("NVIDIA")) {
            return randomStringFromSet(
                    "RTX 2060",
                    "RTX 2070"
            );
        }
        return randomStringFromSet(
                "RX 590",
                "RX 580"
                );
    }

    private String randomGPUBrand() {
        return randomStringFromSet("INVIDIA", "AMD");
    }

    private double randomDouble(double min, double max) {
        return min + rand.nextDouble() * (max - min);
    }

    private int randomInt(int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }

    private String randomCPUName(String brand) {
        if (brand.equals("Intel")) {
            return randomStringFromSet(
                    "Core i3", "Core i5", "Core i7"
            );
        }
        return randomStringFromSet(
                "Ryzen 3",
                "Ryzen 5",
                "Ryzen 7"
        );
    }

    private String randomCPUBrand() {
        return randomStringFromSet("Intel", "AMD");
    }

    private String randomStringFromSet(String ... a) {
        int n = a.length;
        if (n == 0) {
            return "";
        }
        return a[rand.nextInt(n)];
    }

    private Keyboard.Layout randomKeyboardLayout() {
        switch (rand.nextInt(3)) {
        case 1:
            return Keyboard.Layout.QWERTY;
        case 2:
            return Keyboard.Layout.QWERTZ;
        default:
            return Keyboard.Layout.AZERTY;
        }
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        Laptop laptop = generator.NewLaptop();
        System.out.println(laptop);
    }
}
