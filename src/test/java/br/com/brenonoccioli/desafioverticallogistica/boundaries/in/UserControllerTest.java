package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.exceptions.RequestParamsException;
import br.com.brenonoccioli.desafioverticallogistica.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        // Configuração inicial, se necessário.
    }

    @Test
    void getAllOrdersByUsers_ValidParams_ShouldReturnListOfUserResponses() {
        String initDate = "2023-01-01";
        String finishDate = "2023-01-31";
        List<UserResponse> mockResponses = Arrays.asList(
                UserResponse.builder()
                        .id(1L)
                        .name("User 1")
                        .build(),
                UserResponse.builder()
                        .id(2L)
                        .name("User 2")
                        .build()
        );
        when(userService.getAll(initDate, finishDate)).thenReturn(mockResponses);

        ResponseEntity<List<UserResponse>> response = userController.getAllOrdersByUsers(initDate, finishDate);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(userService, times(1)).getAll(initDate, finishDate);
    }

    @Test
    void getAllOrdersByUsers_InvalidParams_ShouldThrowRequestParamsException() {
        String initDate = "invalid-date";
        String finishDate = "2023-01-31";

        RequestParamsException exception = assertThrows(RequestParamsException.class, () -> {
            userController.getAllOrdersByUsers(initDate, finishDate);
        });

        assertNotNull(exception);
        assertEquals(400, exception.getStatus().value());
        verify(userService, never()).getAll(initDate, finishDate);
    }

    @Test
    void getUserByOrderId_ValidId_ShouldReturnUserResponse() {
        // Configuração
        Long orderId = 1L;
        UserResponse mockResponse = UserResponse.builder()
                .id(orderId)
                .name("User Name")
                .build();

        when(userService.getByOrderId(orderId)).thenReturn(mockResponse);

        // Ação
        ResponseEntity<UserResponse> response = userController.getUserByOrderId(orderId);

        // Verificação
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(orderId, response.getBody().getId());
        assertEquals("User Name", response.getBody().getName());

        verify(userService, times(1)).getByOrderId(orderId);
    }
}