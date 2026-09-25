# Coding Problem-Solving Pattern Notebook

A handwritten-first roadmap for learning the reusable patterns behind coding interview problems.

The goal is not to memorize solutions. The goal is to build this thinking loop:

```text
Read the problem
    ↓
Identify the constraints and recognition signals
    ↓
Recognize the likely pattern
    ↓
Explain the brute-force approach
    ↓
Derive the optimized approach
    ↓
Dry-run it by hand
    ↓
Write the code only after the logic is clear
```

## Progress Legend

- [ ] Not started
- [🟡] Logic understood, but cannot reproduce independently yet
- [x] Can explain, dry-run, and solve without referring to notes

> In GitHub Markdown, use `[x]` for completed items. Use the Notes column to record review status and mistakes.

## Notebook Template for Every Problem

Use two handwritten pages for each problem.

```text
Problem Name:
Pattern:

Problem Description:

Input:
Output:

Key Constraints:
What do the constraints tell me?

Recognition Signals:

Brute-Force Approach:
Time Complexity:
Space Complexity:
Why is it insufficient?

Optimized Approach:
Why does it work?

Handwritten Dry Run:

Pseudocode:

Time Complexity:
Space Complexity:

Edge Cases:

Common Mistakes:

Generic Pattern Template:

Similar Problems / Variations:

Can I explain it without seeing the solution? Yes / No
Can I solve it again from a blank page? Yes / No
```

---

# Phase 1: The 25 Anchor Problems

## A. Hashing

### 1. Two Sum

- [ ] **Status:** Not started
- **Pattern:** HashMap lookup
- **Description:** Given an integer array and a target, return the indices of two distinct numbers whose sum equals the target.
- **Input:** `nums = [2, 7, 11, 15]`, `target = 9`
- **Output:** `[0, 1]`
- **Key constraints:**
  - `2 <= nums.length <= 10^4`
  - `-10^9 <= nums[i], target <= 10^9`
  - Exactly one valid answer exists.
  - The same array element cannot be used twice.
- **Recognition signals:** Pair, target sum, indices, fast complement lookup.
- **Core question:** Have I already seen `target - current`?
- **Target complexity:** `O(n)` time, `O(n)` space.
- **Review notes:**

### 2. Contains Duplicate

- [ ] **Status:** Not started
- **Pattern:** HashSet membership
- **Description:** Return `true` if any value occurs at least twice in the array; otherwise return `false`.
- **Input:** `nums = [1, 2, 3, 1]`
- **Output:** `true`
- **Key constraints:**
  - `1 <= nums.length <= 10^5`
  - `-10^9 <= nums[i] <= 10^9`
- **Recognition signals:** Duplicate, seen before, repeated value, membership test.
- **Core question:** Have I seen this value before?
- **Target complexity:** `O(n)` time, `O(n)` space.
- **Review notes:**

### 3. Valid Anagram

- [ ] **Status:** Not started
- **Pattern:** Frequency counting
- **Description:** Determine whether one string can be formed by rearranging all characters of another string.
- **Input:** `s = "anagram"`, `t = "nagaram"`
- **Output:** `true`
- **Key constraints:**
  - `1 <= s.length, t.length <= 5 * 10^4`
  - Standard version uses lowercase English letters.
- **Recognition signals:** Same characters, same frequency, rearrangement.
- **Core question:** Do both strings have exactly the same character counts?
- **Target complexity:** `O(n)` time, `O(1)` space for a fixed alphabet.
- **Review notes:**

### 4. Group Anagrams

- [ ] **Status:** Not started
- **Pattern:** HashMap with a canonical signature
- **Description:** Group strings that contain the same characters with the same frequencies, regardless of their order.
- **Input:** `strs = ["eat", "tea", "tan", "ate", "nat", "bat"]`
- **Output:** `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]` in any group order.
- **Key constraints:**
  - `1 <= strs.length <= 10^4`
  - `0 <= strs[i].length <= 100`
  - Standard version uses lowercase English letters.
- **Recognition signals:** Group equivalent items, common key, signature.
- **Core question:** What identical key can represent every anagram in a group?
- **Target complexity:** `O(n * k)` with a frequency signature, where `k` is the maximum word length.
- **Review notes:**

## B. Two Pointers

### 5. Valid Palindrome

