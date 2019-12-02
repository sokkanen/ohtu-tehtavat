package statistics.matcher;

import statistics.Player;

import java.util.Arrays;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        return Arrays.stream(matchers).anyMatch(m -> m.matches(p));
    }
}
