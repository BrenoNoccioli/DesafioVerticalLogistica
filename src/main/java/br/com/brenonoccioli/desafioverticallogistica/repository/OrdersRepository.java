package br.com.brenonoccioli.desafioverticallogistica.repository;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {

}
