package dabang.star.cafe.application.data.mapper;

import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.domain.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDataMapper extends GenericMapper<UserData, User>{
}
