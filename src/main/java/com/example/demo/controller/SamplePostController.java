package com.example.demo.controller;

import com.example.demo.controller.request.FindSamplePostRequest;
import com.example.demo.controller.request.SamplePostRequest;
import com.example.demo.entity.SamplePost;
import com.example.demo.repository.SamplePostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class SamplePostController {
    @Autowired
    private SamplePostRepository samplePostRepository;

    @GetMapping("/sample_post/test")
    public String test() {return "test";}

    @GetMapping("/sample_post/post")
    public SamplePost returnSamplePost() {
        SamplePost CreatedSamplePost = new SamplePost("제목","글쓴이","내용");
        return CreatedSamplePost;
    }

    @PostMapping("/sample_post/create")
    public SamplePost createSamplePost(@RequestBody SamplePostRequest request) {
        SamplePost requestSamplePost = request.toSamplePost();
        return samplePostRepository.save(requestSamplePost);
    }

    @PostMapping("/sample_post/find")
    public SamplePost findSamplePost(@RequestBody FindSamplePostRequest request) {
        Long postId = request.getPostId();
        Optional<SamplePost> maybeSamplePost = samplePostRepository.findById(postId);

        if(maybeSamplePost.isEmpty()){
            return null;
        }
        return maybeSamplePost.get();
    }
    @GetMapping("/sample_post/read/{id}")
    public SamplePost readSamplePost(@PathVariable Long id) {
        log.info("sample post read id is {}", id);
        Optional<SamplePost> maybeSamplePost = samplePostRepository.findById(id);

        if(maybeSamplePost.isEmpty()){
            return null;
        }
        return maybeSamplePost.get();
    }
    @GetMapping("/sample_post/list")
    public List<SamplePost> listSamplePost() {
        log.info("sample post list");
        return samplePostRepository.findAll();
    }
}
