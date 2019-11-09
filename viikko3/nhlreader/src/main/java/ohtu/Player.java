
package ohtu;

public class Player {
    public final long goals;
    public final long assists;
    public final String name;
    public final long penalties;
    public final String team;
    public final String nationality;
    public final String birthdate;

    Player(long goals, long assists, String name, long penalties,
                  String team, String nationality, String birthdate){
        this.goals = goals;
        this.assists = assists;
        this.name = name;
        this.penalties = penalties;
        this.team = team;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    long getPoints(){
        return this.goals + this.assists;
    }

    long getGoals(){
        return this.goals;
    }

    @Override
    public String toString() {
        return String.format("%-20s Team: %s Points: %s + %s = %s", name, team, goals, assists, getPoints());
    }

}
