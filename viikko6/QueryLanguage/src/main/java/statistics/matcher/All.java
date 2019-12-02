package statistics.matcher;

import statistics.Player;

import java.util.Arrays;

public class All implements Matcher {

    private Matcher[] matchers;

    public All(Matcher... matchers){
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        return matchers.length == Arrays.stream(matchers).filter(m -> m.matches(p)).count();
    }
}
