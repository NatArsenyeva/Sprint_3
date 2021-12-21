package courierTests;

import courier.Courier;
import courier.CourierCredentials;
import courier.CourierMethods;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {

    private CourierMethods courierMethods;
    private int courierId;

    @Before
    public void init() {
        courierMethods = new CourierMethods();
    }

    @Test
    public void testRegisteredNewCourier() {
        Courier courier = Courier.getRandomCourier();
        courierMethods.create(courier)
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat().body("ok",is(true));
        courierId = courierMethods.loginAndGetId(CourierCredentials.getCourierCredentials(courier));
    }

    @Test
    public void testNotRegisteredTheSameCouriers() {
        Courier courier = Courier.getRandomCourier();
        assertTrue("Курьер не создан",courierMethods.isCreated(courier)) ;
        courierMethods.create(courier)
                .statusCode(HttpStatus.SC_CONFLICT)
                .and()
                .assertThat().body("message",equalTo("Этот логин уже используется"));
    }

    @Test
    public void testNotRegisteredCourierWithoutLogin() {
        Courier courier = Courier.getRandomCourierWithoutLogin();
        courierMethods.create(courier)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void testNotRegisteredCourierWithoutPassword() {
        Courier courier = Courier.getRandomCourierWithoutPassword();
        courierMethods.create(courier)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void testRegisteredCourierWithoutFirstName() {
        Courier courier = Courier.getRandomCourierWithoutFirstName();
        courierMethods.create(courier)
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat().body("ok",is(true));
        courierId = courierMethods.loginAndGetId(CourierCredentials.getCourierCredentials(courier));
    }

    @After
    public void tearDown(){
        if (courierId != 0) {
        courierMethods.delete(courierId);}
    }
}

