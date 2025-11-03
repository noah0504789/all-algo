import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long ans;
    private static int[] arr, l, r;
    private static long[] pSum;
    private static Stack<Integer> stack;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        pSum = new long[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            if (i > 0) pSum[i] = pSum[i-1];
            pSum[i] += arr[i];
        }
        
        l = new int[n]; // 값이 나보다 작으면서 가장 내 왼쪽에 있는 인덱스
        r = new int[n]; // 값이 나보다 작으면서 가장 내 오른쪽에 있는 인덱스
        
        stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            l[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        stack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            r[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        ans = 0;
        for (int i = 0; i < n; i++) {
            int left = l[i]+1, right= r[i]-1;
            
            long sum = pSum[right] - (left>0 ? pSum[left-1] : 0);
            long score = sum * arr[i];
            if (score > ans) ans = score;
        }

        System.out.print(ans);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}