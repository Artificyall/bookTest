package com.briendguyot.library

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.restassured.RestAssured
import io.restassured.response.Response
import org.hamcrest.Matchers

class BookStepDefs {
    private lateinit var response: Response

    @Before
    fun setUp() {
        RestAssured.baseURI = "http://localhost:8080"
    }

    @When("^j'envoie une requête GET sur \\"/books\\"$")
    fun sendGetBooksRequest() {
        response = RestAssured.get("/books")
    }

    @Then("^le code de réponse doit être (\\d+)$")
    fun checkStatusCode(expectedStatus: Int) {
        response.then().statusCode(expectedStatus)
    }
}

