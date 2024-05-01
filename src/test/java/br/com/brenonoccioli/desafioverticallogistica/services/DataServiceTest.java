package br.com.brenonoccioli.desafioverticallogistica.services;

import br.com.brenonoccioli.desafioverticallogistica.ProductTestData;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import br.com.brenonoccioli.desafioverticallogistica.repositories.OrdersRepository;
import br.com.brenonoccioli.desafioverticallogistica.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static br.com.brenonoccioli.desafioverticallogistica.OrderTestData.getOrderEntity;
import static br.com.brenonoccioli.desafioverticallogistica.UserTestData.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataServiceTest {
    @InjectMocks
    private DataService dataService;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private OrdersRepository ordersRepository;

    @ParameterizedTest
    @CsvSource({"0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308" +
                "\n0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308"})
    public void testProcessDataSuccessWithNoDataBaseReturn(String dataArchive) {
        when(usersRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());

        List<String> errorList = dataService.proccessData(dataArchive);

        assertTrue(errorList.isEmpty());
        verify(usersRepository, atLeastOnce()).saveAll(anyList());
        verify(ordersRepository, atLeastOnce()).saveAll(anySet());
    }

    @Test
    public void testProcessDataSuccess() {
        String dataArchive = "0000000070                              Palmer Prosacco00000007530000000003     1000.0020210308" +
                           "\n0000000070                              Palmer Prosacco00000007530000000003     1000.0020210308" +
                           "\n0000000070                              Palmer Prosacco00000007540000000003     1000.0020210308";
        Product product = ProductTestData.getProduct();
        ArrayList<Product> listOfProduct = new ArrayList<>();
        listOfProduct.add(product);
        OrderEntity orderEntity = getOrderEntity(listOfProduct);
        UserEntity userEntity = getUserEntity(Set.of());

        when(usersRepository.findById(anyLong()))
                .thenReturn(Optional.of(userEntity));
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(orderEntity));

        List<String> errorList = dataService.proccessData(dataArchive);

        assertTrue(errorList.isEmpty());

        verify(usersRepository, atLeastOnce()).saveAll(anyList());
        verify(ordersRepository, atLeastOnce()).saveAll(anySet());
    }

    @ParameterizedTest
    @CsvSource({
            "0000000000                              Palmer Prosacco00000007530000000003     1836.7420210308",
            "0000000070                                             00000007530000000003     1836.7420210308",
            "0000000070                              Palmer Prosacco00000000000000000003     1836.7420210308",
            "0000000070                              Palmer Prosacco00000007530000000003            20210308",
            "0000000000                              Palmer Prosacco00000007530000000000     1836.7420210308",
            "0000000070                              Palmer Prosacco00000007530000000003     1836.7400000000",
            "0000000000                                             00000000000000000000     000000000000000",
            "invalid line"})
    void testProccessDataWithInvalidLines(String dataArchive){

        List<String> errorList = dataService.proccessData(dataArchive);

        assertFalse(errorList.isEmpty());
        verify(usersRepository, atLeastOnce()).saveAll(anyList());
        verify(ordersRepository, atLeastOnce()).saveAll(anySet());
    }

}