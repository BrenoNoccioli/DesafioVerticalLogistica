package br.com.brenonoccioli.desafioverticallogistica.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
public class OrderEntity {
    @Id
    private Long id;
    @ManyToOne
    private UserEntity user;
    private BigDecimal totalPrice;
    private LocalDate date;
    @ManyToMany
    private List<ProductEntity> products;
}
