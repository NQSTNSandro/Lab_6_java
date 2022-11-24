package moduls;


import services.FirstInterface;
import services.SecondInterface;


public class Bean {
    @AutoInjectable
    private FirstInterface firstInterface;
    @AutoInjectable
    private SecondInterface secondInterface;
    public Bean(){}
    public void foo(){
        firstInterface.printInfo();
        secondInterface.printAnotherInfo();
    }

}
