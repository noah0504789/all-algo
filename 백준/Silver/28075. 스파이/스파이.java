import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static int[][] arr; // 임무/장소
    private static long cnt;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) arr[i][j] = readInt();
        }
        
        work(0, -1, 0);

        System.out.print(cnt);
    }
    
    private static void work(int day, int prev, int sum) {
        if (day == n) {
            if (sum >= m) cnt++;
            return;
        } else if (sum >= m) {
            cnt += pow(6, n-day);
            return;
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) work(day+1, j, sum + (j == prev ? arr[i][j]/2 : arr[i][j]));
        }
    }
    
    private static long pow(int a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b&1) == 1) res *= a;
            a *= a;
            b >>= 1;
        }
        return res;
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