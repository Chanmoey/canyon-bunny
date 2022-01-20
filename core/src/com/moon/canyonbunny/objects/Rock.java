package com.moon.canyonbunny.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.moon.canyonbunny.texture.Assets;

/**
 * @author Chanmoey
 * @date 2022年01月20日
 */
public class Rock extends AbstractGameObject {

    private TextureRegion regEdge;
    private TextureRegion regMiddle;

    private int length;

    public Rock() {
        this.init();
    }

    private void init() {
        this.dimension.set(1, 1.5f);

        this.regEdge = Assets.INSTANCE.rock.edge;
        this.regMiddle = Assets.INSTANCE.rock.middle;

        // 设置 rock 的初始长度
        this.setLength(1);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void increaseLength(int amount) {
        this.setLength(this.length + amount);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
