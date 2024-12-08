import java.util.*;
import java.util.stream.*;

class Solution {
    private Map<Integer, List<Integer>> sums;
    private List<int[]> combs;
    private List<Integer> temp, a, b;
    private int[][] dice;
    private int[] tempC, rst, maxCase;
    private int n, m, max;
    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        m = dice[0].length;

        tempC = new int[n/2];
        rst = new int[n/2];
        maxCase = new int[n/2];

        combs = new ArrayList<>();

        comb(0, 0);

        sums = new HashMap<>();
        temp = new ArrayList<>();

        for (int i = 0; i < combs.size(); i++) {
            temp.clear();
            getSubSums(0, 0, combs.get(i));

            Collections.sort(temp);

            sums.put(i, new ArrayList<>(temp));
        }

        max = 0;

        for (Map.Entry<Integer, List<Integer>> entry : sums.entrySet()) {
            int aKey = entry.getKey();

            a = entry.getValue();
            b = sums.get(sums.size()-aKey-1);

            int cnt = 0;
            for (int c : a) cnt += lowerbound(c, b);

            if (cnt > max) {
                max = cnt;
                maxCase = combs.get(aKey);
            }
        }

        if (maxCase != null) {
            for (int i = 0; i < rst.length; i++) rst[i] = maxCase[i]+1;
        }

        return rst;
    }

    private void getSubSums(int idx, int now, int[] comb) {
        if (idx == comb.length) {
            temp.add(now);
            return;
        }

        for (int d : dice[comb[idx]]) getSubSums(idx+1, now+d, comb);
    }

    private void comb(int depth, int start) {
        if (depth == n/2) {
            combs.add(tempC.clone());
            return;
        }

        for (int i = start; i < n; i++) {
            tempC[depth] = i;
            comb(depth+1, i+1);
        }
    }

    private int lowerbound(int target, List<Integer> list) {
        int l = 0, r = list.size()-1;

        while (l <= r) {
            int mid = (l+r)/2;
            if (list.get(mid) < target) l = mid+1;
            else r = mid-1;
        }

        return l;
    }
}