package br.com.brenonoccioli.desafioverticallogistica.services;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.exceptions.OrderNotFoundException;
import br.com.brenonoccioli.desafioverticallogistica.mappers.ProductMapper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import br.com.brenonoccioli.desafioverticallogistica.repositories.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.brenonoccioli.desafioverticallogistica.constants.ApplicationConstants.YYYY_MM_DD;
import static br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper.convertStringtoLocalDate;
import static br.com.brenonoccioli.desafioverticallogistica.mappers.OrderMapper.mapToOrderResponse;
import static br.com.brenonoccioli.desafioverticallogistica.mappers.UserMapper.mapToUserResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public List<UserResponse> getAll(String initDate, String finishDate){
        Iterable<UserEntity> consultedUsers = usersRepository.findAll();

        boolean hasFilterByRangeDate = initDate != null && finishDate != null;

        List<UserResponse> usersResponse = new ArrayList<>();
        for (UserEntity user : consultedUsers){
            List<OrderResponse> ordersResponse = new ArrayList<>();

            List<OrderEntity> consultedOrders;
            if (hasFilterByRangeDate){
                LocalDate init = convertStringtoLocalDate(initDate, YYYY_MM_DD);
                LocalDate finish = convertStringtoLocalDate(finishDate, YYYY_MM_DD);
                consultedOrders = ordersRepository.findAllByDateBetween(init, finish);
            } else {
                consultedOrders = ordersRepository.findAllByUserId(user.getId());
            }

            for (OrderEntity order : consultedOrders){
                List<ProductResponse> productsResponse = order.getProducts()
                        .stream().map(ProductMapper::mapToProductResponse)
                        .toList();
                ordersResponse.add(mapToOrderResponse(order, productsResponse));
            }
            usersResponse.add(mapToUserResponse(user, ordersResponse));
        }

        return usersResponse;
    }

    public UserResponse getByOrderId(Long orderId){
        Optional<OrderEntity> optionalOrder = ordersRepository.findById(orderId);

        if (optionalOrder.isEmpty()){
            LOGGER.error(String.format("Pedido com id '%s' não encontrado", orderId));
            throw OrderNotFoundException.create(orderId);
        }

        OrderEntity order = optionalOrder.get();
        List<ProductResponse> productsResponse = order.getProducts()
                .stream().map(ProductMapper::mapToProductResponse).toList();
        OrderResponse orderResponse = mapToOrderResponse(order, productsResponse);

        return mapToUserResponse(order.getUser(), List.of(orderResponse));
    }
}
