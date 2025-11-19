import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, INF = Integer.MAX_VALUE;
    private static int[] arr;
    private static int[][] attack = {
        {9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}
    };
    private static long[][][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[3];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        dp = new long[61][61][61];

        System.out.print(dfs(arr[0], arr[1], arr[2]));
    }
    
    private static long dfs(int a_, int b_, int c_) {
        int[] sort = new int[]{a_, b_, c_};
        desc(sort);
        
        int a = sort[0], b = sort[1], c = sort[2];
        
        if (a<=0) return 0;
        else if (b<=0) {b=0; c=0;}
        else if (c<=0) {c=0;}
        
        if (dp[a][b][c] > 0) return dp[a][b][c];
        
        long min = INF;
        for (int[] att : attack) {            
            min = Math.min(min, 1+dfs(a-att[0], b-att[1], c-att[2]));
        }
        
        return dp[a][b][c] = min;
    }   
    
    private static void desc(int[] arr) {
        Arrays.sort(arr);
        int l = 0, r = arr.length-1;
        while (l < r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
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