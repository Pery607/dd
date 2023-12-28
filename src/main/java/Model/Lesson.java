package Model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String name;
    private List<Exercise> exercises;
    public Lesson(String name, ArrayList<Exercise> exercises){
        this.name=name;
        this.exercises=exercises;
    }
    public String getName() {return name;}
    public List<Exercise> getExercises() {return exercises;}
}
