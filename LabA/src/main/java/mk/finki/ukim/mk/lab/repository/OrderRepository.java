package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;
import org.thymeleaf.standard.expression.OrExpression;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    List<Order> orders = new ArrayList<>();

    public List<Order> findAllOrders(){
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);

    }

}
