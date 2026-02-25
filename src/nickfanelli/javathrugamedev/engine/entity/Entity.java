package nickfanelli.javathrugamedev.engine.entity;

import nickfanelli.javathrugamedev.engine.math.Transform;
import nickfanelli.javathrugamedev.engine.math.Vector2f;

import java.awt.*;

public abstract class Entity {

    public String name;
    public Transform transform;
    public int zIndex;

    public boolean isEnabled = true;

    public Entity() {
        this("Unidentified Entity");
    }

    public Entity(String name) {
        this(name, new Transform());
    }

    public Entity(String name, Transform transform) {
        this(name, transform, 0);
    }

    public Entity(String name, Transform transform, int zIndex) {
        this.name = name;
        this.transform = transform;
        this.zIndex = zIndex;
    }

    public void onUpdate(float deltaTime) {}
    public void onRender(Graphics2D g) {}

}
