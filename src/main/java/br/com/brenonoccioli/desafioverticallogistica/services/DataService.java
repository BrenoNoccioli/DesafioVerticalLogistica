package br.com.brenonoccioli.desafioverticallogistica.services;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;

import br.com.brenonoccioli.desafioverticallogistica.repositories.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static br.com.brenonoccioli.desafioverticallogistica.constants.ApplicationConstants.YYYYMMDD;
import static br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper.convertStringtoLocalDate;
import static br.com.brenonoccioli.desafioverticallogistica.helpers.OrderHelper.calculateTotalPrice;
import static br.com.brenonoccioli.desafioverticallogistica.helpers.ProcessDataHelper.*;

@Service
@RequiredArgsConstructor
public class DataService {
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataService.class);

    public List<String> proccessData(String dataArchive) {
        Scanner scanner = new Scanner(dataArchive);

        ArrayList<String> errorList = new ArrayList<>();
        ArrayList<UserEntity> usersForPersistence = new ArrayList<>();
        ArrayList<OrderEntity> ordersForPersistence = new ArrayList<>();

        while (scanner.hasNext()){
            String line = scanner.nextLine();

            if (lineIsValid(line)){
                UserEntity newUser = buildUserFromLine(line);
                OrderEntity newOrder = buildOrderFromLine(line);
                Product newProduct = buildProductFromLine(line);

                if (newUser == null || newOrder == null || newProduct == null){
                    errorList.add(line);
                    continue;
                }

                OrderEntity existingOrder = getExistingOrder(ordersForPersistence, newOrder);

                if (existingOrder != null){
                    existingOrder.getProducts().add(newProduct);
                } else {
                    newOrder.getProducts().add(newProduct);
                    ordersForPersistence.add(newOrder);
                }

                if (!usersForPersistence.contains(newUser)){
                    usersForPersistence.add(newUser);
                }

            } else {
                errorList.add(line);
            }
        }

        persistAndUpdate(usersForPersistence, ordersForPersistence);

        return errorList;
    }

    @Transactional
    private void persistAndUpdate(List<UserEntity> newUserList, List<OrderEntity> newOrderList) {
        try {
            ArrayList<UserEntity> userPersistList = new ArrayList<>();
            for (UserEntity newUser : newUserList){
                Optional<UserEntity> optionalUser = usersRepository.findById(newUser.getId());

                if (optionalUser.isEmpty()){
                    userPersistList.add(newUser);
                }
            }
            usersRepository.saveAll(userPersistList);

            HashSet<OrderEntity> orderPersistList = new HashSet<>();
            for (OrderEntity newOrder : newOrderList){
                Optional<OrderEntity> optionalOrder = ordersRepository.findById(newOrder.getId());

                if (optionalOrder.isPresent()){
                    OrderEntity oldOrder = optionalOrder.get();
                    oldOrder.getProducts().addAll(newOrder.getProducts());
                    orderPersistList.add(oldOrder);
                } else {
                    orderPersistList.add(newOrder);
                }
            }
            orderPersistList.forEach(order -> order.setTotalPrice(calculateTotalPrice(order.getProducts())));
            ordersRepository.saveAll(orderPersistList);

        } catch (Exception ex) {
            LOGGER.error("Algo de inesperado ocorreu durante a persistência dos dados");
            throw new RuntimeException();
        }
    }

    private Product buildProductFromLine(String line) {
        Long productId = getProductId(line);
        BigDecimal productValue = getProductValue(line);

        if (productId == null || productValue == null){
            return null;
        }

        return Product.builder()
                .id(productId)
                .value(productValue)
                .build();
    }

    private OrderEntity buildOrderFromLine(String line) {
        Long orderId = getOrderId(line);
        String orderStrDate = getOrderDate(line);
        UserEntity user = buildUserFromLine(line);

        if (orderId == null || orderStrDate == null){
            return null;
        }

        return OrderEntity.builder()
                .id(orderId)
                .date(convertStringtoLocalDate(orderStrDate, YYYYMMDD))
                .totalPrice(BigDecimal.ZERO)
                .products(new ArrayList<>())
                .user(user)
                .build();
    }

    private UserEntity buildUserFromLine(String line) {
        Long userId = getUserId(line);
        String userName = getUserName(line);

        if (userId == null || userName == null){
            return null;
        }

        return UserEntity.builder()
                .id(userId)
                .name(userName)
                .orders(new HashSet<>())
                .build();
    }

    private OrderEntity getExistingOrder(List<OrderEntity> orderList, OrderEntity newOrder){
        for (OrderEntity order : orderList){
            if (order.getId().equals(newOrder.getId())){
                return order;
            }
        }
        return null;
    }
}
