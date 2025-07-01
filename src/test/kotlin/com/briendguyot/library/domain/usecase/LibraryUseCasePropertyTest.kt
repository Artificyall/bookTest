package com.briendguyot.library.domain.usecase

import com.briendguyot.library.domain.model.Book
import com.briendguyot.library.domain.port.BookRepositoryPort
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.stringPattern
import io.kotest.property.checkAll

class InMemoryBookRepositoryPort : BookRepositoryPort {
    private val books = mutableListOf<Book>()

    override fun getBooks(): List<Book> = books

    override fun addBook(book: Book) {
        books.add(book)
    }

    fun clear() = books.clear()
}

class LibraryUseCasePropertyTest: FunSpec({

    val bookRepository = InMemoryBookRepositoryPort()
    val libraryUseCase = LibraryUseCase(bookRepository)

    test("Should return all elements in the alphabetical order") {
        bookRepository.clear()
        val titles = mutableListOf<String>()
        checkAll(Arb.stringPattern("""[a-z]{1,10}""")) { title ->
            titles.add(title)
            libraryUseCase.addBook(Book(title, "Author"))
        }
        val res = libraryUseCase.getBooks()
        res.map { it.title } shouldContainExactly titles.sorted()
    }
})