import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, cnt;
    private static int[][] arr;
    private static Map<Long, Integer> memo;
    private static final int NONE = -1, H = 0, V = 1;
    
    
    public static void main(String... args) throws IOException {
        n = readInt();
                
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
        
        memo = new HashMap<>();
        
        cnt = solve(0,0,n-1,n-1,NONE);

        System.out.print(cnt == 0 ? -1 : cnt);
    }    
    
    private static int solve(int r1, int c1, int r2, int c2, int lastDir) {
        int g = gems(r1, c1, r2, c2), p = imps(r1, c1, r2, c2);
        
        if (p == 0) return g==1 ? 1 : 0;
        if (g == 0) return 0;
        
        long k = key(r1, c1, r2, c2, lastDir);
        Integer m = memo.get(k);
        if (m != null) return m;
        
        int ways = 0;        
        for (int r = r1; r<= r2; r++) {
            for (int c = c1; c<= c2; c++) {
                if (arr[r][c] != 1) continue;
                
                if (lastDir != H && canCutRow(r, c1, c2, c)) {
                    int gTop = gems(r1, c1, r-1, c2), gBot = gems(r+1, c1, r2, c2);
                    if (gTop > 0 && gBot > 0) {
                        int w1 = solve(r1, c1, r-1, c2, H);
                        int w2 = solve(r+1, c1, r2, c2, H);
                        ways += w1*w2;
                    }
                }
                
                if (lastDir != V && canCutCol(c, r1, r2, r)) {
                    int gL = gems(r1, c1, r2, c-1), gR = gems(r1, c+1, r2, c2);
                    if (gL > 0 && gR > 0) {
                        int w1 = solve(r1, c1, r2, c-1, V);
                        int w2 = solve(r1, c+1, r2, c2, V);
                        ways += w1*w2;
                    }
                }
            }    
        }
        
        memo.put(k, ways);
        return ways;
    }
    
    private static int gems(int r1, int c1, int r2, int c2) {
        int k = 0;
        for (int r = r1; r<= r2; r++) {
            for (int c = c1; c<= c2; c++) {
                if (arr[r][c] == 2) k++;
            }    
        }
        return k;
    }
    
    private static int imps(int r1, int c1, int r2, int c2) {
        int k = 0;
        for (int r = r1; r<= r2; r++) {
            for (int c = c1; c<= c2; c++) {
                if (arr[r][c] == 1) k++;
            }    
        }
        return k;
    }    
    
    private static long key(int r1, int c1, int r2, int c2, int last) {
        long k = r1;
        k = (k<<5)^c1;
        k = (k<<5)^r2;
        k = (k<<5)^c2;
        k = (k<<2)^(last+1);
        
        return k;
    }    
    
    private static boolean canCutRow(int r, int c1, int c2, int colImp) {
        for (int c = c1; c<= c2; c++) {
            if (c == colImp) continue;
            if (arr[r][c] != 0) return false;
        }  
        return true;
    }
    
    private static boolean canCutCol(int c, int r1, int r2, int rowImp) {
        for (int r = r1; r<= r2; r++) {
            if (r == rowImp) continue;
            if (arr[r][c] != 0) return false;
        }  
        return true;
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