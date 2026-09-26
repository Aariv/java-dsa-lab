package com.ariv.problemsolving.patterns.heap;

import java.util.PriorityQueue;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        // Create a min heap with the first k elements of the array
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // Iterate through the rest of the array
        for (int i = k; i < nums.length; i++) {
            // If the current element is greater than the smallest element in the heap
            if (nums[i] > minHeap.peek()) {
                // Remove the smallest element and add the current element to the heap
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        // The root of the heap is the kth largest element
        return minHeap.peek();
    }
}
