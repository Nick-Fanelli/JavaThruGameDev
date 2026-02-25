package nickfanelli.javathrugamedev.engine.state;

import java.awt.*;

public abstract class GameState {

    public abstract void onBegin();

    public abstract void onUpdate(float deltaTime);
    public abstract void onRender(Graphics2D g);

    public abstract void onEnd();

}
