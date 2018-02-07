package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.demo")
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@EntityScan(basePackages = "com.example.demo.domains")
@EnableJpaRepositories(
        basePackages = "com.example.demo.repositories",
        repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class
)
public class LibraryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryDemoApplication.class, args);

        System.out.println("-----RUNNING-----");
    }
}
