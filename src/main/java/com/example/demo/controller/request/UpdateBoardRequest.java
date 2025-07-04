package com.example.demo.controller.request;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardRequest {
    Long boardId;
    String title;
    String content;
}
