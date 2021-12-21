package courierTests;

import courier.Courier;
import courier.CourierCredentials;
import courier.CourierMethods;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginCourierTest {

    private CourierMethods courierMethods;
    private Courier courier;
    private int courierId;

    @Before
    public void init() {
        courierMethods = new CourierMethods();
    }

    @Test
    public void testLoginCourier() {
        courier = Courier.getRandomCourier();
        assertTrue("Курьер не создан",courierMethods.isCreated(courier));

        courierId = courierMethods.loginAndGetId(CourierCredentials.getCourierCredentials(courier));
        assertThat(courierId,is(not(0)));
    }

    @Test
    public void testNotLoggedCourierWithoutLogin() {
        courier = Courier.getRandomCourier();
        assertTrue("Курьер не создан",courierMethods.isCreated(courier));

        courierMethods.login(CourierCredentials.getCourierCredentialsWithoutLogin(courier))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat().body("message",equalTo("Недостаточно данных для входа"));

        courierId = courierMethods.loginAndGetId(CourierCredentials.getCourierCredentials(courier));
    }

    @Test
    public void testNotLoggedCourierWithoutPassword() {
        courier = Courier.getRandomCourier();
        assertTrue("Курьер не создан",courierMethods.isCreated(courier));

        courierMethods.login(CourierCredentials.getCourierCredentialsWithoutPassword(courier))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat().body("message",equalTo("Недостаточно данных для входа"));

        courierId = courierMethods.loginAndGetId(CourierCredentials.getCourierCredentials(courier));
    }

    @Test
    public void testNotLoggedByNotExistedCourier() {
        courier = Courier.getRandomCourier();

        courierMethods.login(CourierCredentials.getCourierCredentials(courier))
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .assertThat().body("message",equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown(){
        if (courierId != 0) {
            courierMethods.delete(courierId);}
    }

}
