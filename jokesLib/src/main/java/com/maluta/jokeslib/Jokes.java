package com.maluta.jokeslib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jokes {
    private List<String> jokes;

    public Jokes() {
        jokes = new ArrayList<>();
        jokes.add("Q:How many programmers does it take to screw in a light bulb?\n\nA: None. It's a hardware problem.");
        jokes.add("A programmer puts two glasses on his bedside table before going to sleep. A full one, in case he gets thirsty, and an empty one, in case he doesn’t.\n\n");
        jokes.add("Java and C were telling jokes. It was C's turn, so he writes something on the wall, points to it and says \"Do you get the reference?\" But Java didn't.\n\n");
        jokes.add("Chuck Norris can take a screenshot of his blue screen");
        jokes.add("Programmer (noun.)\nA machine that turns coffee into code");
    }

    public String getJoke() {
        Random random = new Random();
        return jokes.get(random.nextInt(jokes.size() - 1));
    }
}
