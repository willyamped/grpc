// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: laptop_message.proto
// Protobuf Java Version: 4.27.1

package com.gitlab.techschool.pcbook.pb;

public interface LaptopOrBuilder extends
    // @@protoc_insertion_point(interface_extends:techschool.pcbook.Laptop)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string brand = 2;</code>
   * @return The brand.
   */
  java.lang.String getBrand();
  /**
   * <code>string brand = 2;</code>
   * @return The bytes for brand.
   */
  com.google.protobuf.ByteString
      getBrandBytes();

  /**
   * <code>string name = 3;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 3;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.techschool.pcbook.CPU cpu = 4;</code>
   * @return Whether the cpu field is set.
   */
  boolean hasCpu();
  /**
   * <code>.techschool.pcbook.CPU cpu = 4;</code>
   * @return The cpu.
   */
  com.gitlab.techschool.pcbook.pb.CPU getCpu();
  /**
   * <code>.techschool.pcbook.CPU cpu = 4;</code>
   */
  com.gitlab.techschool.pcbook.pb.CPUOrBuilder getCpuOrBuilder();

  /**
   * <code>.techschool.pcbook.Memory ram = 5;</code>
   * @return Whether the ram field is set.
   */
  boolean hasRam();
  /**
   * <code>.techschool.pcbook.Memory ram = 5;</code>
   * @return The ram.
   */
  com.gitlab.techschool.pcbook.pb.Memory getRam();
  /**
   * <code>.techschool.pcbook.Memory ram = 5;</code>
   */
  com.gitlab.techschool.pcbook.pb.MemoryOrBuilder getRamOrBuilder();

  /**
   * <pre>
   * list of GPUs
   * </pre>
   *
   * <code>repeated .techschool.pcbook.GPU gpus = 6;</code>
   */
  java.util.List<com.gitlab.techschool.pcbook.pb.GPU> 
      getGpusList();
  /**
   * <pre>
   * list of GPUs
   * </pre>
   *
   * <code>repeated .techschool.pcbook.GPU gpus = 6;</code>
   */
  com.gitlab.techschool.pcbook.pb.GPU getGpus(int index);
  /**
   * <pre>
   * list of GPUs
   * </pre>
   *
   * <code>repeated .techschool.pcbook.GPU gpus = 6;</code>
   */
  int getGpusCount();
  /**
   * <pre>
   * list of GPUs
   * </pre>
   *
   * <code>repeated .techschool.pcbook.GPU gpus = 6;</code>
   */
  java.util.List<? extends com.gitlab.techschool.pcbook.pb.GPUOrBuilder> 
      getGpusOrBuilderList();
  /**
   * <pre>
   * list of GPUs
   * </pre>
   *
   * <code>repeated .techschool.pcbook.GPU gpus = 6;</code>
   */
  com.gitlab.techschool.pcbook.pb.GPUOrBuilder getGpusOrBuilder(
      int index);

  /**
   * <code>repeated .techschool.pcbook.Storage storages = 7;</code>
   */
  java.util.List<com.gitlab.techschool.pcbook.pb.Storage> 
      getStoragesList();
  /**
   * <code>repeated .techschool.pcbook.Storage storages = 7;</code>
   */
  com.gitlab.techschool.pcbook.pb.Storage getStorages(int index);
  /**
   * <code>repeated .techschool.pcbook.Storage storages = 7;</code>
   */
  int getStoragesCount();
  /**
   * <code>repeated .techschool.pcbook.Storage storages = 7;</code>
   */
  java.util.List<? extends com.gitlab.techschool.pcbook.pb.StorageOrBuilder> 
      getStoragesOrBuilderList();
  /**
   * <code>repeated .techschool.pcbook.Storage storages = 7;</code>
   */
  com.gitlab.techschool.pcbook.pb.StorageOrBuilder getStoragesOrBuilder(
      int index);

  /**
   * <code>.techschool.pcbook.Screen screen = 8;</code>
   * @return Whether the screen field is set.
   */
  boolean hasScreen();
  /**
   * <code>.techschool.pcbook.Screen screen = 8;</code>
   * @return The screen.
   */
  com.gitlab.techschool.pcbook.pb.Screen getScreen();
  /**
   * <code>.techschool.pcbook.Screen screen = 8;</code>
   */
  com.gitlab.techschool.pcbook.pb.ScreenOrBuilder getScreenOrBuilder();

  /**
   * <code>.techschool.pcbook.Keyboard keyboard = 9;</code>
   * @return Whether the keyboard field is set.
   */
  boolean hasKeyboard();
  /**
   * <code>.techschool.pcbook.Keyboard keyboard = 9;</code>
   * @return The keyboard.
   */
  com.gitlab.techschool.pcbook.pb.Keyboard getKeyboard();
  /**
   * <code>.techschool.pcbook.Keyboard keyboard = 9;</code>
   */
  com.gitlab.techschool.pcbook.pb.KeyboardOrBuilder getKeyboardOrBuilder();

  /**
   * <code>double weight_kg = 10;</code>
   * @return Whether the weightKg field is set.
   */
  boolean hasWeightKg();
  /**
   * <code>double weight_kg = 10;</code>
   * @return The weightKg.
   */
  double getWeightKg();

  /**
   * <code>double weight_lb = 11;</code>
   * @return Whether the weightLb field is set.
   */
  boolean hasWeightLb();
  /**
   * <code>double weight_lb = 11;</code>
   * @return The weightLb.
   */
  double getWeightLb();

  /**
   * <code>double price_usd = 12;</code>
   * @return The priceUsd.
   */
  double getPriceUsd();

  /**
   * <code>uint32 release_year = 13;</code>
   * @return The releaseYear.
   */
  int getReleaseYear();

  /**
   * <code>.google.protobuf.Timestamp updated_at = 14;</code>
   * @return Whether the updatedAt field is set.
   */
  boolean hasUpdatedAt();
  /**
   * <code>.google.protobuf.Timestamp updated_at = 14;</code>
   * @return The updatedAt.
   */
  com.google.protobuf.Timestamp getUpdatedAt();
  /**
   * <code>.google.protobuf.Timestamp updated_at = 14;</code>
   */
  com.google.protobuf.TimestampOrBuilder getUpdatedAtOrBuilder();

  com.gitlab.techschool.pcbook.pb.Laptop.WeightCase getWeightCase();
}