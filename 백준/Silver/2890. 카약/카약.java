import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static char[][] board;
    private static int[] boatRank;
    private static boolean flag;
    private static int n, m, rank, boat;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        for (int i = 0; i < n; i++) board[i] = br.readLine().toCharArray();

        rank = 1;
        boatRank = new int[n+1];
        for (int col = m-2; col >= 1; col--) {
            flag = false;
            for (int row = 0; row < n; row++) {
                boat = board[row][col] - '0';
                if (boat >= 1 && boat <= 9 && boatRank[boat] == 0) {
                    boatRank[boat] = rank;
                    flag = true;
                }
            }

            if (flag) rank++;
        }

        for (int i = 1; i <= 9; i++) bw.write(boatRank[i]+"\n");

        bw.flush();
    }
}
