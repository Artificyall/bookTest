package com.briendguyot.library.infrastructure.driven.postgres

import com.briendguyot.library.domain.model.Book
import com.briendguyot.library.domain.port.BookRepositoryPort
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BookDAO(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): BookRepositoryPort {
    override fun getBooks(): List<Book> {
        return namedParameterJdbcTemplate
            .query("SELECT * FROM BOOK", MapSqlParameterSource()) { rs, _ ->
                Book(
                    title = rs.getString("title"),
                    author = rs.getString("author"),
                    reserved = rs.getBoolean("reserved")
                )
            }
    }


    override fun addBook(book: Book) {
        namedParameterJdbcTemplate
            .update("INSERT INTO BOOK (title, author) values (:title, :author)", mapOf(
                "title" to book.title,
                "author" to book.author
            ))
    }

    override fun findByTitle(title: String): Book? {
        return namedParameterJdbcTemplate
            .query("SELECT * FROM BOOK WHERE title = :title", mapOf("title" to title)) { rs, _ ->
                Book(
                    title = rs.getString("title"),
                    author = rs.getString("author"),
                    reserved = rs.getBoolean("reserved")
                )
            }.firstOrNull()
    }


    override fun updateBook(book: Book) {
        namedParameterJdbcTemplate.update(
            """
        UPDATE BOOK 
        SET author = :author, reserved = :reserved 
        WHERE title = :title
        """.trimIndent(),
            mapOf(
                "title" to book.title,
                "author" to book.author,
                "reserved" to book.reserved
            )
        )
    }
}