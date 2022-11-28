package moduls;

import services.FirstInterface;
import services.SecondInterface;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Sergey Bochkov
 * @version lab 5
 */
public class Injector {
    /**
     * Метод inject принимает
     * @param obj объект любого класса
     * и с помощью рефлексии преобразовывает его
     */
    public <T> T inject(T obj) {
        File file = new File("src/main/resources/data.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(AutoInjectable.class) && field.getType() == FirstInterface.class) {
                try {
                    field.set(obj, getObject(properties, "FirstInterface"));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (field.isAnnotationPresent(AutoInjectable.class) && field.getType() == SecondInterface.class) {
                try {
                    field.set(obj, getObject(properties, "SecondInterface"));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }

    /**
     * @return new Instance для нашего интерфейса
     */
    private Object getObject(Properties properties, String str) {
        try {
            Object someObject = null;
            Class<?> clazz = Class.forName(properties.getProperty(str));
            someObject = clazz.newInstance();
            return someObject;
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
