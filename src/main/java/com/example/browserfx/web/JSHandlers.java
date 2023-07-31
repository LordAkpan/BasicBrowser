package com.example.browserfx.web;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.*;
import javafx.stage.Stage;

import javafx.util.Callback;

public class JSHandlers {
    //Handles window.alert() call by displaying a dialog

    public static void alertHandler(WebEvent<String> e) {
        //show alert in a new window
        Stage stage = new Stage();
        stage.setTitle("Alert");

        Label msg = new Label(e.getData());
        Button okBtn = new Button("OK");
        okBtn.setOnAction(e2 -> stage.close());

        VBox root = new VBox(20, msg, okBtn);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();

    }

    //Returns callback to handle window.prompt() call by displaying a dialog
    public static javafx.util.Callback<PromptData, String> getPromptHandler() {
        javafx.util.Callback<PromptData, String> handler = pData -> {
            //show a window to accept the user input
            Stage stage = new Stage();
            stage.setTitle("Prompt");

            Label msgLabel = new Label(pData.getMessage());
            TextField dataFld = new TextField();
            dataFld.setText(pData.getDefaultValue());
            Button okBtn = new Button("OK");
            okBtn.setOnAction(e -> stage.close());
            VBox root = new VBox(20, msgLabel, dataFld, okBtn);
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root);
            stage.showAndWait();

            String userData = dataFld.getText();
            return userData;
        };
        return handler;
    }

    //returns a callback handle to window.open() by displaying the popup in a separate window using
    //a separate WebEngine
    public static Callback<PopupFeatures, WebEngine> getPopupHandler() {
      Callback<PopupFeatures, WebEngine> handler = pFeatures -> {
          //show a popup in a new window
          Stage stage = new Stage();
          stage.setTitle("PopUp");
          WebView poupView = new WebView();
          VBox root = new VBox(poupView);
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
          return poupView.getEngine();
      };
      return handler;
    }

    // Returns a Callback to handle window.confirm() call by displaying a dialog
    public static Callback<String, Boolean> getConfirmHandler() {
        Callback<String, Boolean> handler = msg -> {
            // Show a popup in a new window
            Stage stage = new Stage();
            stage.setTitle("Confirm");
            Label msgLbl = new Label(msg);
            Button okBtn = new Button("OK");
            okBtn.setOnAction(e -> {
                okBtn.getProperties().put("userPressed", true);
                stage.close();
            });
            Button cancelBtn = new Button("Cancel");
            cancelBtn.setOnAction(e -> stage.close());
            HBox buttons = new HBox(20, okBtn, cancelBtn);
            buttons.setAlignment(Pos.CENTER);
            VBox root = new VBox(20, msgLbl, buttons);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            Boolean userSelection = (Boolean) okBtn.getProperties()
                    .get("userPressed");
            userSelection = (userSelection == null ? false : true);
            return userSelection;
        };
        return handler;
    }
}
