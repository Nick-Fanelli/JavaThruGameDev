package nickfanelli.javathrugamedev.game;

import nickfanelli.javathrugamedev.engine.graphics.GameApplication;

public class Launcher {

    public static void main(String[] args) {
        GameApplication game = new GameApplication("JavaThurGameDev", 1024, 768, TestState.class);
        game.start();
    }

}
