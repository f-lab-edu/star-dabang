package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.office.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OfficeSearchCommand {

    @NotNull(message = "blank current latitude.")
    private BigDecimal curLatitude;

    @NotNull(message = "blank current longitude")
    private BigDecimal curLongitude;

    public Location toLocation() {
        return new Location(curLongitude.doubleValue(), curLatitude.doubleValue());
    }
}
