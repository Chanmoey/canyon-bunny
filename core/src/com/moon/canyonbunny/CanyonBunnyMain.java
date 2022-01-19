package com.moon.canyonbunny;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.moon.canyonbunny.controller.WorldController;
import com.moon.canyonbunny.renderer.WorldRenderer;

/**
 * 游戏的主类。
 *
 * @author Chanmoey
 * @date 2022年01月18日
 */
public class CanyonBunnyMain implements ApplicationListener {

    /**
     * 暂停标记，安卓设备才具有的状态
     */
    private boolean paused;

    /**
     * 该类标签，用于输出日志。
     */
    private static final String TAG = CanyonBunnyMain.class.getName();

    /**
     * 负责掌控游戏世界的逻辑，类似于其他引擎的导演角色。
     */
    private WorldController worldController;

    /**
     * 负责渲染游戏世界的资源。
     */
    private WorldRenderer worldRenderer;

    @Override
    public void create() {
        // 设置日志级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // 初始化控制器和渲染器
        this.worldController = new WorldController();
        this.worldRenderer = new WorldRenderer(this.worldController);

        // 启动时默认激活游戏，安卓设备才考虑。
        this.paused = false;
    }

    @Override
    public void resize(int width, int height) {
        this.worldRenderer.resize(width, height);
    }

    @Override
    public void render() {

        // 只有非暂停状态，才需要更新游戏逻辑。
        if (!this.paused) {
            // 根据最后一帧的增量时间更新游戏世界
            this.worldController.update(Gdx.graphics.getDeltaTime());
        }

        // 设置清屏颜色为浅蓝色
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);

        // 清屏
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 将游戏世界渲染到屏幕上。渲染一定要在更新之后。
        this.worldRenderer.render();
    }

    @Override
    public void pause() {
        this.paused = true;
    }

    @Override
    public void resume() {
        this.paused = false;
    }

    @Override
    public void dispose() {
        this.worldRenderer.dispose();
    }
}
