package com.moon.canyonbunny.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Chanmoey
 * @date 2022年01月19日
 */
public class CameraHelper {
    private static final String TAG = CameraHelper.class.getName();

    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;

    private Vector2 position;
    private float zoom;
    private Sprite target;

    public CameraHelper() {
        this.position = new Vector2();
        this.zoom = 1.0f;
    }

    public void update(float deltaTime) {
        if (!this.hasTarget()) {
            return;
        }

        this.position.x = this.target.getX() + this.target.getOriginX();
        this.position.y = this.target.getY() + this.target.getOriginY();
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void addZoom(float amount) {
        this.setZoom(this.zoom + amount);
    }

    public void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public float getZoom() {
        return this.zoom;
    }

    public void setTarget(Sprite target) {
        this.target = target;
    }

    public Sprite getTarget() {
        return this.target;
    }

    public boolean hasTarget() {
        return this.target != null;
    }

    public boolean hasTarget(Sprite target) {
        return this.hasTarget() && this.target.equals(target);
    }

    public void applyTo(OrthographicCamera camera) {
        camera.position.x = this.position.x;
        camera.position.y = this.position.y;
        camera.zoom = zoom;
        camera.update();
    }
}
