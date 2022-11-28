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

    @AutoInjectable
    private Integer a;

    public void foo(){
        System.out.println("\nContainer running");
        firstInterface.printInfo();
        secondInterface.printAnotherInfo();
    }
}
