import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static Event[] ev;
    private static TreeMap<Integer,Integer> multiset;
    private static int n, l, h, r, i, size, prevMax, curMax;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
                
        n = readInt();
        
        ev = new Event[n*2];
        for (int i = 0; i < n; i++) {
            l = readInt();
            h = readInt();
            r = readInt();
            
            ev[2*i] = new Event(l, h, 1);
            ev[2*i+1] = new Event(r, h, -1);
        }
        
        Arrays.sort(ev, Comparator.comparingInt(a -> a.x));
        
        multiset = new TreeMap<>();
        add(0);
        
        i = 0;
        prevMax = 0;
        size = ev.length;
        
        while (i < size) {
            int x = ev[i].x;
            
            int j = i;
            for (; j < size && ev[j].x == x; j++) {
                if (ev[j].type == 1) add(ev[j].h);
            }
            
            int k = i;
            for (; k < size && ev[k].x == x; k++) {
                if (ev[k].type == -1) remove(ev[k].h);
            }            
            
            curMax = multiset.lastKey();
            if (curMax != prevMax) {
                sb.append(x+ " "+ curMax + " ");
                prevMax=curMax;
            }
            
            i = k;
        }
        
        System.out.print(sb.toString());
    }
    
    private static void add(int h) {
        multiset.put(h, multiset.getOrDefault(h, 0)+1);
    }
    
    private static void remove(int h) {
        int c = multiset.getOrDefault(h, 0);
        if (c <= 1) multiset.remove(h);
        else multiset.put(h, c-1);        
    }
    
    static class Event {
        int x, h, type;
        Event(int x, int h, int type) {
            this.x = x;
            this.h= h;
            this.type = type;
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