package Tests;

import org.example.IntegerList;
import org.example.IntegerListImpl;
import org.example.exceptions.BadIndexException;
import org.example.exceptions.BadItemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static Tests.TestConstants.*;

public class IntegerListTest {
    public static IntegerList out = new IntegerListImpl();

    public static void fillOut() {
        out = new IntegerListImpl(new Integer[]{FIRST_INTEGER, SECOND_INTEGER, THIRD_INTEGER, FOURTH_INTEGER});
    }

    public static Stream<Arguments> provideParamsForAddWithoutIndex() {
        return Stream.of(
                Arguments.of(FIRST_INTEGER),
                Arguments.of(SECOND_INTEGER),
                Arguments.of(THIRD_INTEGER),
                Arguments.of(FOURTH_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForAddWithIndex() {
        return Stream.of(
                Arguments.of(0, 67),
                Arguments.of(2, 100)
        );
    }

    public static Stream<Arguments> provideParamsForSet() {
        return Stream.of(
                Arguments.of(2, FIRST_INTEGER),
                Arguments.of(0, FOURTH_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForRemoveWithItem() {
        return Stream.of(
                Arguments.of(FIRST_INTEGER),
                Arguments.of(SECOND_INTEGER),
                Arguments.of(THIRD_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForRemoveWithIndex() {
        return Stream.of(
                Arguments.of(0, FIRST_INTEGER),
                Arguments.of(2, THIRD_INTEGER),
                Arguments.of(3, FOURTH_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForContains() {
        return Stream.of(
                Arguments.of(true, FIRST_INTEGER),
                Arguments.of(true, SECOND_INTEGER),
                Arguments.of(false, NO_USAGE_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForIndexOf() {
        return Stream.of(
                Arguments.of(0, FIRST_INTEGER),
                Arguments.of(3, FOURTH_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForLastIndexOf() {
        return Stream.of(
                Arguments.of(1, SECOND_INTEGER),
                Arguments.of(3, FOURTH_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForGet() {
        return Stream.of(
                Arguments.of(0, FIRST_INTEGER),
                Arguments.of(1, SECOND_INTEGER),
                Arguments.of(3, FOURTH_INTEGER)
        );
    }

    public static Stream<Arguments> provideParamsForEquals() {
        return Stream.of(
                Arguments.of(true, FIRST_OF_EQUAL_LISTS, SECOND_OF_EQUAL_LISTS),
                Arguments.of(false, FIRST_OF_EQUAL_LISTS, NOT_EQUAL_LIST)
        );
    }

    public static Stream<Arguments> provideParamsForSize() {
        return Stream.of(
                Arguments.of(5, FIRST_OF_EQUAL_LISTS),
                Arguments.of(3, NOT_EQUAL_LIST),
                Arguments.of(4, FIRST_LIST_FOR_ADD)
        );
    }

    public static Stream<Arguments> provideParamsForIsEmpty() {
        return Stream.of(
                Arguments.of(true, EMPTY_LIST),
                Arguments.of(false, SECOND_LIST_FOR_ADD)
        );
    }

    public static Stream<Arguments> provideParamsForClear() {
        return Stream.of(
                Arguments.of(FIRST_LIST_FOR_ADD)
        );
    }

    public static Stream<Arguments> provideParamsForToArray() {
        return Stream.of(
                Arguments.of(new Integer[]{100, 98, 2, 15}, FIRST_LIST_FOR_ADD),
                Arguments.of(new Integer[]{43, 14, 5}, SECOND_LIST_FOR_ADD)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForAddWithoutIndex")
    public void add(Integer item) {
        Assertions.assertEquals(item, out.add(item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForAddWithIndex")
    public void add(int index, Integer item) {
        fillOut();
        Assertions.assertEquals(item, out.add(index, item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSet")
    public void set(int index, Integer item) {
        fillOut();
        Assertions.assertEquals(item, out.set(index, item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForRemoveWithItem")
    public void remove(Integer item) {
        fillOut();
        Assertions.assertEquals(item, out.removeItem(item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForRemoveWithIndex")
    public void remove(int index, Integer item) {
        fillOut();
        Assertions.assertEquals(item, out.removeIndex(index));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForContains")
    public void contains(boolean examination, Integer item) {
        fillOut();
        Assertions.assertEquals(examination, out.contains(item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForIndexOf")
    public void indexOf(int index, Integer item) {
        fillOut();
        Assertions.assertEquals(index, out.indexOf(item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForLastIndexOf")
    public void lastIndexOf(int index, Integer item) {
        fillOut();
        Assertions.assertEquals(index, out.lastIndexOf(item));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForGet")
    public void get(int index, Integer item) {
        fillOut();
        Assertions.assertEquals(item, out.get(index));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEquals")
    public void equals(boolean examination, IntegerList firstList, IntegerList secondList) {
        Assertions.assertEquals(examination, firstList.equals(secondList));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSize")
    public void size(int examination, IntegerList integerList) {
        Assertions.assertEquals(examination, integerList.size());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForIsEmpty")
    public void isEmpty(boolean examination, IntegerList integerList) {
        Assertions.assertEquals(examination, integerList.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForClear")
    public void clear(IntegerList integerList) {
        integerList.clear();
        Assertions.assertTrue(integerList.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForToArray")
    public void toArray(Integer[] list, IntegerList integerList) {
        Assertions.assertEquals(Arrays.hashCode(list), Arrays.hashCode(integerList.toArray()));
    }

    @Test
    public void invalidAddWithOutIndex() {
        Assertions.assertThrows(NullPointerException.class,
                () -> out.add(NULL_INTEGER));
    }

    @Test
    public void invalidAddWithIndex() {
        fillOut();
        Assertions.assertThrows(NullPointerException.class,
                () -> out.add(2, NULL_INTEGER));
        Assertions.assertThrows(NullPointerException.class,
                () -> out.add(FIRST_BAD_INDEX, NULL_INTEGER));
        Assertions.assertThrows(BadIndexException.class,
                () -> out.add(FIRST_BAD_INDEX, FIRST_INTEGER));
        Assertions.assertThrows(BadIndexException.class,
                () -> out.add(SECOND_BAD_INDEX, FOURTH_INTEGER));
    }

    @Test
    public void invalidSet() {
        fillOut();
        Assertions.assertThrows(NullPointerException.class,
                () -> out.set(2, NULL_INTEGER));
        Assertions.assertThrows(NullPointerException.class,
                () -> out.set(FIRST_BAD_INDEX, NULL_INTEGER));
        Assertions.assertThrows(BadIndexException.class,
                () -> out.set(FIRST_BAD_INDEX, FIRST_INTEGER));
        Assertions.assertThrows(BadIndexException.class,
                () -> out.set(SECOND_BAD_INDEX, FOURTH_INTEGER));
    }

    @Test
    public void invalidRemoveWithItem() {
        fillOut();
        Assertions.assertThrows(NullPointerException.class,
                () -> out.removeItem(NULL_INTEGER));
        Assertions.assertThrows(BadItemException.class,
                () -> out.removeItem(NO_USAGE_INTEGER));
    }

    @Test
    public void invalidRemoveWithIndex() {
        fillOut();
        Assertions.assertThrows(BadIndexException.class,
                () -> out.removeIndex(FIRST_BAD_INDEX));
        Assertions.assertThrows(BadIndexException.class,
                () -> out.removeIndex(SECOND_BAD_INDEX));
    }

    @Test
    public void invalidContains() {
        Assertions.assertThrows(NullPointerException.class,
                () -> out.contains(NULL_INTEGER));
    }

    @Test
    public void invalidIndexOf() {
        Assertions.assertThrows(NullPointerException.class,
                () -> out.indexOf(NULL_INTEGER));
    }

    @Test
    public void invalidLastIndexOf() {
        Assertions.assertThrows(NullPointerException.class,
                () -> out.lastIndexOf(NULL_INTEGER));
    }

    @Test
    public void invalidGet() {
        Assertions.assertThrows(BadIndexException.class,
                () -> out.get(FIRST_BAD_INDEX));
        Assertions.assertThrows(BadIndexException.class,
                () -> out.get(SECOND_BAD_INDEX));
    }

    @Test
    public void invalidEquals() {
        Assertions.assertThrows(NullPointerException.class,
                () -> out.equals(NULL_LIST));
    }
}
