package pl.szuflicki.quotes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Quote implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty
    private Long id;
    @NotNull
    @JsonProperty
    private String content;
    @NotNull
    @JsonProperty
    private String author;

//    @ManyToMany(mappedBy = "likedQuotes")
//    private Set<User> likedBy;

    public Quote() {
    }

    public Quote(String content, String author) {
        this.content = content;
        this.author = author;
        this.id = (long) (content.hashCode() + author.hashCode());
    }

//    public boolean addLike(User user) {
//        return likedBy.add(user);
//    }
}
