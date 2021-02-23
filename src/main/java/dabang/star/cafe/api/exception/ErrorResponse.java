package dabang.star.cafe.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import dabang.star.cafe.api.exception.common.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonRootName("errors")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private List<CustomFieldError> infos;
    private String code;

    private ErrorResponse(ErrorCode code, List<CustomFieldError> infos) {
        this.message = code.getMessage();
        this.infos = infos;
        this.code = code.getCode();
    }

    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.infos = new ArrayList<>();
    }


    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, CustomFieldError.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, List<CustomFieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        String value = e.getValue() == null ? "" : e.getValue().toString();
        List<CustomFieldError> errors = CustomFieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.MISMATCH_INPUT_TYPE, errors);
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CustomFieldError {
        private String field;
        private String value;
        private String reason;

        public static List<CustomFieldError> of(String field, String value, String reason) {
            List<CustomFieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new CustomFieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<CustomFieldError> of(BindingResult bindingResult) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new CustomFieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}