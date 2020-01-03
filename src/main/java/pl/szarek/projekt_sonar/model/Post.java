package pl.szarek.projekt_sonar.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;

    @Column(name = "date_of_addition")
    private Date dateOfAddition;

    private String content;

    public Post(String author, Date dateOfAddition, String content) {
        this.author = author;
        this.dateOfAddition = dateOfAddition;
        this.content = content;
    }

    public Post() {
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

    public Date getDatOfAddition() {
        return dateOfAddition;
    }

    public void setDatOfAddition(Date datOfAddition) {
        this.dateOfAddition = datOfAddition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
