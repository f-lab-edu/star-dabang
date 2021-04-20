package dabang.star.cafe.application;

import dabang.star.cafe.application.command.OfficeSearchCommand;
import dabang.star.cafe.application.data.OfficeSearchData;
import dabang.star.cafe.domain.office.Location;
import dabang.star.cafe.domain.office.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceCalculator;
import org.locationtech.spatial4j.distance.GeodesicSphereDistCalc;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.locationtech.spatial4j.distance.DistanceUtils.KM_TO_DEG;

@Service
@RequiredArgsConstructor
public class OfficeQueryService {

    private final OfficeRepository officeRepository;

    private final double NEAR_RADIUS = 3;

    public List<OfficeSearchData> searchOfficeByDistanceFromCurrentLocation(OfficeSearchCommand officeSearchCommand) {
        double curX = officeSearchCommand.getCurLongitude().doubleValue();
        double curY = officeSearchCommand.getCurLatitude().doubleValue();
        Point curPoint = new PointImpl(curX, curY, SpatialContext.GEO);

        // 왼쪽 아래(서쪽 경도, 남쪽 위도)와 오른쪽 위(동쪽 경도, 북쪽 위도)를 활용.
        DistanceCalculator vincenty = new GeodesicSphereDistCalc.Vincenty();
        Point northPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 0.0, SpatialContext.GEO, null);
        Point eastPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 90.0, SpatialContext.GEO, null);
        Point southPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 180.0, SpatialContext.GEO, null);
        Point westPoint = vincenty.pointOnBearing(curPoint, KM_TO_DEG * NEAR_RADIUS, 270.0, SpatialContext.GEO, null);

        return officeRepository.findNearByLineString(
                new Location(curX, curY), westPoint.getX(), southPoint.getY(), eastPoint.getX(), northPoint.getY()
        );
    }
}
