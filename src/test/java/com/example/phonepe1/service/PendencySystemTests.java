package com.example.phonepe1.service;

import com.example.phonepe1.model.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PendencySystemTests {

    List<String> tags = new ArrayList<>();

    @Autowired
    PendencySystem pendencySystem;

    @BeforeEach
    void init() {
        tags.add("XYZ");
    }

    @Test
    void testStartTrackingWithOneCommonTag() {
        tags.add("ABC");
        Entity entity = new Entity(3, tags);
        pendencySystem.startTracking(entity);
        tags.remove("ABC");
        Assertions.assertEquals(1, pendencySystem.getCounts(tags));
    }

    @Test
    void testStartTracking() {
        Entity entity = new Entity(1, tags);
        pendencySystem.startTracking(entity);
        Assertions.assertEquals(1, pendencySystem.getCounts(tags));
    }

    @Test
    void testStartTrackingWithNewEntity() {
        tags.add("ABC");
        Entity entity = new Entity(2, tags);
        pendencySystem.startTracking(entity);
        Assertions.assertEquals(1, pendencySystem.getCounts(tags));
    }

    @Test
    void testStartTrackingWithZeroTags() {
        tags.remove("XYZ");
        Entity entity = new Entity(4, tags);
        pendencySystem.startTracking(entity);
        Assertions.assertEquals(0, pendencySystem.getCounts(tags));
    }

    @Test
    void testStopTrackingWithMultipleTags() {
        pendencySystem.stopTracking(2);
        tags.add("ABC");
        Assertions.assertEquals(0, pendencySystem.getCounts(tags));
    }

    @Test
    void testStopTracking() {
        pendencySystem.stopTracking(1);
        Assertions.assertEquals(1, pendencySystem.getCounts(tags));
    }


    @Test
    void testStopTrackingWithInvalidId() {
        pendencySystem.stopTracking(123);
        Assertions.assertEquals(0, pendencySystem.getCounts(new ArrayList<>()));
    }
}
