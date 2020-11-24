package be.ap.ti.htf.Controllers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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

            System.out.println("REEEEEEEEEEEEEEEEEEEE");
            if (maze.getCells().size() == 4) {

                System.out.println("IFFFFFFFFFFFFFF");

                int xy = 0;
                while (xy < 2) {
                    String object = "{\"cells\": [";
                    for (int i = 0; i < maze.getCells().size(); i++) {
                        MazeCellDto iterable_element = maze.getCells().get(i);
                        object += "{";
                        String challenge = iterable_element.getChallenge();
                        String challengeId = iterable_element.getChallengeId();
                        String answer = null;

                        if (challenge != null) {
                            if (challenge.toLowerCase().contains("alphabetical")) {
                                answer = _service.isAlphabetical(iterable_element.getChallengeParameters().toString());

                            } else if (challenge.toLowerCase().contains("decode")) {
                                answer = _service.decode(iterable_element.getChallengeParameters().toString());
                            } else if (challenge.toLowerCase().contains("common encoding")) {
                                answer = _service.decodeBase64(iterable_element.getChallengeParameters().toString());
                            } else if (challenge.toLowerCase().contains("hexadecimal")) {
                                answer = _service.stringToHex(iterable_element.getChallengeParameters().toString());
                            } else if (challenge.toLowerCase().contains("reverse")) {
                                answer = _service.reverseString(iterable_element.getChallengeParameters().toString());
                            } else if (challenge.toLowerCase().contains("regex")) {
                                answer = _service
                                        .stringMatchRegex(iterable_element.getChallengeParameters().toString());
                            }
                        }

                        if (answer != null && challengeId != null) {
                            object += "\"challengeId\":" + "\"" + challengeId + "\",";
                            object += "\"answer\":" + "\"" + answer + "\",";
                            object += "\"x\":" + iterable_element.getX() + ",";
                            object += "\"y\":" + iterable_element.getY();
                        } else {
                            if (xy == 0) {
                                object += "\"x\":" + 0 + ",";
                                object += "\"y\":" + 1;
                            } else {
                                object += "\"x\":" + 1 + ",";
                                object += "\"y\":" + 0;
                            }
                        }

                        object += "}";
                        if (i < maze.getCells().size() - 1)
                            object += ",";

                        xy++;
                        if (xy > 2) {
                            xy = 0;
                        }
                    }
                    object += "]}";

                    System.out.println("OBJEEEEEEEEEECT" + object);
                    postSolution(maze.getMazeId(), object);

                    postSolution(maze.getMazeId(), "{\"cells\":[{\"x\":0,\"y\":0},{\"x\":1,\"y\":0},{\"x\":1,\"y\":1}]}");
                    postSolution(maze.getMazeId(), "{\"cells\":[{\"x\":0,\"y\":0},{\"x\":0,\"y\":1},{\"x\":1,\"y\":1}]}");
                }

            } else {
                System.err.println("To Long" + maze.getCells().size());

            }

        } catch (Exception e) {
            System.out.println("EXCEPTIOOOOOON" + e.getMessage());
        }

    }

    private void postSolution(String mazeId, String solution) {

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(solution, headers);

        System.out.println("ENTITYYYYY: \n" + entity.toString());

        MazeDto result = restTemplate.postForObject(BASE_URL + "/" + mazeId, entity, MazeDto.class);

        System.out.println("RESULT: \n" + result.toString());

    }

}