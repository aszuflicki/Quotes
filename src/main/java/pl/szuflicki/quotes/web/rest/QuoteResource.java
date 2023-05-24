package pl.szuflicki.quotes.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szuflicki.quotes.domain.Quote;
import pl.szuflicki.quotes.repository.QuoteRepository;
import pl.szuflicki.quotes.service.QuoteAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/quote")
public class QuoteResource {
    QuoteAPI quoteAPI;
    QuoteRepository quoteRepository;

    public QuoteResource(QuoteAPI quoteAPI, QuoteRepository quoteRepository) {
        this.quoteAPI = quoteAPI;
        this.quoteRepository = quoteRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Quote> getQuote() throws ExecutionException, InterruptedException {
        Quote quote = quoteAPI.getNewQuote();
        quoteRepository.save(quote);
        return ResponseEntity.ok(quote);
    }
}
