package pl.szarek.projekt_sonar.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    @Column(name = "date_of_addition")
    private Date datOfAddition;
    private String title;
    private String description;

    public Event(String author, Date datOfAddition, String title, String description) {
        this.author = author;
        this.datOfAddition = datOfAddition;
        this.title = title;
        this.description = description;
    }

    public Event() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDatOfAddition() {
        return datOfAddition;
    }

    public void setDatOfAddition(Date datOfAddition) {
        this.datOfAddition = datOfAddition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
