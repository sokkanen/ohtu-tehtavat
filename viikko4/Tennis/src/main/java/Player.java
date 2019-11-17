public class Player {
    private int score;
    public final String name;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public static int getPointDifference(Player player1, Player player2){
        return player1.getScore() - player2.getScore();
    }

    public static String getLeadingPlayersName(Player player1, Player player2){
        return player1.getScore() - player2.getScore() > 0 ? player1.name : player2.name;
    }
}
