// 2976 Leetcode
// You are given two 0-indexed strings source and target,
//  both of length n and consisting of lowercase English letters.
// You are also given two 0-indexed character arrays original and changed, and an integer array cost,
//  where cost[i] represents the cost of changing the character original[i] to the character changed[i].

// You start with the string source.
//  In one operation, you can pick a character x from the string and change it to the character y at a cost of z if
//   there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

// Return the minimum cost to convert the string source to the string target using any number of operations.
//  If it is impossible to convert source to target, return -1.
// Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] mat = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                mat[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < 26; i++) {
            mat[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            mat[original[i] - 'a'][changed[i] - 'a'] = Math.min(cost[i], mat[original[i] - 'a'][changed[i] - 'a']);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            if (mat[source.charAt(i) - 'a'][target.charAt(i) - 'a'] == Integer.MAX_VALUE) {
                return -1;
            }
            totalCost += mat[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
        }
        return totalCost;
    }
}