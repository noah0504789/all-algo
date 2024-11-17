import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static Queue<Integer> queue;
    private static BitSet visited;
    private static int[] weights;
    private static int wCnt, bCnt, next, bead;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        wCnt = readInt();

        visited = new BitSet();
        queue = new ArrayDeque<>();
        weights = new int[wCnt];

        for (int i = 0; i < wCnt; i++) {
            weights[i] = readInt();
            next = 0;

            while ((next = visited.nextSetBit(next)) != -1) {
                queue.offer(Math.abs(weights[i] - next));
                queue.offer(weights[i] + next);

                next++;
            }

            visited.set(weights[i]);

            while (!queue.isEmpty()) visited.set(queue.poll());
        }

        bCnt = readInt();

        for (int i = 0; i < bCnt; i++) {
            bead = readInt();

            bw.write(visited.get(bead) ? "Y " : "N ");
        }


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
