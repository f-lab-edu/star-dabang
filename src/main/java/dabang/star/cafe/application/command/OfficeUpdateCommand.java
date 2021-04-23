package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.office.Office;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OfficeUpdateCommand {

    @NotNull(message = "blank office id")
    private Integer id;

    @NotBlank(message = "blank office name")
    @Size(max = 50, message = "invalid office name")
    private String name;

    @NotBlank(message = "blank office address")
    private String address;

    public Office toOffice() {

        return Office.builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }
}
