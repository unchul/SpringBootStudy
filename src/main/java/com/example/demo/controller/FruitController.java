package com.example.demo.controller;

import com.example.demo.controller.request.CreateFruitRequest;
import com.example.demo.controller.request.FindFruitNameRequest;
import com.example.demo.entity.Fruit;
import com.example.demo.repository.FruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ConditionalOnIssuerLocationJwtDecoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private FruitRepository fruitRepository;

    @PostMapping("/create")
    public Fruit createFruit(@RequestBody CreateFruitRequest request) {
        log.info("createFruit() -> request: {}", request);

        Fruit requestedFruit = request.toFruit();
        return fruitRepository.save(requestedFruit);

    }
    @PostMapping("/find")
    public Fruit findFruitName(@RequestBody FindFruitNameRequest request){
        log.info("findFruitName() -> request: {}", request);

        String fruitName = request.getFruitName();
        // 주의 사항이라면 findById와는 다르게
        // Repository 인터페이스에 메서드 프로토타입
        Optional<Fruit> maybeFruit = fruitRepository.findByName(fruitName);

        if (maybeFruit.isEmpty()){
            log.info("그런 과일 안 팔아요");
            return null;
        }
        return maybeFruit.get();
    }
}
