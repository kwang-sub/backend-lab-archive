package org.example.level2bookmanagementsystem.base.mapper

import org.example.level2bookmanagementsystem.base.dto.response.UserResponse
import org.example.level2bookmanagementsystem.base.dto.response.UserSimpleResponse
import org.example.level2bookmanagementsystem.security.command.SignupCommand
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", config = BaseMapper::class)
interface UserMapper: BaseMapper<SignupCommand, TbUser, UserResponse, UserSimpleResponse> {

}