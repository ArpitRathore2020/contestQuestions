// The Imos method, or "imos" as it is commonly referred to, is named after the Japanese mathematician and computer scientist Harumi Imai. The technique is widely used in competitive programming and algorithmic contests, and it gained recognition due to its effectiveness in handling range queries or updates in an array efficiently.
// Leetcode 2982

class imos {
    public int maximumLength(String str) {

        // IMOS technique

        char s[] = str.toCharArray();
        int n = s.length;
        int freq[][] = new int[26][n + 2]; // n+2 to access n+1 for len+1

        int begin = 0, end = 0;
        while (end < n) {
            while (end < n && s[begin] == s[end])
                end++;
            int len = end - begin; // [begin,end) therefore no +1

            // Addtion
            freq[s[begin] - 'a'][0] += 3;
            // Corrections
            freq[s[begin] - 'a'][len - 1] -= 1;
            freq[s[begin] - 'a'][len] -= 1;
            freq[s[begin] - 'a'][len + 1] -= 1;

            begin = end;
        }

        // Populate (For values < ans, the freq will be incorrect but it doesn't matter)
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++) {
                freq[i][j] += freq[i][j - 1];
            }
        }

        // Return the largest length (iterate right to left)
        int max = -1;
        for (int i = 0; i < 26; i++) {
            for (int j = n; j >= 1; j--) {
                if (freq[i][j] >= 3) {
                    max = Math.max(max, j);
                    break;
                }
            }
        }

        return max;
    }
}