package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapToResponse(UserEntity entity);

    @IterableMapping(elementTargetType = UserResponse.class)
    List<UserResponse> mapToListResponse(List<UserEntity> entities);

}
