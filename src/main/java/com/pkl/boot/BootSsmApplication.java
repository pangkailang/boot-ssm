package com.pkl.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootSsmApplication {

    public static void main(String[] args) {
//        DocsConfig config = new DocsConfig();
//        config.setProjectPath("F:\\project\\boot-ssm"); // 项目根目录
//        config.setProjectName("boot-ssm"); // 项目名称
//        config.setApiVersion("V1.0");       // 声明该API的版本
//        config.setDocsPath("D:\\apache-tomcat-8.5.15\\webapps"); // 生成API 文档所在目录
//        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
//        Docs.buildHtmlDocs(config); // 执行生成文档
        SpringApplication.run(BootSsmApplication.class, args);
    }

}
