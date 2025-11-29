import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, r, c;
    private static long ans, INF = Long.MAX_VALUE;;
    private static long[] ps;
    private static int[][] checkPoint; // 현재점에서 패스할 경우
    
    public static void main(String... args) throws IOException {
        n = readInt();
        checkPoint = new int[n][2];
        for (int i = 0; i < n; i++) {
            c = readInt();
            r = readInt();
            
            checkPoint[i][0] = r;
            checkPoint[i][1] = c;            
        }
        
        ps = new long[n];
        ps[1] = dist(checkPoint[0], checkPoint[1]);        
        for (int i = 2; i < n; i++) ps[i] = ps[i-1] + dist(checkPoint[i-1], checkPoint[i]);
        ans = INF;
        for (int i = 1; i < n-1; i++) {            
            long straight = dist(checkPoint[i-1], checkPoint[i+1]);
            
            ans = Math.min(ans, ps[i-1]+ps[n-1] - ps[i+1] + straight);
        }

        System.out.print(n == 3 ? dist(checkPoint[0], checkPoint[2]) : ans);
    }
    
    private static long dist(int[] a, int[] b) {
        return Math.abs(a[1]-b[1]) + Math.abs(a[0]-b[0]);
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