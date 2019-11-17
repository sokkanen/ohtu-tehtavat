
public class RefactoredTennisGame implements TennisGame {

    private Player player1;
    private Player player2;

    private static String[] tieScoreStrings = {
            "Love-All",
            "Fifteen-All",
            "Thirty-All",
            "Deuce"
    };

    private static String[] tennisScoreStrings = {
            "Love",
            "Fifteen",
            "Thirty",
            "Forty"
    };

    public RefactoredTennisGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1.name)) {
            player1.incrementScore();
        } else if (playerName.equals(player2.name)) {
            player2.incrementScore();
        } else {
            throw new RuntimeException("Unknown player");
        }
    }

    public boolean theGameIsTie() {
        return player1.getScore() == player2.getScore();
    }

    public String tieScoreToString() {
        if (player1.getScore() < 3) {
            return tieScoreStrings[player1.getScore()];
        }
        return tieScoreStrings[3];
    }

    public boolean playerHasAdvantageOrWon() {
        return player1.getScore() >= 4 || player2.getScore() >= 4;
    }

    public String advantageOrWonToString() {
        int pointDifference = Player.getPointDifference(player1, player2);
        String leadingPlayer = Player.getLeadingPlayersName(player1, player2);
        if (pointDifference == 1 || pointDifference == -1) return "Advantage " + leadingPlayer;
        else return "Win for " + leadingPlayer;
    }

    public String onGoingGameScoreToString(){
        return tennisScoreStrings[player1.getScore()] + "-" + tennisScoreStrings[player2.getScore()];
    }

    public String getScore() {
        if (theGameIsTie()) {
            return tieScoreToString();
        }

        if (playerHasAdvantageOrWon()) {
            return advantageOrWonToString();
        }

        return onGoingGameScoreToString();
    }
}
