package com.briendguyot.library.domain.usecase

import com.briendguyot.library.domain.model.Book
import com.briendguyot.library.domain.port.BookRepositoryPort

class LibraryUseCase(val bookRepository: BookRepositoryPort) {
    fun getBooks(): List<Book> = bookRepository.getBooks().sortedBy { it.title }
    fun addBook(book : Book) {
        bookRepository.addBook(book)
    }
}