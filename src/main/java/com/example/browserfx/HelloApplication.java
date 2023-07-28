package com.example.browserfx;

import com.example.browserfx.web.NavigationBar;
import com.example.browserfx.web.WebOptionsMenu;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        WebView webView = new WebView();

        //update the stage title when the new web page title is available
        webView.getEngine().titleProperty().addListener(
                (ObservableValue<? extends String> p, String oldTitle, String newTitle)
                        -> {stage.setTitle(newTitle);});

        //load the google home page
        String homePageUrl = "https://www.google.com";

        MenuButton options = new WebOptionsMenu(webView);
        NavigationBar navigationBar = new NavigationBar(webView, homePageUrl, true);
        navigationBar.getChildren().add(options);

        VBox root = new VBox(navigationBar, webView);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}