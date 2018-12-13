package cn.codesheep.springbt_mybatis_generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.codesheep.springbt_mybatis_generator.mapper"})
public class SpringbtMybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbtMybatisGeneratorApplication.class, args);
    }
}
