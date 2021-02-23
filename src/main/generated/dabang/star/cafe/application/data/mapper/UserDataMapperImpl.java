package dabang.star.cafe.application.data.mapper;

import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.domain.user.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-23T14:55:32+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.8 (Amazon.com Inc.)"
)
public class UserDataMapperImpl implements UserDataMapper {

    @Override
    public UserData toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserData userData = new UserData();

        userData.setId( entity.getId() );
        userData.setEmail( entity.getEmail() );
        userData.setNickname( entity.getNickname() );
        userData.setTel( entity.getTel() );
        userData.setBirthday( entity.getBirthday() );

        return userData;
    }

    @Override
    public User toEntity(UserData dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }
}
