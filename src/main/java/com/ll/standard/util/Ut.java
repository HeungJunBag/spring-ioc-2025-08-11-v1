package com.ll.standard.util;

public class Ut {
    public static class str {
        public static String lcfirst(String str) {

            // 입력값이 없거나(null), 빈 문자열("")이면 문자열 그대로 반환하여 에러 방지
            if (str == null || str.isEmpty()) {
                return str;
            }

            // 첫 번쨰 글자 추출
            char firstChar = str.charAt(0);

            // 첫 글자가 이미 소문자이면 문자열 그대로 반환
            if (Character.isLowerCase(firstChar)) {
                return str;
            }

            // 첫 글자를 소문자로 바꾸고, 두 번쨰 글자부터 끝까지(substring(1)) 이어붙임
            return Character.toLowerCase(firstChar) + str.substring(1);
        }
    }
}
