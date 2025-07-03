package com.briendguyot.library.domain.usecase

import com.briendguyot.library.domain.model.Book
import com.briendguyot.library.domain.port.BookRepositoryPort

class LibraryUseCase(val bookRepository: BookRepositoryPort) {

    fun getBooks(): List<Book> =
        bookRepository.getBooks().sortedBy { it.title }

    fun addBook(book: Book) {
        bookRepository.addBook(book)
    }

    fun reserveBook(title: String) {
        val book = bookRepository.findByTitle(title)
            ?: throw IllegalArgumentException("Livre non trouvé")

        if (book.reserved) {
            throw IllegalStateException("Le livre est déjà réservé")
        }

        val reservedBook = book.copy(reserved = true)
        bookRepository.updateBook(reservedBook)
    }
}
