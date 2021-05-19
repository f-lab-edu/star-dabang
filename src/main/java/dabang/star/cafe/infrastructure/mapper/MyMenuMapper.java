package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.MyMenuData;
import dabang.star.cafe.domain.mymenu.MyMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyMenuMapper {

    void insert(MyMenu myMenu);

    List<MyMenuData> getAllByMemberId(long memberId);

}
