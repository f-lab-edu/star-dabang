package dabang.star.cafe.domain.office;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceCalculator;
import org.locationtech.spatial4j.distance.GeodesicSphereDistCalc;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.impl.PointImpl;

import java.math.BigDecimal;
import java.util.StringTokenizer;

import static org.locationtech.spatial4j.distance.DistanceUtils.KM_TO_DEG;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private double longitude;

    private double latitude;

    private final double NEAR_RADIUS = 3;

    public Location(BigDecimal lng, BigDecimal lat) {
        longitude = lng.doubleValue();
        latitude = lat.doubleValue();
    }

    public Location(String lng, String lat) {
        longitude = Double.parseDouble(lng);
        latitude = Double.parseDouble(lat);
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

    public String nearLineString() {
        Point curPoint = new PointImpl(longitude, latitude, SpatialContext.GEO);

        // 왼쪽 아래(서쪽 경도, 남쪽 위도)와 오른쪽 위(동쪽 경도, 북쪽 위도)를 활용.
        DistanceCalculator vincenty = new GeodesicSphereDistCalc.Vincenty();
        Point northPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 0.0, SpatialContext.GEO, null);
        Point eastPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 90.0, SpatialContext.GEO, null);
        Point southPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 180.0, SpatialContext.GEO, null);
        Point westPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 270.0, SpatialContext.GEO, null);

        return "LINESTRING(" + westPoint.getX() + " " + southPoint.getY() + ", " + eastPoint.getX() + " " + northPoint.getY() + ")";
    }
}
