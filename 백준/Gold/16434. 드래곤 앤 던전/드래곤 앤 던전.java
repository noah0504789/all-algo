import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, a;
    private static long l, r, m;
    private static int[] types;
    private static int[][] rooms;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        a = readInt();
        
        types = new int[n];
        rooms = new int[n][2];
        for (int i = 0; i < n; i++) {
            types[i] = readInt();
            rooms[i][0] = readInt();  // a
            rooms[i][1] = readInt();  // h
        }
        
        l = 1;
        r = Long.MAX_VALUE/2;
        while (l < r) {
            m = l+((r-l)>>>1);
            if (can(m)) {
                r = m;
            } else {
                l = m+1;
            }
        }
        
        System.out.print(l);
    }
    
    private static boolean can(long max) {
        long hp = max;
        long atk = a;
        for (int i = 0; i < n; i++) {
            if (types[i] == 1) { // 몬스터
                int mA = rooms[i][0], mHP = rooms[i][1];
                
                long mS = (mHP-1)/atk+1, s = mS-1;
                long dmg = s * mA;
                if (hp <= dmg) return false;
                
                hp -= dmg;
            } else { // 포션
                int pA = rooms[i][0], pHP = rooms[i][1];
                atk += pA;
                hp = Math.min(max, hp+pHP);
            }
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