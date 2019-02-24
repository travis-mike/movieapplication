package com.example.movieapplication.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_movies", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> userMovieList;

    @Column
    private int horrorPoints;

    @Column
    private int dramaPoints;

    @Column
    private int comedyPoints;

    @Column
    private int romancePoints;

    @Column
    private int sciFiPoints;

    @Column
    private int bank;

    public User() {
        this.horrorPoints = 0;
        this.dramaPoints = 0;
        this.comedyPoints = 0;
        this.romancePoints = 0;
        this.sciFiPoints = 0;
        this.userMovieList = new ArrayList<>();
        this.bank = 40;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        userMovieList = copy.userMovieList;
        horrorPoints = copy.horrorPoints;
        dramaPoints = copy.dramaPoints;
        comedyPoints = copy.comedyPoints;
        romancePoints = copy.romancePoints;
        sciFiPoints = copy.sciFiPoints;
        bank = copy.bank;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Movie> getUserMovieList() {
        return userMovieList;
    }

    public void setUserMovieList(Movie test) {
        userMovieList.add(test);
    }

    public void setInitialGenrePoints (List <Movie> movieList) {
        for (Movie movie : movieList) {
            if (movie.getGenre().equals("horror")) {
                this.horrorPoints++;
            } else if (movie.getGenre().equals("drama")) {
                this.dramaPoints++;
            } else if (movie.getGenre().equals("comedy")) {
                this.comedyPoints++;
            } else if (movie.getGenre().equals("romance")) {
                this.romancePoints++;
            } else if (movie.getGenre().equals("scifi")) {
                this.sciFiPoints++;
            }
        }
    }
}
