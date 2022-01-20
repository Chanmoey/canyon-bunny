package com.moon.canyonbunny.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.moon.canyonbunny.CanyonBunnyMain;

/**
 * @author Chen
 */
public class DesktopLauncher {

    private static final String GAME_NAME = "Canyon Bunny";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static boolean rebuildAtlas = true;
    private static boolean drawDebugOutLine = false;

    public static void main(String[] arg) {

        if (rebuildAtlas) {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.debug = drawDebugOutLine;
            settings.duplicatePadding = true;

            // output参数的路径，是以desktop作为根目录开始的。
            // 上面是书上说的，实际上我这里是以android项目下的assets为根目录，然后必须通过gradle的run来运行程序。
            TexturePacker.process(settings, "assets-raw/images",
                    "images",
                    "canyonbunny");
        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = GAME_NAME;
        config.width = WIDTH;
        config.height = HEIGHT;
        new LwjglApplication(new CanyonBunnyMain(), config);
    }
}
