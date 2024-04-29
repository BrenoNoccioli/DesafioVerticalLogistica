package br.com.brenonoccioli.desafioverticallogistica.service;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.mappers.OrderMapper;
import br.com.brenonoccioli.desafioverticallogistica.mappers.ProductMapper;
import br.com.brenonoccioli.desafioverticallogistica.mappers.UserMapper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import br.com.brenonoccioli.desafioverticallogistica.repository.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;


    public List<OrderResponse> getAllOrdersForUser(){
        Iterable<OrderEntity> ordersIterable = ordersRepository.findAll();

        List<OrderEntity> orderList = StreamSupport.stream(
                ordersIterable.spliterator(), false).toList();

        if (orderList.isEmpty()){
            return List.of();
        }




//        for (UserResponse user : userResponseList){
//            orderList.forEach(orderEntity -> {
//                if (orderEntity.getUser().getId().equals(user.getId())){
//                    user.getOrders().add(orderMapper.mapToResponse(orderEntity));
//                }
//            });
//        }

        return orderMapper.mapToListResponse(orderList);
    }
}
