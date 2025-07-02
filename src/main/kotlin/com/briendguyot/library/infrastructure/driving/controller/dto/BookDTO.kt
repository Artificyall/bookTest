package com.briendguyot.library.infrastructure.driving.controller.dto

import com.briendguyot.library.domain.model.Book

data class BookDTO(val title: String, val author: String) {
    fun toDomain(): Book {
        return Book(
            title = this.title,
            author = this.author
        )
    }
}

fun Book.toDto() = BookDTO(
    title = this.title,
    author = this.author
)