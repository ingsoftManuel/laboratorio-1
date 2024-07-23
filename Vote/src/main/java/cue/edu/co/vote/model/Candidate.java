package cue.edu.co.vote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    private int id;
    private String name;
    private String photoPath;
    private String jornada;

}
