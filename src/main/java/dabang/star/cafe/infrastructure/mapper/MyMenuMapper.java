package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.mymenu.MyMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyMenuMapper {

    void insert(MyMenu myMenu);

}
