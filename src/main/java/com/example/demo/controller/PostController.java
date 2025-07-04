package com.example.demo.controller;

import com.example.demo.controller.request.FindPostRequest;
import com.example.demo.controller.request.PostRequest;
import com.example.demo.controller.request.UpdatePostRequest;
import com.example.demo.entity.Board;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// RestController의 경우
// 'Controller' 계열이 붙어 있지 않으면
// 웹 브라우저 상에서 요청을 처리할 수 없습니다
// 'Controller'가 붙어 있는 클래스의 '모든 함수'의 리턴값은
// 전부 자동으로 'JSON' 처리가 되어 반환됩니다
// JSON이란 것은 보편적으로 key와 value로 구성됨
// '문자열의 경우 한정적으로 단일로 나갈수 있음'
// 사물함의 열쇠가 key 사물함에 들어 있는 내용을 value임

//@Component 라는 이야기가 나오는데
//@Component는 ' Spring에서 관리하는 Bean객체' 입니다
//'Bean객체'는 'Spring 서버가 구동하면서 만들어 놓는 싱글톤(Singleton)'입니다
//싱글톤은 유일한 인스턴스 라는 의미를 가짐

//인스턴스와 객체 의 차이점
//인스턴스의 경우 특정 class가 객체화
//객체는 컴퓨터가 사용하는 메모리에 올라갔다 라는 의미
//즉 싱글톤은 특정 class의 정보가 메모리에 올라가는데 유일하다는 소리
//결론적으로 Bean객체 는 아래와 같이 적을 수 있습니다
//Spring 서버가 구동하면서 만들어 놓는 특정 class의 정보(근데 유일함)
@Slf4j
@RestController
public class PostController {

    //@Autowired 는 JpaRepository는 무조건 Bean이 되는데
    //Bean이 해당하는 정보를 변수 이용인  postRepository로 사용하겠다는 뜻입니다
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/post")
    public Post returnPost() {
        Post CreatedPost = new Post("제목","내용");
        return CreatedPost;
    }

    //@PostMapping 은 POST방식이라고 해서 JSON형태로 데이터를 처리
    //그렇기 때문에 URL에 정보 노출이 안된다는 이점이 있음
    //웹 브라우저로는 post
    //@RequestBody를 통해 JSON 형태의 입력(파라미터)을 수신
    @PostMapping("/create")
    public Post createPost(@RequestBody PostRequest request) {
        /*
        응답이
        {
            "id":null,
            "title":null,
            "content":null
         }
         이렇게 내용을 넣어도 안들어감
         => request에 생성자만들기
         @NoArgsConstructor
         @AllArgsConstructor

         그래도 여전히 null

         @ToString을 request에 넣어줌으로 JSON형식을 이쁘게 출력해줄 준비해줌

         그래도 여전히 null

         @Getter @Setter 추가하자 마자 request 정보가 나옴
         =? Spring 내부적으로 JSON형태로 들어온 정보를 해석해서 먼저 생성자를 만듬
         그리고 title정보와 content정보가 있을 것인데
         직접 생성자를 만든 객체에 set을 사용해서 정보들을 배치해주기 때문에 null이 아니라 body에 담은 값이 전달이 되서 들어감

         */
//        log.info("post create -> request: {}",request);
//        Post requestedPost = request.toPost();
//        return requestedPost;

        Post requestedPost = request.toPost();
        return postRepository.save(requestedPost);
    }
    @PostMapping("/post/find")
    public Post findPost(@RequestBody FindPostRequest request) {
        Long postId = request.getPostId();
        Optional<Post> maybepost = postRepository.findById(postId);

        if(maybepost.isEmpty()){
            return null;
        }
        return maybepost.get();
    }
    @GetMapping("/post/read/{id}")
    public Post readPost(@PathVariable long id){
        //@PathVariable 이 위의 {id} 형태로 만들어진 가변 정보를 실제 특정 데이터 타입(long)으로 바꿉니다
            //현재 케이스에서는 id는 long이 되었습니다
        log.info("post read -> id:{}",id);
        Optional<Post> maybePost = postRepository.findById(id);

        if(maybePost.isEmpty()){
            return null;
        }
        return maybePost.get();
    }
    @GetMapping("/post/list")
    public List<Post> listPost(){
        log.info("post list");
        return postRepository.findAll();
    }

    @GetMapping("/post/delete")
    public void deletePost(@RequestParam Long postId){
        log.info("post delete -> postId:{}",postId);

        postRepository.deleteById(postId);
    }

    @PostMapping("/post/update")
    public Post updatePost(@RequestBody UpdatePostRequest request) {
        log.info("post update -> request:{}",request);

        Long postId = request.getPostId();
        Optional<Post> maybePost = postRepository.findById(postId);

        if(maybePost.isEmpty()){
            return null;
        }

        Post foundPost = maybePost.get();
        foundPost.setTitle(request.getTitle());
        foundPost.setContent(request.getContent());

        return postRepository.save(foundPost);
    }

}
