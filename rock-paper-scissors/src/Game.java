import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The Game instance should be a singleton encapsulating the core
 * RPS game loop logic - no actual enforcement of the singleton pattern
 * will be demonstrated except stated in this docblock.
 */
public class Game {
    private boolean isPlaying;
    private GameMode mode;
    private int match;
    private Player leftPlayer;
    private Player rightPlayer;
    private Scanner in;
    private File matchHistory;
    private FileWriter fileWriter;
    private Scanner fileReader;
    private enum GameMode {
        UNKNOWN,
        PVC,
        PVP,
    }

    public Game(Scanner in) {
        this.in = in;
        this.match = 1;
        this.matchHistory = new File("MatchHistory.txt");
        try{
            if(!this.matchHistory.exists()){
                this.matchHistory.createNewFile();
            }
            this.fileWriter = new FileWriter(this.matchHistory, true);
            this.fileReader = new Scanner(this.matchHistory);
        }
        catch(IOException e){

        }
        this.mode = GameMode.UNKNOWN;
        this.isPlaying = true;
    }

    /**
     * Pre-game setup configuration.
     *
     * This can be considered as the title screen equivalent of our
     * command-line application.
     *
     * User(s) will be prompted for run-time configuration of the game.
     * e.g. 1-player or 2-player mode
     */
    public void setup() {
        System.out.println("Welcome to Rock-Paper-Scissors!");
        while (this.mode == GameMode.UNKNOWN) {
            System.out.println("Choose game mode:\n\t1. Player Vs Computer\n\t2. Player Vs Player\n\t3. Exit\n\t4.View Match History");
            String userInput = this.in.nextLine();
            switch (userInput) {
                case "1":
                    this.mode = GameMode.PVC;
                    this.leftPlayer = new HumanPlayer("Player 1", this.in);
                    userInput = " ";
                    while(!userInput.equals("1") && !userInput.equals("2")){
                        System.out.println("Chose Difficulty: \n1. Normal\n2. Hard");
                        userInput = this.in.nextLine();
                    }
                    this.rightPlayer = new ComputerPlayer("Computer Player", Integer.parseInt(userInput));
                    System.out.println("Game mode: Player vs Computer");
                    break;
                case "2":
                    this.mode = GameMode.PVP;
                    this.leftPlayer = new HumanPlayer("Player 1", this.in);
                    this.rightPlayer = new HumanPlayer("Player 2", this.in);
                    System.out.println("Game mode: Player vs Player");
                    break;
                case "3":
                    System.out.println("Quitting game...");
                    this.isPlaying = false;
                    return;
                case "4":
                    System.out.println(getMatchHistory());
                    break;
                default:
                    System.out.println("Invalid game mode");
                    break;
            }
        }
    }

    /**
     * Core game loop logic.
     *
     * This is the bulk of the game logic and should be repeatable.
     */
    public void loop() {
        while (isPlaying) {
            System.out.println("\n\nMatch " + this.match);
            Command leftInput = this.leftPlayer.getInput();
            Command rightInput = this.rightPlayer.getInput(leftInput);
            if (leftInput == Command.QUIT || rightInput == Command.QUIT) {
                System.out.println("Quitting game...");
                this.isPlaying = false;
                try{
                    fileWriter.close();
                }
                catch(IOException e){

                }
                break;
            }
            String matchOverview =
                "Match " + this.match + " results:\n" +
                "\t" + this.leftPlayer.getName() + " chose " + leftInput.toString().toLowerCase(Locale.ROOT) + "\n" +
                "\t" + this.rightPlayer.getName() + " chose " + rightInput.toString().toLowerCase(Locale.ROOT) + "\n";

            String matchResult = "";
            switch (Game.calculateWinner(leftInput, rightInput)) {
                case TIE:
                    matchResult = "\tIt's a tie!";
                    break;
                case LEFT:
                    matchResult = "\t" + this.leftPlayer.getName() + " wins!";
                    break;
                case RIGHT:
                    matchResult = "\t" + this.rightPlayer.getName() + " wins!";
                    break;
            }
            System.out.println(matchOverview + "\n" + matchResult);
            updateMatchHistory("p1: " + rightInput + "p2: " + leftInput + matchResult + "\n");
            this.match++;
        }
    }

    public String getMatchHistory(){
        String temp = "";
        while(fileReader.hasNextLine()){
            temp += "\n"+fileReader.nextLine();
        }
        return temp;

    }
    public void updateMatchHistory(String match){
        try{
            this.fileWriter.write(match);
        }
        catch(IOException e){

        }
    }

    /**
     * Helper method for determining the winner in a 2-player RPS game.
     *
     * Logic is if all of one side's win conditions + tie condition
     * are not met, the other side has won.
     *
     * `Side` in this context is purely virtual.
     *
     * @param lhs player from one side
     * @param rhs player from another side
     * @return the winning side
     */
    private static Side calculateWinner(Command lhs, Command rhs) {
        if (lhs == rhs) {
            return Side.TIE;
        } else if (lhs == Command.PAPER && rhs == Command.ROCK) {
            return Side.LEFT;
        } else if (lhs == Command.ROCK && rhs == Command.SCISSORS) {
            return Side.LEFT;
        } else if (lhs == Command.SCISSORS && rhs == Command.PAPER) {
            return Side.LEFT;
        } else {
            return Side.RIGHT;
        }
    }
}