- [ ] **Status:** Not started
- **Pattern:** Opposite-direction two pointers
- **Description:** Determine whether a string reads the same forward and backward after ignoring case and non-alphanumeric characters.
- **Input:** `s = "A man, a plan, a canal: Panama"`
- **Output:** `true`
- **Key constraints:**
  - `1 <= s.length <= 2 * 10^5`
  - The input can contain printable ASCII characters.
- **Recognition signals:** Compare both ends, palindrome, ignore characters.
- **Core question:** After skipping invalid characters, do the left and right characters match?
- **Target complexity:** `O(n)` time, `O(1)` extra space.
- **Review notes:**

### 6. Two Sum II: Input Array Is Sorted

- [ ] **Status:** Not started
- **Pattern:** Opposite-direction two pointers on sorted data
- **Description:** Given a non-decreasing array, return the one-based indices of two numbers whose sum is the target.
- **Input:** `numbers = [2, 7, 11, 15]`, `target = 9`
- **Output:** `[1, 2]`
- **Key constraints:**
  - `2 <= numbers.length <= 3 * 10^4`
  - The array is sorted in non-decreasing order.
  - Exactly one solution exists.
  - Constant extra space is expected.
- **Recognition signals:** Sorted array, pair sum, one answer.
- **Core question:** Is the current sum too small or too large?
- **Target complexity:** `O(n)` time, `O(1)` space.
- **Review notes:**

### 7. Container With Most Water

- [ ] **Status:** Not started
- **Pattern:** Two pointers with elimination
- **Description:** Choose two vertical lines that form a container holding the maximum possible amount of water.
- **Input:** `height = [1, 8, 6, 2, 5, 4, 8, 3, 7]`
- **Output:** `49`
- **Key constraints:**
  - `2 <= height.length <= 10^5`
  - `0 <= height[i] <= 10^4`
- **Recognition signals:** Maximum area, two boundaries, width decreases as pointers move.
- **Core question:** Why is moving the shorter line the only move that may improve the area?
- **Target complexity:** `O(n)` time, `O(1)` space.
- **Review notes:**

### 8. 3Sum

- [ ] **Status:** Not started
- **Pattern:** Sort, fix one value, then use two pointers
- **Description:** Return every unique triplet whose values sum to zero, without duplicate triplets.
- **Input:** `nums = [-1, 0, 1, 2, -1, -4]`
- **Output:** `[[-1, -1, 2], [-1, 0, 1]]`
- **Key constraints:**
  - `3 <= nums.length <= 3000`
  - `-10^5 <= nums[i] <= 10^5`
  - Duplicate triplets must not be returned.
- **Recognition signals:** Three values, target sum, unique combinations.
- **Core question:** After fixing one value, can the remainder be solved as a sorted Two Sum problem?
- **Target complexity:** `O(n^2)` time, excluding output storage.
- **Review notes:**

## C. Sliding Window

### 9. Maximum Sum Subarray of Size K

- [ ] **Status:** Not started
- **Pattern:** Fixed-size sliding window
- **Description:** Find the maximum sum of any contiguous subarray containing exactly `k` elements.
- **Input:** `nums = [2, 1, 5, 1, 3, 2]`, `k = 3`
- **Output:** `9`
- **Key constraints:**
  - `1 <= k <= nums.length`
  - Use a large input assumption such as `nums.length <= 10^5` to rule out recomputing every window.
- **Recognition signals:** Contiguous, exact window size, maximum or minimum aggregate.
- **Core question:** When the window moves, what leaves and what enters?
- **Target complexity:** `O(n)` time, `O(1)` space.
- **Review notes:**

### 10. Longest Substring Without Repeating Characters

- [ ] **Status:** Not started
- **Pattern:** Variable-size sliding window
- **Description:** Find the length of the longest contiguous substring containing no repeated character.
- **Input:** `s = "abcabcbb"`
- **Output:** `3`, from `"abc"`
- **Key constraints:**
  - `0 <= s.length <= 5 * 10^4`
  - The string may contain letters, digits, symbols, and spaces.
- **Recognition signals:** Longest, substring, contiguous, uniqueness condition.
- **Core question:** How do I expand while valid and shrink when a duplicate makes the window invalid?
- **Target complexity:** `O(n)` time, `O(min(n, alphabet))` space.
- **Review notes:**

## D. Prefix Sum

### 11. Range Sum Query: Immutable

