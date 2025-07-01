package com.briendguyot.library.domain.usecase

import com.briendguyot.library.domain.model.Book
import com.briendguyot.library.domain.port.BookRepositoryPort
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class LibraryUseCaseTest: FunSpec({
  val bookRepository = mockk<BookRepositoryPort>()
  val libraryUseCase = LibraryUseCase(bookRepository)

  test("getBooks returns sorted list of books") {
    val books = listOf(
      Book("Le Petit Prince", "Antoine de Saint-Exupéry"),
      Book("Hypérion", "Dan Simons"),
      Book("Axiomatique", "Greg Egan")
    )
    every { bookRepository.getBooks() } returns books

    val res = libraryUseCase.getBooks()
    res shouldContainExactly listOf(
      Book("Axiomatique", "Greg Egan"),
      Book("Hypérion", "Dan Simons"),
      Book("Le Petit Prince", "Antoine de Saint-Exupéry")
    )
  }

  test("addBook adds a book to the repository") {
    val book = Book("Le Petit Prince", "Antoine de Saint-Exupéry")
    justRun { bookRepository.addBook(any()) }

    libraryUseCase.addBook(book)

    verify(exactly = 1) { bookRepository.addBook(book)}
  }
})