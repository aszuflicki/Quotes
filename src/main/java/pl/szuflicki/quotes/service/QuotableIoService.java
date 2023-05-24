package pl.szuflicki.quotes.service;

import pl.szuflicki.quotes.config.IQuoteService;
import pl.szuflicki.quotes.config.QuoteService;
import pl.szuflicki.quotes.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@QuoteService
public class QuotableIoService implements IQuoteService {


    public QuotableIoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final RestTemplate restTemplate;
    private final String msgServiceUrl = "https://api.quotable.io/random";
    private final String contentRegex = "content\":\"(.*?)\"";
    private final String authorRegex = "author\":\"(.*?)\"";
    Pattern contentPattern = Pattern.compile(contentRegex, Pattern.CASE_INSENSITIVE);
    Pattern authorPattern = Pattern.compile(authorRegex, Pattern.CASE_INSENSITIVE);

    @Async
    public CompletableFuture<Quote> get() {
        final String response = restTemplate.getForObject(msgServiceUrl, String.class);

        if (response == null) {
            return CompletableFuture.failedFuture(new Exception("Error"));
        }

        Matcher contentMatcher = contentPattern.matcher(response);
        if (!contentMatcher.find()) {
            return CompletableFuture.failedFuture(new Exception("Error"));
        }
        var content = contentMatcher.group(1);

        Matcher authorMatcher = authorPattern.matcher(response);
        if (!authorMatcher.find()) {
            return CompletableFuture.failedFuture(new Exception("Error"));
        }
        var author = authorMatcher.group(1);

        return CompletableFuture.completedFuture(new Quote(content, author));
    }
}
