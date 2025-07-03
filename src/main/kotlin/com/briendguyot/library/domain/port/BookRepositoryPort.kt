package com.briendguyot.library.domain.port

import com.briendguyot.library.domain.model.Book

interface BookRepositoryPort {
    fun getBooks(): List<Book>
    fun addBook(book: Book)
    fun findByTitle(title: String): Book?
    fun updateBook(book: Book)
}
