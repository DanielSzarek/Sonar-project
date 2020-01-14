package pl.szarek.projekt_sonar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class ProjektSonarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektSonarApplication.class, args);
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC+01:00"));
    }

}
