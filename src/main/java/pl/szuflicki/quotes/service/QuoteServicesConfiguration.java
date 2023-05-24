package pl.szuflicki.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.szuflicki.quotes.config.QuoteService;

@Configuration
public class QuoteServicesConfiguration {

    RestTemplate restTemplate = new RestTemplate();

    @Bean
    QuotableIoService quotableIoService() {
        return new QuotableIoService(restTemplate);
    }

    @Bean
    QuoteGardenService quoteGardenService() {
        return new QuoteGardenService(restTemplate);
    }
}
