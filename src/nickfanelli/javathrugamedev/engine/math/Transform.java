package nickfanelli.javathrugamedev.engine.math;

public class Transform {

    public Vector2f position;
    public Vector2f scale;
    public float rotation;

    public Transform() {
        this(new Vector2f(), new Vector2f());
    }

    public Transform(Transform transform) {
        this(transform.position.copy(), transform.scale.copy());
    }

    public Transform(Vector2f position) {
        this(position, new Vector2f());
    }

    public Transform(Vector2f position, Vector2f scale) {
        this(position, scale, 0.0f);
    }

    public Transform(Vector2f position, Vector2f scale, float rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Transform copy() { return new Transform(this); }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Transform transform)) {
            System.err.println("[Harmony Engine]: Could not use .equals with " + obj + " and Transform");
            return false;
        }

        return transform.position.equals(this.position) && transform.scale.equals(this.scale) && transform.rotation == this.rotation;
    }

    @Override
    public String toString() {
        return String.format("[Transform: %s, %s, %s]", this.position.toString(), this.scale.toString(), this.rotation);
    }

}
