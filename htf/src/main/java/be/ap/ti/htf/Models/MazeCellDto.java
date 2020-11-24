package be.ap.ti.htf.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class MazeCellDto {

    private String challenge;
    private String challengeId;
    private String challengeParameters;
    private boolean decisionPoint;
    private int prize;
    private ArrayList<String> sides;
    private int x;
    private int y;

}