package com.siwuxie095.network.chapter15th.example11th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 22:32:07
 */
public class Main {

    /**
     * Twitter 成长的烦恼
     *
     * Twitter 最初是作为一个整体式的 Ruby On Rails 应用程序构建的，他们半亲切地称之为 Monorail。
     * 随着 Twitter 开始经历大规模的成长，Ruby 运行时以及 Rails 框架开始成为瓶颈。从计算机的角度来
     * 看，Ruby 对资源的利用是相对低效的。从开发的角度来看，该 Monorail 开始变得难以维护。对一个部
     * 分的代码修改将会不透明地影响到另外的部分。代码的不同方面的所属权也不清楚。无关核心业务对象的小
     * 改动也需要一次完整的部署。核心业务对象也没有暴露清晰的 API，其加剧了内部结构的脆弱性以及发生
     * 故障的可能性。
     *
     * 他们决定将该 Monorail 分拆为不同的服务，明确归属人并且精简 API，使迭代更快速，维护更容易。
     * 每个核心业务对象都将由一个专门的团队维护，并且由它自己的服务提供支撑。公司内部已经有了在 JVM
     * 上进行开发的先例：几个核心的服务已经从该 Monorail 中迁移出去，并已经用 Scala 重写了。他们的
     * 运维团队也有运维 JVM 服务的背景，并且知道如何运维它们。鉴于此，他们决定使用 Java 或者 Scala 
     * 在 JVM 上构建所有的新服务。大多数的服务开发团队都决定选用 Scala 作为他们的 JVM 语言。
     */
    public static void main(String[] args) {

    }

}
