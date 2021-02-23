package dabang.star.cafe.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String tel;
    private LocalDate birthday;

    public User(String email, String password, String nickname, String tel, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.birthday = birthday;
    }
}
