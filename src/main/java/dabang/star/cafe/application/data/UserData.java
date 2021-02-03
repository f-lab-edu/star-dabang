package dabang.star.cafe.application.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("user")
public class UserData {
    private String id;
    private String email;
    private String nickname;
    private String tel;
    private LocalDate birthday;
}
