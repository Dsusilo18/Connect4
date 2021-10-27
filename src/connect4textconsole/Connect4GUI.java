/**
 * Display GUI and control user input and button action events.
 *
 * Completion Time: 7
 *
 * @author Susilo
 * @version 04/04/2021
 */
package connect4textconsole;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Connect4GUI extends Application {

    // Declare class variables.
    static boolean againstComp = false;
    static int turn = 1;
    static char player = 'X';
    static int play = 0;
    static int play2 = 0;
    static char[][] grid = new char[6][7];

    public void start(Stage stage) {

        // Declare new object.
        Connect4 c4 = new Connect4();

        // Create new VBox pane used to contain buttons.
        VBox vBox = new VBox();
        Button btnPVP = new Button("Player VS Player");
        btnPVP.setOnAction(e -> {
            stage.close();
            StartG(c4);
        });

        Button btnPVC = new Button("Player VS Computer");
        btnPVC.setOnAction(e -> {
            againstComp = true;
            stage.close();
            StartG(c4);
        });
        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> Platform.exit());

        btnPVP.setMinWidth(250);
        btnPVC.setMinWidth(250);
        btnExit.setMinWidth(250);

        vBox.getChildren().addAll(btnPVP, btnPVC, btnExit);

        // Create the scene that contains the pane.
        Scene scene = new Scene(vBox);

        // Create stage that contains the scene.
        stage.setScene(scene);
        stage.setX(100);
        stage.setY(100);
        stage.setTitle("Main Menu");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setAlwaysOnTop(true);
        stage.show();

        // make window visible
        stage.show();
    }

    static void StartG(Connect4 c4) {

        // Create a new pane that would contain labels to show the game grid.
        GridPane Gmain = new GridPane();

        // Get 2D array needed to print out the game grid and print.
        grid = c4.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Label lbl = new Label(Character.toString(grid[i][j]));
                Gmain.add(lbl, j, i);
            }
        }
        for (int i = 0; i < 7; i++) {
            Label lbl = new Label(String.valueOf(i + 1));
            Gmain.add(lbl, i, 6);
        }

        // Create textfield for user input.
        TextField txtX = new TextField();
        TextField txtO = new TextField();
        if (againstComp) {
            txtO.setEditable(false);
        }

        Label lblX = new Label("X Player Move [First]:");
        Label lblO = new Label("O Player Move [Second]:");

        Stage stage = new Stage();

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> Platform.exit());

        Button btnEnter = new Button("Enter");
        btnEnter.setOnAction(e -> {
            try {
                // If Usere is against computer, only take input from one textfield.
                if (againstComp) {
                    play = Integer.parseInt(txtX.getText());
                    enterMove(c4);
                } else {
                    play = Integer.parseInt(txtX.getText());
                    play2 = Integer.parseInt(txtO.getText());
                    enterMove(c4);
                }
                // Close current stage and start new stage to print the refreshed
                // game grid.
                stage.close();
                StartG(c4);
            } catch (NumberFormatException ex) {
                // If user insert wrong values, show a warning and clear
                //textfields.
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("WARNING");
                alert.setContentText("Please Insert correct numerical values.");
                alert.showAndWait();
                txtX.clear();
                txtO.clear();
            }
        });

        // Create new Horizontal Box that contains the game grid.
        HBox Hroot = new HBox();
        Hroot.getChildren().add(Gmain);
        Hroot.setAlignment(Pos.CENTER);

        btnEnter.setMinWidth(250);
        btnExit.setMinWidth(250);

        // Create new Vertical Box that contains the HBox of the grid and all
        // labels, buttons and textfields.
        VBox root = new VBox();
        root.getChildren().addAll(Hroot, lblX, txtX, lblO, txtO,
                btnEnter, btnExit);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Connect 4 Game");
        stage.show();
    }

    static void enterMove(Connect4 c4) {
        char player = 'X';
        boolean winner = false;
        boolean validPlay = false;
        if (againstComp && turn <= 42) {

            // Check that user input fits in grid.
            play--;
            validPlay = c4.validateIN(play);
            if (validPlay == false) {
                return;
            }

            // Put user input in grid and increment turn.
            c4.drop(player, play);
            turn++;
            winner = c4.CheckWin(player);

            if (winner == false && turn <= 42) {

                // Switch players.
                player = c4.switchP(player);
                validPlay = false;
                do {
                    // Have the computer make a choice on column number.
                    Connect4ComputerPlayer c4Comp = new Connect4ComputerPlayer(c4.getGrid());
                    play = c4Comp.CompTurn(player, turn);
                    play--;

                    // Makes sure computer choice can be put inside the grid.
                    validPlay = c4.validateIN(play);
                } while (validPlay == false);

                // Put computer input in grid and increment turn.
                c4.drop(player, play);
                turn++;
                winner = c4.CheckWin(player);
            }

        } else if (turn <= 42) {

            play--;
            validPlay = c4.validateIN(play);
            if (validPlay == false) {
                return;
            }
            c4.drop(player, play);
            turn++;
            winner = c4.CheckWin(player);

            if (winner == false && turn <= 42) {
                // Swicth player and make sure player 2 input is valid.
                player = c4.switchP(player);
                play2--;
                validPlay = c4.validateIN(play2);
                if (validPlay == false) {
                    return;
                }
                c4.drop(player, play2);
                turn++;
                winner = c4.CheckWin(player);

            }

        }

        if (winner) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Result:");
            alert.setContentText("Player " + player + " Wins!");
            alert.showAndWait();
            Platform.exit();
        } else if (turn > 42) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Result:");
            alert.setContentText("Tie Game!");
            alert.showAndWait();
            Platform.exit();
        }

    }

}
