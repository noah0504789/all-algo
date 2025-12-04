import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int tc = 5, b1, b2, b3, k1, k2;    
    private static int[] tArr, moves;
    private static boolean[][] win;
    
    public static void main(String... args) throws IOException {
        b1 = readInt();
        b2 = readInt();
        b3 = readInt();
        
        moves = new int[]{b1, b2, b3};
        
        tArr = new int[tc];
        while (tc-- > 0) {
            k1 = readInt();
            k2 = readInt();
            
            win = new boolean[k1+1][k2+1];
            
            for (int a = 0; a <= k1; a++) {
                for (int b = 0; b <= k2; b++) {
                    boolean res = false;
                    
                    for (int mv : moves) {
                        if (a - mv >= 0 && !win[a-mv][b]) {res = true; break;}
                    }
                    
                    if (!res) {
                        for (int mv : moves) {
                            if (b - mv >= 0 && !win[a][b-mv]) {res = true; break;}
                        }
                    }
                    
                    win[a][b] = res;
                }    
            }            
            
            sb.append(win[k1][k2] ? "A" : "B").append("\n");
        }
        
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