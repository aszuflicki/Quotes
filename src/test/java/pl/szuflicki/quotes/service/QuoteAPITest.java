package pl.szuflicki.quotes.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuoteAPITest {

    @Test
    void testIfPicksUpAllQuoteServices() {
        var quoteApi = new QuoteAPI();
        var actual = quoteApi.getAllQuoteServices();

        assertEquals(2, actual.size());
    }

}