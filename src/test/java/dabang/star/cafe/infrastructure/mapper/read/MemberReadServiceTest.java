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

    @DisplayName("이메일이 존재하는지 확인하고 Boolean값을 리턴한다")
    @Test
    public void existsEmailTest() {
        boolean exist = memberReadService.exist("test1@naver.com");
        boolean noExist = memberReadService.exist("noEmail@naver.com");

        assertThat(exist).isTrue();
        assertThat(noExist).isFalse();
    }
}