import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder(), sb2 = new StringBuilder();
    private static int n, a, b, len;
    private static int[] lisVal, lisIdx, parent;
    private static int[][] wires;
    private static boolean[] inLis;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        wires = new int[n][2];
        for (int i = 0; i < n; i++) {
            a = readInt();
            b = readInt();
            wires[i][0] = a;
            wires[i][1] = b;
        }
        
        Arrays.sort(wires, Comparator.comparingInt(o -> o[0]));
        
        lisVal = new int[n];
        lisIdx = new int[n];
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            b = wires[i][1];
            int pos = lowerBound(len, b);
            lisVal[pos] = b;
            lisIdx[pos] = i;
            parent[i] = pos>0?lisIdx[pos-1] : -1;
            
            if (pos == len) len++;
        }
        
        inLis = new boolean[n];
        int idx = lisIdx[len-1];
        while (idx != -1) {
            inLis[idx] = true;
            idx = parent[idx];
        }
        
        sb.append(n-len).append("\n");
        for (int i = 0; i < n; i++) {
            if (!inLis[i]) sb.append(wires[i][0]).append("\n");
        }

        System.out.print(sb);
    }
    
    private static int lowerBound(int r, int x) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (lisVal[m] >= x) r = m;
            else l = m+1;
        }
        return l;
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