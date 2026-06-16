package com.example.userservice.mapper;

import com.example.userservice.vo.ResponseUser;
import com.example.userservice.entity.User;
import com.example.userservice.vo.RequestUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<RequestUser, ResponseUser, User> {

    @Override
    User toEntity(RequestUser command);

    @Override
    ResponseUser toDto(User command);
}
