import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static boolean[][] visited;

    // 동, 북, 서, 남 (왼쪽으로 회전하는 순서)
    private static int[][] dir = {
        {0, 1},   // 동쪽:   r, c+1
        {-1, 0},  // 북쪽:   r-1, c
        {0, -1},  // 서쪽:   r, c-1
        {1, 0}    // 남쪽:   r+1, c
    };

    public static void main(String... args) throws IOException {
        N = readInt(); // x방향 길이
        M = readInt(); // y방향 길이

        visited = new boolean[M][N]; // [row][col] = [y쪽(M)][x쪽(N)]

        // 시작: 남서쪽 (0,0) → 배열 기준 (M-1, 0)
        int r = M - 1;
        int c = 0;
        int d = 0; // 처음엔 동쪽

        int total = N * M;

        // total번 방문하는데, 마지막 칸은 움직이지 않고 끝나니까
        // total-1번만 이동하면 됨
        for (int cnt = 1; cnt < total; cnt++) {
            visited[r][c] = true;

            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            // 밖으로 나가거나 이미 방문한 칸 -> 왼쪽으로 회전
            if (nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc]) {
                d = (d + 1) % 4;
                nr = r + dir[d][0];
                nc = c + dir[d][1];
            }

            r = nr;
            c = nc;
        }

        // r, c 가 나선의 끝 위치 (배열 좌표)

        int x = c;          // 동쪽으로 간 칸 수
        int y = M - 1 - r;  // 남쪽에서부터 위로 센 칸 수

        System.out.println(x + " " + y);
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
            r = r * 10 + (c - '0');
            c = System.in.read();
        }
        return negative ? -r : r;
    }
}
