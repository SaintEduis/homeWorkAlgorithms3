package Tests;

import org.example.IntegerList;
import org.example.IntegerListImpl;

public class TestConstants {
    public static Integer FIRST_INTEGER = 34;
    public static Integer SECOND_INTEGER = 25;
    public static Integer THIRD_INTEGER = 55;
    public static Integer FOURTH_INTEGER = 4;
    public static Integer NO_USAGE_INTEGER = 999;
    public static Integer NULL_INTEGER = null;
    public static int FIRST_BAD_INDEX = 100;
    public static int SECOND_BAD_INDEX = -1;
    public static IntegerList EMPTY_LIST = new IntegerListImpl();
    public static IntegerList NULL_LIST = null;
    public static IntegerList FIRST_OF_EQUAL_LISTS = new IntegerListImpl(new Integer[]{1, 23, 33, 3, 15});
    public static IntegerList SECOND_OF_EQUAL_LISTS = new IntegerListImpl(new Integer[]{1, 23, 33, 3, 15});
    public static IntegerList NOT_EQUAL_LIST = new IntegerListImpl(new Integer[]{33, 3, 15});
    public static IntegerList FIRST_LIST_FOR_ADD = new IntegerListImpl(new Integer[]{100, 98, 2, 15});
    public static IntegerList SECOND_LIST_FOR_ADD = new IntegerListImpl(new Integer[]{43, 14, 5});
}
