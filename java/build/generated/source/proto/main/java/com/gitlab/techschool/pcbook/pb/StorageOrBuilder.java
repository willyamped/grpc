// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: storage_message.proto
// Protobuf Java Version: 4.27.1

package com.gitlab.techschool.pcbook.pb;

public interface StorageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:techschool.pcbook.Storage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.techschool.pcbook.Storage.Driver driver = 1;</code>
   * @return The enum numeric value on the wire for driver.
   */
  int getDriverValue();
  /**
   * <code>.techschool.pcbook.Storage.Driver driver = 1;</code>
   * @return The driver.
   */
  com.gitlab.techschool.pcbook.pb.Storage.Driver getDriver();

  /**
   * <code>.techschool.pcbook.Memory memory = 2;</code>
   * @return Whether the memory field is set.
   */
  boolean hasMemory();
  /**
   * <code>.techschool.pcbook.Memory memory = 2;</code>
   * @return The memory.
   */
  com.gitlab.techschool.pcbook.pb.Memory getMemory();
  /**
   * <code>.techschool.pcbook.Memory memory = 2;</code>
   */
  com.gitlab.techschool.pcbook.pb.MemoryOrBuilder getMemoryOrBuilder();
}
