package dabang.star.cafe.api;

import dabang.star.cafe.api.exception.NotFoundException;
import dabang.star.cafe.api.request.OptionCreateRequest;
import dabang.star.cafe.domain.admin.OptionAdminService;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionFactory;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
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

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OptionAdminService optionAdminService;

    static MockedStatic<OptionFactory> optionFactoryMockedStatic;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @BeforeAll
    static void before() {
        optionFactoryMockedStatic = mockStatic(OptionFactory.class);
    }

    @AfterAll
    static void after() {
        optionFactoryMockedStatic.close();
    }

    @DisplayName("새로운 옵션을 추가에 성공한다면 상태코드 201과 Option 정보를 반환한다")
    @Test
    void successfulCreateOptionTest() {

        OptionCreateRequest optionCreateRequest = new OptionCreateRequest("option", 500, 10);
        Option option = OptionFactory.from(optionCreateRequest);
        Option newOption = new Option(1, "option", 500, 10);

        when(OptionFactory.from(optionCreateRequest)).thenReturn(option);
        when(optionAdminService.createOption(option)).thenReturn(newOption);

        RestAssuredMockMvc
                .given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateRequest, JACKSON_2)
                .when()
                .post("/options")
                .then()
                .body("id", equalTo(1))
                .body("name", equalTo("option"))
                .body("price", equalTo(500))
                .body("maxQuantity", equalTo(10));

        verify(optionAdminService).createOption(option);
    }

    @DisplayName("새로운 옵션을 추가할 때 price가 음수라면 상태코드 422와 not valid price를 반환한다")
    @Test
    void noNegativePriceTest() {

        OptionCreateRequest optionCreateRequest = new OptionCreateRequest("option", -100, 10);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateRequest, JACKSON_2)
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

        OptionCreateRequest optionCreateRequest = new OptionCreateRequest("option", null, 10);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateRequest, JACKSON_2)
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

        OptionCreateRequest optionCreateRequest = new OptionCreateRequest("option", 500, -10);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateRequest, JACKSON_2)
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

        OptionCreateRequest optionCreateRequest = new OptionCreateRequest("option", 500, null);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(optionCreateRequest, JACKSON_2)
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

        when(optionAdminService.getAllOption())
                .thenThrow(new NotFoundException("No options were found"));

        RestAssuredMockMvc.when()
                .get("/options")
                .then()
                .statusCode(NOT_FOUND.value())
                .body("message", equalTo("No options were found"))
                .body("status", equalTo(404));
    }

    @DisplayName("옵션들을 성공적으로 조회하면 상태코드 200과 List<Option>를 반환한다")
    @Test
    void successGetOptionTest() {

        List<Option> response = new ArrayList<>();
        response.add(new Option(1, "option1", 100, 10));

        when(optionAdminService.getAllOption())
                .thenReturn(response);

        RestAssuredMockMvc.when()
                .get("/options")
                .then()
                .statusCode(OK.value())
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("option1"))
                .body("[0].price", equalTo(100))
                .body("[0].maxQuantity", equalTo(10));
    }

}