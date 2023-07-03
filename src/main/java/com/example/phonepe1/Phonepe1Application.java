package com.example.phonepe1;

import com.example.phonepe1.model.Entity;
import com.example.phonepe1.service.PendencySystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Phonepe1Application {

    public static void main(String[] args) {
        PendencySystem pendencySystem = new PendencySystem();

        List<String> tags = new ArrayList<>();
        tags.add("Bangalore");

        Entity entity = new Entity(123, tags);

        pendencySystem.startTracking(entity);

        List<String> tags1 = new ArrayList<>();
        tags1.add("Bangalore");
        tags1.add("Karnataka");

        Entity entity1 = new Entity(1234, tags1);

        pendencySystem.startTracking(entity1);

        List<String> tags2 = new ArrayList<>();
        tags2.add("Bangalore");
        tags2.add("Tamilnadu");

        Entity entity2 = new Entity(12345, tags2);

        pendencySystem.startTracking(entity2);

        System.out.println(pendencySystem.getCounts(tags));
        System.out.println(pendencySystem.getCounts(tags1));
        System.out.println(pendencySystem.getCounts(tags2));

        pendencySystem.stopTracking(123);
        pendencySystem.stopTracking(1234);

        System.out.println(pendencySystem.getCounts(tags));
        System.out.println(pendencySystem.getCounts(tags1));
        System.out.println(pendencySystem.getCounts(tags2));
        System.out.println(pendencySystem.getCounts(new ArrayList<>()));

    }
}