package bestoftheyear.gioia.bestOfTheYear.controller;

import bestoftheyear.gioia.bestOfTheYear.model.Movie;
import bestoftheyear.gioia.bestOfTheYear.model.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//Indico a Spring che la classe è un suo components, di fatto è IoC
@Controller //ha il compito di gestire le richieste HTTML che arrivano dal server
@RequestMapping("/")
public class BestOfTheYearController {
    @GetMapping("/") //quando arriva una richiesta HTTP di tipo GET all'indirizzo '/', esegue questo metodo
    public String index(Model model){ //Model model, parametro che verrà passato alla view che rappresenta il modello dati
        String name = "Gioia";
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        List<Movie> movies = getBestMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String moviesById(@PathVariable int id, Model model) {
        Movie movie = getBestMovies()
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("movie", movie);
        return "movieDetail";
    }

    private List<Movie> getBestMovies() {
        return List.of(new Movie(1, "Avatar: The Way of War"),
                new Movie(2, "Everything Everywhere All at once"),
                new Movie(3, "Top Gun: Maverick"));
    }

    @GetMapping("/songs")
    public String songs(Model model) {
        List<Song> songs = getBestSongs();
        return "index";
    }

    @GetMapping("/songs/{id")
    public String songsById(@PathVariable int id, Model model) {
        Song song = getBestSongs()
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("song", song);
        return "songDetail";
    }

    private List<Song> getBestSongs(){
        return List.of(new Song(1, "As it Was"),
                new Song(2, "Bring me to life"),
                new Song(3, "Me and my brother"));
    }
}

