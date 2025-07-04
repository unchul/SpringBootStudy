package com.example.demo.controller.request;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {
    Long postId;
    String title;
    String content;
}
