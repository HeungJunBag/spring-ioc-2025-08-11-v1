
package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
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

        // 파사드는 서비스와 저장소 모두를 제어하거나 조합할 수 있어야 한다
        // 여기서 중요한 점은 '동일한' Repository 인스턴스를 전달하여 데이터 일관성을 유지
        TestFacadePostService testFacadePostService =
                new TestFacadePostService(testPostService, testPostRepository);

        // Map에 저장
        // 외부에서 이름만으로 꺼내 쓸 수 있도록 저장소(Map 등)에 담아둔다
        beans.put("testPostRepository", testPostRepository);
        beans.put("testPostService", testPostService);
        beans.put("testFacadePostService", testFacadePostService);
    }

    @SuppressWarnings("unchecked")
    public <T> T genBean(String beanName) {
        // Map에서 이름으로 객체를 찾아서 반환  
        // 한 번 저장된 객체는 계속 재사용됨  
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


/*
 * t4: testPostRepository 빈에 넣기
 * testPostRepository 객체를 만들고,
 * beans에 넣지 않으면 genBean("testPostRepository")가 null을 반환함
 * */

/* t5: testPostService 안에 testPostRepository가 있어야함
         - 이미 t5가 통과되게 구현되어이 있음

        // ApplicationContext 생성자에서
        TestPostRepository testPostRepository = new TestPostRepository();  // 객체 A
        TestPostService testPostService = new TestPostService(testPostRepository);  // A를 주입!

        beans.put("testPostRepository", testPostRepository);  // A를 Map에 저장
        beans.put("testPostService", testPostService);         // A를 품고 있는 testPostService 저장
* */