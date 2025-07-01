package com.briendguyot.library.domain.port

import com.briendguyot.library.domain.model.Book

interface BookRepositoryPort {
    fun addBook(book: Book)
    fun getBooks(): List<Book>
}