// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: processor_message.proto
// Protobuf Java Version: 4.27.1

package com.gitlab.techschool.pcbook.pb;

public final class ProcessorMessage {
  private ProcessorMessage() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 1,
      /* suffix= */ "",
      ProcessorMessage.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_techschool_pcbook_CPU_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_techschool_pcbook_CPU_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_techschool_pcbook_GPU_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_techschool_pcbook_GPU_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027processor_message.proto\022\021techschool.pc" +
      "book\032\024memory_message.proto\"r\n\003CPU\022\r\n\005bra" +
      "nd\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\024\n\014number_cores\030\003" +
      " \001(\r\022\026\n\016number_threads\030\004 \001(\r\022\017\n\007min_ghz\030" +
      "\005 \001(\001\022\017\n\007max_ghz\030\006 \001(\001\"o\n\003GPU\022\r\n\005brang\030\001" +
      " \001(\t\022\014\n\004name\030\002 \001(\t\022\017\n\007min_ghz\030\003 \001(\001\022\017\n\007m" +
      "ax_ghz\030\004 \001(\001\022)\n\006memory\030\005 \001(\0132\031.techschoo" +
      "l.pcbook.MemoryB*\n\037com.gitlab.techschool" +
      ".pcbook.pbP\001Z\005./;pbb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gitlab.techschool.pcbook.pb.MemoryMessage.getDescriptor(),
        });
    internal_static_techschool_pcbook_CPU_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_techschool_pcbook_CPU_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_techschool_pcbook_CPU_descriptor,
        new java.lang.String[] { "Brand", "Name", "NumberCores", "NumberThreads", "MinGhz", "MaxGhz", });
    internal_static_techschool_pcbook_GPU_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_techschool_pcbook_GPU_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_techschool_pcbook_GPU_descriptor,
        new java.lang.String[] { "Brang", "Name", "MinGhz", "MaxGhz", "Memory", });
    descriptor.resolveAllFeaturesImmutable();
    com.gitlab.techschool.pcbook.pb.MemoryMessage.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
