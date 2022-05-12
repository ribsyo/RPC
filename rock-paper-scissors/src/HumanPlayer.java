import java.util.Locale;
import java.util.Scanner;

/**
 * Human implementation of Player.
 *
 * Implements user-input logic.
 */
public class HumanPlayer extends Player {
    Scanner in;

    public HumanPlayer(String playerName, Scanner in) {
        super(playerName);
        this.in = in;
    }

    @Override
    public Command getInput() {
        Command result = Command.UNKNOWN;
        System.out.println(this.getName() + " turn. Enter rock (r), paper (p), scissors (s) or quit (q)!");
        while (result == Command.UNKNOWN) {
            String userInput = this.in.nextLine();
            switch (userInput.toLowerCase(Locale.ROOT)) {
                case "rock":
                case "r":
                    result = Command.ROCK;
                    break;
                case "paper":
                case "p":
                    result = Command.PAPER;
                    break;
                case "scissors":
                case "s":
                    result = Command.SCISSORS;
                    break;
                case "quit":
                case "q":
                    result = Command.QUIT;
                    break;
                default:
                    System.out.println("Unknown command. Valid values are r, p, s, rock, paper, scissors. Try Again.");
                    break;
            }
        }

        return result;
    }
}
