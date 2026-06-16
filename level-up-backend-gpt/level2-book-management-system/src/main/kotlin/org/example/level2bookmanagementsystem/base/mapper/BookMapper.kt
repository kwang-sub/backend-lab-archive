package org.example.level2bookmanagementsystem.base.mapper

import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.example.level2bookmanagementsystem.base.dto.response.BookResponse
import org.example.level2bookmanagementsystem.base.dto.response.BookSimpleResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", config = BaseMapper::class)
interface BookMapper : BaseMapper<BookCommand, Book, BookResponse, BookSimpleResponse> {

}