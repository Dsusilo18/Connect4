/**
 * Tests the methods used in Connect4.
 *
 * Completion Time: 2
 *
 * @author Susilo
 * @version 04/16/2021
 */
package connect4textconsole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

public class Connect4Test {

    private Connect4 instance;

    public Connect4Test() {
        instance = new Connect4();
    }

    /**
     * Test of getGrid method, of class Connect4.
     */
    @org.junit.Test
    public void testGetGrid() {
        // Make sure getGrid returns a grid.
        char[][] result = instance.getGrid();
        assertNotNull(result);
    }

    /**
     * Test of display method, of class Connect4.
     */
    @org.junit.Test
    public void testDisplay() {
        // Make sure display works.
        instance.display();
    }

    /**
     * Test of validateIN method, of class Connect4.
     */
    @org.junit.Test
    public void testValidateIN() {

        // Tests showing false is returned when input is not valid.
        assertEquals(false, instance.validateIN(-1));
        assertEquals(false, instance.validateIN(8));
        assertEquals(false, instance.validateIN(100));
        assertEquals(false, instance.validateIN(-100));
        // Tests showing true is returned when input is valid.
        assertEquals(true, instance.validateIN(0));
        assertEquals(true, instance.validateIN(6));

        // Fill up all spots of the grid.
        char player = 'X';
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                instance.drop(player, col);
            }
        }

        // Tests showing false when grid is full and input is trying
        // to be put into the grid.
        assertEquals(false, instance.validateIN(6));
        assertEquals(false, instance.validateIN(0));
    }

    /**
     * Test of drop method, of class Connect4.
     */
    @org.junit.Test
    public void testDrop() {

        // Checks to see if tokens are drop if it will be saved in the grid.
        char player = 'X';
        instance.drop(player, 0);
        instance.drop(player, 6);
        char[][] result = instance.getGrid();

        assertEquals('X', result[5][0]);
        assertEquals('X', result[5][6]);

        // Tests showing that if a token is trying to dropped into a
        // column that is out of bounds, an exception will be thrown.
        try {
            instance.drop(player, -1);
            instance.drop(player, 7);

        } catch (ArrayIndexOutOfBoundsException ex) {
            assertNotNull(ex.getMessage());
        }
    }

    /**
     * Test of CheckWin method, of class Connect4.
     */
    @org.junit.Test
    public void testCheckWin() {

        // Eventhough, the grid is empty or filled with ' ',
        // checking win for ' ' will still be false.
        assertEquals(false, instance.CheckWin(' '));
        // Checking win for a random token will be false.
        assertEquals(false, instance.CheckWin('B'));
        // Since grid is empty checking win for X or O tokens will return false.
        assertEquals(false, instance.CheckWin('X'));
        assertEquals(false, instance.CheckWin('O'));

        // Test that simulate playing and checking win after each player move.
        // Test if CheckWin will recognize horizontal wins made by X or O.
        instance.drop('X', 0);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 0);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 1);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 1);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 2);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 2);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 3);
        assertEquals(true, instance.CheckWin('X'));
        instance.drop('O', 3);
        assertEquals(true, instance.CheckWin('O'));

        // Test if CheckWin will recognize vertical wins made by X or O.
        instance = new Connect4();
        instance.drop('X', 0);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 1);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 0);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 1);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 0);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 1);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 0);
        assertEquals(true, instance.CheckWin('X'));
        instance.drop('O', 1);
        assertEquals(true, instance.CheckWin('O'));

        // Test if CheckWin will recognize diagnoally upward wins made by X or O.
        instance = new Connect4();
        instance.drop('X', 6);
        instance.drop('X', 6);
        instance.drop('X', 6);
        instance.drop('X', 5);
        instance.drop('X', 5);
        instance.drop('X', 4);
        instance.drop('X', 2);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 3);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 3);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 4);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 4);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 5);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 5);
        assertEquals(true, instance.CheckWin('X'));
        instance.drop('O', 6);
        assertEquals(true, instance.CheckWin('O'));

        // Test if CheckWin will recognize diagonally downward
        // wins made by X or O.
        instance = new Connect4();
        instance.drop('X', 0);
        instance.drop('X', 0);
        instance.drop('X', 0);
        instance.drop('X', 1);
        instance.drop('X', 1);
        instance.drop('X', 2);
        instance.drop('X', 4);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 3);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 3);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 2);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 2);
        assertEquals(false, instance.CheckWin('X'));
        instance.drop('O', 1);
        assertEquals(false, instance.CheckWin('O'));
        instance.drop('X', 1);
        assertEquals(true, instance.CheckWin('X'));
        instance.drop('O', 0);
        assertEquals(true, instance.CheckWin('O'));

    }

    /**
     * Test of switchP method, of class Connect4.
     */
    @org.junit.Test
    public void testSwitchP() {
        // Testes showing switchP will return the opposite player from its
        // parameter.
        assertEquals('O', instance.switchP('X'));
        assertEquals('X', instance.switchP('O'));

        // Tests showing that if wrong player tokens are inserted as the
        // parameter it will not work, X or O will not be returned by switchP.
        assertNotSame('X', instance.switchP('B'));
        assertNotSame('O', instance.switchP('B'));
    }

}
