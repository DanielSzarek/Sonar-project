package pl.szarek.projekt_sonar.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;

    @Column(name = "date_of_addition")
    private Timestamp dateOfAddition;

    private String content;

    public Post(String author, String content) {
        this.author = author;
        this.dateOfAddition = Timestamp.valueOf(LocalDateTime.now());
        this.content = content;
    }

    public Post() {
        this.dateOfAddition = Timestamp.valueOf(LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(Timestamp datOfAddition) {
        this.dateOfAddition = datOfAddition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
