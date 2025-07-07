package com.example.demo.controller.request;

import com.example.demo.entity.Fruit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFruitRequest {
    String name;
    Long price;

    public Fruit toFruit() {
        return new Fruit(name, price);
    }
}
