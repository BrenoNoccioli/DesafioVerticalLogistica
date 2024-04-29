package br.com.brenonoccioli.desafioverticallogistica.service;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.mappers.OrderMapper;
import br.com.brenonoccioli.desafioverticallogistica.mappers.ProductMapper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import br.com.brenonoccioli.desafioverticallogistica.repository.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.brenonoccioli.desafioverticallogistica.mappers.OrderMapper.mapToOrderResponse;
import static br.com.brenonoccioli.desafioverticallogistica.mappers.UserMapper.mapToUserResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;


    public List<UserResponse> getAll(){
        Iterable<UserEntity> usersIterable = usersRepository.findAll();

        List<UserResponse> usersResponse = new ArrayList<>();
        for (UserEntity user : usersIterable){
            List<OrderResponse> ordersResponse = new ArrayList<>();

            List<OrderEntity> orders = ordersRepository.findAllByUserId(user.getId());

            for (OrderEntity order : orders){
                List<ProductResponse> productsResponse = order.getProducts()
                        .stream().map(ProductMapper::mapToProductResponse)
                        .toList();
                ordersResponse.add(mapToOrderResponse(order, productsResponse));
            }
            usersResponse.add(mapToUserResponse(user, ordersResponse));
        }

        return usersResponse;
    }

    public List<UserResponse> getByOrderId(Long orderId){
        ordersRepository.findById(orderId);
    return null;
    }
}
