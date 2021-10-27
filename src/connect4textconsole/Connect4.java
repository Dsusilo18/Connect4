/**
 * Initialize grid, print out grid, check for winner, validate input, and place
 * user input in grid.
 *
 * Completion Time: 3
 *
 * @author Susilo
 * @version 04/16/2021
 */
package connect4textconsole;

public class Connect4 {

    static char[][] grid = new char[6][7];

    public Connect4() {

        // Initializes the array.
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public char[][] getGrid() {
        char[][] gridTemp = grid;
        return gridTemp;
    }

    public void display() {
        System.out.println();

        // Display contents of the grid with formatting.
        for (int row = 0; row < grid.length; row++) {
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4 5 6 7");
        System.out.println();
    }

    public boolean validateIN(int column) {
        // Validate if user input is in range.
        if (column < 0 || column >= grid[0].length) {
            return false;
        }
        // Check if grid is full.
        if (grid[0][column] != ' ') {
            return false;
        }

        return true;
    }

    public void drop(char player, int play) {
        // Places the user input into the grid.
        for (int row = grid.length - 1; row >= 0; row--) {
            if (grid[row][play] == ' ') {
                grid[row][play] = player;
                break;
            }
        }
    }

    public boolean CheckWin(char player) {
        if ((player != 'X') && (player != 'O')) {
            return false;
        }
        // Check if there are 4 across.
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player
                        && grid[row][col + 1] == player
                        && grid[row][col + 2] == player
                        && grid[row][col + 3] == player) {
                    return true;
                }
            }
        }
        // Check there are 4 vertically.
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == player
                        && grid[row + 1][col] == player
                        && grid[row + 2][col] == player
                        && grid[row + 3][col] == player) {
                    return true;
                }
            }
        }
        // Check if there are 4 diagonally upward.
        for (int row = 3; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player
                        && grid[row - 1][col + 1] == player
                        && grid[row - 2][col + 2] == player
                        && grid[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }
        // Check if there are 4 diagonally downward.
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player
                        && grid[row + 1][col + 1] == player
                        && grid[row + 2][col + 2] == player
                        && grid[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public char switchP(char player) {
        // Switch players.
        if (player == 'X') {
            player = 'O';
        } else if (player == 'O') {
            player = 'X';
        }
        return player;
    }
}
