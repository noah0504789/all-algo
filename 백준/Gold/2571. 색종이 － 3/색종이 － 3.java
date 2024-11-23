import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static boolean[][] black;
    private static int[][] paper;
    private static int n, x, y, ans, height;

    public static void main(String[] args) throws Exception{
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        black = new boolean[101][101];
        paper = new int[101][101];

        n = readInt();
        while (n-- > 0) {
            x = readInt();
            y = readInt();

            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) black[x+i][y+j] = true;
            }

            for (int i = 1; i <= 100; i++) {
                for (int j = 1; j <= 100; j++) {
                    if (!black[i][j]) continue;

                    paper[i][j] = paper[i][j-1]+1;
                }
            }
        }

        ans = 0;

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                height = paper[i][j];
                for (int k = i; k >= 1; k--) {
                    height = Math.min(height, paper[k][j]);
                    ans = Math.max(ans, height * (i-k+1));
                }
            }
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
