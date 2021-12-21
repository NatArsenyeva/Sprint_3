package courier;
import base.BaseSpec;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class CourierMethods extends BaseSpec {

    private final static String COURIER_PATH = "api/v1/courier/";

    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    public boolean isCreated(Courier courier){
        return create(courier)
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .path("ok")
                .equals(true);
    }

    public ValidatableResponse login (CourierCredentials courierCredentials){
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "login")
                .then();
    }

    public int loginAndGetId(CourierCredentials courierCredentials){
        return login(courierCredentials)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("id");
    }

    public boolean delete(int courierId) {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .delete(COURIER_PATH + courierId)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("ok");
        }



}
