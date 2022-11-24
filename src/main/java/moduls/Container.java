package moduls;

import services.FirstInterface;
import services.SecondInterface;
/**
 * Класс Container для теста
 */
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
