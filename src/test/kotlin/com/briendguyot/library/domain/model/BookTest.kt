package com.briendguyot.library.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class BookTest : FunSpec({
    test("Name is mandatory") {
        shouldThrow<IllegalArgumentException> { Book("", "Victor Hugo")}
    }

    test("Author is mandatory") {
        shouldThrow<IllegalArgumentException> { Book("Le Petit Prince", "")}
    }
})