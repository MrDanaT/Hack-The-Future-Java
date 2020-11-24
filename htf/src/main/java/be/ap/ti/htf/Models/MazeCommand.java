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

public class MazeCommand {

    private ArrayList<MazeCellCommand> cells;

}