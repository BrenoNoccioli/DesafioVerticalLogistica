package br.com.brenonoccioli.desafioverticallogistica.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
    @Id
    private Long id;
    private BigDecimal value;
    @ManyToMany(mappedBy = "products")
    private HashSet<OrderEntity> orders;
}
