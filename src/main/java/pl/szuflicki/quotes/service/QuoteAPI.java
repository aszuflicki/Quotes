package pl.szuflicki.quotes.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.szuflicki.quotes.QuotesApp;
import pl.szuflicki.quotes.config.IQuoteService;
import pl.szuflicki.quotes.config.QuoteService;
import pl.szuflicki.quotes.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class QuoteAPI {

    public Quote getNewQuote() throws ExecutionException, InterruptedException {
        var quotesServices = getAllQuoteServices().stream() //
                .map(IQuoteService::get) //
                .toList();

        CompletableFuture<Object> quote = CompletableFuture.anyOf(quotesServices.toArray(new CompletableFuture[]{}));
        return (Quote) quote.get();
    }

    public List<IQuoteService> getAllQuoteServices() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(QuoteServicesConfiguration.class);
        return applicationContext.getBeansWithAnnotation(QuoteService.class).values() //
                .stream() //
                .filter(quoteService -> quoteService instanceof IQuoteService) //
                .map(service -> (IQuoteService) service).toList();

    }
}