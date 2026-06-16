package org.example.level2bookmanagementsystem.jpa.util

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.domain.Pageable

class QueryDslUtil {
    companion object {
        fun buildOrderSpecifiers(
            pageable: Pageable,
            pathMap: Map<String, Expression<out Comparable<*>>>
        ): Array<OrderSpecifier<out Comparable<*>?>?> {
            return pageable.sort
                .filter { pathMap.containsKey(it.property) }
                .map {
                    val order = if (it.isAscending) Order.ASC else Order.DESC
                    OrderSpecifier(order, pathMap[it.property])
                }.toList().toTypedArray()
        }
    }
}