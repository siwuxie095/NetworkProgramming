package com.siwuxie095.network.chapter14th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-12-04 08:12:05
 */
public class Main {

    /**
     * Droplr 是怎样工作的
     *
     * Droplr 拥有一个非常简单的工作流：将一个文件拖动到应用程序的菜单栏图标，然后 Droplr 将会上传该文件。
     * 当上传完成之后，Droplr 将复制一个短 URL —— 也就是所谓的拖乐（drop）到剪贴板。
     *
     * 就是这样。欢畅地、实时地分享。
     *
     * 而在幕后，拖乐元数据将会被存储到数据库中（包括创建日期、名称以及下载次数等信息），而文件本身则被存储在
     * Amazon S3 上。
     */
    public static void main(String[] args) {

    }

}
