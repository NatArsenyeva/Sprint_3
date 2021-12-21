package orderTests;

import order.Order;
import order.OrderMethods;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private List<String> color;
    private OrderMethods orderMethods;

    public CreateOrderTest(List<String> color){
        this.color=color;
    }

    @Parameterized.Parameters
    public static Object[][] getParams(){
        return new Object[][]{
                {List.of("GRAY","BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK")},
                {null},
        };
    }

    @Before
    public void init() {
        orderMethods = new OrderMethods();
    }

    @Test
    public void testCreateOrder(){
        Order order = new Order(color);
        orderMethods.createOrder(order)
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat().body("track",is(not(0)));
    }

}
