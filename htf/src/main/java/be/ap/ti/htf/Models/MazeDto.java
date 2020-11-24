package be.ap.ti.htf.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MazeDto {

    private ArrayList<MazeCellDto> cells = new ArrayList<MazeCellDto>();
    private String mazeId;


}