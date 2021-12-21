package orderTests;

import order.OrderData;
import order.OrderMethods;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetListOrderTest {

    private OrderMethods orderMethods;

    @Before
    public void init() {
        orderMethods = new OrderMethods();
    }

    @Test
    public void testGetListOrder() {
        OrderData orderData = new OrderData(1);
        orderMethods.getListOrder(orderData)
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
