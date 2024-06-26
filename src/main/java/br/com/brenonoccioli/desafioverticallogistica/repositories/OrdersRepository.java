package br.com.brenonoccioli.desafioverticallogistica.repositories;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUserId(Long id);

    List<OrderEntity> findAllByDateBetween(LocalDate init, LocalDate finish);
}
