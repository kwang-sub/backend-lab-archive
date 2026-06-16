package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog implements Serializable {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private Date createAt;
}
