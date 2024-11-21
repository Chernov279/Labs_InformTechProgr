package lab6;

import java.util.EmptyStackException;

public class Stack<T> {
    private T[] data;
    private int size;
    private int capacity;

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public void push(T element) {
        if (size == capacity) {
            throw new StackOverflowError("Стек переполнен");
        }
        data[size++] = element;
    }

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T element = data[--size];
        data[size] = null;
        return element;
    }

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public class StackTest {
        public static void main(String[] args) {
            Stack<Integer> someStack = new Stack<>(5);

            someStack.push(10);
            someStack.push(20);
            someStack.push(30);
            System.out.println("Peek element: " + someStack.peek()); // 30
            System.out.println("Popped element: " + someStack.pop()); // 30
            System.out.println("Peek element: : " + someStack.peek()); // 20

            someStack.push(40);
            System.out.println("Peek element: " + someStack.peek()); // 40

            Stack<String> someStack2 = new Stack<>(3);

            someStack2.push("apple");
            someStack2.push("banana");
            someStack2.push("orange");

            System.out.println("Peek element: " + someStack2.peek()); // "orange"
            System.out.println("Popped element: " + someStack2.pop()); // "orange"
            System.out.println("Peek element: " + someStack2.peek()); // "banana"

            someStack2.push("kiwi");
            System.out.println("Peek element: " + someStack2.peek()); // "kiwi"
            someStack2.push("cherry"); // StackOverflowError
        }
    }
}