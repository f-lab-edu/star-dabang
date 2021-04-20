package dabang.star.cafe.domain.office;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.StringTokenizer;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String longitude;

    private String latitude;

    public Location(BigDecimal lng, BigDecimal lat) {
        longitude = lng.toString();
        latitude = lat.toString();
    }

    public Location(double lng, double lat) {
        longitude = String.valueOf(lng);
        latitude = String.valueOf(lat);
    }

    public static Location parseFrom(String pointString) {
        StringTokenizer stringTokenizer = new StringTokenizer(pointString.toLowerCase()
                .replaceAll("point\\(", "")
                .replaceAll("\\)", "")
        );

        return new Location(stringTokenizer.nextToken(), stringTokenizer.nextToken());
    }

    @Override
    public String toString() {
        return "point(" + longitude + " " + latitude + ")";
    }
}
