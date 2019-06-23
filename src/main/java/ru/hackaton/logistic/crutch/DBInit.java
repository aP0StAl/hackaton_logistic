package ru.hackaton.logistic.crutch;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.domain.Usr;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.repository.UsrRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DBInit {
    private final OrderRepository orderRepository;
    private final RouteRepository routeRepository;
    private final UsrRepository usrRepository;

    @PostConstruct
    public void initAll(){
        initOrders();
        initRoutes();
        initUsers();
    }

    private void initUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("jsons/users.json").getFile());
            Usr[] users = mapper.readValue(file, Usr[].class);
            for (Usr user : users) {
                usrRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

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

    private void initRoutes(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("jsons/routes.json").getFile());
            Route[] routes = mapper.readValue(file, Route[].class);
            for (Route route : routes) {
                routeRepository.save(route);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
