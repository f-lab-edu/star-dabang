package dabang.star.cafe.domain.office;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfficeData {

    private Long id;

    private String name;

    public OfficeData(Office office) {
        this.id = office.getId();
        this.name = office.getName();
    }
}
