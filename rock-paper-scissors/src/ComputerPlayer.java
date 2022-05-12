

/**
 * Computer implementation of player.
 *
 * Students should ideate other strategies for the Computer player
 * to determine a command rather than all random.
 *
 * see strategy pattern.
 */
public class ComputerPlayer extends Player {
    
    Strategy strategy;


    public ComputerPlayer(String playerName, int num) {
        super(playerName);
        if(num == 1){
            strategy = new Normal();
        }
        else{
            strategy = new Hard();
        }
    }

    @Override
    public Command getInput() {
        return strategy.getInput();
    }

    public Command getInput(Command previousInput) {
        return strategy.getInput(previousInput);
    }
}
