package com.example.browserfx.web;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class BrowserHistory extends HBox {

    public BrowserHistory(WebView webView) {
        this.setSpacing(4);
        WebHistory history = webView.getEngine().getHistory();
        Button backBtn = new Button("Back");
        Button forwardBtn = new Button("Forward");
        backBtn.setDisable(true);
        forwardBtn.setDisable(true);

        //add action listener to the back and forward buttons
        backBtn.setOnAction(e -> history.go(-1));
        forwardBtn.setOnAction(e -> history.go(1));

        //add a change listener to the current index property
        history.currentIndexProperty().addListener((p, oldValue, newValue) -> {
            int currentIndex = newValue.intValue();
            if(currentIndex <= 0) {
                backBtn.setDisable(true);
            } else {
                backBtn.setDisable(false);
            }

            if (currentIndex >= history.getEntries().size()) {
                forwardBtn.setDisable(true);
            } else {
                forwardBtn.setDisable(false);
            }
        });

        //create the history list dropdown
        ComboBox<WebHistory.Entry> historyList = new ComboBox<>();
        historyList.setPrefWidth(150);
        historyList.setItems(history.getEntries());

        //set a cell factory to show only the page title in the history list
        historyList.setCellFactory(entry -> {
            ListCell<WebHistory.Entry> cell = new ListCell<WebHistory.Entry>(){
                @Override
                protected void updateItem(WebHistory.Entry item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        this.setText(null);
                        this.setGraphic(null);
                    } else {
                        String pageTitle = item.getTitle();
                        this.setText(pageTitle);
                    }
                }
            };
            return cell;
        });

        //Let the user navigate to a page using the history list
        historyList.setOnAction(e -> {
            int currentIndex = history.getCurrentIndex();
            WebHistory.Entry selectedEntry = historyList.getValue();
            int selectedIndex = historyList.getItems().indexOf(selectedEntry);
            int offset = selectedIndex - currentIndex;
            history.go(offset);
        });

        this.getChildren().addAll(backBtn, forwardBtn, new Label("History: "), historyList);
    }
}
