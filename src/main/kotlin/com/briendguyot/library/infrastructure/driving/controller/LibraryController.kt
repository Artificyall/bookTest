package com.briendguyot.library.infrastructure.driving.controller

import com.briendguyot.library.domain.usecase.LibraryUseCase
import com.briendguyot.library.infrastructure.driving.controller.dto.BookDTO
import com.briendguyot.library.infrastructure.driving.controller.dto.toDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/books")
class LibraryController(
    private val libraryUseCase: LibraryUseCase
) {
    @CrossOrigin
    @GetMapping
    fun getAllBooks(): List<BookDTO> {
        return libraryUseCase.getBooks()
            .map { it.toDto() }
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBook(@RequestBody bookDTO: BookDTO) {
        libraryUseCase.addBook(bookDTO.toDomain())
    }
    
    @CrossOrigin
    @PostMapping("/{title}/reserve")
    fun reserveBook(@PathVariable title: String): ResponseEntity<Unit> {
        return try {
            libraryUseCase.reserveBook(title)
            ResponseEntity.ok().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        } catch (e: IllegalStateException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }
}