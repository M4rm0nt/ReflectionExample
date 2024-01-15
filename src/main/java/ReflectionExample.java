import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class ReflectionExample {
    public static void main(String[] args) {

        try {

            Class<MyClass> clazz = MyClass.class;
            System.out.println("\nKlassenname: " + clazz.getName());

            Constructor<?>[] constructors = clazz.getConstructors();
            System.out.println("\nKonstruktoren: ");

            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

            Method[] methods = clazz.getMethods();
            System.out.println("\nMethoden: ");

            for (Method method : methods) {
                System.out.println(method);
            }

            Field[] fields = clazz.getDeclaredFields();
            System.out.println("\nFelder: ");

            for (Field field : fields) {
                System.out.println(field);
            }

            MyClass instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("\nInstanz erstellt: " + instance);

            Field field = clazz.getDeclaredField("myField");
            field.setAccessible(true);
            field.setInt(instance, 10);
            System.out.println("\nGeänderter Feldwert: " + field.get(instance));

            Method method = clazz.getMethod("myMethod");
            method.invoke(instance);

        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class MyClass {
    private int myField;

    public MyClass() {
    }

    public void myMethod() {
        System.out.println("myMethod wurde aufgerufen.");
    }
}
