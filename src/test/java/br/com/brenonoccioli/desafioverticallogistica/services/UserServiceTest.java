package br.com.brenonoccioli.desafioverticallogistica.services;

import br.com.brenonoccioli.desafioverticallogistica.OrderTestData;
import br.com.brenonoccioli.desafioverticallogistica.UserTestData;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.exceptions.OrderNotFoundException;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import br.com.brenonoccioli.desafioverticallogistica.repositories.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static br.com.brenonoccioli.desafioverticallogistica.OrderTestData.getOrderEntity;
import static br.com.brenonoccioli.desafioverticallogistica.UserTestData.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService service;
    @Mock
    OrdersRepository ordersRepository;
    @Mock
    UsersRepository usersRepository;

    @Test
    void getAllWithDateRange() {
        String initDate = "2023-01-01";
        String finishDate = "2023-01-31";
        UserEntity user = getUserEntity(Set.of());
        OrderEntity orderEntity = getOrderEntity(List.of());

        when(usersRepository.findAll()).thenReturn(List.of(user));
        when(ordersRepository.findAllByDateBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(orderEntity));

        List<UserResponse> resp = service.getAll(initDate, finishDate);

        assertFalse(resp.isEmpty());
        verify(usersRepository, times(1)).findAll();
        verify(ordersRepository, times(1)).findAllByDateBetween(any(LocalDate.class), any(LocalDate.class));
        verify(ordersRepository, never()).findAllByUserId(anyLong());
        assertEquals(user.getId(), resp.get(0).getId());
        assertEquals(user.getName(), resp.get(0).getName());
        assertEquals(orderEntity.getId(), resp.get(0).getOrders().get(0).getId());
        assertEquals(orderEntity.getDate().toString(), resp.get(0).getOrders().get(0).getDate());
        assertEquals(orderEntity.getTotalPrice().toString(), resp.get(0).getOrders().get(0).getTotal());
    }

    @Test
    void getAllWithoutDateRangeAndNoReturnOrderForUsers() {
        UserEntity user = getUserEntity(Set.of());

        when(usersRepository.findAll()).thenReturn(List.of(user));
        when(ordersRepository.findAllByUserId(anyLong()))
                .thenReturn(List.of());

        List<UserResponse> resp = service.getAll("", null);

        assertFalse(resp.isEmpty());
        assertEquals(user.getId(), resp.get(0).getId());
        assertEquals(user.getName(), resp.get(0).getName());
        verify(usersRepository, times(1)).findAll();
        verify(ordersRepository, times(1)).findAllByUserId(anyLong());
        verify(ordersRepository, never()).findAllByDateBetween(any(), any());
    }

    @Test
    void getAllWithoutDateRangeAndNoReturnUser() {
        when(usersRepository.findAll()).thenReturn(List.of());

        List<UserResponse> resp = service.getAll(null, null);

        assertTrue(resp.isEmpty());
        verify(usersRepository, times(1)).findAll();
        verify(ordersRepository, never()).findAllByUserId(anyLong());
        verify(ordersRepository, never()).findAllByDateBetween(any(), any());
    }

    @Test
    void getByOrderId_OrderExists_ReturnsUserResponse() {
        OrderEntity order = getOrderEntity(List.of());
        order.setUser(getUserEntity(Set.of()));
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(order));

        UserResponse result = service.getByOrderId(order.getId());

        assertNotNull(result);
        verify(ordersRepository, times(1)).findById(anyLong());
    }

    @Test
    void getByOrderId_OrderDoesNotExist_ThrowsException() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());

        OrderNotFoundException resp = assertThrows(OrderNotFoundException.class,
                () -> service.getByOrderId(1L));

        verify(ordersRepository, times(1)).findById(anyLong());
        assertEquals(HttpStatus.NOT_FOUND, resp.getStatus());
        assertFalse(resp.getMessage().isEmpty());
    }

}