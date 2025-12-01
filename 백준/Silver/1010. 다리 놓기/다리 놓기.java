import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int tc, n, r, maxN;
    private static int[][] comb, tArr; // n, r
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        tArr = new int[tc][2];
        for (int i = 0; i < tc; i++) {            
            tArr[i][1] = readInt();
            tArr[i][0] = readInt();            
            
            maxN = Math.max(maxN, tArr[i][0]);
        }
        
        comb = new int[maxN+1][maxN+1];
        comb[0][0] = 1;
        for (int i = 1; i <= maxN; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
        }
        
        for (int i = 0; i < tc; i++) sb.append(comb[tArr[i][0]][tArr[i][1]] + "\n");
        
        System.out.print(sb);
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