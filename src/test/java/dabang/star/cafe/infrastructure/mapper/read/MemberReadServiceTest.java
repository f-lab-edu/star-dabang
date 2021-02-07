package dabang.star.cafe.infrastructure.mapper.read;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@MybatisTest
class MemberReadServiceTest {

    @Autowired
    MemberReadService memberReadService;

    @DisplayName("이메일이 존재한다면 True 반환")
    @Test
    public void existsEmailTest() {
        boolean exist = memberReadService.exist("test1@naver.com");

        assertThat(exist).isTrue();
    }

    @DisplayName("이메일이 존재하지 않으면 False 반환")
    @Test
    public void notExistsEmailTest() {
        boolean noExist = memberReadService.exist("noEmail@naver.com");

        assertThat(noExist).isFalse();
    }
}