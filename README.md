# Connect4

Connect 4 Game-

Normal Connect 4 game that can be played on a console-based UI or GUI. The game allows the user to select whether they would like to play against another player or computer. The computer uses an algorithm that can detect if the player is about to win or if the computer could win the game. If the user were to enter invalid inputs, exception handling have been integrated to show error messages. The GUI version of the application involves Textfields, Buttons and a GridPane.

Project Difficulties:
This project tested my skills in JavaFX as it required me to show the gameboard and pieces on the GUI and update both after every turn. I also had a difficult time figuring out the best algorithm for the computer player because of the fact that it needed to be intelligent enough to come up with ways to win and block when needed.

My Solution:
At first I tried printing a table to the GUI, but I later learned that it wouldn't work because the game pieces of X and O had to fill the gameboard from the bottom to the top. Not like a table that gets filled from the top to bottom. Instead, I decided to use a single that would be used to show a 2d array to create a look that imitates a table but is more effecient. As for the alogrithm for the computer player, I made sure that only when the number of turns that have been played by both computer and player reaches 5, would the computer start trying to find if there are any 3 across, vertically or diagonally upward and downward. If the number of turn is less than 5 or if there isn't a way for the player or computer to win, then the computer will pick a row randomly for its turn. In order for the algorthm to be simple and not complicated, the computer will always place the 4th piece to connect four in a row if it finds any 3 pieces on the game board that are in a row. It does not care if those 3 pieces are from the player or computer. Making the computer automatically defend when needed or win. 

Technology Used:
Java
JavaFX
