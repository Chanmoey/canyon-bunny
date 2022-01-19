package com.moon.canyonbunny.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.moon.canyonbunny.controller.WorldController;
import com.moon.canyonbunny.util.Constans;

/**
 * @author Chanmoey
 * @date 2022年01月18日
 */
public class WorldRenderer implements Disposable {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        this.init();
    }

    private void init() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera(Constans.VIEWPORT_WIDTH, Constans.VIEWPORT_HEIGHT);
        this.camera.position.set(0, 0, 0);
        this.camera.update();

    }

    public void render() {
        this.renderTestObjects();
    }

    private void renderTestObjects() {
        this.worldController.cameraHelper.applyTo(camera);
        this.batch.setProjectionMatrix(this.camera.combined);

        // 开始渲染
        this.batch.begin();
        for (Sprite sprite : this.worldController.testSprites) {
            sprite.draw(this.batch);
        }

        // 结束渲染
        this.batch.end();
    }

    public void resize(int width, int height) {
        this.camera.viewportWidth = (Constans.VIEWPORT_HEIGHT / height) * width;
        this.camera.update();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }
}
