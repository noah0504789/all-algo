import java.util.*;

class Solution {
    private Map<Integer, List<Integer>> sumMap;
    private List<int[]> combList, maxCase;
    private List<Integer> sumTemp, a, b;
    
    private int[][] dice;
    private int[] combTemp, rst;
    private int n;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        
        combTemp = new int[n/2];
        combList = new ArrayList<>();
        
        comb(0, 0);
        
        sumTemp = new ArrayList<>();
        sumMap = new HashMap<>();
        
        for (int i = 0; i < combList.size(); i++) {
            sumTemp.clear();
        
            combSum(0, 0, combList.get(i));
            
            Collections.sort(sumTemp);
            
            sumMap.put(i, sumTemp);
        }        
        
        int max = 0;
        
        for (Map<Integer, List<Integer>> entry : sumMap.entrySet()) {
            int aKey = entry.getKey();
            a = entry.getValue();
            b = sumMap.get(sumMap.size()-1-aKey);
            
            int cnt = 0;
            for (int sum : a) cnt += lowerbound(c, b);
            
            if (cnt > max) {
                max = cnt;
                maxCase = combList.get(aKey);
            }
        }
        
        rst = new int[n/2];
        
        if (maxCase != null) {
            for (int i = 0; i < n/2; i++) rst[i] = maxCase.get(i)+1;
        }
        
        return rst;
    }
    
    private int lowerbound(int target, List<Integer> list) {
        int l = 0, r = list.size()-1;
        while (l <= r) {
            int mid = (l+r)/2;
            if (list.get(mid) < target) l = mid+1;
            else r = mid -1;
        }
        
        return l;
    }
    
    private void combSum(int idx, int val, int[] comb) {
        if (idx == comb.length) {
            sumTemp.add(val);
            return;
        }
        
        for (int d : dice[comb[idx]]) combSum(idx+1, val+d, comb);
    }
    
    private void comb(int depth, int start) {
        if (depth == n/2) {
            combList.add(combTemp.clone());
            return;
        }
        
        for (int i = start; i < n; i++) {
            combTemp[depth] = i;
            comb(depth+1, i+1);
        }
    }
}