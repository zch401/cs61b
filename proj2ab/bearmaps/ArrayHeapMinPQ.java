package bearmaps;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private PriorityNode[] items;
    private int rFactor = 2;
    private int size;
    private int capacity; //size of array
    private Map<T, Integer> map; //map that stores the position of each key

    public ArrayHeapMinPQ() {
        items = new ArrayHeapMinPQ.PriorityNode[9];
        capacity = 8;
        size = 0;
        map = new HashMap<>();
    }

    @Override
    /**return true is the item is in the minPQ*/
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    /**add items to the tree*/
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }

        PriorityNode addition = new PriorityNode(item, priority);
        items[size + 1] = addition;
        size += 1;
        map.put(item, size);
        moveUp(addition);
        if (size == capacity) {
            resize(size * rFactor);
        }
    }

    /**move the current node to upper level of the tree*/
    private void moveUp(PriorityNode Node) {
        while (map.get(Node.item) > 1) {
            int pos = map.get(Node.item);
            int parent = parent(pos);
            PriorityNode parentNode = items[parent];
            if (items[pos].priority < parentNode.priority) {
                swap(pos, parent);
            } else {
                break;
            }
        }
    }

    /**swap position of node1 and node2*/
    private void swap(int node1Pos, int node2Pos) {
        PriorityNode temp = items[node1Pos];
        items[node1Pos] = items[node2Pos];
        items[node2Pos] = temp;
        map.put(items[node1Pos].item, node1Pos);
        map.put(items[node2Pos].item, node2Pos);
    }

    @Override
    /**return the smallest item and remove it*/
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T smallest = items[1].item;
        PriorityNode lastNode = items[size];
        map.put(lastNode.item, 1);
        items[1] = lastNode;
        items[size] = null;
        size -= 1;
        moveDown(lastNode);
        if (size < (capacity / 4)) {
            resize(capacity / rFactor);
        }
        return smallest;
    }

    /**move the current node to the lower level of the tree*/
    private void moveDown(PriorityNode Node) {
        while (leftChild(map.get(Node.item)) <= size) {
            int pos = map.get(Node.item);
            int leftChild = leftChild(pos);
            int rightChild = rightChild(pos);
            if ((items[pos].priority > items[leftChild].priority) || (items[pos].priority > items[rightChild].priority)) {
                if ( items[rightChild] == null || items[leftChild].priority <= items[rightChild].priority ) {
                    swap(pos, leftChild);
                } else {
                    swap(pos, rightChild);
                }
            } else {
                break;
            }
        }
    }

    /**resize the array to size i + 1*/
    public void resize(int i) {
        PriorityNode[] temp = new ArrayHeapMinPQ.PriorityNode[i + 1];
        for (int j = 1; j < size; j++) {
            temp[j] = items[j];
        }
        items = temp;
        capacity = i;
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return items[1].item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int pos = map.get(item);
        double oldPriority = items[pos].priority;
        items[pos].priority = priority;
        if (oldPriority < priority) {
            moveDown(items[pos]);
        } else {
            moveUp(items[pos]);
        }
    }

    private int leftChild(int i) {
        return i * 2;
    }

    private int rightChild(int i) {
        return i * 2 + 1;
    }

    private int parent(int i) {
        return (int) i / 2;
    }

    private class PriorityNode{
        T item;
        double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

    }


}
