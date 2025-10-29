import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    private static int[][] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        
        arr = new int[7][99];
        
        solve(0, n-1, 0, 0);
        
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < n; c++) {
                sb.append(arr[r][c] == 1 ? 'A': 'B');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
    
    private static void solve(int l, int r, int day, int team) {
        if (day == 7 || l > r) return;
        int m = (l+r)>>1;
        for (int i = l; i <= r; i++) arr[day][i] = i <= m ? team : team^1;
        
        solve(l, m, day+1, team^1);
        solve(m+1, r, day+1, team);
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