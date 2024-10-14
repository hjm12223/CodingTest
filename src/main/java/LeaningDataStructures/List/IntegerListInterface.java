package LeaningDataStructures.List;

import java.io.IOException;

public interface IntegerListInterface {
	void add(int i, Integer x) throws IOException;
	void append(Integer x) throws IOException;
	Integer remove(int i);
	boolean removeItem(Integer x);
	Integer get(int i);
	void set(int i, Integer x);
	int indexOf(Integer x);
	int len();
	boolean isEmpty();
	void clear();
}
