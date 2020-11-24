package be.ap.ti.htf.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MazeCellCommand {

    private String answer;
    private String challengeId;
    private int x;
    private int y;

}