package org.example.level2bookmanagementsystem.base.mapper

import org.example.level2bookmanagementsystem.base.dto.command.BookLoanCommand
import org.example.level2bookmanagementsystem.base.dto.response.BookLoanResponse
import org.example.level2bookmanagementsystem.base.dto.response.BookLoanSimpleResponse
import org.example.level2bookmanagementsystem.jpa.entity.BookLoan
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", config = BaseMapper::class)
interface BookLoanMapper: BaseMapper<BookLoanCommand, BookLoan, BookLoanResponse, BookLoanSimpleResponse> {

}