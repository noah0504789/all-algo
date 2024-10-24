import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, ans = 0;
    private static int[] check;
    private static MyCircularlyLinkedList<Integer> list;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        check = new int[m];
        
        for (int i = 0; i < m; i++) check[i] = readInt();
        
        list = new MyCircularlyLinkedList<>();
        
        for (int i = 0; i < n; i++) list.add(i, i+1);
        
        for (int numToFind : check) {  
            boolean isLeft = list.check(numToFind);
            
            while (list.peek() != numToFind) {
                if (isLeft) list.leftShift();                    
                else list.rightShift();
                
                ans++;
            }  
                                                                 
            list.poll();
        }
        
        System.out.print(ans);
    }        
    
    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }
        
        return r;
    }    
    
    static class MyCircularlyLinkedList<T> {
        class Node {
            final T data;
            Node prev, next;

            public Node(T data) {
                this.data = data;
            }
        }

        private Node head, tail;
        private int cnt;
        
        public T peek() {
            return head.data;
        }
        
        public void poll() {
            removeFirst();
        }
        
        public boolean check(int numToFind) {
            Node target = head;
            for (int i = 0; i <= cnt / 2; i++) {
                if (target.data.equals(numToFind)) return true;
                
                target = target.next;
            }
            
            return false;
        }
        
        public void leftShift() {
            T val = head.data;
            removeFirst();
            addLast(val);
        }
        
        public void rightShift() {
            T val = tail.data; 
            removeLast();
            addFirst(val);
        }

        public Node searchByIdx(int idx) {
            emptyCheck();
            rangeCheck(idx);
            
            Node target = head;
            for (int i = 0; i < idx; i++) target = target.next;
            
            return target;
        }

        public Node searchByVal(T val) {
            emptyCheck();
            
            Node target = head;
            do {
                if (target.data == val) return target;
                 target = target.next;                   
            } while (target != head);
                        
            throw new IllegalStateException("");
        }

        public void add(int idx, T val) {
            rangeCheckForAdd(idx);            
            
            if (idx == 0) {
                addFirst(val);
                return;
            } else if (idx == cnt) {
                addLast(val);
                return;
            }
            
            Node newNode = new Node(val);
            Node prev = searchByIdx(idx);
            
            newNode.prev = prev;
            newNode.next = prev.next;
            prev.next = newNode;
            prev.next.prev = newNode;
            
            cnt++;
        }

        public void remove(T val) {
            emptyCheck();
            
            if (head.data.equals(val)) {
                removeFirst();
                return;                  
            } else if (tail.data.equals(val)) {
                removeLast();
                return;                  
            }
            
            Node target = searchByVal(val);
            if (target == null) throw new IllegalStateException("");
            
            target.next.prev = target.prev;
            target.prev.next = target.next;
            
            cnt--;
        }

        private void addFirst(T val) {
            Node newNode = new Node(val);
            
            if (cnt > 0) {
                newNode.prev = tail;
                newNode.next = head;
                head.prev = newNode;
                tail.next = newNode;
                head = head.prev;    
            } else {
                head = tail = newNode;
                head.next = head.prev = head;
            }
            
            cnt++;
        }

        private void addLast(T val) {
            Node newNode = new Node(val);
            
            if (cnt > 0) {
                newNode.next = head;
                newNode.prev = tail;
                tail.next = newNode;
                head.prev = newNode;
                tail = tail.next;
            } else {
                head = tail = newNode;
                head.next = head.prev = head;
            }
            
            cnt++;
        }

        private void removeFirst() {
            if (cnt > 1) {
                head = head.next;
                head.prev = tail;
                tail.next = head;    
            } else {
                head = tail = null;
            }

            cnt--;
        }

        private void removeLast() {
            if (cnt > 1) {                
                tail = tail.prev;
                tail.next = head;
                head.prev = tail;
            } else {
                head = tail = null;
            }
            
            cnt--;
        }

        private void rangeCheck(int idx) {
            if (idx < 0 || idx >= cnt) throw new IllegalStateException("");
        }

        private void rangeCheckForAdd(int idx) {
            if (idx < 0 || idx > cnt) throw new IllegalStateException("");
        }

        private void emptyCheck() {
            if (head == null || tail == null) throw new IllegalStateException("");
        }
    }    
}