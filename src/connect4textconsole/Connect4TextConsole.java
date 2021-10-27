/**
 * Display grid, whose turn it is, and if a player won as well as get user
 * column input.
 *
 * Completion Time: 2
 *
 * @author Susilo
 * @version 03/28/2021
 */
package connect4textconsole;

import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.application.Application;

public class Connect4TextConsole extends Connect4 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Connect4 c4 = new Connect4();

        int turn = 1;
        char player = 'X';
        boolean winner = false;
        boolean againstComp = false;
        boolean GUI = false;
        boolean begin = true;
        char insert;

        // Asks user if they want to play aginst Player or Computer.
        System.out.print("Enter G for GUI "
                + ", Enter U for console-based UI:");
        insert = input.next().charAt(0);
        if (insert == 'G') {
            GUI = true;
        } else if (insert == 'U') {
            GUI = false;
        } else {
            // Keep on checking if input is invalid.
            GUI = CheckIn('G', 'U');
        }

        if (GUI) {
            Application.launch(Connect4GUI.class, null);
        }
        if (GUI == false) {
            try {
                // Start game.
                while (winner == false && turn <= 42) {
                    boolean validPlay = false;
                    int play = 0;
                    do {
                        c4.display();
                        if (begin) {
                            // Asks user if they want to play aginst Player or Computer.
                            System.out.print("Begin Game. Enter P if you want to play "
                                    + "against another player, Enter C to play against"
                                    + " computer:");
                            insert = input.next().charAt(0);
                            if (insert == 'C') {
                                againstComp = true;
                            } else if (insert == 'P') {
                                againstComp = false;
                            } else {
                                // Keep on checking if input is invalid.
                                againstComp = CheckIn('C', 'P');
                            }

                            begin = false;
                        }
                        if (againstComp) {
                            if (player == 'X') {

                                // Que player to insert a column number.
                                System.out.print("Player " + player + " - your turn. Choose a column number from 1-7: ");
                                play = input.nextInt();

                            } else {

                                // Have the computer make a choice on column number.
                                Connect4ComputerPlayer c4Comp = new Connect4ComputerPlayer(grid);
                                play = c4Comp.CompTurn(player, turn);

                            }
                            // Makes sure user column can be put inside the array.
                            play--;

                            // Validate input.
                            validPlay = c4.validateIN(play);
                        } else {
                            System.out.print("Player " + player + " - your turn. Choose a column number from 1-7: ");
                            play = input.nextInt();

                            // Makes sure user column can be put inside the array.
                            play--;

                            // Validate input.
                            validPlay = c4.validateIN(play);
                        }

                    } while (validPlay == false);

                    // Put user input into the grid.
                    c4.drop(player, play);

                    // Check if there's a winner.
                    winner = c4.CheckWin(player);

                    // Switch players.
                    player = c4.switchP(player);
                    turn++;
                }
                c4.display();

                // Display who won if there was a winner.
                if (winner) {
                    if (player == 'X') {
                        System.out.println("O won");
                    } else {
                        System.out.println("X won");
                    }
                } else {
                    System.out.println("Tie game");
                }

            } catch (InputMismatchException e) {
                // Error message in case user input a non-numeric value for column.
                System.out.println("Please insert the correct type of values.");
            }
        }

    }

    static boolean CheckIn(char val1, char val2) {
        Scanner input = new Scanner(System.in);
        char insert = ' ';
        // Keep on checking if input is invalid.
        while (insert != val1 && insert != val2) {
            System.out.printf("Please insert %c or %c:", val1, val2);
            insert = input.next().charAt(0);
        }
        if (insert == val1) {
            return true;
        }
        return false;
    }
}
