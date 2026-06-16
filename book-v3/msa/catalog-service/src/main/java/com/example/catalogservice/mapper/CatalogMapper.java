package com.example.catalogservice.mapper;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.vo.RequestCatalog;
import com.example.catalogservice.vo.ResponseCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatalogMapper extends BaseMapper<RequestCatalog, ResponseCatalog, Catalog> {

    @Override
    Catalog toEntity(RequestCatalog command);

    @Override
    ResponseCatalog toDto(Catalog command);
}
