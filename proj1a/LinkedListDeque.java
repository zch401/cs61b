public class LinkedListDeque<T> implements Deque<T> {
    private class LLDNode {
        LLDNode prev;
        T item;
        LLDNode next;

        public LLDNode(LLDNode front, T n, LLDNode back) {
            prev = front;
            item = n;
            next = back;
        }
    }

    private LLDNode sentinel;
    private int size;

    /**create empty linked list deque*/
    public LinkedListDeque() {
        sentinel = new LLDNode (null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**add item to front of the deque*/
    @Override
    public void addFirst(T item) {
        LLDNode temp = new LLDNode(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size = size + 1;
    }

    /**add item to back of the deque*/
    @Override
    public void addLast(T item) {
        LLDNode temp = new LLDNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size = size + 1;
    }

    /**return if deque is empty*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**number of items in the deque*/
    @Override
    public int size() {
        return size;
    }

    /**print items in the deque*/
    @Override
    public void printDeque() {
        LLDNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**remove and return item at front of deque*/
    @Override
    public T removeFirst() {
        /**no first item*/
        if (isEmpty()) {
            return null;
        }

        T result = sentinel.next.item;
        LLDNode target = sentinel.next.next;
        LLDNode temp = new LLDNode(sentinel, target.item, target.next);
        sentinel.next = temp;
        size = size - 1;
        return result;
    }

    /**remove and return the item at back of the deque*/
    @Override
    public T removeLast() {
        /**no last item*/
        if (isEmpty()) {
            return null;
        }

        T result = sentinel.prev.item;
        LLDNode target = sentinel.prev.prev;
        LLDNode temp = new LLDNode(target.prev, target.item, sentinel);
        sentinel.prev = temp;
        size = size - 1;
        return result;
    }

    /**get item at given index with iteration*/
    @Override
    public T get(int index) {
        /**if index item does not exist*/
        if (index > size - 1) {
            return null;
        }

        int num = 0;
        LLDNode p = sentinel.next;
        while (num != index) {
            p = p.next;
            num = num + 1;
        }
        return p.item;
    }

    /**create a deep copy of other*/
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new LLDNode (null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size() ; i++ ) {
            addLast((T) other.get(i));
        }
    }

    /**get item at given index with recursion*/
    public T getRecursive(int index) {
        /**if index item does not exist*/
        if (index > size - 1) {
            return null;
        }

        return getHelper(sentinel.next, index);
    }

    /**helper program to get item*/
    public T getHelper(LLDNode node, int index) {
        if (index == 0) {
            return node.item;
        }

        return getHelper(node.next, index - 1);
    }
}
