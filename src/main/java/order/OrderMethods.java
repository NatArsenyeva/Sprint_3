package order;
import base.BaseSpec;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderMethods extends BaseSpec {

    private final static String ORDER_PATH = "/api/v1/orders/";

    public ValidatableResponse createOrder(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    public ValidatableResponse getListOrder(OrderData orderData){
        return given()
                .spec(getBaseSpec())
                .body(orderData)
                .when()
                .get(ORDER_PATH)
                .then();
    }

}
