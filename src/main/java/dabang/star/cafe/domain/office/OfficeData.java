package dabang.star.cafe.domain.office;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfficeData {

    private Long id;

    private String name;

    private String address;

    public static OfficeData from(Office office) {

        return new OfficeData(office.getId(),
                office.getName(),
                office.getAddress()
        );
    }
}
