package com.moon.canyonbunny.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.moon.canyonbunny.util.Constans;

/**
 * @author Chanmoey
 * @date 2022年01月19日
 */
public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();

    public static final Assets INSTANCE = new Assets();

    private AssetManager assetManager;

    /**
     * 内部资源类
     */
    public AssetBunny bunny;
    public AssetRock rock;
    public AssetGoldCoin goldCoin;
    public AssetFeather feather;
    public AssetLevelDecoration levelDecoration;

    private Assets() {

    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;

        // 设置资源管理器的错误处理对象
        this.assetManager.setErrorListener(this);

        // 预加载纹理集资源
        this.assetManager.load(Constans.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);

        // 开始加载资源
        this.assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded:" + this.assetManager.getAssetNames().size);
        for (String a : this.assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "asset: " + a);
        }

        TextureAtlas atlas = this.assetManager.get(Constans.TEXTURE_ATLAS_OBJECTS);


        //  激活平滑纹理过滤
        for (Texture t : atlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        this.bunny = new AssetBunny(atlas);
        this.rock = new AssetRock(atlas);
        this.goldCoin = new AssetGoldCoin(atlas);
        this.feather = new AssetFeather(atlas);
        this.levelDecoration = new AssetLevelDecoration(atlas);
    }

    @Override
    public void dispose() {
        this.assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor assetDescriptor, Throwable throwable) {
        Gdx.app.debug(TAG, "Couldn't load asset " + assetDescriptor.fileName + "'" + throwable);
    }

    public class AssetBunny {
        public final TextureAtlas.AtlasRegion head;

        public AssetBunny(TextureAtlas atlas) {
            this.head = atlas.findRegion("bunny_head");
        }
    }

    public class AssetRock {
        public final TextureAtlas.AtlasRegion edge;
        public final TextureAtlas.AtlasRegion middle;

        public AssetRock(TextureAtlas atlas) {
            this.edge = atlas.findRegion("rock_edge");
            this.middle = atlas.findRegion("rock_middle");
        }
    }

    public class AssetGoldCoin {
        public final TextureAtlas.AtlasRegion goldCoin;

        public AssetGoldCoin(TextureAtlas atlas) {
            this.goldCoin = atlas.findRegion("item_gold_coin");
        }
    }

    public class AssetFeather {
        public final TextureAtlas.AtlasRegion feather;

        public AssetFeather(TextureAtlas atlas) {
            this.feather = atlas.findRegion("item_feather");
        }
    }

    public class AssetLevelDecoration {
        public final TextureAtlas.AtlasRegion cloud01;
        public final TextureAtlas.AtlasRegion cloud02;
        public final TextureAtlas.AtlasRegion cloud03;
        public final TextureAtlas.AtlasRegion mountainLeft;
        public final TextureAtlas.AtlasRegion mountainRight;
        public final TextureAtlas.AtlasRegion waterOverlay;

        public AssetLevelDecoration(TextureAtlas atlas) {
            this.cloud01 = atlas.findRegion("cloud01");
            this.cloud02 = atlas.findRegion("cloud02");
            this.cloud03 = atlas.findRegion("cloud03");
            this.mountainLeft = atlas.findRegion("mountain_left");
            this.mountainRight = atlas.findRegion("mountain_right");
            this.waterOverlay = atlas.findRegion("water_overlay");
        }
    }
}
