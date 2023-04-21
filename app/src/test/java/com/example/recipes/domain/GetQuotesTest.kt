package com.example.recipes.domain

import com.example.recipes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetQuotesTest{

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotes: GetQuotes

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotes = GetQuotes(quoteRepository)
    }

    @Test
    fun `When the api doest return any then get values from database`() = runBlocking{
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        //When
        getQuotes()

        //Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `When the api return something then get values from api`() = runBlocking{
        //Given
        val myList = listOf<Quote>(
            Quote("Si", "Claro")
        )
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList

        //When
        val response = getQuotes()

        //Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(myList == response)
    }


}