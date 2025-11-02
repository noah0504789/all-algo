import java.io.*;
import java.util.*;

public class Main {
    
    private static int[] dx = {0,0,1,1};
    private static int[] dy = {0,1,1,0};
    
    public static void main(String... args) throws IOException {
        int n = readInt();
        int m = readInt();
        
        int[] res = walk(n, 1, 1, 0, m);
        
        System.out.println(res[0] + " " + res[1]);
    }
    
    private static int[] walk(int n, int x, int y, int dir, int step) {
        if (n == 2) {
            int xpos = ((step-1) + dir) % 4;
            int ypos = ((step-1) + 4 - dir) % 4;
            return new int[]{x+dx[xpos], y+dy[ypos]};
        }
        
        int crit = n/2;
        int curpos = (step-1) / (crit * crit);
        int nxtxpos = (curpos+dir)%4;
        int nxtypos = (curpos+4-dir)%4;
        
        int nxtdir = dir;
        if ((dir%2 == 0 && curpos == 0) || (dir%2==1 && curpos==3)) nxtdir = (nxtdir+1)%4;
        if ((dir%2 == 0 && curpos == 3) || (dir%2==1 && curpos==0)) nxtdir = (nxtdir+3)%4;
        
        return walk(
            n/2,
            x + dx[nxtxpos] * crit,
            y + dy[nxtypos] * crit,
            nxtdir,
            step - curpos * crit * crit
        );
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