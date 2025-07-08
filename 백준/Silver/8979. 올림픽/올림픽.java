import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, rank;     
    private static Nation[] nations;
    private static Nation prev, cur;
       
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt()-1;
        
        nations = new Nation[n];
               
        for (int i = 0; i < n; i++) {
            nations[i] = new Nation(readInt()-1, readInt(), readInt(), readInt());           
        }
        
        Arrays.sort(nations);
        
        rank = 1;
        
        for (int i = 0; i < n; i++) {
            cur = nations[i];
            
            if (i > 0) {
                prev = nations[i-1];
                                
                if (!cur.isSameRank(prev)) rank++;
            }
            
            if (cur.num != k) continue;
            
            System.out.print(rank);
            return;
        }
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
    
    static class Nation implements Comparable<Nation> {
        int num;
        int g, s, b;
        
        public Nation(int num, int g, int s, int b) {
            this.num= num;
            this.g= g;
            this.s= s;
            this.b= b;
        }
        
        public boolean isSameRank(Nation other) {
            return this.g == other.g && this.s == other.s && this.b == other.b;
        }
        
        @Override
        public int compareTo(Nation other) {
            if (this.g != other.g) return other.g - this.g;
            if (this.s != other.s) return other.s - this.s;
            
            return other.b - this.b;
        }
    }
}
