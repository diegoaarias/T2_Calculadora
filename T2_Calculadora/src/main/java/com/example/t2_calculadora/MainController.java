package com.example.t2_calculadora;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable,EventHandler<ActionEvent> {

        @FXML
        private Button boton3;

        @FXML
        private Button boton2;

        @FXML
        private Button boton5;

        @FXML
        private Button boton4;

        @FXML
        private GridPane cientifica;

        @FXML
        private Button boton1;

        @FXML
        private Button boton0;

        @FXML
        private Button botonCos;

        @FXML
        private Button botonResta;

        @FXML
        private Button botonTan;

        @FXML
        private Button botonDivision;

        @FXML
        private Button botonSin;

        @FXML
        private Button boton7;

        @FXML
        private TextField textoCalculadora;

        @FXML
        private Button boton6;

        @FXML
        private Button boton9;

        @FXML
        private Button boton8;

        @FXML
        private Button botonCientifica;

        @FXML
        private Button botonMultiplicacion;

        @FXML
        private Button botonBorrar;

        @FXML
        private GridPane gridpane;

        @FXML
        private TextArea registro;

        @FXML
        private BorderPane borderGeneral;

        @FXML
        private Button botonIgual;

        @FXML
        private Button botonLog;

        @FXML
        private Button botonSuma;

        @FXML
        private HBox grupoBotones;

        @FXML
        private VBox parteSuperior;

        @FXML
        private Button botonRegistro;

        private Double num1 = 0.0;
        private String operacion = "";
        private String funcion = "";



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                acciones();
                borderGeneral.setLeft(null);
                borderGeneral.setRight(null);

                boton1.setOnAction(e -> appendNumero("1"));
                boton2.setOnAction(e -> appendNumero("2"));
                boton3.setOnAction(e -> appendNumero("3"));
                boton4.setOnAction(e -> appendNumero("4"));
                boton5.setOnAction(e -> appendNumero("5"));
                boton6.setOnAction(e -> appendNumero("6"));
                boton7.setOnAction(e -> appendNumero("7"));
                boton8.setOnAction(e -> appendNumero("8"));
                boton9.setOnAction(e -> appendNumero("9"));
                boton0.setOnAction(e -> appendNumero("0"));

                botonSuma.setOnAction(e -> handleOperacion("+"));
                botonResta.setOnAction(e -> handleOperacion("-"));
                botonMultiplicacion.setOnAction(e -> handleOperacion("*"));
                botonDivision.setOnAction(e -> handleOperacion("/"));
                botonIgual.setOnAction(e -> handleOperacion("="));

        }

        private void acciones() {
                botonCientifica.setOnAction(this);
                botonRegistro.setOnAction(this);
                botonBorrar.setOnAction(this);

                botonSin.setOnAction(this::handleFuncion);
                botonCos.setOnAction(this::handleFuncion);
                botonTan.setOnAction(this::handleFuncion);
                botonLog.setOnAction(this::handleFuncion);
        }

        @Override
        public void handle(ActionEvent actionEvent) {
                if (actionEvent.getSource() == botonCientifica)
                        if (borderGeneral.getLeft() == cientifica) {
                                borderGeneral.setLeft(null);
                        } else {
                                borderGeneral.setLeft(cientifica);
                        }
                else if (actionEvent.getSource() == botonRegistro) {
                        if (borderGeneral.getRight() == registro) {
                                borderGeneral.setRight(null);

                        } else {
                                registro.setEditable(false);
                                borderGeneral.setRight(registro);
                        }
                }else if (actionEvent.getSource() == botonBorrar) {
                        textoCalculadora.clear();

                }
        }

        private void handleOperacion(String op) {
                if (!operacion.isEmpty() || op.equals("=")) {
                        double num2 = Double.parseDouble(textoCalculadora.getText());
                        double resultado = 0;

                        switch (operacion) {
                                case "+":
                                        resultado = num1 + num2;
                                        break;
                                case "-":
                                        resultado = num1 - num2;
                                        break;
                                case "*":
                                        resultado = num1 * num2;
                                        break;
                                case "/":
                                        if (num2 != 0) {
                                                resultado = num1 / num2;
                                        } else {
                                                textoCalculadora.setText("Error");
                                                return;
                                        }
                                        break;
                        }

                        textoCalculadora.setText(String.valueOf(resultado));
                        registro.appendText(num1 + " " + operacion + " " + num2 + " = " + resultado + "\n");
                        operacion = "";
                        num1 = resultado;
                } else {
                        num1 = Double.parseDouble(textoCalculadora.getText());
                        operacion = op;
                        textoCalculadora.setText("");
                }
        }

        @FXML
        public void handleFuncion(ActionEvent event) {
                Button button = (Button) event.getSource();
                String btnText = button.getText();

                double num = Double.parseDouble(textoCalculadora.getText());
                double resultado = 0;

                switch (btnText) {
                        case "log":
                                if (num > 0) {
                                        resultado = Math.log10(num);
                                } else {
                                        textoCalculadora.setText("Error");
                                        return;
                                }
                                break;
                        case "sin":
                                resultado = Math.sin(Math.toRadians(num));
                                break;
                        case "cos":
                                resultado = Math.cos(Math.toRadians(num));
                                break;
                        case "tan":
                                if (Math.cos(Math.toRadians(num)) != 0) {
                                        resultado = Math.tan(Math.toRadians(num));
                                } else {
                                        textoCalculadora.setText("Error");
                                        return;
                                }
                                break;
                }

                textoCalculadora.setText(String.valueOf(resultado));
                registro.appendText(btnText + "(" + num + ")" + " = " + resultado + "\n");
        }
        private void appendNumero(String number) {
                String texto = textoCalculadora.getText();
                String textoNuevo = texto + number;
                textoCalculadora.setText(textoNuevo);
        }
}


