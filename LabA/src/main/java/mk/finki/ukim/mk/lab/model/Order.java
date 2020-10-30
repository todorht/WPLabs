package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.Random;

@Data
public class Order {

    private String balloonColor;
    private String balloonSize;
    private String clientName;
    private String clientAddress;
    private Long orderId;

    public Order(){}

    public Order(String balloonColor, String clientName, String address) {
        this.balloonColor = balloonColor;
        this.clientName = clientName;
        this.clientAddress = address;
        Random random = new Random();
        this.orderId = random.nextLong();
    }

    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        Random random = new Random();
        this.orderId = random.nextLong();;
    }

}
