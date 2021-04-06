package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.manager.Manager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ManagerUpdateCommand {

    @NotNull(message = "blank id")
    private Long id;

    @NotBlank(message = "blank password")
    private String password;

    @NotBlank(message = "blank office_name")
    private String officeName;

    public Manager toManager(int officeId, String encryptedPassword) {

        return Manager.builder()
                .id(id)
                .officeId(officeId)
                .password(encryptedPassword)
                .build();
    }

}
