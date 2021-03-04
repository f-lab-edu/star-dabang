package dabang.star.cafe.utils;

public interface SessionKey {

    /**
     * 로그인 회원의 ID를 가져오는 세션 키
     */
    String LOGIN_MEMBER_ID = "MEMBER";

    /**
     * 마이 페이지 접속 ID를 가져오는 세션 키
     */
    String CURRENT_MEMBER_ID = "CURRENT";

}
