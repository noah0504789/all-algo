import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int g, c, p;
    private static boolean flag;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        g = readInt();
        
        c = 2;
        p = 1;
        
        while (c <= g) {
            int val = c*c - p*p;
            if (val == g) {
                sb.append(c).append("\n");
                flag = true;
                c++;
                p++;
            } else if (val > g && c > p+1) {
                p++;
            } else {
                c++;
            }
        }

        System.out.print(flag ? sb : "-1");
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

