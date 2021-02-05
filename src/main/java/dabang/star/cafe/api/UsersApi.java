package dabang.star.cafe.api;

import dabang.star.cafe.api.exception.InvalidRequestException;
import dabang.star.cafe.api.request.RegisterParam;
import dabang.star.cafe.application.UserApplicationService;
import dabang.star.cafe.application.data.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersApi {
    private final UserApplicationService userApplicationService;

    @PostMapping("/users")
    public ResponseEntity<UserData> createUser(@Valid @RequestBody RegisterParam registerParam,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }

        String id = userApplicationService.register(registerParam, bindingResult);
        return ResponseEntity.status(201).body(
                userApplicationService.findById(id).get()
        );
    }
}