- [ ] **Status:** Not started
- **Pattern:** Prefix sum preprocessing
- **Description:** Given an immutable array, answer repeated queries asking for the sum between two inclusive indices.
- **Input:** `nums = [-2, 0, 3, -5, 2, -1]`, query `sumRange(0, 2)`
- **Output:** `1`
- **Key constraints:**
  - `1 <= nums.length <= 10^4`
  - Many calls can be made to `sumRange`.
  - `0 <= left <= right < nums.length`
- **Recognition signals:** Repeated range sum queries, immutable input, preprocessing allowed.
- **Core question:** Can I compute once and reuse the result for every query?
- **Target complexity:** `O(n)` preprocessing and `O(1)` per query.
- **Review notes:**

### 12. Subarray Sum Equals K

- [ ] **Status:** Not started
- **Pattern:** Prefix sum plus frequency HashMap
- **Description:** Count the number of contiguous subarrays whose sum is exactly `k`.
- **Input:** `nums = [1, 1, 1]`, `k = 2`
- **Output:** `2`
- **Key constraints:**
  - `1 <= nums.length <= 2 * 10^4`
  - Negative values and zero are allowed.
  - Count all matching subarrays, not merely whether one exists.
- **Recognition signals:** Contiguous subarray, exact target sum, count occurrences, negatives allowed.
- **Core question:** How many earlier prefix sums equal `currentPrefix - k`?
- **Target complexity:** `O(n)` time, `O(n)` space.
- **Review notes:**

## E. Binary Search

### 13. Binary Search

- [ ] **Status:** Not started
- **Pattern:** Classic binary search
- **Description:** Find a target in a sorted array and return its index, or `-1` if it is absent.
- **Input:** `nums = [-1, 0, 3, 5, 9, 12]`, `target = 9`
- **Output:** `4`
- **Key constraints:**
  - `1 <= nums.length <= 10^4`
  - Values are unique.
  - The array is sorted in ascending order.
  - An `O(log n)` solution is expected.
- **Recognition signals:** Sorted search space, target lookup, logarithmic requirement.
- **Core question:** Which half can be safely eliminated?
- **Target complexity:** `O(log n)` time, `O(1)` space.
- **Review notes:**

### 14. First Bad Version

- [ ] **Status:** Not started
- **Pattern:** Boundary binary search
- **Description:** Versions become bad from one point onward. Find the first bad version while minimizing calls to the checking API.
- **Input:** `n = 5`, with version `4` as the first bad version.
- **Output:** `4`
- **Key constraints:**
  - `1 <= bad <= n`
  - Once a version is bad, every later version is bad.
  - Minimize calls to `isBadVersion(version)`.
- **Recognition signals:** First true, transition point, monotonic condition, `FFFFTTTT`.
- **Core question:** How do I preserve the first possible valid boundary?
- **Target complexity:** `O(log n)` API calls, `O(1)` space.
- **Review notes:**

### 15. Search in Rotated Sorted Array

- [ ] **Status:** Not started
- **Pattern:** Modified binary search
- **Description:** Find a target in a unique, ascending array that was rotated at an unknown pivot.
- **Input:** `nums = [4, 5, 6, 7, 0, 1, 2]`, `target = 0`
- **Output:** `4`
- **Key constraints:**
  - `1 <= nums.length <= 5000`
  - All values are unique.
  - An `O(log n)` solution is expected.
- **Recognition signals:** Rotated sorted array, target search, logarithmic requirement.
- **Core question:** Which half is sorted, and does the target lie inside that half?
- **Target complexity:** `O(log n)` time, `O(1)` space.
- **Review notes:**

## F. Linked Lists

### 16. Reverse Linked List

- [ ] **Status:** Not started
- **Pattern:** Iterative pointer manipulation
- **Description:** Reverse the direction of every link in a singly linked list and return the new head.
- **Input:** `1 -> 2 -> 3 -> 4 -> 5`
- **Output:** `5 -> 4 -> 3 -> 2 -> 1`
- **Key constraints:**
  - The number of nodes may be between `0` and `5000`.
  - An in-place iterative solution uses constant extra space.
- **Recognition signals:** Reverse links, in place, preserve next node before rewiring.
- **Core question:** What must I save before changing `current.next`?
- **Target complexity:** `O(n)` time, `O(1)` extra space.
- **Review notes:**

