package cn.codesheep.springbt_jwt_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.codesheep.springbt_jwt_mybatis.mapper"})
public class SpringbtJwtMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbtJwtMybatisApplication.class, args);
    }
}
