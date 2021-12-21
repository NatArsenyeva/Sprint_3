package order;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    public final String firstName;
    public final String lastName;
    public final String address;
    public final String metroStation;
    public final String phone;
    public final int rentTime;
    public final String deliveryDate;
    public final String comment;
    public final List<String> color;

    public Order(List<String> color) {
        this.firstName = "Иван";
        this.lastName = "Иванов";
        this.address = "г. Москва, Ул. Ангарская, д. 67";
        this.metroStation = "4";
        this.deliveryDate = LocalDateTime.now().toString();
        this.phone = "+7(999)9999999";
        this.rentTime = 3;
        this.comment = "Позвонить за 30 мин";
        this.color = color;
    }

}