### 17. Linked List Cycle

- [ ] **Status:** Not started
- **Pattern:** Fast and slow pointers
- **Description:** Determine whether following `next` pointers eventually revisits an earlier node.
- **Input:** `3 -> 2 -> 0 -> -4`, where `-4` points back to node `2`.
- **Output:** `true`
- **Key constraints:**
  - The number of nodes can be as high as `10^4`.
  - Solve using `O(1)` extra space.
- **Recognition signals:** Cycle, repeated traversal, constant-space detection.
- **Core question:** Why must a faster pointer eventually meet a slower pointer inside a cycle?
- **Target complexity:** `O(n)` time, `O(1)` space.
- **Review notes:**

## G. Tree DFS

### 18. Maximum Depth of Binary Tree

- [ ] **Status:** Not started
- **Pattern:** Recursive DFS
- **Description:** Return the number of nodes along the longest path from the root to a leaf.
- **Input:** Tree `[3, 9, 20, null, null, 15, 7]`
- **Output:** `3`
- **Key constraints:**
  - The tree may be empty.
  - Each node must be considered in the general case.
- **Recognition signals:** Height, depth, root-to-leaf path, recursive subtrees.
- **Core question:** What answer should each subtree return to its parent?
- **Target complexity:** `O(n)` time, `O(h)` recursion space.
- **Review notes:**

### 19. Same Tree

- [ ] **Status:** Not started
- **Pattern:** Parallel recursive DFS
- **Description:** Determine whether two binary trees have identical structures and equal values at corresponding nodes.
- **Input:** `p = [1, 2, 3]`, `q = [1, 2, 3]`
- **Output:** `true`
- **Key constraints:**
  - Either tree may be empty.
  - Both structure and node values must match.
- **Recognition signals:** Compare two trees, corresponding nodes, structure and values.
- **Core question:** What are the matching and mismatching base cases?
- **Target complexity:** `O(n)` time, `O(h)` recursion space.
- **Review notes:**

### 20. Lowest Common Ancestor of a Binary Tree

- [ ] **Status:** Not started
- **Pattern:** Post-order DFS with upward information flow
- **Description:** Find the lowest node in a binary tree that has both given nodes as descendants, allowing a node to be its own descendant.
- **Input:** Tree `[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]`, `p = 5`, `q = 1`
- **Output:** Node `3`
- **Key constraints:**
  - Node values are unique.
  - Both target nodes exist in the tree.
  - `p` and `q` are different nodes.
- **Recognition signals:** Two target nodes, ancestor, answers returned from left and right subtrees.
- **Core question:** What does it mean when the left and right recursive calls both return non-null?
- **Target complexity:** `O(n)` time, `O(h)` recursion space.
- **Review notes:**

## H. Breadth-First Search

### 21. Binary Tree Level Order Traversal

- [ ] **Status:** Not started
- **Pattern:** BFS using a queue
- **Description:** Return the values of a binary tree grouped by depth, from the root level downward.
- **Input:** Tree `[3, 9, 20, null, null, 15, 7]`
- **Output:** `[[3], [9, 20], [15, 7]]`
- **Key constraints:**
  - The tree may be empty.
  - Values must be grouped by level.
- **Recognition signals:** Level by level, nearest first, breadth, queue.
- **Core question:** How do I use the queue size to process exactly one level?
- **Target complexity:** `O(n)` time, `O(w)` queue space, where `w` is maximum width.
- **Review notes:**

## I. Heap

### 22. Kth Largest Element in an Array

- [ ] **Status:** Not started
- **Pattern:** Size-`k` min-heap
- **Description:** Return the element that would appear in the `k`th position when the array is ordered from largest to smallest, counting duplicates.
- **Input:** `nums = [3, 2, 1, 5, 6, 4]`, `k = 2`
- **Output:** `5`
- **Key constraints:**
  - `1 <= k <= nums.length`
  - Duplicates count as separate positions.
  - The goal is to understand how to keep only the best `k` candidates.
- **Recognition signals:** Top K, kth largest or smallest, stream of candidates.
- **Core question:** Why does the root of a size-`k` min-heap represent the kth largest value?
- **Target complexity:** `O(n log k)` time, `O(k)` space.
- **Review notes:**

## J. Backtracking

### 23. Subsets

