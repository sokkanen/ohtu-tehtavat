package statistics.matcher;

public class QueryBuilder {

    public Matcher matcher;

    public QueryBuilder(){
        matcher = new All();
    }

    public QueryBuilder(Matcher matcher){
        this.matcher = matcher;
    }

    public Matcher build(){
        Matcher finished = matcher;
        this.matcher = new All();
        return finished;
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

    public QueryBuilder oneOf(Matcher... matchers){
        matcher = new Or(matchers);
        return this;
    }
}
