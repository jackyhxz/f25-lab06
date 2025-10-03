package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;
    private List<Integer> capacityList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        //mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
        capacityList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
        assertEquals(mQueue.size(), 0);
    }

    @Test
    public void testNotEmpty() {
        assertFalse(!mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertEquals(mQueue.peek(), null);
    }

    @Test
    public void testPeekNoEmptyQueue() {
        mQueue.enqueue(2);
        assertEquals(mQueue.peek(), Integer.valueOf(2));
        mQueue.dequeue();
    }

    @Test
    public void testClear() {
        mQueue.enqueue(2);
        mQueue.clear();
        assertEquals(mQueue.peek(), null);
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
            assertFalse(mQueue.isEmpty());
        }
    }

    @Test
    public void testDequeue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
        }
        for (int i = 0; i < testList.size(); i++) {
            assertFalse(mQueue.isEmpty());
            assertEquals(testList.size() - i, mQueue.size());
            assertEquals(testList.get(i), mQueue.dequeue());
            assertEquals(testList.size() - i - 1, mQueue.size());
        }
        assertEquals(null, mQueue.dequeue());
    }


    @Test
    public void testCapacity() {
        for (int i = 0; i < capacityList.size(); i++) {
            mQueue.enqueue(capacityList.get(i));
            assertEquals(capacityList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
            assertFalse(mQueue.isEmpty());
        }
        assertEquals(capacityList.get(0), mQueue.dequeue());
        mQueue.enqueue(114);

        // now head = 1, size = 10, try to resize
        mQueue.enqueue(514);

        // now do the asserts
        assertEquals(capacityList.get(1), mQueue.dequeue());
        assertEquals(capacityList.get(2), mQueue.dequeue());
        assertEquals(capacityList.get(3), mQueue.dequeue());
        assertEquals(capacityList.get(4), mQueue.dequeue());
        assertEquals(capacityList.get(5), mQueue.dequeue());
        assertEquals(capacityList.get(6), mQueue.dequeue());
        assertEquals(capacityList.get(7), mQueue.dequeue());
        assertEquals(capacityList.get(8), mQueue.dequeue());
        assertEquals(capacityList.get(9), mQueue.dequeue());
        assertEquals(Integer.valueOf(114), mQueue.dequeue());
        assertEquals(Integer.valueOf(514), mQueue.dequeue());
        assertEquals(null, mQueue.dequeue());
        
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }


}
