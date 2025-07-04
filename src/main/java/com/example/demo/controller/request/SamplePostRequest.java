package com.example.demo.controller.request;

import com.example.demo.entity.SamplePost;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SamplePostRequest {
    String title;
    String writer;
    String content;

    public SamplePost toSamplePost(){
        return new SamplePost(title, writer, content);
    }
}

public class FindSamplePostRequest {
    Long postId;
}
public class UpdateSamplePostRequest {
    Long postId;
    String title;
    String writer;
    String content;

}
