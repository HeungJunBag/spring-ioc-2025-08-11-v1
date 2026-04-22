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

    @Test
    @DisplayName("testFacadePostService has testPostService, testPostRepository")
    public void t6() {
        TestFacadePostService testFacadePostService = applicationContext
                .genBean("testFacadePostService");

        assertThat(testFacadePostService).hasFieldOrPropertyWithValue(
                "testPostService",
                applicationContext.genBean("testPostService")
        );

        assertThat(testFacadePostService).hasFieldOrPropertyWithValue(
                "testPostRepository",
                applicationContext.genBean("testPostRepository")
        );
    }
}