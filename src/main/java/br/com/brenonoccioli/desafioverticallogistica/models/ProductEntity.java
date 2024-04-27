package br.com.brenonoccioli.desafioverticallogistica.models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@Entity
public class ProductEntity {
    @Id
    private Long id;
    private BigDecimal value;
}
