import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Integer> list;
    private static PriorityQueue<Integer> pq;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        pq = new PriorityQueue<>();
        list = new ArrayList<>();

        n = readInt();
        for (int i = 0; i < n; i++) list.add(readInt());

        Collections.sort(list);

        ans = 0;

        while (list.size() > 1) {
            list.set(0, list.get(0)-1);
            list.remove(list.size()-1);
            if (list.get(0)==0) list.remove(0);
            ans++;
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
