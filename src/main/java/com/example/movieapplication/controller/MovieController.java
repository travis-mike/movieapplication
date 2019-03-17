package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.repository.Movies;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MovieController {

    @Autowired
    private Movies movies;
    private HttpResponse<JsonNode> jsonNodeHttpResponse;
    private static final String baseURL = "https://api.themoviedb.org/3/movie/";
    private static final String apiKey = "?api_key=49990b53c193c05b1ce005f26fbf50c1&language=en-US";

    @RequestMapping("movies/{movie}")
    public String getMoviePage (Model model, @PathVariable ("movie") String movie) {

        try {
            jsonNodeHttpResponse = Unirest.get(baseURL + "{movieId}" + apiKey)
                    .routeParam("movieId", "3")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        JSONObject testObject = jsonNodeHttpResponse.getBody().getObject();
//        JSONObject testObject = testArray.getJSONObject(0);
        String testString = testObject.getString("backdrop_path");
        System.out.println(testString);

//        Movie foundMovie = movies.findByTitle(movie);
//        //Need to write an exception if movie is not found
//        model.addAttribute("movie", foundMovie);
//        return "movie-page";
        return "test";
    }
}
