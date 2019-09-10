/**
 * Created by shawn on 3/27/2017.
 */
public class Stack<T> {
    private Node<T> top;
    private int size;

    public Stack() {
        top = new Node<T>(null, null);
        size = 0;
    }
    private static class Node<T> {
        public T       data;
        public Node<T> next;
        public Node(T t, Node<T> next) {
            this.data = t;
            this.next = next;
        }
    }
    public boolean isEmpty() {
        return (size == 0);
    }
    public T peek() {
        if (isEmpty()){
            throw new java.util.EmptyStackException();
        }
        return top.data;
    }
    public void push(T t) {
        Node<T> newTop = new Node<T>(t, null);
        if (isEmpty()){
            top = newTop;
        }
        else{
            newTop.next = top;
            top = newTop;
        }
        size += 1;
    }
    public T pop() {
        if (isEmpty()){
            throw new java.util.EmptyStackException();
        }
        T t = top.data;
        top = top.next;
        size -= 1;
        return t;
    }
    public int size() {
        return size;
    }
}
