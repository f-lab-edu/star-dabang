package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 유저를_저장하는데_성공하는지_테스트() {
        userRepository.save(user);
        Optional<User> byEmail = userRepository.findByEmail("test@test.com");
        assertThat(byEmail.get()).isEqualTo(user);
    }
}