class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        long count = 0; // dont mistakenly make it int here
        while (left + 1 < n && nums[left] < nums[left + 1]) {
            left++;
        }
        while (right - 1 > -1 && nums[right - 1] < nums[right]) {
            right--;
        }
        if (left == n - 1) { // Array is sorted
            return (n * (n + 1)) / 2;
        }
        count += n - right + 1; // If subarrays [0...x] are removed where x >= right-1
        for (int l = 0; l <= left; l++) { // If subarrays [l+1...x] are removed where x >= right-1
            while (right != n && nums[l] >= nums[right]) {
                right++; // right can be permanently changed because [0...left] is sorted
            }
            count += n - right + 1;
        }
        return count;
    }
}