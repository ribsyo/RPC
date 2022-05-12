public class Hard extends Strategy{
    Hard(){}
    public Command getInput(Command previousInput){
        if(previousInput == Command.ROCK){
            return Command.PAPER;
        }
        else if(previousInput == Command.PAPER){
            return Command.SCISSORS;
        }
        else{
            return Command.ROCK;
        }
    }
}
