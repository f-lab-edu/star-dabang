package dabang.star.cafe.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonRootName("errors")
@JsonSerialize(using = ErrorResourceSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
    private List<FieldErrorResource> fieldErrors;
}