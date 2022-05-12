/**
 * Base class representing a playable character in RPS.
 * Can be a computer or human.
 * See ComputerPlayer and HumanPlayer for details.
 */
public abstract class Player {
    private String name;

    public Player(String playerName) {
        this.name = playerName;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Any player must be able to retrieve their own input
     * @return the RPS command result
     */
    public abstract Command getInput();

    /**
     * Overloaded getInput() method - mostly for demo purposes.
     * To be overridden in subclass.
     * @param previousInput change strategy based on some input
     * @return defaults to getInput()
     */
    public Command getInput(Command previousInput) {
        return getInput();
    }
}
