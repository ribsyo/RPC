import java.util.Scanner;
/**
 * Simple RPS game demonstrating core topics
 * for the Canadian AP CS A curriculum.
 *
 * For simplicity and scope of the exam, there will
 * not be any build tools (e.g. maven) or exceptions.
 *
 * The Main class is the entrypoint to the game.
 */
public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Game g = new Game(input);
        g.setup();
        g.loop();
        input.close();
        System.exit(0);


    }
}
