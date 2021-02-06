package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@MybatisTest
@Import(MyBatisUserRepository.class)
class MyBatisUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("test@test.com", "test", "nick", "01011112222", LocalDate.now());
    }

    @Test
    @DisplayName("유저 저장에 성공해야 합니다.")
    void saveUserSuccess() {
        userRepository.save(user);
        Optional<User> byEmail = userRepository.findByEmail("test@test.com");
        assertThat(byEmail.get()).isEqualTo(user);
    }

    @Test
    @DisplayName("존재하는 이메일로 검색하면 true가 리턴됩니다.")
    void existsByEmailSuccess() {
        userRepository.save(user);
        assertThat(userRepository.existsByEmail("test@test.com")).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 이메일로 검색하면 false가 리턴됩니다.")
    void existsByEmailFail() {
        String email = "tmp@tmp.com";
        userRepository.save(user);
        assertThat(userRepository.existsByEmail(email)).isFalse();
    }
}