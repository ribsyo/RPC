import java.util.Random;

public abstract class Strategy {

    Strategy(){

    }

    public Command getInput(){
        return Command.getByIndex(1);
    }

    public Command getInput(Command previousInput){
        return getInput();
    }
}
