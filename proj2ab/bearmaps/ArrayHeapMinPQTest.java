package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3, 3);
        minPQ.add(5, 1);
        minPQ.add(2, 4);
        assertEquals(3, minPQ.size());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3, 3);
        minPQ.add(5, 1);
        minPQ.add(2, 4);
        assertTrue(minPQ.contains(2));
        assertFalse(minPQ.contains(8));
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3, 3);
        minPQ.add(5, 1);
        minPQ.add(2, 4);
        //System.out.print(minPQ.getSmallest());
        Integer result = 5;
        assertTrue(result.equals(minPQ.getSmallest()));
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3, 3);
        minPQ.add(5, 1);
        minPQ.add(2, 4);
        Integer result = 5;
        assertTrue(result.equals(minPQ.removeSmallest()));
        assertEquals(2, minPQ.size());
        result = 3;
        assertTrue(result.equals(minPQ.getSmallest()));
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3, 3);
        minPQ.add(5, 1);
        minPQ.add(2, 4);
        minPQ.changePriority(5, 4);
        minPQ.changePriority(2, 1);
        Integer result = 2;
        assertTrue(result.equals(minPQ.getSmallest()));
    }


}
