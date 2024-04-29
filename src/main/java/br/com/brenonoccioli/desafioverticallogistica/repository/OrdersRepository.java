package br.com.brenonoccioli.desafioverticallogistica.repository;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUserId(Long id);
}
