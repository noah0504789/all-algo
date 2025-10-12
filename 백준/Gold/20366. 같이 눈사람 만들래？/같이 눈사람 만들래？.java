import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
                        
        ans = Integer.MAX_VALUE;
        
        for (int l = 0; l < n-3; l++) {
            for (int r = l+3; r < n; r++) {
                snow(l, r);
                if (ans == 0) break;
            }
        }
        
        System.out.print(ans);
    }
    
    private static void snow(int l, int r) {
        int endsSum = arr[l] + arr[r];
        int lr = l+1, rl = r-1;
        while (lr < rl) {
            int sum = arr[lr] + arr[rl];
            ans = Math.min(ans, Math.abs(endsSum-sum));
            if (sum == endsSum) break;
            else if (sum > endsSum) rl--;
            else lr++;
        }
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