package nickfanelli.javathrugamedev.engine.graphics;

import java.awt.*;

public abstract class GameApplication implements Runnable {

    private static final int TARGET_FPS = 120;
    private static final double FRAME_TIME = 1.0 / TARGET_FPS;

    private final Thread gameThread;
    protected final Window window;

    private boolean isRunning;
    private int currentFps;

    private String windowTitle;

    public GameApplication(String title, int width, int height) {

        this.window = new Window(width, height);
        this.gameThread = new Thread(this, "GameThread");
        this.windowTitle = title;

    }

    public void start() {
        if (this.isRunning)
            return;

        this.window.create(this.windowTitle);

        this.isRunning = true;
        this.gameThread.start();
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {

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
                update((float) FRAME_TIME);
                accumulator -= FRAME_TIME;
            }

            render();
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
    }

    private void render() {
        Graphics2D g = window.beginFrame();

        render(g);

        g.dispose();
    }

    protected abstract void update(float deltaTime);
    protected abstract void render(Graphics2D g);
}