package dabang.star.cafe.domain.mymenu;

import dabang.star.cafe.application.data.MyMenuData;

import java.util.List;
import java.util.Optional;

public interface MyMenuRepository {

    void save(MyMenu myMenu);

    List<MyMenuData> findAllByMemberId(long memberId);

    Optional<MyMenu> findById(long myMenuId);

    int deleteByIdAndMemberId(long myMenuId, long memberId);

}
