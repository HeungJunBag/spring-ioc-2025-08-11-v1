package com.ll.domain.testPost.testPost.service;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestPostService {
    private final TestPostRepository testPostRepository;
}

/*
* @RequiredArgsConstructor는 Lombok 어노테이션이므로,
* final 필드를 파라미터로 받는 생성자를 컴파일 시 자동으로 생성함
* 실제로는 아래와 같은 생성자가 존재함
*
*       public TestPostService(TestPostRepository testPostRepository) {
*           this.testPostRepository = testPostRepository;
*       }
* */
