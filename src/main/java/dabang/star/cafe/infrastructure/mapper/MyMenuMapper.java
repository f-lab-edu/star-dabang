package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.MyMenuData;
import dabang.star.cafe.domain.mymenu.MyMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyMenuMapper {

    void insert(MyMenu myMenu);

    void update(MyMenu myMenu);

    List<MyMenuData> getAllByMemberId(long memberId);

    Optional<MyMenu> getById(long myMenuId);
}
