package dabang.star.cafe.application;

import dabang.star.cafe.api.request.RegisterParam;
import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.domain.user.EncryptService;
import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserRepository;
import dabang.star.cafe.domain.user.UserService;
import dabang.star.cafe.domain.user.exception.EmailDuplicationException;
import dabang.star.cafe.infrastructure.mybatis.readservice.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserReadService userReadService;

    private final UserRepository userRepository;

    private final UserService userService;

    private final EncryptService encryptService;

    public Optional<UserData> findById(Long id) {
        return Optional.ofNullable(userReadService.findById(id));
    }

    public Long register(RegisterParam registerParam) {
        User user = new User(
                registerParam.getEmail(),
                encryptService.encrypt(registerParam.getPassword()),
                registerParam.getNickname(),
                registerParam.getTel(),
                registerParam.getBirthday()
        );

        if (userService.exists(user)) {
            throw new EmailDuplicationException(user.getEmail());
        }

        userRepository.save(user);
        return user.getId();
    }
}
