import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] card1, card2;
    private static long ans, tmp;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        card1 = new long[n+1];
        card2 = new long[n+1];
        
        for (int i = 1; i <= n/2; i++) {
            card1[i] = card1[i-1] + readInt();
            card2[i] = card2[i-1] + readInt();
        }
        
        ans = card1[n/2];
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) tmp = card1[i/2+1] + card2[n/2-1] - card2[i/2]; // 나일 때 밑장빼기
            else tmp = card1[i/2] + card2[n/2] - card2[i/2];
            
            ans = Math.max(ans, tmp);
        }

        bw.write(ans+"");
        bw.flush();
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
}