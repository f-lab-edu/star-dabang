package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.mymenu.MyMenu;
import dabang.star.cafe.domain.mymenu.MyMenuRepository;
import dabang.star.cafe.infrastructure.mapper.MyMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MybatisMyMenuRepository implements MyMenuRepository {

    private final MyMenuMapper myMenuMapper;

    @Override
    public void save(MyMenu myMenu) {

        if (myMenu.getId() == null) {
            myMenuMapper.insert(myMenu);
        }
    }

}
