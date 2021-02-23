package dabang.star.cafe.api;

import dabang.star.cafe.api.request.LoginParam;
import dabang.star.cafe.api.request.RegisterParam;
import dabang.star.cafe.application.UserApplicationService;
import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.application.data.mapper.UserDataMapper;
import dabang.star.cafe.domain.service.AuthenticationService;
import dabang.star.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersApi {
    private final UserApplicationService userApplicationService;

    private final AuthenticationService authenticationService;

    @PostMapping("/users")
    public ResponseEntity<UserData> createUser(@Valid @RequestBody RegisterParam registerParam) {
        Long id = userApplicationService.register(registerParam);
        return ResponseEntity.status(201).body(
                userApplicationService.findById(id).get()
        );
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserData> loginUser(@Valid @RequestBody LoginParam loginParam) {
        User user = authenticationService.authenticateByLoginParam(loginParam.getEmail(), loginParam.getPassword());

        return ResponseEntity.ok(
                Mappers.getMapper(UserDataMapper.class).toDto(user)
        );
    }

    @GetMapping("/users/logout")
    public ResponseEntity logoutUser() {
        authenticationService.removeCurrentUser();
        return ResponseEntity.status(204).build();
    }
}