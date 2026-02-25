package nickfanelli.javathrugamedev.game;

import nickfanelli.javathrugamedev.engine.graphics.GameApplication;

import java.awt.*;

public class Launcher extends GameApplication {

    public static void main(String[] args) {
        new Launcher("JavaThurGameDev", 1024, 768).start();
    }

    public Launcher(String title, int width, int height) {
        super(title, width, height);
    }

    @Override
    protected void update(float deltaTime) {

    }

    @Override
    protected void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(100, 100, 400, 400);

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, 100, 100);
    }
}
