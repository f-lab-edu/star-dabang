package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.office.Office;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfficeData {

    private Integer id;

    private String name;

    private String address;

    public static OfficeData from(Office office) {

        return new OfficeData(office.getId(),
                office.getName(),
                office.getAddress()
        );
    }
}
