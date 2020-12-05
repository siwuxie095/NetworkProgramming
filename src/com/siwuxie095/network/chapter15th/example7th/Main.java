package com.siwuxie095.network.chapter15th.example7th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 20:38:25
 */
public class Main {

    /**
     * Swift：一种更快的构建 Java Thrift 服务的方式
     *
     * 新的 Java Thrift 框架的另一个关键部分叫作 Swift。它使用了 Nifty 作为它的 I/O 引擎，但是其服务规范
     * 可以直接通过 Java 注解来表示，使得 Thrift 服务开发人员可以纯粹地使用 Java 进行开发。当你的服务启动时，
     * Swift 运行时将通过组合使用反射以及解析 Swift 的注解来收集所有相关服务以及类型的信息。通过这些信息，它
     * 可以构建出和 Thrift 编译器在解析 Thrift IDL 文件时构建的模型一样的模型。然后，它将使用这个模型，并通
     * 过从字节码生成用于序列化/反序列化这些自定义类型的新类，来直接运行服务器以及客户端（而不需要任何生成的服
     * 务器或者客户端的存根代码）。
     *
     * 跳过常规的 Thrift 代码生成，还能使添加新功能变得更加轻松，而无需修改 IDL 编译器，所以许多新功能（如异
     * 步客户端）都是首先在 Swift 中得到支持。如果你感兴趣，可以查阅 Swift 的 GitHub 页面上的介绍信息。
     *
     * PS：https://github.com/facebook/swift
     */
    public static void main(String[] args) {

    }

}
