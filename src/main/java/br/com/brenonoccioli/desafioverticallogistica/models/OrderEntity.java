package br.com.brenonoccioli.desafioverticallogistica.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    private BigDecimal totalPrice;

    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "Order_products", joinColumns = @JoinColumn(name = "order_id"))
    @Embedded
    private List<Product> products = new ArrayList<>();
}
