package com.upb.runrocks.screens;

import com.upb.runrocks.RunRocks;
import java.util.Stack;

/**
 * Administrados de los Screen
 */
public class ScreenManager {

    private Stack<BaseScreen> screens; // Pila de los screens
    private RunRocks game;

    /**
     * @param game clase principal del juego
     */
    public ScreenManager(RunRocks game){
        this.game = game;
        screens = new Stack<>();
    }

    /**
     * Agregar un screen y se pone
     * @param screen nuevo screen en la pila
     */
    public void push(BaseScreen screen){
        game.setScreen(screen);
        screens.push(screen);
    }

    /**
     * Eliminar un screen
     * @param set si se podran el anterior o no
     */
    public void pop(boolean set){
        screens.pop().dispose();
        if(set) game.setScreen(screens.peek());
    }

    /**
     * Agrega un screen y elimina el actual
     * @param screen
     */
    public void set(BaseScreen screen){
        if(!screens.empty()) pop(false);
        push(screen);
    }

    /**
     * Elimina los screen de la memoria
     */
    public void dispose(){
        while (!screens.empty())
            screens.pop().dispose();
    }

    // Metodos para crear nuevos Screens
    public SplashScreen newSplash(){ return new SplashScreen(game); }
    public MenuScreen newMenu(){ return new MenuScreen(game); }
    public SettingScreen newSetting(){ return new SettingScreen(game); }
    public InfoScreen newInfo(){ return new InfoScreen(game); }
    public GameScreen newGame(){ return new GameScreen(game); }
    public GameOverScreen newGameOver(){ return new GameOverScreen(game); }
}
