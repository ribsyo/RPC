import java.util.Random;

public class Normal extends Strategy{
    private final Random seed;
    private static final int RANDOM_COMMAND_MAX_INDEX = 2;
    Normal(){
        this.seed = new Random();
    }

    public Command getInput(){
        int generatedValue = this.seed.nextInt(Normal.RANDOM_COMMAND_MAX_INDEX + 1);
        return Command.getByIndex(generatedValue);
    }
}
