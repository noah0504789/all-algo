import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans;
    private static int[] time, fin;
    private static int[][] prereq;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        time = new int[n+1];
        prereq = new int[n+1][];
        for (int k = 1; k <= n; k++) {
            int t = readInt();
            int cnt = readInt();
            time[k] = t;
            prereq[k] = new int[cnt];
            for (int i = 0; i < cnt; i++) prereq[k][i] = readInt();
        }
        
        fin = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int need = 0;
            for (int p : prereq[i]) need = Math.max(need, fin[p]);
            fin[i] = need+time[i];
            ans = Math.max(ans, fin[i]);
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