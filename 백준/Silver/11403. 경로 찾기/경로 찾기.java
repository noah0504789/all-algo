import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, v;
    private static UDG udg;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        
        udg = new UDG(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (readInt() == 1) udg.addEdge(i, j);                
            }
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {            
                if (udg.getAdj(i).get(k)) {
                    udg.getAdj(i).or(udg.getAdj(k));
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) sb.append(udg.hasEdge(i, j) ? "1 " : "0 ");
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    static class UDG {
        private int vCnt;
        private BitSet[] adj;
        
        UDG(int n) {
            this.vCnt = n;
            
            this.adj = new BitSet[vCnt];
            for (int i = 0; i < vCnt; i++) adj[i] = new BitSet(vCnt);
        }
        
        public void addEdge(int s, int d) {
            adj[s].set(d);
        }
        
        public boolean hasEdge(int s, int d) {
            return adj[s].get(d);
        }
        
        public void removeEdge(int s, int d) {
            adj[s].clear(d);
        }
        
        public BitSet getAdj(int v) {
            return adj[v];
        }
        
        public int[] getAdj2(int v) {
            return IntStream.range(0, vCnt).filter(i -> adj[v].get(i)).toArray();
        }
        
        public int getDegree(int v) {
            return adj[v].cardinality();
        }
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