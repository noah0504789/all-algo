import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, max = 60;
    private static int[] arr;
    private static int[][] PERMS = {
        {9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}
    }; 
    private static int[][][] dp;
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        arr = new int[3];        
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        desc(arr);
        
        dp = new int[max+1][max+1][max+1];
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j <= max; j++) Arrays.fill(dp[i][j], -1);
        }
        
        queue = new LinkedList<>();
        dp[arr[0]][arr[1]][arr[2]] = 0;
        queue.offer(new int[]{arr[0],arr[1],arr[2]});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int d = dp[cur[0]][cur[1]][cur[2]];
            if (cur[0] == 0 && cur[1] == 0 && cur[2] == 0) {
                System.out.print(d);
                return;
            }
            
            for (int[] p : PERMS) {
                int a = Math.max(0, cur[0]-p[0]);
                int b = Math.max(0, cur[1]-p[1]);
                int c = Math.max(0, cur[2]-p[2]);
                
                int[] next = new int[]{a, b, c};
                desc(next);
                
                if (dp[next[0]][next[1]][next[2]] == -1) {
                    dp[next[0]][next[1]][next[2]] = d+1;
                    queue.offer(next);
                }
            }
        }
    }
    
    private static void desc(int[] arr) {
        Arrays.sort(arr);
        reverse(arr);
    }
    
    private static void reverse(int[] arr) {
        int i = 0, j = arr.length-1;
        while (i < j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++;
            j--;
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