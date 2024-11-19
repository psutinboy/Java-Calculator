package com.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ScientificCalculator extends Application {

    private TextField display;
    private String currentNumber = "";
    private String operator = "";
    private double result = 0;

    @Override
    public void start(Stage primaryStage) {
        // Create the root node
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);

        // Set column constraints to make columns grow equally
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            column.setFillWidth(true);
            root.getColumnConstraints().add(column);
        }

        // Set row constraints to make rows grow equally
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            row.setFillHeight(true);
            root.getRowConstraints().add(row);
        }

        // Create text field for input/output
        display = new TextField();
        display.setEditable(false);
        display.setPrefHeight(50);
        display.setMaxWidth(Double.MAX_VALUE);
        display.setStyle("-fx-font-size: 20px;");
        display.setAlignment(Pos.CENTER_RIGHT);
        root.add(display, 0, 0, 4, 1);

        // Create and style number buttons
        for (int i = 1; i <= 9; i++) {
            Button button = new Button(String.valueOf(i));
            styleButton(button);
            root.add(button, (i - 1) % 3, (i - 1) / 3 + 1);
            button.setOnAction(e -> numberPressed(button.getText()));
        }

        // Add 0 button
        Button zeroButton = new Button("0");
        styleButton(zeroButton);
        root.add(zeroButton, 1, 4);
        zeroButton.setOnAction(e -> numberPressed("0"));

        // Create operator buttons
        String[] operators = {"+", "-", "*", "/"};
        for (int i = 0; i < operators.length; i++) {
            Button button = new Button(operators[i]);
            styleButton(button);
            root.add(button, 3, i + 1);
            button.setOnAction(e -> operatorPressed(button.getText()));
        }

        // Add equals button
        Button equalsButton = new Button("=");
        styleButton(equalsButton);
        root.add(equalsButton, 2, 4);
        equalsButton.setOnAction(e -> calculateResult());

        // Add clear button
        Button clearButton = new Button("C");
        styleButton(clearButton);
        root.add(clearButton, 0, 4);
        clearButton.setOnAction(e -> clear());

        // Create the scene with minimum size
        Scene scene = new Scene(root, 300, 400);

        // Make root grid pane expand to fill window
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Basic Calculator");
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setStyle("-fx-font-size: 18px;");
        GridPane.setFillWidth(button, true);
        GridPane.setFillHeight(button, true);
    }

    private void numberPressed(String number) {
        currentNumber += number;
        display.setText(currentNumber);
    }

    private void operatorPressed(String op) {
        if (!currentNumber.isEmpty()) {
            if (operator.isEmpty()) {
                result = Double.parseDouble(currentNumber);
            } else {
                calculateResult();
            }
            operator = op;
            currentNumber = "";
        }
    }

    private void calculateResult() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            switch (operator) {
                case "+":
                    result += secondNumber;
                    break;
                case "-":
                    result -= secondNumber;
                    break;
                case "*":
                    result *= secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result /= secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            currentNumber = "";
            operator = "";
        }
    }

    private void clear() {
        currentNumber = "";
        operator = "";
        result = 0;
        display.clear();
    }
}
