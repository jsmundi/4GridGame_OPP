import java.util.Scanner;

/*
 *
 * Main controller file extends connect four to
 * create a new board.
 *
 * The Game asks for a user name and makes the user selects
 * a Blue or Yellow color to play the game. A user can place
 * their mark by entering the x and y coordinates. If the move
 * is valid the mark is placed else user is prompted for
 * new coordinates. After that the computer takes its turn. If
 * the game is over (win or tie), the user is prompted to exit
 * or start a new game from the beginning.
 *
 */

public class Main extends ConnectFour {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in); // Create a Scanner object

        ConnectFour game = new ConnectFour();

        String userName;
        String temp;
        int gameStatus = 0;
        int userPlayer;
        int compPlayer;
        int turn;
        int xCord;
        int yCord;

        while (gameStatus != 1) {

            // Get a user name from the user
            System.out.println("Welcome to Connect 4 Game! 🛸");
            System.out.println("Please enter Gamer Name (Example: FlyingCat)");
            userName = myObj.nextLine();

            // Loop to get the correct player mark
            while (true) {
                System.out.println("Please select your Mark Blue or Yellow. Press 1 for Blue and 2 for Yellow");
                temp = myObj.nextLine();
                if (temp.equals("1") || temp.equals("2")) {
                    userPlayer = Integer.parseInt(temp);
                    break;
                }
            }

            // Set the blue or yellow mark based on user input
            if (userPlayer == 1) {
                System.out.println(ANSI_BLUE + userName + " You have selected Blue" + ANSI_RESET);
                turn = 1;
                compPlayer = 2;
            } else {
                System.out.println(ANSI_YELLOW + userName + " You have selected Yellow" + ANSI_RESET);
                turn = 1;
                compPlayer = 1;
            }

            // Initialize the game board with all zeros
            System.out.println("\nCoordinate layout\n");
            System.out.println(
                    "(0,0) (0,1) (0,2) (0,3)\n(1,0) (1,1) (1,2) (1,3)\n(2,0) (2,1) (2,2) (2,3)\n(3,0) (3,1) (3,2) (3,3)");
            System.out.println("\nStarting new game. Good luck 👍 " + userName);
            game.initializeBoard();

            // While the game is not won, lost or tied.
            while (game.getStatus() != 1 && game.getStatus() != 2 && game.getStatus() != 3) {

                // User turn
                if (turn == 1) {
                    turn = 2;
                    while (true) {
                        System.out.println(userName + " it is your turn now.");
                        System.out.println("Enter Row Number: ");
                        xCord = myObj.nextInt();
                        System.out.println("Enter Column Number: ");
                        yCord = myObj.nextInt();

                        // Error check for correct input
                        if (xCord == 0 || xCord == 1 || xCord == 2 || xCord == 3 || yCord == 0 || yCord == 1 || yCord == 2
                                || yCord == 3) {
                            if (!game.placeMark(xCord, yCord, userPlayer)) {
                                System.out.println(ANSI_RED + "❌ Invalid move try again ❌" + ANSI_RESET);
                            } else {
                                break;
                            }
                        }
                    }
                    game.printGrid();
                }

                // Computer turn
                else {
                    turn = 1;
                    System.out.println("💻 Computer's turn.");
                    try {
                        System.out.println("🤔 Thinking...");
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    game.placeMark(compPlayer);
                    game.printGrid();
                }
            }

            // Print out win status
            if (game.getStatus() == userPlayer) {
                game.printGrid();
                System.out.println(ANSI_GREEN + userName + " wins! ☹️" + ANSI_RESET);
            } else if (game.getStatus() == 3) {
                game.printGrid();
                System.out.println("Its a tie! 😑");
            } else {
                game.printGrid();
                System.out.println("Computer wins! 😂");
            }

            // Prompt user to restart or exit game
            System.out.println("Enter 0 for new game and 1 to exit 😬");
            gameStatus = myObj.nextInt();

            // Reset the board
            if (gameStatus == 0) {
                game.restart();
            }
        }

    }
}
