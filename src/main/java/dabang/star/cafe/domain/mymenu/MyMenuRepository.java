package dabang.star.cafe.domain.mymenu;

import dabang.star.cafe.application.data.MyMenuData;

import java.util.List;

public interface MyMenuRepository {

    void save(MyMenu myMenu);

    List<MyMenuData> findAllByMemberId(long memberId);

}
