package com.example.tictactoe_prj1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.control.Button;


public class TicTacToe extends Application {

    private int playerXscore  = 0, playerOscore = 0;
    private boolean PlayerXturn = true;
    private Button buttons[][] = new Button[3][3];
    private Label PlayerXscorelabel,PlayerOscorelabel;


    private BorderPane createContent(){
        BorderPane root = new BorderPane();

        // tilte
        Label Titlelabel = new Label("Tic Tac Toe");
        BorderPane.setAlignment(Titlelabel,Pos.CENTER);
        Titlelabel.setStyle("-fx-font-size:40pt;-fx-font-weight:bold;");
        root.setTop(Titlelabel);
        root.setPadding(new Insets(20));
        //board
        GridPane Board = new GridPane();
        Board.setHgap(10);
        Board.setVgap(10);
        Board.setAlignment(Pos.CENTER);
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(150,150);
                button.setStyle("-fx-font-size:16pt;-fx-font-weight:bold;");
                button.setOnAction(event->checkButtons(button));
                buttons[i][j] = button;
                Board.add(button,j,i);
            }
        }
        root.setCenter(Board);

        //score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setStyle("-fx-font-size:20pt;-fx-font-weight:bold;");
        scoreBoard.setAlignment(Pos.CENTER);
        PlayerXscorelabel = new Label("player X : 0");
        PlayerOscorelabel = new Label("player O : 0");
        scoreBoard.getChildren().addAll(PlayerXscorelabel, PlayerOscorelabel);

        root.setBottom(scoreBoard);

        return root;
    }

    private void checkButtons(Button button){
       if(button.getText().equals("")){ // when a box to be choosen only once
           if(PlayerXturn){
               button.setText("X");
           }else {
               button.setText("O");
           }
           PlayerXturn = !PlayerXturn;

           checkWinner();
       }
            return;
    }

    private void checkWinner(){
        //row
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
            &&  buttons[row][1].getText().equals(buttons[row][2].getText())
            && !buttons[row][0].getText().isEmpty()){
                String Winner = buttons[row][0].getText();
                WinnerBox(Winner);
                Updatescoare(Winner);
                reSet();
                return;
            }
        }
        // column
        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    &&  buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()){
                String Winner = buttons[0][col].getText();
                WinnerBox(Winner);
                Updatescoare(Winner);
                reSet();
                return;
            }
        }
        // diagonal for left
        for (int dig = 0; dig < 3; dig++) {
            if(buttons[0][0].getText().equals(buttons[1][1].getText())
                    &&  buttons[1][1].getText().equals (buttons[2][2].getText())
                    && !buttons[0][0].getText().isEmpty())
            {
                String Winner = buttons[0][0].getText();
                WinnerBox(Winner);
                Updatescoare(Winner);
                reSet();
                return;
            }
        }
        // diagonal for right
        for (int dig = 0; dig < 3; dig++) {
            if (buttons[0][2].getText().equals(buttons[1][1].getText())
                    && buttons[1][1].getText().equals(buttons[2][0].getText())
                    && !buttons[0][2].getText().isEmpty())
            {
                String Winner = buttons[0][2].getText();
                WinnerBox(Winner);
                Updatescoare(Winner);
                reSet();
                return;
            }
        }

        //fot tie
        boolean tie = true;
            for(Button row[] : buttons ){
                for(Button button : row){
                    if(button.getText().isEmpty()){
                        tie = false;
                        break;
                    }
                }
            }
        if(tie){
            TieBox();
            reSet();
        }
    }


    private void WinnerBox(String Winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulatins. " + Winner +" you defeated the other player");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void TieBox(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME IS TIE");
        alert.setContentText(" IT's A TIE_____    PLAY ONE MORE GAME TO GET A WINNER.");

        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void Updatescoare(String winner){
        if(winner.equals("X")){
            playerXscore++;
            PlayerXscorelabel.setText("Player X :" + playerXscore);
        }else{
            playerOscore++;
            PlayerOscorelabel.setText("Player O :" + playerOscore);
        }
    }

    private void reSet(){
        for(Button row[] : buttons ){
            for(Button button : row){
                button.setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}