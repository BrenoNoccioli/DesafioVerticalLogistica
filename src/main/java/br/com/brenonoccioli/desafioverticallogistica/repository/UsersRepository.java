package br.com.brenonoccioli.desafioverticallogistica.repository;

import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

}
