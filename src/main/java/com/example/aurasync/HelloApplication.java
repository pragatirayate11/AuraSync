package com.example.aurasync;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        // Title
        Text title = new Text("✨ AuraSync ✨");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 36));
        title.setFill(Color.web("#e0b4ff"));

        Text subtitle = new Text("discover your aura");
        subtitle.setFont(Font.font("Georgia", FontPosture.ITALIC, 16));
        subtitle.setFill(Color.web("#c9a0dc"));

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("your name...");
        nameField.setMaxWidth(300);
        styleField(nameField);

        TextField dobField = new TextField();
        dobField.setPromptText("date of birth (DD/MM/YYYY)...");
        dobField.setMaxWidth(300);
        styleField(dobField);

        TextField moodField = new TextField();
        moodField.setPromptText("your current mood...");
        moodField.setMaxWidth(300);
        styleField(moodField);

        // Button
        Button generateBtn = new Button("reveal my aura ✨");
        generateBtn.setStyle(
                "-fx-background-color: #7b2fff;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10 25;" +
                        "-fx-background-radius: 20;" +
                        "-fx-cursor: hand;"
        );

        // Result area
        VBox resultBox = new VBox(10);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setVisible(false);

        Text auraColor = new Text();
        auraColor.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        auraColor.setFill(Color.web("#e0b4ff"));

        Text energyLevel = new Text();
        energyLevel.setFont(Font.font("Georgia", 16));
        energyLevel.setFill(Color.web("#c9f0ff"));

        Text luckyElement = new Text();
        luckyElement.setFont(Font.font("Georgia", 16));
        luckyElement.setFill(Color.web("#c9ffd8"));

        Text poeticLine = new Text();
        poeticLine.setFont(Font.font("Georgia", FontPosture.ITALIC, 14));
        poeticLine.setFill(Color.web("#fff0c9"));
        poeticLine.setWrappingWidth(350);
        poeticLine.setTextAlignment(TextAlignment.CENTER);

        resultBox.getChildren().addAll(auraColor, energyLevel, luckyElement, poeticLine);

        // Button action
        generateBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String dob = dobField.getText().trim();
            String mood = moodField.getText().trim().toLowerCase();

            if (name.isEmpty() || dob.isEmpty() || mood.isEmpty()) {
                auraColor.setText("please fill all fields! 🌸");
                resultBox.setVisible(true);
                return;
            }

            String[] aura = generateAura(dob, mood);
            auraColor.setText("🎨 aura color: " + aura[0]);
            energyLevel.setText("⚡ energy: " + aura[1]);
            luckyElement.setText("🌿 element: " + aura[2]);
            poeticLine.setText("✨ " + aura[3]);
            resultBox.setVisible(true);
        });

        // Layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #0d0d1a; -fx-padding: 40;");
        root.getChildren().addAll(title, subtitle, nameField, dobField, moodField, generateBtn, resultBox);

        Scene scene = new Scene(root, 500, 600);
        stage.setTitle("AuraSync ✨");
        stage.setScene(scene);
        stage.show();
    }

    private void styleField(TextField field) {
        field.setStyle(
                "-fx-background-color: #1a1a2e;" +
                        "-fx-text-fill: #e0b4ff;" +
                        "-fx-prompt-text-fill: #7a7a9a;" +
                        "-fx-border-color: #7b2fff;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 8 15;" +
                        "-fx-font-size: 13px;"
        );
    }

    private String[] generateAura(String dob, String mood) {
        String[] colors = {"Sky Blue", "Rose Gold", "Violet", "Sage Green", "Golden", "Pearl White", "Midnight Purple"};
        String[] energies = {"Radiant", "Calm", "Electric", "Grounded", "Charged"};
        String[] elements = {"Air 🌬️", "Water 🌊", "Fire 🔥", "Earth 🌿", "Ether ✨"};
        String[] lines = {
                "she moves like the wind — quietly powerful, endlessly free.",
                "her stillness holds the depth of a thousand oceans.",
                "she burns bright — a flame that lights every room she enters.",
                "rooted like mountains, she holds the world together.",
                "she exists between worlds — magic made human."
        };

        int dobSum = 0;
        for (char c : dob.replaceAll("[^0-9]", "").toCharArray()) dobSum += (c - '0');

        int colorIndex = dobSum % colors.length;
        int energyIndex = (dobSum + mood.length()) % energies.length;
        int elementIndex = mood.length() % elements.length;
        int lineIndex = elementIndex;

        return new String[]{colors[colorIndex], energies[energyIndex], elements[elementIndex], lines[lineIndex]};
    }

    public static void main(String[] args) {
        launch(args);
    }
}