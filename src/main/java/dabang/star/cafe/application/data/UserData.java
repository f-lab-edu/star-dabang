package dabang.star.cafe.application.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String id;
    private String email;
    private String nickname;
    private String tel;
    private LocalDate birthday;
}
