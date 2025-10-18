import java.io.*;
import java.util.*;

public class Main {
    
    private static boolean flag;
    private static int n, m, k, ramp;
    private static int[] deg, ramps;
    private static int[][] sw;    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        deg = new int[m+1];
        sw = new int[n+1][];
        
        for (int i = 1; i <= n; i++) {
            k = readInt();
            ramps = new int[k];
            for (int j = 0; j < k; j++) {
                ramp = readInt();
                ramps[j] = ramp;
                deg[ramp]++;
            }
            sw[i] = ramps;
        }
        
        for (int i = 1; i <= n; i++) {
            flag = true;
            for (int ramp : sw[i]) {
                if (deg[ramp] == 1) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                System.out.print(1);
                return;
            }
        }

        System.out.print(0);
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