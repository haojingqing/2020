package bytes;

import java.util.Arrays;

/**
 * @author jingqing
 */
// todo
public class Byte20200703_4 {

//    寻找两个正序数组的中位数
//    给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
//    请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//    你可以假设 nums1 和 nums2 不会同时为空。
//
//    示例 1:
//    nums1 = [1, 3]
//    nums2 = [2]
//    则中位数是 2.0
//    示例 2:
//    nums1 = [1, 2]
//    nums2 = [3, 4]
//    则中位数是 (2 + 3)/2 = 2.5

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3,4};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }

    /*
     * 时间复杂读O(n),还是大,题目要求log(m+n)
     */
    public static float findMiddleNum(int[] arr1, int[] arr2) {

        float middle;
        int arr1Length = arr1.length;
        int arr2Length = arr2.length;

        int[] arr = new int[arr1Length + arr2Length];

        int i = 0, j = 0, k = 0;
        while (i < arr1Length && j < arr2Length) {
            arr[k]=arr1[i] < arr2[j]?arr1[i++] :arr2[j++];
            k++;
        }

        while (i < arr1Length) {
            arr[k++] = arr1[i++];
        }

        while (j < arr2Length) {
            arr[k++] = arr2[j++];
        }

        int length =arr.length;
        if(length % 2 ==0){
            middle = (float) (arr[length / 2] + arr[length / 2 - 1]) /2;
        }else{
            middle = arr[arr.length /2];
        }
        return middle;
    }

        // todo
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2;
                return getKthElement(nums1, nums2, midIndex + 1);
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            }
        }

        public static int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }


}
