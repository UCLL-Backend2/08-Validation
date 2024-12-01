package be.ucll.backend2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "every actor has a name")
    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private Set<Movie> movies;

    protected Actor() {
        this.movies = new HashSet<>();
    }

    public Actor(String name) {
        this.name = name;
        this.movies = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void update(Actor actor) {
        this.name = actor.getName();
    }
}
