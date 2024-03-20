package com.mygdx.utils;

public final class Commons {
    /*
    *   window size constants
    */
    public static final int WINDOW_WIDTH = 1440;
    public static final int WINDOW_HEIGHT = 810;
    public  static final int WORLD_X_START = 10;
    public static final int WORLD_X_END = WINDOW_WIDTH-300;
    public static final int WORLD_Y_START = 10;
    public static final int WORLD_Y_END = WINDOW_HEIGHT-10;

    /*
    *   gameover height limit constant
    */
    public static final int GAMEOVER_LIMIT = 200;

    /*
    *   texture and animation paths constant
    */
    public static final String GAME_BACKGROUND_IMG_PATH = "game-background.png";
    public static final String UI_BACKGROUND_IMG_PATH = "ui-background.png";
    public static final String HEART_IMG_PATH = "heart.png";
    public static final String EARTH_HP_BAR = "earth-health-bar.png";
    public static final String HERO_IMG_PATH = "entities/hero.png";
    public static final String EARTH_IMG_PATH = "entities/earth.png";
    public static final String ENEMY_IMG_PATH = "entities/enemy.png";
    public static final String ADVANCED_ENEMY_IMG_PATH = "entities/advanced_enemy.png";
    public static final String BULLET_IMG_PATH = "entities/bullet.png";
    public static final String GAMEOVER_TITLE_IMG_PATH = "gameover/gameOverTitle.png";
    public static final String GAMEOVER_SKULL_IMG_PATH = "gameover/skull.png";
    public static final String MENU_INVADERS_IMG_PATH = "menu/invaders.png";

    /*
    *   Entities size
     */
    public static final int HERO_WIDTH = 30;
    public static final int HERO_HEIGHT = 30;
    public static final int BULLET_WIDTH = 8;
    public static final int BULLET_HEIGHT = 8;
    public static final int EARTH_WIDTH = WORLD_X_END-WORLD_X_START;
    public static final int EARTH_HEIGHT = 30;
    public static final int ENEMY_WIDTH = 64;
    public static final int ENEMY_HEIGHT = 64;


}
