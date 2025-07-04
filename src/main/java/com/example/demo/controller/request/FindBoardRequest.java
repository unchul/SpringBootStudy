package com.example.demo.controller.request;

import com.example.demo.entity.Board;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindBoardRequest {
    Long boardId;
}
