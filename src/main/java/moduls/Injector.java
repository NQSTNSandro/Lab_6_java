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
                    Object someObject = null;
                    try {
                        Class<?> clazz = Class.forName(properties.getProperty("FirstInterface"));
                        try {
                            someObject = clazz.newInstance();
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    field.set(obj, someObject);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (field.isAnnotationPresent(AutoInjectable.class) && field.getType() == SecondInterface.class) {
                try {
                    Object someObject = null;
                    try {
                        Class<?> clazz = Class.forName(properties.getProperty("SecondInterface"));
                        try {
                            someObject = clazz.newInstance();
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    field.set(obj, someObject);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }
}
