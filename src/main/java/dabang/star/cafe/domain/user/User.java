package dabang.star.cafe.domain.user;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String tel;
    private LocalDate birthday;

    public User(String email, String password, String nickname, String tel, LocalDate birthday) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.birthday = birthday;
    }
}
