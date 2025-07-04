package com.example.demo.controller;

import com.example.demo.controller.request.BoardRequest;
import com.example.demo.controller.request.FindBoardRequest;
import com.example.demo.controller.request.UpdateBoardRequest;
import com.example.demo.entity.Board;
import com.example.demo.entity.Post;
import com.example.demo.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/board/test")
    public Board justCreateBoard() {
        return new Board("title","content");
    }

    @PostMapping("/board/create")
    public Board createBoard(@RequestBody BoardRequest board) {
        Board requestedBoard = board.toBoard();
        return boardRepository.save(requestedBoard);
    }
    @PostMapping("/board/find")
    public Board findBoard(@RequestBody FindBoardRequest request) {
        Long boardId = request.getBoardId();
        //JpaReposiory에 해당하는 인터페이스에서
        //findById()를 하면 실제 DB테이블에서 id값에 해당하는 정보를 검색해서 가져옴
        //이때 데이터가 존재하지 않을 수도 있기 때문에 Optional<Entity>형태로 표현
        //그래서 변수 이름이 maybeBoard이기도 함
        Optional<Board> maybeBoard = boardRepository.findById(boardId);

        //만약 진짜 없어
        if(maybeBoard.isEmpty()){
            //그럼걍 아무것도 하지마
            return null;
        }
        //존재하면 해당 정보(Board)를 리턴해라
        return maybeBoard.get();
    }

    @GetMapping("/board/read/{id}")
    public Board readboard(@PathVariable long id){
        //@PathVariable 이 위의 {id} 형태로 만들어진 가변 정보를 실제 특정 데이터 타입(long)으로 바꿉니다
        //현재 케이스에서는 id는 long이 되었습니다
        log.info("board read -> id:{}",id);
        Optional<Board> maybeBoard = boardRepository.findById(id);

        if(maybeBoard.isEmpty()){
            return null;
        }
        return maybeBoard.get();
    }
    //전체 조회
    @GetMapping("board/list")
    public List<Board> listBoard(){
        log.info("board list");
        return boardRepository.findAll();
    }

    @GetMapping("/board/delete")
    //@RequestParam을 사용시 URL을 통해 boardId를 전달할 수 있다
    public void deleteBoard(@RequestParam Long boardId){
        log.info("board delete -> boardId:{}",boardId);

        boardRepository.deleteById(boardId);
    }
    @PostMapping("/board/update")
    public Board updateBoard(@RequestBody UpdateBoardRequest request) {
        log.info("board update -> board:{}",request);

        Long boardId = request.getBoardId();
        Optional<Board> maybeBoard = boardRepository.findById(boardId);

        if(maybeBoard.isEmpty()){
            return null;
        }

        Board foundBoard = maybeBoard.get();
        foundBoard.setTitle(request.getTitle());
        foundBoard.setContent(request.getContent());

        return boardRepository.save(foundBoard);
    }
}
