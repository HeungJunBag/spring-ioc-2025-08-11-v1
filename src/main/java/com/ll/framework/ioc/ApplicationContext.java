package com.ll.framework.ioc;

public class ApplicationContext {
    public ApplicationContext() {
        // 아직 아무것도 하지 않음
        // 예외만 안나면 t1은 통과!
    }

    public <T> T genBean(String beanName) {
        return (T) null; // 일단 null 반환 (컴파일 에러 방지용)
    }
}
