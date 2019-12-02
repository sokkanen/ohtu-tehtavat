package statistics.matcher;

public class QueryBuilder {

    Matcher matcher;

    public QueryBuilder(){
        matcher = new All();
    }

    public Matcher build(){
        return matcher;
    }

    public QueryBuilder hasAtLeast(int value, String category){
        matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category){
        matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder playsIn(String team){
        matcher = new And(matcher, new PlaysIn(team));
        return this;
    }
}
