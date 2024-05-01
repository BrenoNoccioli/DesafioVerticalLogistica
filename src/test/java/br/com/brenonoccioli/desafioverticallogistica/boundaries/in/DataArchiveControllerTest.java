package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;


import br.com.brenonoccioli.desafioverticallogistica.exceptions.RequestNotValidException;
import br.com.brenonoccioli.desafioverticallogistica.services.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataArchiveControllerTest {
    @InjectMocks
    DataArchiveController controller;
    @Mock
    DataService service;

    @Test
    void testInputDataSuccess(){
        when(service.proccessData(anyString()))
                .thenReturn(List.of("test"));

        ResponseEntity<?> resp = assertDoesNotThrow(() -> controller.inputData("teste"));

        assertNotNull(resp);
        verify(service, times(1)).proccessData(anyString());
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertNotNull(resp.getBody());
    }

    @Test
    void testInputDataSuccessTotal(){
        when(service.proccessData(anyString()))
                .thenReturn(List.of());

        ResponseEntity<?> resp = assertDoesNotThrow(() -> controller.inputData("teste"));

        assertNotNull(resp);
        verify(service, times(1)).proccessData(anyString());
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertNotNull(resp.getBody());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testInputDataError(String nullOrEmpty){
        RequestNotValidException resp = assertThrows(RequestNotValidException.class,
                () -> controller.inputData(nullOrEmpty));

        verify(service, never()).proccessData(anyString());
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatus());
        assertNotNull(resp.getMessage());
    }

}