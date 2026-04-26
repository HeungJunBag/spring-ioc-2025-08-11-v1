package com.ll.framework.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
import com.ll.domain.testPost.testPost.service.TestPostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextTest {
    private static ApplicationContext applicationContext;

    @BeforeAll
    public static void beforeAll() {
        applicationContext = new ApplicationContext();
    }

    @Test
    @DisplayName("ApplicationContext 객체 생성")
    public void t1() {
        System.out.println(applicationContext);
    }

    @Test
    @DisplayName("testPostService 빈 얻기")
    public void t2() {
        TestPostService testPostService = applicationContext
                .genBean("testPostService"); // "testPostService"라는 이름으로 요청

        assertThat(testPostService).isNotNull(); // 실제값이 null이 아니어야 통과!
    }

    @Test
    @DisplayName("testPostService 빈을 다시 얻기, 싱글톤이어야 함")
    public void t3() {
        TestPostService testPostService1 = applicationContext
                .genBean("testPostService");

        TestPostService testPostService2 = applicationContext
                .genBean("testPostService");

        assertThat(testPostService1).isNotNull();
        assertThat(testPostService2).isNotNull();
        assertThat(testPostService1).isSameAs(testPostService2);
    }
    /*
    * isSamsAs
    *   - 같은 인스턴스 인지 확인, 단순히 값이 같은게 아니라,
    *   - 메모리 주소가 완전히 동일한 객체이어야함
    * */

    @Test
    @DisplayName("testPostRepository")
    public void t4() {
        TestPostRepository testPostRepository = applicationContext
                .genBean("testPostRepository");

        assertThat(testPostRepository).isNotNull();
    }
    /*
    * t4: "testPostRepository"라는 이름으로 빈을 요청했는데, 실제로는 ApplicationContext에서 "testPostRepository"라는 이름으로 빈이 등록되어 있지 않음
    *   - "testPostRepository" 이름으로 빈을 요청하는데, 아직 이 이름으로 등록하지 않음
    *   - testPostRepository 객체는 만들었지만, beans에 넣지 않아서 genBean("testPostRepository")가 null을 반환함
    * */

    @Test
    @DisplayName("testPostService has testPostRepository")
    public void t5() {
        TestPostService testPostService = applicationContext
                .genBean("testPostService");

        assertThat(testPostService).hasFieldOrPropertyWithValue(
                "testPostRepository",
                applicationContext.genBean("testPostRepository")
        );
    }
    /*
    * t5: testPostService 안에 testPostRepository가 있어야 함
    * 이 테스트는 두 가지를 동시에 확인함
    *   - 1. testPostService 객체 내부의 testPostRepostiory 필드가 존재하는가
    *   - 2. 그 필드의 값이 컨테이너에 등록된 testPostRepository 빈과 같은 인스턴스인가
    * 개념: hasFieldOrPropertyWithValue
    *   - AssertJ의 이 메서드는 리플렉션을 사용함
    *       - 리플렉션이란? Java의 기능으로, 컴파일 시점에 알 수 없는 클래스의 구조(필드, 메서드 등)를 런타임에 동적으로 탐색하고 접근할 수 있게 해줌
    *         private 접근 제어자도 우회 가능
    *   - private으로 선언된 필드도 접근해서 값을 비교할 수 있음
    * */

    @Test
    @DisplayName("testFacadePostService has testPostService, testPostRepository")
    public void t6() {
        TestFacadePostService testFacadePostService = applicationContext
                .genBean("testFacadePostService");

        // testPostService 필드가 컨테이너의 testPostService 빈과 같아야함
        assertThat(testFacadePostService).hasFieldOrPropertyWithValue(
                "testPostService",
                applicationContext.genBean("testPostService")
        );

        // testPostRepository 필드가 컨테이너의 testPostRepository 빈과 같아야 함
        assertThat(testFacadePostService).hasFieldOrPropertyWithValue(
                "testPostRepository",
                applicationContext.genBean("testPostRepository")
        );
    }
    /*
    * t6: testFacadePostService 안에 두 의존성이 있어야 함
    * TestFacadePostService가 갖는 두 의존성이 모두 컨테이너에 등록된 같은 싱글톤 빈이어야 한다.
    * */
}