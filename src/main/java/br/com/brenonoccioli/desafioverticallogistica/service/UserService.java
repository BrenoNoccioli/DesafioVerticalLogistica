package br.com.brenonoccioli.desafioverticallogistica.service;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.mappers.OrderMapper;
import br.com.brenonoccioli.desafioverticallogistica.mappers.ProductMapper;
import br.com.brenonoccioli.desafioverticallogistica.mappers.UserMapper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import br.com.brenonoccioli.desafioverticallogistica.repository.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static br.com.brenonoccioli.desafioverticallogistica.mappers.OrderMapper.mapToResponse;
import static br.com.brenonoccioli.desafioverticallogistica.mappers.ProductMapper.mapToResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;


    public List<UserResponse> getAllOrdersForUser(){
        Iterable<UserEntity> usersIterable = usersRepository.findAll();


        List<UserResponse> usersResponse = new ArrayList<>();
        for (UserEntity user : usersIterable){
            List<OrderResponse> ordersResponse = new ArrayList<>();
            List<ProductResponse> productsResponse = new ArrayList<>();

            List<OrderEntity> orders = ordersRepository.findAllByUserId(user.getId());
            for (OrderEntity order : orders){
                order.getProducts().forEach(product -> productsResponse.add(ProductMapper.mapToResponse(product)));
                ordersResponse.add(OrderMapper.mapToResponse(order, productsResponse));
            }
            usersResponse.add(UserMapper.mapToUserResponse(user, ordersResponse));
        }




        return usersResponse;
    }
}
