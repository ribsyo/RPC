public enum Command {
    ROCK,
    PAPER,
    SCISSORS,
    QUIT,
    UNKNOWN;

    private static final Command[] commands = Command.values();

    public static Command getByIndex(int index) {
        if (index < 0 || index > 2) {
            return Command.UNKNOWN;
        }
        return commands[index];
    }
}
