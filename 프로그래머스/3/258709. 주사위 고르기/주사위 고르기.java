import java.util.*;

class Solution {
    private StringBuilder sb;
    private Map<String, Memo> map;
    private List<int[]> dPerms;
    private int[][] dice;
    private int[] selectedA, selectedB, pTemp;
    private int n, m;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        m = dice[0].length;

        sb = new StringBuilder();
        map = new HashMap<>();
        dPerms = new ArrayList<>();

        selectedA = new int[n/2];
        selectedB = new int[n/2];
        pTemp = new int[n/2];

        permDup(n/2, 0);
        comb(0, 1);

        return getMax();
    }

    private void comb(int depth, int start) {
        if (depth == n/2) {
            selectB();

            String aKey = getKey(selectedA), bKey = getKey(selectedB);

            if (map.containsKey(aKey) || map.containsKey(bKey)) return;

            int win = 0, lose = 0, draw = 0;

            for (int[] permA : dPerms) {
                int a = 0;
                for (int i = 0; i < n / 2; i++) a += dice[selectedA[i] - 1][permA[i]];

                for (int[] permB : dPerms) {
                    int b = 0;
                    for (int i = 0; i < n / 2; i++) b += dice[selectedB[i] - 1][permB[i]];

                    if (a > b) win++;
                    else if (a ==b) draw++;
                    else if (a < b) lose++;
                }
            }

            map.put(aKey, new Memo(win, draw, lose, (double) win / (win+draw+lose)));
            map.put(bKey, new Memo(lose, draw, win, (double) lose / (win+draw+lose)));

            return;
        }

        for (int i = start; i <= n; i++) {
            selectedA[depth] = i;
            comb(depth+1, i+1);
        }
    }

    private void permDup(int k, int depth) {
        if (depth == k) {
            dPerms.add(pTemp.clone());
            return;
        }

        for (int i = 0; i < m; i++) {
            pTemp[depth] = i;
            permDup(k, depth+1);
        }
    }

    private void selectB() {
        int bitmask = (1 << (n+1)) - 1;

        for (int a : selectedA) bitmask &= ~(1<<a);

        int idx = 0;

        for (int i = 1; i <= n; i++) {
            if ((bitmask & (1<<i)) > 0) selectedB[idx++] = i;
        }
    }

    private String getKey(int[] arr) {
        sb.setLength(0);

        sb.append(arr[0]+"");

        if (arr.length == 1) return sb.toString();

        for (int i = 1; i < arr.length; i++) sb.append(", ").append(arr[i]+"");

        return sb.toString();
    }

    private int[] getMax() {
        String key = map.entrySet().stream()
                .sorted(Map.Entry.<String, Memo>comparingByValue().thenComparing(Map.Entry::getKey))
                .findFirst().get().getKey();

        return Arrays.stream(key.split(", ")).mapToInt(Integer::parseInt).toArray();
    }

    class Memo implements Comparable<Memo> {
        final int win, draw, lose;
        final double percent;

        public Memo(int win, int draw, int lose, double percent) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
            this.percent = percent;
        }

        @Override
        public int compareTo(Memo o) {
            return Double.compare(o.percent, this.percent);
        }
    }
}