package com.equipment.school_equipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@PropertySource("classpath:/aws.properties")
public class SchoolEquipmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolEquipmentApplication.class, args);
    }

}
