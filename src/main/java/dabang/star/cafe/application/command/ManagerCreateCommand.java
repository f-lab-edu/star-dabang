package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ManagerCreateCommand {

    @NotBlank(message = "invalid name")
    private String name;

    @NotBlank(message = "invalid password")
    private String password;

    @NotBlank(message = "invalid office")
    private String officeName;

    public Manager toManager(int officeId, String encryptedPassword) {

        return Manager.builder()
                .officeId(officeId)
                .password(encryptedPassword)
                .role(Role.MANAGER)
                .name(name)
                .build();
    }

}
