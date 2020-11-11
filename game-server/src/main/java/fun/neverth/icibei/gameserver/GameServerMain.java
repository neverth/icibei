package fun.neverth.icibei.gameserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author NeverTh
 * @date 19:16 2020/11/9
 */
@SpringBootApplication
public class GameServerMain {
    public static void main(String[] args) {
        SpringApplication.run(GameServerMain.class, args);
        GameServer.start();
    }
}
