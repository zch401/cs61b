public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int RFACTOR;
    private int R;


    /**create empty array deque*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**calculate nextFirst*/
    private int nextF(int i) {
        int next;
        if (i == 0) {
            next = items.length - 1;
        } else {
            next = i - 1;
        }
        return next;
    }

    /**calculate nextLast*/
    private int nextL(int i) {
        int next;
        if (i == items.length - 1) {
            next = 0;
        } else {
            next = i + 1;
        }
        return next;
    }

    /**resize array, create a larger one*/
    private void resizeL(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int old_size = size;
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < old_size ; i++ ) {
            addFirst((T) get(i));
        }
        items = temp;
    }

    /**resize array, create a smaller one*/
    private void resizeS(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int old_size = size;
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < old_size ; i++ ) {
            addFirst((T) get(i));
        }
        items = temp;

    }

    /**add item to the front of the deque*/
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resizeL(size * RFACTOR);
        }
        items[nextFirst] = item;
        size = size + 1;

        nextFirst = nextF(nextFirst);
    }

    /**add item to the back of deque*/
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resizeL(size * RFACTOR);
        }
        items[nextLast] = item;
        size = size + 1;

        nextLast = nextL(nextLast);
    }

    /**if deque is empty*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**number of items in deque*/
    @Override
    public int size() {
        return size;
    }

    /**print item in the deque from first to last*/
    @Override
    public void printDeque() {
        int pos = nextL(nextFirst);
        while (pos != nextLast) {
            System.out.print(items[pos] + " ");
            pos = nextL(pos);
        }
        System.out.println();
    }

    /**remove and return the item at front of deque*/
    @Override
    public T removeFirst() {
        /**if first item does not exist*/
        if (size == 0) {
            return null;
        }

        R = size / items.length;
        if (R < 0.25) {
            resizeS(items.length / 2);
        }

        int pos = nextL(nextFirst);
        T result = items[pos];
        items[pos] = null;
        size =  size - 1;
        nextFirst = pos;
        return result;
    }

    /**remove and return the item at back of the deque*/
    @Override
    public T removeLast() {
        /**if last item does not exist*/
        if (size == 0) {
            return null;
        }

        R = size / items.length;
        if (R < 0.25) {
            resizeS(items.length / 2);
        }

        int pos = nextF(nextLast);
        T result = items[pos];
        items[pos] = null;
        size =  size - 1;
        nextLast = pos;
        return result;
    }

    /**get item at given index*/
    @Override
    public T get(int index) {
        /**if the index does not exist*/
        if (index >= size ) {
            return null;
        }

        int pos = nextFirst;

        pos = nextL(pos) + index;

        if (pos >= items.length ) {
            pos = pos - items.length;
        }
        return items[pos];
    }

    /**create a deep copy*/
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

        for (int i = 0; i < other.size ; i++ ) {
             addFirst((T) other.get(i));
        }
    }
}
