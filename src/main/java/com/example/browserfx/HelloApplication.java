package com.example.browserfx;

import com.example.browserfx.web.BrowserHistory;
import com.example.browserfx.web.BrowserPane;
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
import java.net.URL;

public class HelloApplication extends Application {
    private final String DEFAULT_HOME_PAGE = "static/testjavascripthandlers.html";

    @Override
    public void start(Stage stage) {
        String homePageUrl = getDefaultHomePageUrl();
        BrowserPane root = new BrowserPane(homePageUrl, stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  //  @Override
//    public void start(Stage stage) throws IOException {
//        WebView webView = new WebView();
//
//        //update the stage title when the new web page title is available
//        webView.getEngine().titleProperty().addListener(
//                (ObservableValue<? extends String> p, String oldTitle, String newTitle)
//                        -> {stage.setTitle(newTitle);});
//
//        //load the google home page
//        String homePageUrl = "https://www.duckduckgo.com";
//
//        MenuButton options = new WebOptionsMenu(webView);
//        BrowserHistory historyComponent = new BrowserHistory(webView);
//        NavigationBar navigationBar = new NavigationBar(webView, homePageUrl, true);
//        navigationBar.getChildren().addAll(options, historyComponent);
//
//        VBox root = new VBox(navigationBar, webView);
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
        launch(args);
    }

    public String getDefaultHomePageUrl() {
        String pageUrl = "http://www.google.com";
        URL url = this.getClass().getClassLoader()
                .getResource(DEFAULT_HOME_PAGE);
        if (url == null) {
            System.out.println(
                    "Could not find " + DEFAULT_HOME_PAGE + " in CLASSPATH. " +
                            "Using " + pageUrl + " as the default home page.");

        } else {
            pageUrl = url.toExternalForm();
        }

        return pageUrl;
    }
}