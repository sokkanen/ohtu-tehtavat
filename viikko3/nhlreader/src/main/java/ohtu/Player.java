
package ohtu;

public class Player {
    public long goals;
    public long assists;
    public String name;
    public long penalties;
    public String team;
    public String nationality;
    public String birthdate;

    public Player(long goals, long assists, String name, long penalties,
                  String team, String nationality, String birthdate){
        this.goals = goals;
        this.assists = assists;
        this.name = name;
        this.penalties = penalties;
        this.team = team;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    public void setGoals(long goals) {
        this.goals = goals;
    }

    public long getPoints(){
        return this.goals + this.assists;
    }

    public long getGoals(){
        return this.goals;
    }

    public void setAssists(long assists) {
        this.assists = assists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPenalties(long penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return String.format("%-20s Team: %s Points: %s + %s = %s", name, team, goals, assists, getPoints());
    }

}
