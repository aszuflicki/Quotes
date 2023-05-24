package pl.szuflicki.quotes.config;

import pl.szuflicki.quotes.domain.Quote;

import java.util.concurrent.CompletableFuture;

public interface IQuoteService {
    CompletableFuture<Quote> get();
}
