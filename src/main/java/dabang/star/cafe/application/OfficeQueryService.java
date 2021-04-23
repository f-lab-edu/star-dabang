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

    public List<OfficeSearchData> searchOfficeByDistanceFromCurrentLocation(OfficeSearchCommand officeSearchCommand) {

        Location curLocation = officeSearchCommand.toLocation();
        return officeRepository.findNearByLineString(curLocation, curLocation.nearLineString());
    }
}
