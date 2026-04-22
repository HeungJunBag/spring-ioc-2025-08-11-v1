package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestPostService;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    // 빈 저장소: 이름 -> 객체
    private final Map<String, Object> beans = new HashMap<>();

    public ApplicationContext() {

        // 의존성이 없는 것부터 먼저 만든다
        TestPostRepository testPostRepository = new TestPostRepository();

        // TestPostService 객체를 만들어서 "testPostService"라는 이름으로 등록
        // 단, testPostRepository를 주입해서 testPostService를 만든다
        TestPostService testPostService = new TestPostService(testPostRepository);

        // Map에 저장
        beans.put("testPostService", testPostService);
    }

    public <T> T genBean(String beanName) {
        return (T) beans.get(beanName); // 일단 null 반환 (컴파일 에러 방지용)
    }
}
