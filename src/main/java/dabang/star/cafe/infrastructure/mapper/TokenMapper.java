package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.push.Token;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface TokenMapper {

    void insert(Token token);

    void update(Token token);

    Optional<String> getTokenByMemberId(long memberId);

    Optional<Token> getByMemberId(long memberId);

}
