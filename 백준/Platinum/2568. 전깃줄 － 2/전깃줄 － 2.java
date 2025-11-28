import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int n, len, cnt;
    private static int[] lisIdx, tails, parent;
    private static int[][] wires;
    private static boolean[] isLis;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        wires = new int[n][2];
        for (int i =0; i < n; i++) {
            wires[i][0] = readInt();
            wires[i][1] = readInt();
        }
        
        Arrays.sort(wires, Comparator.comparingInt(o -> o[0]));
        
        lisIdx = new int[n];
        tails = new int[n];
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            int x = wires[i][1];
            int pos = lowerBound(len, x);
            tails[pos] = x;
            lisIdx[pos] = i;
            parent[i] = pos-1>=0?lisIdx[pos-1] : -1;
            if (len == pos) len++;
        }
        
        isLis = new boolean[n];
        int idx = lisIdx[len-1];
        while (idx != -1) {
            isLis[idx] = true;
            idx = parent[idx];
        }
        
        sb.append((n-len)+"\n");
        
        for (int i = 0; i <n; i++) {
            if (!isLis[i]) sb.append(wires[i][0]+"\n");
        }

        System.out.print(sb);
    }
    
    private static int lowerBound(int r, int x) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (tails[m]>=x) r=m;
            else l=m+1;
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