- [ ] **Status:** Not started
- **Pattern:** Include-or-skip backtracking
- **Description:** Generate the power set containing every possible subset of an array of unique values.
- **Input:** `nums = [1, 2, 3]`
- **Output:** `[[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]` in any order.
- **Key constraints:**
  - Values are unique.
  - Every element has two choices: include or skip.
  - The output itself contains `2^n` subsets.
- **Recognition signals:** All combinations, power set, choose or skip.
- **Core question:** What decision does each level of the recursion tree represent?
- **Target complexity:** `O(n * 2^n)` including copied output.
- **Review notes:**

### 24. Permutations

- [ ] **Status:** Not started
- **Pattern:** Choose, explore, undo
- **Description:** Generate every possible ordering of an array of distinct values.
- **Input:** `nums = [1, 2, 3]`
- **Output:** `[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]`
- **Key constraints:**
  - Values are distinct.
  - Each value must be used exactly once in each permutation.
  - The output contains `n!` permutations.
- **Recognition signals:** All orderings, arrange, each item used once.
- **Core question:** How do I track which values are already used in the current path?
- **Target complexity:** `O(n * n!)` including copied output.
- **Review notes:**

### 25. Combination Sum

- [ ] **Status:** Not started
- **Pattern:** Backtracking with controlled reuse
- **Description:** Return all unique combinations of candidate values whose sum equals the target; each candidate may be chosen repeatedly.
- **Input:** `candidates = [2, 3, 6, 7]`, `target = 7`
- **Output:** `[[2, 2, 3], [7]]`
- **Key constraints:**
  - Candidate values are distinct positive integers.
  - A candidate may be reused any number of times.
  - Combinations must be unique, regardless of order.
- **Recognition signals:** All combinations, target sum, reuse allowed, prune when total is too large.
- **Core question:** When reusing a value, why do I recurse from the same index rather than index `+ 1`?
- **Target complexity:** Output-dependent exponential time; recursion depth is bounded by the target and smallest candidate.
- **Review notes:**

---

# Recommended Practice Order

Complete the problems sequentially. Do not move ahead merely because you saw the solution.

## Stage 1: Understand

- [ ] I can restate the problem in my own words.
- [ ] I can identify the input, output, and edge cases.
- [ ] I can explain the brute-force method.
- [ ] I understand why the brute-force method is insufficient.

## Stage 2: Derive

- [ ] I can identify the pattern from the recognition signals.
- [ ] I can derive the optimized logic without copying code.
- [ ] I can explain why every pointer move, lookup, or recursive return is correct.

## Stage 3: Reproduce

- [ ] I can dry-run the algorithm on paper.
- [ ] I can write pseudocode from memory.
- [ ] I can implement it from a blank editor.
- [ ] I can state the time and space complexity correctly.

## Stage 4: Retain

- [ ] I solved it again after one day.
- [ ] I solved it again after one week.
- [ ] I solved a variation without seeing the pattern label.

---

# Constraint Cheat Sheet

Constraints are clues, not absolute rules. The acceptable complexity also depends on the language, constants, input shape, and number of test cases.

```text
n <= 10            Exponential search may be practical
n <= 20            Backtracking, subsets, or bitmasking may be practical
n <= 100           O(n^3) may sometimes be practical
n <= 1,000         O(n^2) may be practical
n <= 10,000        Prefer approximately O(n log n) or better
n <= 100,000       Usually O(n log n) or O(n)
n >= 1,000,000     Usually near O(n)
```

Other important clues:

```text
Sorted input               → Two pointers or binary search
Contiguous subarray/string → Sliding window or prefix sum
Exact fixed window size    → Fixed sliding window
Longest/shortest valid     → Variable sliding window or BFS
Repeated range query       → Prefix sum or preprocessing
Pair and target            → HashMap or two pointers if sorted
Top K / Kth largest        → Heap, selection, or sorting
First/last valid position  → Boundary binary search
Level / nearest / minimum  → BFS
All combinations/orderings → Backtracking
Cycle with constant space  → Fast and slow pointers
```

---

# Completion Dashboard

| Group | Problems | Completed |
|---|---:|---:|
| Hashing | 4 | 0 |
| Two Pointers | 4 | 0 |
| Sliding Window | 2 | 0 |
| Prefix Sum | 2 | 0 |
| Binary Search | 3 | 0 |
| Linked Lists | 2 | 0 |
| Tree DFS | 3 | 0 |
| BFS | 1 | 0 |
| Heap | 1 | 0 |
| Backtracking | 3 | 0 |
| **Total** | **25** | **0** |

