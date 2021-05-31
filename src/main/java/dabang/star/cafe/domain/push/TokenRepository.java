package dabang.star.cafe.domain.push;

import java.util.Optional;

public interface TokenRepository {

    void save(Token token);

    Optional<String> findTokenByMemberId(long memberId);

    Optional<Token> findByMemberId(long memberId);

}
