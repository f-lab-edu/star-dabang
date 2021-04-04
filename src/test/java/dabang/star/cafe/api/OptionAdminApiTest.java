package dabang.star.cafe.api;

import dabang.star.cafe.application.OptionAdminService;
import dabang.star.cafe.application.command.OptionCreateCommand;
import dabang.star.cafe.application.command.OptionUpdateCommand;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.mapper.ObjectMapperType.JACKSON_2;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ActiveProfiles("test")
@WebMvcTest(OptionAdminApi.class)
class OptionAdminApiTest {

    private final String OPTION_NAME = "새로운 옵션";
    private final int PRICE = 100;
    private final int MAX_QUANTITY = 10;
    private final int DEFAULT_PAGE = 1;
    private final int DEFAULT_SIZE = 20;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OptionAdminService optionAdminService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("새로운 옵션을 추가에 성공한다면 상태코드 201과 Option 정보를 반환한다")
    @Test
    void successfulCreateOptionTest() {

        OptionCreateCommand optionCreateCommand = new OptionCreateCommand(OPTION_NAME, PRICE, MAX_QUANTITY);
        Option newOption = new Option(1, OPTION_NAME, PRICE, MAX_QUANTITY);

        when(optionAdminService.createOption(any(OptionCreateCommand.class))).thenReturn(newOption);

        RestAssuredMockMvc
                .given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateCommand, JACKSON_2)
                .when()
                .post("/options")
                .then()
                .body("id", equalTo(1))
                .body("name", equalTo("새로운 옵션"))
                .body("price", equalTo(100))
                .body("maxQuantity", equalTo(10));

        verify(optionAdminService).createOption(any(OptionCreateCommand.class));
    }

    @DisplayName("새로운 옵션을 추가할 때 price가 음수라면 상태코드 422와 not valid price를 반환한다")
    @Test
    void noNegativePriceTest() {

        OptionCreateCommand optionCreateCommand = new OptionCreateCommand(OPTION_NAME, -100, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateCommand, JACKSON_2)
                .when()
                .post("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid price"))
                .body("status", equalTo(422));
    }

    @DisplayName("새로운 옵션을 추가할 때 price가 음수라면 상태코드 422와 blank price를 반환한다")
    @Test
    void notNullPriceCreateOptionTest() {

        OptionCreateCommand optionCreateCommand = new OptionCreateCommand(OPTION_NAME, null, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateCommand, JACKSON_2)
                .when()
                .post("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank price"))
                .body("status", equalTo(422));
    }

    @DisplayName("새로운 옵션을 추가할 때 maxQantity가 음수라면 상태코드 422와 not valid max quantity를 반환한다")
    @Test
    void noNegativeMaxQuantityTest() {

        OptionCreateCommand optionCreateCommand = new OptionCreateCommand(OPTION_NAME, PRICE, -10);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateCommand, JACKSON_2)
                .when()
                .post("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid max quantity"))
                .body("status", equalTo(422));
    }

    @DisplayName("새로운 옵션을 추가할 때 maxQuantity가 null이라면 상태코드 422와 blank max quantity를 반환한다")
    @Test
    void notNullMaxQuantityCreateOptionTest() {

        OptionCreateCommand optionCreateCommand = new OptionCreateCommand(OPTION_NAME, PRICE, null);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateCommand, JACKSON_2)
                .when()
                .post("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank max quantity"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션들을 조회할 때 옵션 정보가 없으면 상태코드 404와 No options were found를 반환한다")
    @Test
    void failedGetOptionTest() {

        when(optionAdminService.getAllOption(any(Pagination.class)))
                .thenThrow(new ResourceNotFoundException("No options were found"));

        RestAssuredMockMvc.when()
                .get("/options")
                .then()
                .statusCode(NOT_FOUND.value())
                .body("message", equalTo("No options were found"))
                .body("status", equalTo(404));
    }

    @DisplayName("옵션들을 성공적으로 조회하면 상태코드 200과 옵션 목록을 반환한다")
    @Test
    void successGetOptionTest() {

        List<Option> response = new ArrayList<>();
        response.add(new Option(1, OPTION_NAME, PRICE, MAX_QUANTITY));
        Page<Option> page = new Page<>(response, response.size(), DEFAULT_SIZE, DEFAULT_PAGE);

        when(optionAdminService.getAllOption(any(Pagination.class)))
                .thenReturn(page);

        RestAssuredMockMvc.when()
                .get("/options")
                .then()
                .statusCode(OK.value())
                .body("content[0].id", equalTo(1))
                .body("content[0].name", equalTo("새로운 옵션"))
                .body("content[0].price", equalTo(100))
                .body("content[0].maxQuantity", equalTo(10));
    }

    @DisplayName("옵션을 성공적으로 수정하면 상태코드 200을 반환한다")
    @Test
    void successUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(1, OPTION_NAME, PRICE, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(OK.value());

        verify(optionAdminService).updateOption(any(OptionUpdateCommand.class));
    }

    @DisplayName("옵션정보를 수정할 때 id가 없으면 blank option id를 반환한다")
    @Test
    void notNullIdUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(null, OPTION_NAME, PRICE, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank option id"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션정보를 수정할 때 name이 없으면 blank option name를 반환한다")
    @Test
    void notBlankNameUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(1, null, PRICE, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank option name"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션정보를 수정할 때 price가 없으면 blank option price를 반환한다")
    @Test
    void notNullPriceUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(1, OPTION_NAME, null, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank option price"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션정보를 수정할 때 price가 음수라면 not valid option price를 반환한다")
    @Test
    void notPositivePriceUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(1, OPTION_NAME, -100, MAX_QUANTITY);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid option price"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션정보를 수정할 때 maxQuantity가 음수라면 not valid option max quantity를 반환한다")
    @Test
    void notPositiveMaxQuantityUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(1, OPTION_NAME, PRICE, -10);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid option max quantity"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션정보를 수정할 때 maxQuantity가 없으면 blank option max quantity를 반환한다")
    @Test
    void notNullMaxQuantityUpdateOptionTest() {

        OptionUpdateCommand optionUpdateCommand = new OptionUpdateCommand(1, OPTION_NAME, PRICE, null);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionUpdateCommand, JACKSON_2)
                .when()
                .patch("/options")
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank option max quantity"))
                .body("status", equalTo(422));
    }

    @DisplayName("옵션을 삭제할 때 존재하지 않는 옵션이라면 option not found를 반환한다")
    @Test
    void noExistsDeleteOptionTest() {

        int noExistsOptionId = 1;

        doThrow(new ResourceNotFoundException("option not found"))
                .when(optionAdminService)
                .deleteOption(noExistsOptionId);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/options/1")
                .then()
                .statusCode(NOT_FOUND.value())
                .body("message", equalTo("option not found"))
                .body("status", equalTo(404));

        verify(optionAdminService).deleteOption(1);
    }

    @DisplayName("옵션을 삭제할 때 존재하는 옵션이라면 성공적으로 삭제를 한다")
    @Test
    void successDeleteOptionTest() {

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/options/1")
                .then()
                .statusCode(OK.value());

        verify(optionAdminService).deleteOption(1);
    }

}