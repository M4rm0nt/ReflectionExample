import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ReflectionExample {
    public static void main(String[] args) {

        try {
            Class<Mensch> clazz = Mensch.class;
            System.out.println("\nKlassenname: " + clazz.getName());

            Constructor<?>[] constructors = clazz.getConstructors();
            System.out.println("\nKonstruktoren: ");
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

            Method[] methods = clazz.getDeclaredMethods();
            System.out.println("\nMethoden: ");
            for (Method method : methods) {
                System.out.println(method);
                if (method.getParameterCount() == 0) {
                    method.setAccessible(true);
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                }
            }

            Field[] fields = clazz.getDeclaredFields();
            System.out.println("\nFelder: ");
            for (Field field : fields) {
                System.out.println(field);
            }

            Mensch instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("\nInstanz erstellt: " + instance);

            Field fieldAlter = clazz.getDeclaredField("alter");
            fieldAlter.setAccessible(true);
            fieldAlter.setInt(instance, 43);

            Field fieldName = clazz.getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(instance, "Max");

            System.out.println("\n" + "Instanzfelder geändert: " + instance);

        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class Mensch {
    private int alter;
    private String name;

    public Mensch() {
    }

    public Mensch(int alter, String name) {
        this.alter = alter;
        this.name = name;
    }

    private void myAlter() {
        System.out.println("myAlter wurde aufgerufen.");
    }

    private void myName() {
        System.out.println("myName wurde aufgerufen.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensch mensch = (Mensch) o;
        return alter == mensch.alter && Objects.equals(name, mensch.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alter, name);
    }

    @Override
    public String toString() {
        return "Mensch{" +
                "alter=" + alter +
                ", name='" + name + '\'' +
                '}';
    }
}
