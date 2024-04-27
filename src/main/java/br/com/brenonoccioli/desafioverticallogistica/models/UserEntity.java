package br.com.brenonoccioli.desafioverticallogistica.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@Data
@Entity
public class UserEntity {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;
}
