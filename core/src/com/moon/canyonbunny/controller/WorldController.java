package com.moon.canyonbunny.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.moon.canyonbunny.texture.Assets;
import com.moon.canyonbunny.util.CameraHelper;

/**
 * @author Chanmoey
 * @date 2022年01月18日
 */
public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public Sprite[] testSprites;
    public int selectedSprite;
    public CameraHelper cameraHelper;

    public WorldController() {
        this.init();
    }

    /**
     * 用于初始化WorldController类的实例对象。
     * 不在构造函数里面初始化对象，是因为有时候新建一个对象的时候，不需要给所有属性都赋值。
     * 提高效率。
     */
    private void init() {
        Gdx.input.setInputProcessor(this);
        this.cameraHelper = new CameraHelper();
        this.initTestObjects();
    }

    private void initTestObjects() {
        this.testSprites = new Sprite[5];

        // 创建一个TextureRegion列表
        Array<TextureRegion> regions = new com.badlogic.gdx.utils.Array<>();
        regions.add(Assets.INSTANCE.bunny.head);
        regions.add(Assets.INSTANCE.feather.feather);
        regions.add(Assets.INSTANCE.goldCoin.goldCoin);

        // 随机选取TextureRegion创建Sprite对象
        for (int i = 0; i < this.testSprites.length; i++) {
            Sprite sprite = new Sprite(regions.random());
            // 设置精灵在游戏世界中的尺寸为1X1
            sprite.setSize(1, 1);

            // 设置精灵对象的原点为中心。
            sprite.setOrigin(sprite.getWidth() / 2.0f, sprite.getHeight() / 2.0f);

            // 为精灵对象计算随机坐标
            float randomX = MathUtils.random(-2.0f, 2.0f);
            float randomY = MathUtils.random(-2.0f, 2.0f);
            sprite.setPosition(randomX, randomY);

            // 将精灵对象添加到数组中
            this.testSprites[i] = sprite;
        }

        this.selectedSprite = 0;
    }

    private Pixmap createProcedurePixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        pixmap.setColor(1, 1, 0, 1);
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height);
        pixmap.setColor(0, 1, 1, 1);
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;
    }

    private void updateTestObjects(float deltaTime) {
        float rotation = this.testSprites[this.selectedSprite].getRotation();
        rotation += 90 * deltaTime;
        rotation %= 360;
        this.testSprites[this.selectedSprite].setRotation(rotation);
    }

    private void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }

        // 控制精灵移动
        float spriteMoveSpeed = 5 * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveSelectedSprite(-spriteMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveSelectedSprite(spriteMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveSelectedSprite(0, spriteMoveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveSelectedSprite(0, -spriteMoveSpeed);
        }

        // 控制相机移动
        float cameraMoveSpeed = 5 * deltaTime;
        float cameraMoveSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            cameraMoveSpeed *= cameraMoveSpeedAccelerationFactor;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveCamera(-cameraMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveCamera(cameraMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveCamera(0, cameraMoveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveCamera(0, -cameraMoveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            this.cameraHelper.setPosition(0, 0);
        }

        // 控制相机缩放
        float cameraZoomSpeed = 1 * deltaTime;
        float cameraZoomSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            cameraZoomSpeed *= cameraZoomSpeedAccelerationFactor;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            this.cameraHelper.addZoom(cameraZoomSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            this.cameraHelper.addZoom(-cameraZoomSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SLASH)) {
            this.cameraHelper.setZoom(1);
        }
    }

    private void moveSelectedSprite(float x, float y) {
        this.testSprites[this.selectedSprite].translate(x, y);
    }

    private void moveCamera(float x, float y) {
        x += this.cameraHelper.getPosition().x;
        y += this.cameraHelper.getPosition().y;
        this.cameraHelper.setPosition(x, y);
    }

    /**
     * 调用次数于帧率有关
     *
     * @param deltaTime 上一次渲染完成的帧到现在的增量时间
     */
    public void update(float deltaTime) {
        this.handleDebugInput(deltaTime);
        this.updateTestObjects(deltaTime);
        this.cameraHelper.update(deltaTime);
    }

    @Override
    public boolean keyUp(int keycode) {
        // 重置游戏世界
        if (keycode == Input.Keys.R) {
            this.init();
            Gdx.app.debug(TAG, "Game world reset");
        }
        // 选择下一个精灵
        else if (keycode == Input.Keys.SPACE) {
            this.selectedSprite = (this.selectedSprite + 1) % this.testSprites.length;
            if (this.cameraHelper.hasTarget()) {
                this.cameraHelper.setTarget(this.testSprites[this.selectedSprite]);
            }
            Gdx.app.debug(TAG, "Sprite #" + this.selectedSprite + "selected");
        }
        // 相机跟踪开关
        else if (keycode == Input.Keys.ENTER) {
            this.cameraHelper.setTarget(this.cameraHelper.hasTarget() ? null : this.testSprites[this.selectedSprite]);
            Gdx.app.debug(TAG, "Camera follow enabled: " + this.cameraHelper.hasTarget());
        }
        return false;
    }
}
