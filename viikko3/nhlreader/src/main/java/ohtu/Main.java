package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        List<Player> playerList = List.of(mapper.fromJson(bodyText, Player[].class));

        playerList.stream()
                .filter(player -> player.nationality.equals("FIN"))
                .sorted(Comparator.comparing(Player::getPoints, Comparator.reverseOrder())
                .thenComparing(Player::getGoals, Comparator.reverseOrder()))
                .forEach(player -> {
                    System.out.println(player);
                });

    }

}

