package dabang.star.cafe.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import dabang.star.cafe.api.exception.InvalidRequestException;
import dabang.star.cafe.application.UserQueryService;
import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.domain.user.EncryptService;
import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UsersApi {

    private final UserRepository userRepository;
    private final UserQueryService userQueryService;
    private final EncryptService encryptService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterParam registerParam,
                                     BindingResult bindingResult) {
        checkInput(registerParam, bindingResult);
        User user = new User(
                registerParam.getEmail(),
                encryptService.encrypt(registerParam.getPassword()),
                registerParam.getNickname(),
                registerParam.getTel(),
                registerParam.getBirthday()
        );
        userRepository.save(user);
        UserData userData = userQueryService.findById(user.getId()).get();
        return ResponseEntity.status(201).body(userResponse(userData));
    }

    private Map<String, Object> userResponse(UserData userData) {
        return new HashMap<String, Object>() {{
            put("user", userData);
        }};
    }

    private void checkInput(@Valid @RequestBody RegisterParam registerParam,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }

        if (userRepository.findByEmail(registerParam.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "DUPLICATED", "duplicated email");
        }

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
    }
}

@Data
@JsonRootName("user")
@NoArgsConstructor
class RegisterParam {
    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "can't be empty")
    private String password;
    @NotBlank(message = "can't be empty")
    private String nickname;
    @NotBlank(message = "can't be empty")
    private String tel;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}

