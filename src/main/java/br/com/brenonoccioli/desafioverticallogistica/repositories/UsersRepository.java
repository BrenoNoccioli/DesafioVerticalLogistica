package br.com.brenonoccioli.desafioverticallogistica.repositories;

import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

}
