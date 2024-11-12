import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Node[] arr;
    private static int n, max;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        arr = new Node[n];

        for (int i = 0; i < n; i++) arr[i] = new Node(readInt(), i);

        Arrays.sort(arr);

        max = 0;

        for (int i = 0; i < n; i++) max = Math.max(max, arr[i].idx - i);

        bw.write(String.valueOf(max+1));

        bw.flush();
    }

    static class Node implements Comparable<Node> {
        final int val, idx;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.val, o.val);
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
}
