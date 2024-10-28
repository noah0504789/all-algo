import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static int[] seq;
    private static int n;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        seq = new int[n];

        for (int i = 0; i < n; i++) seq[i] = readInt();
        
        int curNum = 1;
        MyStack stack = new MyStack(n);
        
        for (int i = 0; i < n; i++) {            
            while (curNum <= seq[i]) {
                stack.push(curNum++);    
                sb.append("+").append("\n");
            }
            
            if (stack.peek() == seq[i]) {
                stack.pop();
                sb.append("-").append("\n");
            } else {
                System.out.print("NO");
                System.exit(0);
            }
        }
        
        System.out.print(sb);
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
    
    static class MyStack {
        final int size;
        int[] arr;        
        int top;
        
        MyStack(int size) {
            this.size = size;
            arr = new int[size];
            top = -1;
        }
        
        public void push(int item) {
            fullCheck();
            
            arr[++top] = item;
        }
        
        public int peek() {
            emptyCheck();
            
            return arr[top];
        }
        
        public int pop() {
            emptyCheck();
            
            return arr[top--];
        }
        
        private void emptyCheck() {
            if (top == -1) throw new IllegalStateException("");
        }
        
        private void fullCheck() {
            if (top == size-1) throw new IllegalStateException("");
        }
    }
}