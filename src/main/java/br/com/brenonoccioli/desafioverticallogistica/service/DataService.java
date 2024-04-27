package br.com.brenonoccioli.desafioverticallogistica.service;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.ProductEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    public void processData(String dataArchive) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(dataArchive));
        String line = bf.readLine();

        ArrayList<UserEntity> userList = new ArrayList<>();
        ArrayList<List<OrderEntity>> orderList = new ArrayList<>();

        while (line != null){
           UserEntity user = buildUserFromLineFields(line);
           OrderEntity order = buildOrderFromLineFields(line);
           ProductEntity product = buildProductFromLineFields(line);
        }
    }

    private ProductEntity buildProductFromLineFields(String line) {
        return null;
    }

    private OrderEntity buildOrderFromLineFields(String line) {
        return null;
    }

    private UserEntity buildUserFromLineFields(String line) {
        final Long user_id;
        final String name;



        return null;
    }

}
