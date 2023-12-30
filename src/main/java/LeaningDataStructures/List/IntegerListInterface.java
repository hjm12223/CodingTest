package LeaningDataStructures.List;

public interface IntegerListInterface {
    void add(int inedx, Integer x);
    void append(Integer x);
    Integer remove(int i);
    boolean removeItem(Integer x);
    Integer get(int i);
    void set(int i , Integer x);
    int len();
    boolean isEmpty();
    void claer();
}
