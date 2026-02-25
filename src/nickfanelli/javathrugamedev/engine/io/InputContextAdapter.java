package nickfanelli.javathrugamedev.engine.io;

import nickfanelli.javathrugamedev.engine.math.Vector2f;

public class InputContextAdapter {

    private final InputContext inputContext;

    public InputContextAdapter(InputContext inputContext) {
        this.inputContext = inputContext;
    }

    public boolean isKey(int keycode) { return this.inputContext.getKeys()[keycode]; }
    public boolean isKeyUp(int keycode) { return !this.inputContext.getKeys()[keycode] && this.inputContext.getKeysLast()[keycode]; }
    public boolean isKeyDown(int keycode) { return this.inputContext.getKeys()[keycode] && !this.inputContext.getKeysLast()[keycode]; }

    public boolean isMouseButton(int button) { return this.inputContext.getButtons()[button]; }
    public boolean isMouseButtonUp(int button) { return !this.inputContext.getButtons()[button] && this.inputContext.getButtonsLast()[button]; }
    public boolean isMouseButtonDown(int button) { return this.inputContext.getButtons()[button] && !this.inputContext.getButtonsLast()[button]; }

    public Vector2f getMousePosition() { return this.inputContext.getMousePosition(); }
    public int getScroll() { return this.inputContext.getScroll(); }

}
