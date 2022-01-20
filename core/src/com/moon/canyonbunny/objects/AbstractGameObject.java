package com.moon.canyonbunny.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Chanmoey
 * @date 2022年01月20日
 */
public abstract class AbstractGameObject {

    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    public AbstractGameObject() {
        this.position = new Vector2();
        this.dimension = new Vector2(1, 1);
        this.origin = new Vector2();
        this.scale = new Vector2(1, 1);
        this.rotation = 0;
    }

    public void update(float deltaTime) {

    }

    /**
     * 渲染游戏对象
     * @param batch SpriteBatch
     */
    public abstract void render(SpriteBatch batch);
}
