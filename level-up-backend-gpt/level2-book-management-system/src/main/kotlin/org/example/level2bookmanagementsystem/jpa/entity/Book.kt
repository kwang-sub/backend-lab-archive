package org.example.level2bookmanagementsystem.jpa.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@SQLDelete(sql = "UPDATE book SET is_deleted = true WHERE id = ?")
@SQLRestriction(value = "is_deleted = false")
class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var author: String,
    var publisher: String,
    var publishedDate: LocalDate,
    var isbn: String,
    var price: BigDecimal,
    var stockQuantity: Int = 0
) : BaseEntity() {

    fun update(command: BookCommand): Book {
        this.title = command.title
        this.author = command.author
        this.publisher = command.publisher
        this.publishedDate = command.publishedDate
        this.isbn = command.isbn
        this.price = command.price
        this.stockQuantity = command.stockQuantity
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false
        if (id == null || other.id == null) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    fun isAvailabilityStock(): Boolean {
        return this.stockQuantity > 0
    }

    fun decreaseStock() {
        if (this.stockQuantity <= 0) {
            throw CustomBadRequestException(ErrorCode.OUT_OF_STOCK)
        }

        this.stockQuantity -= 1
    }

    fun increaseStock() {
        this.stockQuantity += 1
    }
}