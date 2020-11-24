package be.ap.ti.htf.Controllers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import be.ap.ti.htf.Models.MazeCellDto;
import be.ap.ti.htf.Models.MazeDto;
import be.ap.ti.htf.Services.MazeCommandService;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Controller
public class HomeController {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private final String BASE_URL = "http://maze-staging.bewire.org/maze";

    @Autowired
    MazeCommandService _service;

    @GetMapping("/")
    public void getMaze() {
        System.out.println("Searching and solving mazes...\n");

        try {
            MazeDto maze = restTemplate.getForObject(BASE_URL + "?teamId=f7858567-7db4-4c9c-9d32-77e563393644",
                    MazeDto.class);

            System.out.println("Mazes...\n" + maze.getCells());
            if (maze.getCells().size() == 4) {

                for (MazeCellDto iterable_element : maze.getCells()) {

                    var challenge = iterable_element.getChallenge();
                    if (challenge == null) {
                        continue;
                    }
                    System.out.println(iterable_element);
                    if (challenge.toLowerCase().contains("alphabetical")) {
                        String result = _service.isAlphabetical(iterable_element.getChallengeParameters().toString());
                        postSolution(maze.getMazeId(), result, iterable_element.getChallengeId());
                    } else if (challenge.toLowerCase().contains("decode")) {
                        String result = _service.decode(iterable_element.getChallengeParameters().toString());
                        postSolution(maze.getMazeId(), result, iterable_element.getChallengeId());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("EXCEPTIOOOOOON" + e.getMessage());
        }

    }

    private void postSolution(String mazeId, String solution, String challengeId) {

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);

        String answer = String.format("{\"answer\":" + solution + "\"challengeId\":" + challengeId + "\"x\":" + x + "\"y\":" + y);

        
        HttpEntity<String> entity = new HttpEntity<>(answer, headers);

        MazeDto result = restTemplate.postForObject(BASE_URL + "/" + mazeId, entity, MazeDto.class);

        System.out.println("RESULT: \n" + result.toString());

    }

}