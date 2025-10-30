package br.com.michael_fausto.formulaSAE.mapper.users;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.model.users.UsersDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersEntity toEntity(UsersDTO usersDTO);

    UsersDTO toDto(UsersEntity pilot);

}
