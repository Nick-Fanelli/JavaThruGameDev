package nickfanelli.javathrugamedev.engine.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;

    private BufferedImage backBuffer;

    private final int internalWidth;
    private final int internalHeight;

    public Window(int internalWidth, int internalHeight) {
        this.internalWidth = internalWidth;
        this.internalHeight = internalHeight;
    }

    public void create(String title) {

        frame = new JFrame(title);
        canvas = new Canvas();

        canvas.setIgnoreRepaint(true);

        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(internalWidth, internalHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true); // <-- now resizable
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        backBuffer = new BufferedImage(
                internalWidth,
                internalHeight,
                BufferedImage.TYPE_INT_ARGB
        );
    }

    public Graphics2D beginFrame() {

        Graphics2D g = backBuffer.createGraphics();

        // Clear internal resolution
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, internalWidth, internalHeight);

        return g;
    }

    public void endFrame() {

        do {
            do {
                Graphics g = bufferStrategy.getDrawGraphics();

                int windowWidth = canvas.getWidth();
                int windowHeight = canvas.getHeight();

                // Maintain aspect ratio
                float aspect = (float) internalWidth / internalHeight;
                int drawWidth = windowWidth;
                int drawHeight = (int) (windowWidth / aspect);

                if (drawHeight > windowHeight) {
                    drawHeight = windowHeight;
                    drawWidth = (int) (windowHeight * aspect);
                }

                int x = (windowWidth - drawWidth) / 2;
                int y = (windowHeight - drawHeight) / 2;

                // Clear screen (black bars)
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, windowWidth, windowHeight);

                // Draw scaled game
                g.drawImage(backBuffer, x, y, drawWidth, drawHeight, null);

                g.dispose();

            } while (bufferStrategy.contentsRestored());

            bufferStrategy.show();

        } while (bufferStrategy.contentsLost());
    }

    public void dispose() {
        frame.dispose();
    }

    public int getInternalWidth() { return internalWidth; }
    public int getInternalHeight() { return internalHeight; }

    public int getWindowWidth() { return canvas.getWidth(); }
    public int getWindowHeight() { return canvas.getHeight(); }
}