/**
 * Takes in grid and uses it to determine what moves the computer should take.
 *
 * Completion Time: 2
 *
 * @author Susilo
 * @version 04/16/2021
 */
package connect4textconsole;

import java.util.Random;

public class Connect4ComputerPlayer {

    static char[][] grid = new char[6][7];

    public Connect4ComputerPlayer(char[][] oldgrid) {
        // Recieve grid.
        grid = oldgrid;
    }

    public void setGrid(char[][] inputgrid) {
        // Recieve input grid.
        grid = inputgrid;
    }

    public int CompTurn(char player, int turn) {

        // Checks if computer can block or win the game.
        if (turn >= 5) {

            // Check if there are 3 across.
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length - 3; col++) {
                    if (grid[row][col] == player
                            && grid[row][col + 1] == player
                            && grid[row][col + 2] == player) {
                        if (grid[row][col + 3] == ' ') {
                            return col + 4;
                        }
                    }
                }
            }
            // Check there are 3 vertically.
            for (int row = 0; row < grid.length - 3; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row + 1][col] == player
                            && grid[row + 2][col] == player
                            && grid[row + 3][col] == player) {
                        if (grid[row][col] == ' ') {
                            return col + 1;
                        }
                    }
                }
            }
            // Check if there are 3 diagonally upward.
            for (int row = 3; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length - 3; col++) {
                    if (grid[row][col] == player
                            && grid[row - 1][col + 1] == player
                            && grid[row - 2][col + 2] == player) {
                        if (grid[row - 3][col + 3] == ' ') {
                            return col + 4;
                        }
                    }
                }
            }
            // Check if there are 3 diagonally downward.
            for (int row = 0; row < grid.length - 3; row++) {
                for (int col = 0; col < grid[0].length - 3; col++) {
                    if (grid[row][col] == player
                            && grid[row + 1][col + 1] == player
                            && grid[row + 2][col + 2] == player) {
                        if (grid[row + 3][col + 3] == ' ') {
                            return col + 4;
                        }
                    }
                }
            }
            if (player == 'O') {
                player = 'X';
                return CompTurn(player, turn);
            }
        }

        // If computer can't block or win the game, computer play random number.
        Random rand = new Random();
        return rand.nextInt(7) + 1;
    }
}
