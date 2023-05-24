package pl.szuflicki.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.szuflicki.quotes.domain.Quote;


@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> , JpaRepository<Quote, Long> {

}
