package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//@Entity -> Spring JPA가 자동으로 db 테이블을 생성해줍니다
@Entity
//Getter의 경우 get으로 시작하는 메서드를 전부 자동화해줌
//현재 케이스에서는 getTitle() 과 getContent()
@Getter
//Setter의 경우 set으로 시작하는 메서드 전부 자동화
//setTitle() setContent
@Setter
//ToString의 경우 작성한 클래스의 내용을 확인하기 위한 용도
//내부에 toString() 메서드를 작성했을 것임
//클래스 내부의 내용을 출력하기 위한 용도로 사용 하며 이를 자동화해줌
@ToString
// No Argument(인자 없음) Constuctor(생성자) 라고 보면 됨
// 경우에 따라 new Post() 를 할 수도 있기 때문에 구성하는 부분
@NoArgsConstructor
// 모든 인자 생성자
//new Post("제목, 내용")와 같은 형태로 Post 인스턴스를 생성하기 위해 사용하는 부분
@AllArgsConstructor

public class Post {
    //@Entity를 사용할시 Long id를 하나 만들어 줘야함
    // Long id 에 해당하는 필드가 unique 및 pk가 되도록 구성
    // => @Id , @GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String content;

    /*
    C:\proj\SpringBoot\demo\src\main\java\com\example\demo\controller\PostController.java:38: error: no suitable constructor found for Post(String,String)
        Post CreatedPost = new Post("제목","내용");
                           ^
    constructor Post.Post() is not applicable
      (actual and formal argument lists differ in length)
    constructor Post.Post(Long,String,String) is not applicable
      (actual and formal argument lists differ in length)

      위와 같은 에러가 발생할 수 있기 때문에 별도의 생성자를 작성해야 함
      Alt+Inser로 생성자를 자동화
     */

    public Post(Long id) {
        this.id = id;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
