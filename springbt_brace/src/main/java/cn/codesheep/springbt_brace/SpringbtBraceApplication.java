package cn.codesheep.springbt_brace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan("cn.codesheep.springbt_brace")
@EnableCaching
public class SpringbtBraceApplication {



    public static void main(String[] args) {

//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        SpringApplication.run(SpringbtBraceApplication.class, args);
    }

}

