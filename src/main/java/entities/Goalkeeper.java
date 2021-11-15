package entities;

import java.util.HashMap;

public class Goalkeeper extends Player {
    public Goalkeeper(String name, int age, double height, double weight, String team, int rating,
                      double value, String position, HashMap<String, Integer> skills) {
        super(name, age, height, weight, team, rating, value, position, skills);
    }
}