## Mastery Check

- [ ] Completed all 25 handwritten notes.
- [ ] Explained every solution without code.
- [ ] Reproduced all optimized approaches from a blank page.
- [ ] Completed spaced reviews.
- [ ] Solved at least one unlabelled variation for every major pattern.

---

# Phase 2: Pattern Variations

Start this only after the 25 anchor problems are reproducible without notes.

## Hashing Variations

- [ ] Ransom Note
- [ ] Top K Frequent Elements
- [ ] Longest Consecutive Sequence
- [ ] Isomorphic Strings

## Two-Pointer Variations

- [ ] Move Zeroes
- [ ] Squares of a Sorted Array
- [ ] Remove Duplicates from Sorted Array
- [ ] Trapping Rain Water

## Sliding-Window Variations

- [ ] Longest Repeating Character Replacement
- [ ] Permutation in String
- [ ] Fruit Into Baskets
- [ ] Minimum Window Substring

## Prefix-Sum Variations

- [ ] Find Pivot Index
- [ ] Product of Array Except Self
- [ ] Binary Subarrays With Sum
- [ ] Continuous Subarray Sum

## Binary-Search Variations

- [ ] Find First and Last Position of Element
- [ ] Find Minimum in Rotated Sorted Array
- [ ] Search a 2D Matrix
- [ ] Koko Eating Bananas

## Linked-List Variations

- [ ] Middle of the Linked List
- [ ] Merge Two Sorted Lists
- [ ] Remove Nth Node From End
- [ ] Reorder List

## Tree Variations

- [ ] Invert Binary Tree
- [ ] Diameter of Binary Tree
- [ ] Balanced Binary Tree
- [ ] Validate Binary Search Tree
- [ ] Kth Smallest Element in a BST
- [ ] Binary Tree Right Side View

## Heap and Backtracking Variations

- [ ] K Closest Points to Origin
- [ ] Task Scheduler
- [ ] Combination Sum II
- [ ] Letter Combinations of a Phone Number
- [ ] Word Search

---

# Phase 3: New Pattern Families

After Phase 2, learn these anchor problems in order.

## Stack and Monotonic Stack

- [ ] Valid Parentheses
- [ ] Min Stack
- [ ] Daily Temperatures
- [ ] Largest Rectangle in Histogram

## Intervals

- [ ] Merge Intervals
- [ ] Insert Interval
- [ ] Non-overlapping Intervals
- [ ] Meeting Rooms II

## Graphs

- [ ] Number of Islands
- [ ] Clone Graph
- [ ] Course Schedule
- [ ] Rotting Oranges
- [ ] Word Ladder

## Greedy

- [ ] Best Time to Buy and Sell Stock
- [ ] Jump Game
- [ ] Gas Station
- [ ] Partition Labels

## Dynamic Programming

- [ ] Climbing Stairs
- [ ] House Robber
- [ ] Coin Change
- [ ] Longest Increasing Subsequence
- [ ] Longest Common Subsequence

## Design-Oriented DSA

- [ ] LRU Cache
- [ ] Time-Based Key-Value Store
- [ ] Design Twitter
- [ ] Design a Rate Limiter

---

# Weekly Reflection

```text
Week:
Problems completed:
Patterns revised:

Problem I explained most clearly:

Problem where I got stuck:

Was the difficulty caused by:
[ ] Understanding the statement
[ ] Recognizing the pattern
[ ] Deriving the logic
[ ] Handling an edge case
[ ] Implementing the logic
[ ] Analyzing complexity

Most important mistake:

Rule I learned from that mistake:

Pattern to revise next:
```

# Definition of Done

A problem is complete only when you can:

1. Restate it without copying the original statement.
2. Identify the recognition signals.
3. Explain the brute-force approach and its cost.
4. Derive the optimized approach.
5. Prove informally why the approach works.
6. Dry-run it with at least one normal case and one edge case.
7. Write pseudocode and working code from a blank page.
8. State time and space complexity.
9. Solve a related variation without being told the pattern.

The end goal is not to remember 25 answers. It is to look at an unfamiliar problem and ask the right questions automatically.
