package nickfanelli.javathrugamedev.engine.io;

import nickfanelli.javathrugamedev.engine.math.Vector2f;

import java.awt.*;
import java.awt.event.*;

public class InputContext implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private static final int NUM_KEYS = 256;
    private final boolean[] keys     = new boolean[NUM_KEYS];
    private final boolean[] keysLast = new boolean[NUM_KEYS];

    private static final int NUM_BUTTONS = 5;
    private final boolean[] buttons     = new boolean[NUM_BUTTONS];
    private final boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private final Vector2f mousePosition = new Vector2f();
    private int scroll;

    public InputContext(Canvas canvas) {
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);

        canvas.setFocusable(true);
        canvas.requestFocus();
    }

    public void update() {
        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
    }

    // Keyboard Keys
    @Override public void keyPressed(KeyEvent e) { if(e.getKeyCode() <= keys.length) keys[e.getKeyCode()] = true; }
    @Override public void keyReleased(KeyEvent e) { if(e.getKeyCode() <= keys.length) keys[e.getKeyCode()] = false; }

    @Override public void mousePressed(MouseEvent e) { if(e.getButton() <= buttons.length) buttons[e.getButton()] = true; }
    @Override public void mouseReleased(MouseEvent e) { if(e.getButton() <= buttons.length) buttons[e.getButton()] = false; }
    @Override public void mouseWheelMoved(MouseWheelEvent e) { scroll = e.getWheelRotation(); }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    @Deprecated @Override public void keyTyped(KeyEvent keyEvent) {}
    @Deprecated @Override public void mouseClicked(MouseEvent e) {}
    @Deprecated @Override public void mouseEntered(MouseEvent e) {}
    @Deprecated @Override public void mouseExited(MouseEvent e) {}

    public Vector2f getMousePosition() { return this.mousePosition; }
    public int getScroll() { return this.scroll; }

    public boolean[] getKeys() { return this.keys; }
    public boolean[] getKeysLast() { return this.keysLast; }

    public boolean[] getButtons() { return this.buttons; }
    public boolean[] getButtonsLast() { return this.buttonsLast; }

}
