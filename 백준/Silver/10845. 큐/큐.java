import java.io.*;
import java.util.*;

public class Main {
        
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static MyCircularQueue queue;
    private static String cmd;
    private static int n, result;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        queue = new MyCircularQueue(10000);
        
        n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {            
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            
            if (cmd.equals("push")) {
                queue.enqueue(Integer.parseInt(st.nextToken()));
            } else {                                
                result = -1;
                
                if (cmd.equals("front")) {
                    if (queue.size() > 0) result = queue.peekFirst();                    
                } else if (cmd.equals("back")) {
                    if (queue.size() > 0) result = queue.peekLast();
                } else if (cmd.equals("pop")) {
                    if (queue.size() > 0) result = queue.pollFirst();
                } else if (cmd.equals("size")) {
                    result = queue.size();
                } else if (cmd.equals("empty")) {
                    result = queue.size() == 0 ? 1 : 0;
                }
                
                sb.append(result).append("\n");
            }
        }
        
        System.out.print(sb);
    }
    
    static class MyCircularQueue {
        private int[] arr;
        private int front, rear;

        public MyCircularQueue(int size) {
            this.arr = new int[size];            
        }

        public void enqueue(int item) {
            //fullCheck();
            
            arr[rear++] = item;
            
            if (rear == arr.length) rear %= arr.length;
        }

        public int pollFirst() {
            emptyCheck();
            
            int result = arr[front++];
            
            if (front == arr.length) front %= arr.length;
            
            return result;
        }
        
        public int peekFirst() {
            emptyCheck();
            
            return arr[front];
        }
        
        public int peekLast() {
            emptyCheck();
            
            return arr[(rear-1+arr.length)%arr.length];
        }        

        public int size() {
            return (rear - front + arr.length) % arr.length;
        }

        private void emptyCheck() {
            if (rear == front) throw new IllegalStateException("");
        }

        private void fullCheck() {
            if ((rear+1) % arr.length == front) throw new IllegalStateException("");
        }
    }    
}