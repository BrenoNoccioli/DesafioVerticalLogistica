package br.com.brenonoccioli.desafioverticallogistica.repository;

import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

}
