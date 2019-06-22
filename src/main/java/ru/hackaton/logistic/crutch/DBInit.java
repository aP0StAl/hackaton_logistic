package ru.hackaton.logistic.crutch;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DBInit {
    private final OrderRepository orderRepository;

    @PostConstruct
    public void initAll(){
        initOrders();
    }

    private void initOrders(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("jsons/orders.json").getFile());
            Order[] orders = mapper.readValue(file, Order[].class);
            for (Order order : orders) {
                orderRepository.save(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
