package nickfanelli.javathrugamedev.engine.graphics;

import nickfanelli.javathrugamedev.engine.io.InputContext;
import nickfanelli.javathrugamedev.engine.state.GameState;
import nickfanelli.javathrugamedev.engine.state.GameStateManager;

import java.awt.*;

public class GameApplication <T extends GameState> implements Runnable {

    private static final int TARGET_FPS = 120;
    private static final double FRAME_TIME = 1.0 / TARGET_FPS;

    private final Thread gameThread;
    private final Window window;
    private final GameStateManager gameStateManager;
    private final Class<T> initialState;

    private InputContext inputContext;

    private boolean isRunning;
    private int currentFps;

    private String windowTitle;

    public GameApplication(String title, int width, int height, Class<T> initialState) {

        this.gameThread = new Thread(this, "GameThread");
        this.window = new Window(width, height);
        this.gameStateManager = new GameStateManager();
        this.initialState = initialState;

        this.setGameState(initialState);

        this.windowTitle = title;

    }

    public void start() {
        if (this.isRunning)
            return;

        this.window.create(this.windowTitle);
        this.inputContext = new InputContext(this.window.getCanvas());

        this.isRunning = true;
        this.gameThread.start();
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {

        this.setGameState(this.initialState);

        double lastTime = System.nanoTime() / 1_000_000_000.0;
        double accumulator = 0.0;
        double fpsTimer = 0.0;
        int frames = 0;

        while (isRunning) {

            double now = System.nanoTime() / 1_000_000_000.0;
            double delta = now - lastTime;
            lastTime = now;

            accumulator += delta;
            fpsTimer += delta;

            while (accumulator >= FRAME_TIME) {
                this.update((float) delta);
                accumulator -= FRAME_TIME;
            }

            Graphics2D g = this.window.beginFrame();
            this.render(g);
            g.dispose();
            this.window.endFrame();

            frames++;

            if (fpsTimer >= 1.0) {
                currentFps = frames;
                frames = 0;
                fpsTimer = 0;
            }

            // Small sleep to prevent 100% CPU
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {}
        }

        window.dispose();
        this.gameStateManager.end();
    }

    private synchronized void update(float deltaTime) {
        this.gameStateManager.update(deltaTime);
        this.inputContext.update();
    }

    private synchronized void render(Graphics2D g) {
        this.gameStateManager.render(g);
    }

    public <T extends GameState> void setGameState(Class<T> stateClass) {
        try {
            this.gameStateManager.setState(stateClass, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InputContext getInputContext() { return this.inputContext; }

}