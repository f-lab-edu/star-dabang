package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.push.Token;
import dabang.star.cafe.domain.push.TokenRepository;
import dabang.star.cafe.infrastructure.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MybatisTokenRepository implements TokenRepository {

    private final TokenMapper tokenMapper;

    @Override
    public void save(Token token) {
        if (token.getId() == null) {
            tokenMapper.insert(token);
        } else {
            tokenMapper.update(token);
        }
    }

    @Override
    public Optional<String> findTokenByMemberId(long memberId) {
        return tokenMapper.getTokenByMemberId(memberId);
    }

    @Override
    public Optional<Token> findByMemberId(long memberId) {
        return tokenMapper.getByMemberId(memberId);
    }

}
