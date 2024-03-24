package com.mygdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAssetManager extends AssetManager {

    Animation<TextureRegion> baseEnemyAnimation;
    Animation <TextureRegion> advancedEnemyAnimation;
    private float animationTime = 0f;

    public MyAssetManager(){
        baseEnemyAnimation = new Animation<>(
                0.5f ,
                (new TextureAtlas(Gdx.files.internal("entities/enemySpritesheet.pack"))).findRegions("enemySprite"),
                Animation.PlayMode.LOOP);

        advancedEnemyAnimation = new Animation<>(
                0.5f,
                (new TextureAtlas(Gdx.files.internal("entities/advancedEnemySpritesheet.pack"))).findRegions("advancedEnemySprite"),
                Animation.PlayMode.LOOP
        );
    }

    public void updateAnimationTime(){
        animationTime += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getAnimationFrame(java.lang.String fileName){

        if(fileName.equals(Commons.ENEMY_IMG_PATH))
            return baseEnemyAnimation.getKeyFrame(animationTime);
        else if(fileName.equals(Commons.ADVANCED_ENEMY_IMG_PATH))
            return advancedEnemyAnimation.getKeyFrame(animationTime);
        else return null;

    }
}
