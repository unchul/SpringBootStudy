package com.example.demo.controller;

import com.example.demo.controller.request.FindSamplePostRequest;
import com.example.demo.controller.request.SamplePostRequest;
import com.example.demo.entity.SamplePost;
import com.example.demo.repository.SamplePostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    Long postId = request.getPostId()
    }
}
