package com.ll.domain.testPost.testPost.service;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestFacadePostService {
    private final TestPostService testPostService;
    private final TestPostRepository testPostRepository;
}

/*
* Facade(파사드) 패턴이란?
*       - 이 클래스 이름에 포함된 Facade는 건물의 정면(외관)을 뜻합니다. 복잡한 내부 시스템(여러 Service, Repository 등)을 직접 호출하는 대신,
*       단순화된 하나의 창구를 통해 기능을 제공하는 패턴
*       - 왜 사용?
*           컨트롤러가 3~4개의 서비스를 직접 호출하면 코드가 복잡해짐
*           이를 FacadePostService 하나로 묶어 컨트롤러는 이 클래스만 호출하게 함으로써 결합도를 낮춤
*       - 예시: 게시글 작성 시 알림 서비스, 포인트 적립 서비스, 게시글 저장 서비스를 동시에 실행해야 할 때 이들을 묶어주는 역할
* */

/*
* @RequiredArgsConstructor와 생성자 주입
*       Lombok 라이브러리에서 제공하는 이 어노테이션은 final이 붙은 필드를 모아 생성자를 만들어줌
*       동작 원리:
*
*       // 롬복이 내부적으로 만들어주는 코드
*       public TestFacadePostService(TestPostService testPostService, TestPostRepository testPostRepository) {
*           this.testPostService = testPostService;
*           this.testPostRepository = testPostRepository;
*       }
* */
