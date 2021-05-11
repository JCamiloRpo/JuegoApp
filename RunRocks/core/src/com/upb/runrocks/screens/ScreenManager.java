package com.upb.runrocks.screens;

import com.upb.runrocks.RunRocks;
import java.util.Stack;

public class ScreenManager {

    private Stack<com.upb.runrocks.screens.BaseScreen> screens;
    private RunRocks game;

    public ScreenManager(RunRocks game){
        this.game = game;
        screens = new Stack<>();
    }

    private void push(com.upb.runrocks.screens.BaseScreen screen){
        game.setScreen(screen);
        screens.push(screen);
    }

    private void pop(){ screens.pop().dispose(); }

    public void set(BaseScreen screen){
        if(!screens.empty()) pop();
        push(screen);
    }

    public void dispose(){
        while (!screens.empty())
            screens.pop().dispose();
    }

    public SplashScreen newSplash(){ return new SplashScreen(game); }
    public MenuScreen newMenu(){ return new MenuScreen(game); }
    public SettingScreen newSetting(){ return new SettingScreen(game); }
    public InfoScreen newInfo(){ return new InfoScreen(game); }
    public GameScreen newGame(){ return new GameScreen(game); }
    public GameOverScreen newGameOver(){ return new GameOverScreen(game); }
}
