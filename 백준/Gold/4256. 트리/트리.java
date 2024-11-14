import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] preorder, inorder, inorderIdx;
    private static int tc, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        for (int i = 0; i < tc; i++) {
            n = readInt();

            preorder = new int[n];
            for (int j = 0; j < n; j++) preorder[j] = readInt();

            inorder = new int[n];
            inorderIdx = new int[1001];
            for (int j = 0; j < n; j++) inorderIdx[inorder[j] = readInt()] = j;

            postOrder(0, n-1, 0, n-1);

            bw.write("\n");
        }

        bw.flush();
    }

    public static void postOrder(int ps, int pe, int is, int ie) throws IOException {
        if (ps > pe || is > ie) return;

        int root = preorder[ps];
        int rootIdxInorder = inorderIdx[root];
        int length = rootIdxInorder - is;

        postOrder(ps+1, ps+length, is, rootIdxInorder-1);
        postOrder(ps+length+1, pe, rootIdxInorder+1, ie);

        bw.write(root + " ");
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
