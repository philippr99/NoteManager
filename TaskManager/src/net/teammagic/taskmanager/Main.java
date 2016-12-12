package net.teammagic.taskmanager;

import javafx.application.Application;
import javafx.stage.Stage;
import net.teammagic.taskmanager.gui.WindowManager;
import net.teammagic.taskmanager.gui.login.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws Exception{
        WindowManager.initLoginWindow(loginStage);
    }


    public static void main(String[] args) { launch(args); }
}
