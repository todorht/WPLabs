package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order placeOrder(String balloonColor, String clientName, String address) {
        Order newOrder = new Order(balloonColor,clientName, address);
        return newOrder;
    }
}
