package br.com.brenonoccioli.desafioverticallogistica.service;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.ProductEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;

import br.com.brenonoccioli.desafioverticallogistica.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper.convertStringtoLocalDate;
import static br.com.brenonoccioli.desafioverticallogistica.helpers.ProcessDataHelper.*;

@Service
@RequiredArgsConstructor
public class DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataService.class);

    private final UsersRepository usersRepository;

    public void proccessData(String dataArchive) {
        LOGGER.info(String.format("Iniciando processamento do arquivo: %s", dataArchive));

        Scanner scanner = new Scanner(dataArchive);

        ArrayList<String> errorList = new ArrayList<>();
        ArrayList<UserEntity> newUserEntities = new ArrayList<>();

        while (scanner.hasNext()){
            String line = scanner.nextLine();

            if (lineIsValid(line)){
                UserEntity newUser = buildUserFromLineFields(line);
                OrderEntity newOrder = buildOrderFromLineFields(line);
                ProductEntity newProduct = buildProductFromLineFields(line);

                if (newUser == null || newOrder == null || newProduct == null){
                    errorList.add(line);
                    continue;
                }

                UserEntity existingUser = getExistingUser(newUserEntities, newUser);

                if (existingUser != null){
                    OrderEntity existingOrder = getExistingOrder(existingUser.getOrders(), newOrder);
                    if (existingOrder != null){
                        existingOrder.getProducts().add(newProduct);
                    } else {
                        newOrder.getProducts().add(newProduct);
                        existingUser.getOrders().add(newOrder);
                    }
                } else {
                    newOrder.getProducts().add(newProduct);
                    newUser.getOrders().add(newOrder);
                    newUserEntities.add(newUser);
                }
            } else {
                errorList.add(line);
            }
        }

        persistOrUpdateNewData(newUserEntities);

        if (errorList.size() > 0){
            LOGGER.error(String.format("Linhas inválidas do arquivo: %s", errorList));
        } else {
            LOGGER.info("Arquivo processado inteiramente com sucesso!");
        }

    }

    private void persistOrUpdateNewData(ArrayList<UserEntity> newUserEntities) {
        for (UserEntity newUser : newUserEntities){
            HashSet<OrderEntity> newOrdersList = newUser.getOrders();
            Optional<UserEntity> optionalUser = usersRepository.findById(newUser.getId());

            if (optionalUser.isPresent()){
                UserEntity oldUser = optionalUser.get();
                HashSet<OrderEntity> oldOrdersList = oldUser.getOrders();

                oldOrdersList.forEach(oldOrder -> {
                    newOrdersList.forEach(newOrder -> {
                        if (oldOrder.getId().equals(newOrder.getId())){
                            oldOrder.getProducts().addAll(newOrder.getProducts());
                        } else {
                            oldOrdersList.add(newOrder);
                        }
                    });
                });
                usersRepository.save(oldUser);
            } else {
                usersRepository.save(newUser);
            }
        }
    }

    private ProductEntity buildProductFromLineFields(String line) {
        Long productId = getProductId(line);
        BigDecimal productValue = getProductValue(line);

        if (productId == null || productValue == null){
            return null;
        }

        return ProductEntity.builder()
                .id(productId)
                .value(productValue)
                .build();
    }

    private OrderEntity buildOrderFromLineFields(String line) {
        Long orderId = getOrderId(line);
        String orderStrDate = getOrderDate(line);

        if (orderId == null || orderStrDate == null){
            return null;
        }

        return OrderEntity.builder()
                .id(orderId)
                .date(convertStringtoLocalDate(orderStrDate, "yyyMMdd"))
                .totalPrice(BigDecimal.ZERO)
                .products(new ArrayList<>())
                .build();
    }

    private UserEntity buildUserFromLineFields(String line) {
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

    private OrderEntity getExistingOrder(HashSet<OrderEntity> orderList, OrderEntity newOrder){
        for (OrderEntity order : orderList){
            if (order.getId().equals(newOrder.getId())){
                return order;
            }
        }
        return null;
    }

    private UserEntity getExistingUser(List<UserEntity> userList, UserEntity newUser){
        for (UserEntity user : userList){
            if (user.getId().equals(newUser.getId())){
                return user;
            }
        }
        return null;
    }
}
