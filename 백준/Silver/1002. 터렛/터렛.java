import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        tc = readInt();
        
        for (int i = 0; i < tc; i++) {
            int x1 = readInt(), y1 = readInt(), r1 = readInt();
            int x2 = readInt(), y2 = readInt(), r2 = readInt();
            int ans = 0;
            
            int dx = x1 - x2, dy = y1 - y2;
            int dSquare = dx*dx + dy*dy; // 두 원의 중심 거리 제곱
            int sumRSquare = (r1 + r2)*(r1 + r2);
            int diffRSquare = (r1 - r2)*(r1 - r2);
            
            if (dSquare == 0 && r1==r2) ans = -1; // 일치하면 -1
            else if (dSquare == 0 && r1 != r2) ans = 0;           // 중심 같고 반지름 다름
            else if (sumRSquare == dSquare || diffRSquare == dSquare) ans = 1;
            else if (diffRSquare < dSquare && dSquare < sumRSquare) ans = 2;
            else ans = 0;
            
            sb.append(ans+"\n");
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
