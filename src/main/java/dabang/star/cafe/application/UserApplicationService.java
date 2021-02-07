package dabang.star.cafe.application;

import dabang.star.cafe.api.exception.InvalidRequestException;
import dabang.star.cafe.api.request.LoginParam;
import dabang.star.cafe.api.request.RegisterParam;
import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.domain.user.EncryptService;
import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserRepository;
import dabang.star.cafe.domain.user.UserService;
import dabang.star.cafe.infrastructure.mybatis.readservice.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserReadService userReadService;

    private final UserRepository userRepository;

    private final UserService userService;

    private final EncryptService encryptService;

    public Optional<UserData> findById(String id) {
        return Optional.ofNullable(userReadService.findById(id));
    }

    public String register(RegisterParam registerParam, BindingResult bindingResult) {
        User user = new User(
                registerParam.getEmail(),
                encryptService.encrypt(registerParam.getPassword()),
                registerParam.getNickname(),
                registerParam.getTel(),
                registerParam.getBirthday()
        );

        if (userService.exists(user)) {
            bindingResult.rejectValue("email", "DUPLICATED", "duplicated email");
            throw new InvalidRequestException(bindingResult);
        }

        userRepository.save(user);
        return user.getId();
    }

    public String existsByLoginParam(LoginParam loginParam, BindingResult bindingResult) {
        Optional<User> userOptional = userRepository.findByEmail(loginParam.getEmail());

        if (userOptional.isEmpty()
                || !(userOptional.get().getPassword().equals(encryptService.encrypt(loginParam.getPassword())))) {
            bindingResult.rejectValue("password", "INVALID", "invalid email or password");
            throw new InvalidRequestException(bindingResult);
        }

        return userOptional.get().getId();
    }
}
