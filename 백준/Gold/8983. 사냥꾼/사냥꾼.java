import java.io.*;
import java.util.*;

public class Main {
    
    private static int m, n, l, x, y;
    private static long dist, kill;
    private static int[] xArr;
    private static Map<Long, Integer> map; // 거리/갯수
    
    public static void main(String... args) throws IOException {
        m = readInt();
        n = readInt();
        l = readInt();
                
        xArr = new int[m];
        for (int i = 0; i < m; i++) xArr[i] = readInt();
        Arrays.sort(xArr);
        
        kill = 0;
        for (int i = 0; i < n; i++) {
            int ax = readInt();
            int ay = readInt();
            
            if (ay > l) continue;
            
            int idx = lowerBound(ax);
            
            if (idx < m) {
                long dist = dist(xArr[idx], ax, ay);
                if (dist <= l) { kill++; continue; }
            }
            
            if (idx-1 >= 0) {
                long dist = dist(xArr[idx-1], ax, ay);
                if (dist <= l) { kill++; }
            }
        }
        
        System.out.print(kill);
    }
    
    private static int lowerBound(int x) {
        int l = 0, r = xArr.length;
        while (l < r) {
            int mid = (l+r)>>>1;
            if (xArr[mid] >= x) r = mid;
            else l = mid+1;
        }
        return l;
    }
    
    private static long dist(int xx, int x, int y) {
        return Math.abs(xx-x) + y;
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