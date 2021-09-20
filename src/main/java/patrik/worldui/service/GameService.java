package patrik.worldui.service;

import org.springframework.stereotype.Service;
import patrik.restapi.objects.GameResponseObject;
import patrik.worldui.utils.Utils;

@Service
public class GameService {

    public GameResponseObject initGame(Integer seed) {
        try {
            String res = "";
            res = Utils.webParser("http://localhost:8080/api/init-game?seed=" + seed);

            return (GameResponseObject) Utils.fromString(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
