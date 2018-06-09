package ListModel;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 基于数组实现的list
 * @param <E>
 */
public class IArrayList<E> implements IList<E> {
    private Object[] data;  //数据存储数组
    private int size;       //元素个数

    public IArrayList() {
        this.data = new Object[0];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

    @Override
    public boolean add(E e) {
        checkObject(e);
        ensoureCapacity();
        this.data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object e) {
        checkObject(e);
        boolean bool = false;
        if (contains(e)) {
            int index = indexOf(e);
            remove(index);
            bool = true;
        }
        return bool;
    }

    @Override
    public void clear() {
        this.data = new Object[0];
        this.size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) this.data[index];
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);
        E da = (E) this.data[index];
        this.data[index] = e;
        return da;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        for (int i = index; i < size-1; ) {
            this.data[i] = this.data[++i];
        }
        E value = (E) this.data[size - 1];
        size--;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        checkObject(o);
        int index=-1;
        for (int i = 0; i < data.length; i++) {
            if (o.equals(data[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        checkObject(o);
        int index=-1;
        for (int i = data.length - 1; i >= 0; i--) {
            if (o.equals(data[i])) {
                index=i;
                break;
            }
        }
        return index;
    }

    private void checkObject(Object o) {
        if (o == null) {
            throw new NullPointerException("元素为null");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 检查是否需要扩容，如果需要则扩容原先的2倍
     *
     */
    private void ensoureCapacity() {
        if (data.length <= size + 1) {
            Object[] temp = Arrays.copyOf(this.data, this.data.length);
            this.data = new Object[temp.length * 2];
            for (int i = 0; i < temp.length; i++) {
                this.data[i] = temp[i];
            }
        }
    }
}
