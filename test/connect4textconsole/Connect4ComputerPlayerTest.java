/**
 * Tests the methods used in Connect4ComputerPlayer.
 *
 * Completion Time: 3
 *
 * @author Susilo
 * @version 04/16/2021
 */
package connect4textconsole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class Connect4ComputerPlayerTest {

    private Connect4 instance;
    private Connect4ComputerPlayer instTest;

    public Connect4ComputerPlayerTest() {
        instance = new Connect4();
        instTest = new Connect4ComputerPlayer(instance.getGrid());
    }

    /**
     * Test of getGrid method, of class Connect4.
     */
    @Test
    public void testSetGrid() {
        char[][] testGrid = new char[6][7];
        // Initializes the a test array.
        for (int row = 0; row < testGrid.length; row++) {
            for (int col = 0; col < testGrid[0].length; col++) {
                testGrid[row][col] = ' ';
            }
        }
        // Test to see if the grid will be taken by the method.
        instTest.setGrid(testGrid);
    }

    /**
     * Test of CompTurn method, of class Connect4ComputerPlayer.
     */
    @Test
    public void testCompTurn() {
        // Simulate a player vs computer game and
        // making sure computer is actually making a move.
        int turn = 0;
        instance.drop('X', 2);
        turn++;
        instTest.setGrid(instance.getGrid());
        assertNotNull(instTest.CompTurn('O', turn));
        turn++;
        instance.drop('X', 1);
        turn++;
        instTest.setGrid(instance.getGrid());
        assertNotNull(instTest.CompTurn('O', turn));
        turn++;
        instance.drop('X', 0);
        turn++;
        instTest.setGrid(instance.getGrid());
        assertEquals(4, instTest.CompTurn('O', turn));

        // Making sure the computer will block player if player is about to win.
        // Also, making sure computer will try to win if about to win.
        char player = 'O';
        int round = 0;
        while (round != 2) {
            char player2 = 'O';
            if (player == 'O') {
                player2 = 'X';
            } else if (player == 'X') {
                player2 = 'O';
            }

            // Block or win horizontally.
            instance = new Connect4();
            instance.drop(player, 0);
            instance.drop(player, 1);
            instance.drop(player, 2);
            turn = 5;
            instTest.setGrid(instance.getGrid());
            assertEquals(4, instTest.CompTurn('O', turn));

            // Block or win vertically.
            instance = new Connect4();
            instance.drop(player, 0);
            instance.drop(player, 0);
            instance.drop(player, 0);
            turn = 5;
            instTest.setGrid(instance.getGrid());
            assertEquals(1, instTest.CompTurn('O', turn));

            // Block or win diagonally upward.
            instance = new Connect4();
            instance.drop(player2, 6);
            instance.drop(player2, 6);
            instance.drop(player2, 6);
            instance.drop(player2, 5);
            instance.drop(player2, 5);
            instance.drop(player2, 4);
            instance.drop(player, 3);
            instance.drop(player, 4);
            instance.drop(player, 5);
            assertEquals(7, instTest.CompTurn('O', turn));

            // Block or win diagonally downward.
            instance = new Connect4();
            instance.drop(player2, 0);
            instance.drop(player2, 0);
            instance.drop(player2, 0);
            instance.drop(player2, 1);
            instance.drop(player2, 1);
            instance.drop(player2, 2);
            instance.drop(player, 0);
            instance.drop(player, 1);
            instance.drop(player, 2);
            assertEquals(4, instTest.CompTurn('O', turn));

            player = 'X';
            round++;
        }
    }
}
