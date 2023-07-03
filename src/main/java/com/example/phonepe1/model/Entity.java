package com.example.phonepe1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Entity {
    Integer id;
    List<String> tags;
}
