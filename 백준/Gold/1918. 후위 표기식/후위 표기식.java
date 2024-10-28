import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    
    private static Map<Character, Integer> precedence;
    private static MyStack stack;
    private static char[] expArr;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
                
        expArr = br.readLine().toCharArray();
        
        precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        
        stack = new MyStack(expArr.length);
        
        for (char ch : expArr) {
            if (Character.isUpperCase(ch)) sb.append(ch);
            else if (ch == '(') stack.push(ch);
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                
                if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
            }
            else {
                while (!stack.isEmpty() && stack.peek() != '(' && precedence.get(ch) <= precedence.get(stack.peek())) sb.append(stack.pop());
                
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) sb.append(stack.pop());
        
        System.out.print(sb);
    }
    
    static class MyStack {
        final int size;
        char[] arr;
        int top;
        
        MyStack(int size) {
            this.size = size;
            arr = new char[size];
            top = -1;           
        }
        
        public void push(char item) {
            fullCheck();
            
            arr[++top] = item;
        }
        
        public char pop() {
            emptyCheck();
            
            return arr[top--];
        }
        
        public char peek() {
            emptyCheck();
            
            return arr[top];            
        }
        
        public boolean isEmpty() {
            return top == -1;
        }
        
        public void fullCheck() {
            if (top == size -1) throw new IllegalStateException("");
        }
        
        public void emptyCheck() {
            if (top == -1) throw new IllegalStateException("");
        }
    }
}