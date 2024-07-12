package org.example;

import org.example.exceptions.BadIndexException;
import org.example.exceptions.BadItemException;

public class IntegerListImpl implements IntegerList {
    private Integer[] integerList;
    private int size;

    public IntegerListImpl() {
        this.integerList = new Integer[8];
        this.size = 0;
    }

    public IntegerListImpl(Integer[] integerList) {
        this.integerList = integerList;
        this.size = 0;
        for(Integer item: integerList) {
            if (item != null) {
                this.size++;
            }
        }
    }

    @Override
    public Integer add(Integer item) {
        if (item != null) {
            if (size == integerList.length) {
                grow();
            }
            integerList[size] = item;
            size++;
            return item;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item != null) {
            if (index < size && index >= 0) {
                if (size == integerList.length) {
                    grow();
                }
                System.arraycopy(integerList, index, integerList, index + 1, size - index);
                integerList[index] = item;
                size++;
                return item;
            } else {
                throw new BadIndexException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item != null) {
            if (index < size && index >= 0) {
                integerList[index] = item;
                return item;
            } else {
                throw new BadIndexException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer removeItem(Integer item) {
        if (item != null) {
            int index = indexOf(item);

            if (index != -1) {
                System.arraycopy(integerList, index + 1, integerList, index, size - index - 1);
                size--;
                return item;
            } else {
                throw new BadItemException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer removeIndex(int index) {
        if (index < size && index >= 0) {
            Integer result = integerList[index];
            System.arraycopy(integerList, index + 1, integerList, index, size - index - 1);
            size--;
            return result;
        } else {
            throw new BadIndexException();
        }
    }

    @Override
    public boolean contains(Integer item) {
        if (item != null) {
            IntegerListImpl arr = new IntegerListImpl(this.integerList);
            arr.sort(0, arr.size - 1);
            return arr.binarySearch(item) != -1;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public int indexOf(Integer item) {
        if (item != null) {
            for (int i = 0; i < size; i++) {
                if (integerList[i].equals(item)) {
                    return i;
                }
            }
            return -1;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item != null) {
            for (int i = size - 1; i >= 0; i--) {
                if (integerList[i].equals(item)) {
                    return i;
                }
            }
            return -1;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Integer get(int index) {
        if (index < size && index >= 0) {
            return integerList[index];
        } else {
            throw new BadIndexException();
        }
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList != null) {
            if (size != otherList.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(integerList[i].equals(otherList.toArray()[i]))) {
                    return false;
                }
            }
            return true;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        integerList = new Integer[0];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return integerList;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += integerList[i].hashCode();
        }
        return result + size;
    }

    private void sort(int begin, int end) {
        if (this.size != 0) {
            if (begin < end) {
                int partitionIndex = partition(begin, end);

                sort(begin, partitionIndex - 1);
                sort(partitionIndex + 1, end);
            }
        } else {
            throw new NullPointerException();
        }
    }

    private int partition(int begin, int end) {
        int pivot = this.integerList[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (this.integerList[j] <= pivot) {
                i++;

                int temp = this.integerList[i];
                this.integerList[i] = this.integerList[j];
                this.integerList[j] = temp;
            }
        }

        int temp = this.integerList[i + 1];
        this.integerList[i + 1] = this.integerList[end];
        this.integerList[end] = temp;
        return i + 1;
    }

    private int binarySearch(Integer item) {
        int min = 0;
        int max = this.size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(this.integerList[mid])) {
                return mid;
            }

            if (item < this.integerList[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    private void grow() {
        Integer[] temporaryList = integerList;
        integerList = new Integer[integerList.length + integerList.length / 2];
        System.arraycopy(temporaryList, 0, integerList, 0, size);
    }
}
