package moduls;

import services.FirstInterface;
import services.SecondInterface;

public class Container {
    @AutoInjectable
    private FirstInterface firstInterface;
    @AutoInjectable
    private SecondInterface secondInterface;

    public void foo(){
        System.out.println("Container running \n");
        firstInterface.printInfo();
        secondInterface.printAnotherInfo();
    }
}
