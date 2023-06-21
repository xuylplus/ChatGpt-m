package xyl.me;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("xyl.me.mapper")
public class OpenServiceOcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenServiceOcrApplication.class,args);
    }

}
