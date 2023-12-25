package DataTypeErrors;

import java.util.*;
// 2975 leetcode

class Solution {
    // When working with ArrayLists dont do "==" because it compares the refrence
    // not the actual value
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        HashSet<Long> hD = new HashSet<>();
        HashSet<Long> vD = new HashSet<>();
        long hf[] = new long[hFences.length + 2];
        long vf[] = new long[vFences.length + 2];
        hf[0] = vf[0] = 1;
        hf[hf.length - 1] = m;
        vf[vf.length - 1] = n;
        for (int i = 1; i < hf.length - 1; i++) {
            hf[i] = hFences[i - 1];
        }
        for (int i = 1; i < vf.length - 1; i++) {
            vf[i] = vFences[i - 1];
        }
        for (int i = 0; i < hf.length; i++) {
            for (int j = i + 1; j < hf.length; j++) {
                hD.add(Math.max(hf[i] - hf[j], hf[j] - hf[i]));
            }
        }
        for (int i = 0; i < vf.length; i++) {
            for (int j = i + 1; j < vf.length; j++) {
                vD.add(Math.max(vf[i] - vf[j], vf[j] - vf[i]));
            }
        }
        long ans;
        ArrayList<Long> hL = new ArrayList<>(hD);
        ArrayList<Long> vL = new ArrayList<>(vD);
        Collections.sort(hL, Collections.reverseOrder());
        Collections.sort(vL, Collections.reverseOrder());
        int h = 0;
        int v = 0;
        while (h != hL.size() && v != vL.size()) {
            if (hL.get(h).equals(vL.get(v))) { // Don't comapare the refrences !!!!
                return (int) (hL.get(h) * vL.get(v) % (long) (1e9 + 7)); // Dont't forget 1e9+7 && extra carefull wiht
                                                                         // datatypes
            } else if (hL.get(h) > vL.get(v)) {
                h++;
            } else {
                v++;
            }
        }
        return -1;
    }
}