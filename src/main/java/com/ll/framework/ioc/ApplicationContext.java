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

    @SuppressWarnings("unchecked")
    public <T> T genBean(String beanName) {
        // Map에서 이름으로 객체를 찾아서 반환
        // 한 번 저장된 객체는 계속 재사용되므로, t3의 isSameAd(주소값 비교)를 통과!
        return (T) beans.get(beanName);
    }
}

/*
* @SuppressWarnings("unchecked")
*   - 경고를 억제
*   - "unchecked"
*       - 억제할 경고의 종류를 지정
*       - 여기서는, 타입 체크를 하지 않은 형변환에 대한 경고
* */
