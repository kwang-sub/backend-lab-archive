package com.example.catalogservice.service;

import com.example.catalogservice.mapper.CatalogMapper;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.vo.ResponseCatalog;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    @Transactional(readOnly = true)
    public List<ResponseCatalog> getAllCatalogs() {
        return catalogRepository.findAll().stream().map(catalogMapper::toDto).collect(Collectors.toList());
    }
}
