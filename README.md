# Arrays and Hashing

## Group Anagrams
- O(n^2) solution is to do 2 loops and search for anagrams. Times out in large tests.
- O(n) solution is to maintain list of hashmaps< char, int> uniquely tracking the anagram.
    An additional list< string> will track strings matching the hashmap. Both string and hm list are index mapped.
- Time complexity: O(n), Space: O(n)

## Top K Frequent Elements
- Given a integer array, return top k Frequent elements.
- Give solution better than O(n^2) time.
- Uses a combination of hashmap and modified bucket sort.
- HM maintains Item:Frequency
- Bucket size is the total array size. 
    - Bucket index is frequency. Value is list of values matching frequency
- Read hm and fill bucket.
- Finally read bucket in reverse order to return k elements. Note that a list size can be > or < k. Add retval carefully.
- Complexity: Time: O(n), Space: O(n)

## Longest Consecutive Sequence
- Given an array, return the length of longests consecutive sequence.
- Key idea: Imagine numbers in an axis. There will be clusters of consecutive numbers. Start-1 of a sequence wont be present in the input. This is the test to identify sequence start. Example 1,2,3,4. Start-1 is 0. Wont be there in input.
- Create a hashset and store all numbers. Loop over the input and see if entry-1 is in the hashset. If so, this is start of sequence. Count the continuous entry in hashset. Return the max count.
- Complexity: Time: O(n), Space: O(n)

## Encode decode a string
- Problem: Complete encode() and decode() methods for network transmission. 
- Use Len:String format to transmit. Since the len can be 200 (size 3),len size =3.
- Java: String.format for int to fixed length str, Integer.parseInt for str -> int 
- substring method is [) start - inclusive, end -exclusive.
- Kept string type instead of converting to char array which is more efficient but tricky given the method return types.

# Linked List
## Reverse singly linked list
- 2 pointer solution. prev = head, curr = head.next while (curr!=null) {swap pointers; if curr.next != null, set newhead}
- Recursive time and space are O(n) and iterative O(n) time and O(1) space solutions possible. 

## Reorder List
- Problem: You are given the head of a singly linked-list. The list can be represented as:
-      L0 → L1 → … → Ln - 1 → Ln
-      Reorder the list to be on the following form:
-      L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …

- 1. Split the list into 2. Find middle and split into 2. To find middle, run fast and slow pointers.
- 2. Reverse the second list
- 3. Merge 2 lists.
- 4. Take care of odd/even list size.

## Remove Nth Node From Last
- Problem: Remove nth node from end
- No recursion needed.
- Reverse the list and delete nth node in the rev list is possible. But requires O(n) space. Following is O(1) space.
- 1. Maintain 2 pointers left and right. 
- 2. Right is moved n positions away from left.
- 3. To get the node prev to the node to delete, maintain a dummy pointer at the start.

## LRU Cache
- Problem: LRU Cache limited to a capacity. Evict using LRU policy. Implement get() and set(). Both operations should be in O(1)
- Double linked list of nodes. Head points to MRU and tail points to LRU. Only DLL will yield O(n) 
- Use Hashmap to store pointers to node. HashMap< key, Node>.
- Can have dummy head and tail pointers instead of complex head and tail manipulation.
- In get() 
        -      a) Check in hm. 
        -      b) if exists, move node to head in DLL. 
        -      c) return val or -1
- In set()
        -      b) Check in hm, 
        -      c) if exist, remove node from DLL and hm
        -      a) if capacity reached, evict tail node and remove from hm.
        -      d) create new DLL node and move to head. 
        -      e) Add entry to hm



# Intervals

## Insert intervals
- Problem: Given an array of [start, end] in ascending sorted form and a new interval [start, end], make the intervals non-overlapping entries.
- Since input is already sorted, no external sorting needed.
- 1. Loop on all inputs.
- 2. Track newIntervals with newStart, newEnd.
- 3. Two non-overlapping cases:
    - a. New interval is before current entry. If so, add new entry and rest of the input (already sorted) to answer and return.
    - b. New interval is after current entry. Add currEntry to answer.
- 4. Overlaps. Find new values for newStart and newEnd using min() and max()
- 5. Add newEntries to answer list.

## Merge intervals
- Problem: Merge intervals into non-overlapping entries. Entries need not be in sorting.
- 0. Visualize with axis diagram.
- 1. Sort the array first. Java: Arrays.sort and custom comparator.
- 2. Add first entry to answer list.
- 3. Compare with previous answer entry only.
- 4. If doesnt overlap, add curr entry to answer
- 5. If overlaps, change the end of previous entry in answer (extending the range use Math.max().)
- Note: Since input is sorted, above step #5 will work.

## Non-overlapping intervals
- Problem: Given a set of intervals, remove min no of intervals so that the input is non-overlapping.
- Greedy approach. 
- Sort the intervals by start. Compare curr interval with prevEnd. If overlapping remove the interval with higher endvalue.
- Update prevEnd and loop over all intervals.
- Time: O(nlogn) (for sort) and O(n) for greedy traversal.

## Meeting room
- Problem: Check if intervals overlap. Note: prevEnd == start is valid.
- 1. Sort the array
- 2. if (start < prevEnd) -> Overlaps. Return false.
- 3. Return true if no overlap after all intervals.

## Meeting room II
- Problem: Find min no. of conf rooms required.
- 1. Store sorted start and end separately. And sort them independently.
- 2. Have 2 pointers for start and end arrays. 
- 3. If start entry is less than end entry imples overlapping. Move start and increment result when start pointer moves, 
    decrement when end pointer moves
- 4. Repeat until all intervals are checked.
- 5. Track max count using Math.max(result, count)
- Time: O(nlogn), Space: O(n)

# Sliding window

## Longest Repeating Character Replacement
- Replace k characters in a given string s to get max string length.
- Combination of sliding window and hashmap.
- Hashmap tracks char count in the window. Inc char entry for right ptr move. Decrement char entry for left ptr move.
- Left and right pointers starts from 0.
- Condition: `windowlen ie r-l+1 - maxCharCountInHashmap < k`. If not, move left pointer and adjust hashmap count by s[left]
- Right pointer is moved for each char scan.
- Use max() to track max window size.

# Binary Search

## Find min in rotated sorted array
- Problem: Given a sorted and rotated array, find the min value.
- Use binary search for O(logn). Property: Contents in left sorted array are alway greater than right sorted array
- Track min value seen till now in res. Initialize to arr[0]
- The following condition (arr[left] <= arr[mid]) wont work we have already reached the sorted array.
- So if arr[left] <= arr[right] return min (arr[left], res)
- res = min(arr[mid], res)
- Find if mid is in left or right sorted array. If left subarray, search right. If right, we dont know if left pointer is at the min value. so go left without terminating search. 

## Search in rotated sorted array

- Problem: Array is sorted but rotated. Find a given target location else return -1. Do it in O(logn) not O(n)
- Visualize with a graph. both sorted arrays are separated. Left line (left sorted array) start at higher y than right line (representing right sorted array)
- Solution is to code many discrete cases. Using binary search. The input has 2 sorted array left and right. Build cases around this.
- Like BS, left, mid and right pointers are used. End condition is while (left<=right)
- mid = (l+r)/2. If arr[mid] == target, return mid;
- Idea is to check if the mid is in left or right sorted array.
      - If arr[left] <= arr[mid] - mid is in left sorted array. 
          - Should you go left or right. Compare target with arr[mid]. 
          - If target > arr[mid], go right. so l = mid+1;
          - Else - we've 2 choices target is left of mid or right. compare target with arr[left]
              - if (target < arr[left]) go right. left = mid +1 else go left right = mid - 1;
      - Else // mid is in right sorted array. Do the exact reverse checking and direction movement as above.

## Timebased KVS
- Problem: Cache storing multiple values across timestamps. Implement get() and set(). If get(ts) doesnt match, return floor of ts value or ""
- Java: Use HashMap< String, TreeMap< Integer, String>> to store sorted timestamps in the cache.
- Java: Use floorKeys() to get <= ts key.
- Alternatively, you can implement a binary search over ts array to find the nearest entry. Edgecases to be handled carefully as BS returns 1 index more or less than the target if not present. Another edge case is to handle non-presence of entry and return -1 safely esp when mid is close to 0;
- However handcrafted BS approach failed in large testcases due to time limit error. 20k sets + 20k gets.

# Stack
## Generate Parenthesis
- Use backtracking via recursion (implicit stack).
- Write a separate backtrack method to handle recursion
- In java, use class properties to store each string entry (aka) stack and result list. To eliminate passing values to method.

- Exit condition: `openVal == closedVal == total`
- Insert Open brace when `openVal < total`
- Insert Closed brace when `closedVal < openVal`
- Pop last char from stack after backtrack call (to clear the global string for each subtree path)

# Trees
## Level order
- Problem: Given a binary tree, return list of entries in each level.
- 1. Run BFS on the tree. Use Queue ie LinkedList< TreeNode>.
- 2. Add root to Q before looping. Loop till Q not empty.
- 3. Dequeue all elements and store it array< TreeNode>. For each element in this array, add values to results.
- 4. If left and right pointers are not null, add to Q.

## Invert Binary Tree
- Problem: Tree mirroring. Swap left and right subtrees.
- 1. Run DFS either postorder or preorder.
- 2. Swap left and right pointers of the node.
- 3. Recurse on left and right subtrees.

## Is Same Binary Tree
- Problem: Given 2 binary trees, check if they are same.
- 3 lines of recursive solution.
- 1. if (p == null && q == null) return true;
- 2. if (p == null || q == null) return false;
- 3. return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);


### Alternative:
- 1. Run DFS either preorder or postorder.
- 2. Maintain 2 lists, one for each tree. Walk the tree and add entries to the list.
- 3. Compare 2 lists.
- Note: It is necessary to add null entries to the list to determine correct structure of the tree.

## Is Balanced Binary Tree
- Problem: Given a binary tree, say if it is height balanced []
- 1. Run postorder DFS. Maintain 2 retvals (isBalanced, height) in recursive DFS.
- 1a. Bottom solution. Subtree heights and isBalanced are calculated. Hence tree traversed at O(n)
- 2. Define class Pair to hold (boolean isBalance, int height)
- 3. Base condition: if (node == null) return [True, 0] - 0 height and balanced.
- 4. Pair left =  dfs (node.left); Pair right =  dfs (node.right);
- 5. boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
- 6. int height = 1 + Math.max(left.height, right.height);

## Max diameter of binary tree
- Problem: Find the longest path (no. of edges thru a node) in the tree. May or may not be via root.
- Run postorder DFS. Find height of each subtree (1+max(left st, right st)).
- Find diameter of a node as dia = LeftHeight + RightHeight + 2 (2 = no. of edges from that node)
- Maintain a max = 0 to track max diameter. max = max (max, nodediameter)
- IMPORTANT: in base case, return -1 instead of 0 for the diameter math to workout.

## Is Valid BST
- Problem: BST: all entries in left subtree is < node.val < all entries in right subtree.
- Problem: Given root, find if it is a BST.
- 1. Run preorder DFS. Have 2 pointers (left = -infinity, right = +infinity).
- 2. Base condition: if (!node) return true
- 3. if !( (left < node.val) and (node.val < right)) return false.
- 4. return dfs(node.left, left, node.val) && dfs(node.left, node.val, right);
- 5. Invoke dfs (root, -inf, +inf)
- 6. NOTE: Change min, max type from int to Long to pass the edgecases.

## Kth Min value in a BST
 - Do in-order BST. Maintain a count during traversal. Return element once count is reached.
 - Track count and min outside of recursive function.


## Find Lowest Common Ancestor
- Problem: Find lowest common ancestor, given 2 values; p, q.
- Run preorder DFS and solve distint cases.
- Case 1: If p, q < node.val, search left subtree.
- Case 2: If p, q > node.val, search right subtree.
- Case 3: If p or q < node.val, there is a split. One of them is in right subtree. So return node as lca.
- Case 4: If p or q = node.val, return node as lca.


# Bit operations
## Missing numbers
- Problem: Find missing no. between [0,n].
- Two solutions: 
    - 1. Sum between 0 to n+1 using loop or arithmetic progression n*n+1/2 and subtract the sum by the sum of no. in array.
    - 2. Find XOR of no. betweeen 0-n. Find XOR of no. in input array. XOR those 2 values gives missing no.
- Time: O(n), Space: O(1)
- Other solutions: Sort and iterate: O(nlogn), Hashset: Space - O(n), likely sorth binary search: O(nlogn)

## Reverse bits
- 1. Java has no unsigned int. Doesnt affect the output. Use Integer.toBinaryString to print binary equivalent of int.
- 2. Loop for 32 times and >> input by 1 and get LSB using & 1. Then OR this LSB with << 1 out val.
- 3. Loop exit condition: while (i < 32 && n != 0)
- 4. Add extra 0s to out if n=0 but i< 32. Run additional loop.
- Time: O(1), Space O(1)

## Count no. of 1s
- Problem: Count no of 1s.
- 1. Java specific: Use & instead of mod. use >>> for unsigned instead of >>
- 2. Get LSB of n using & 1 and >>>1. If LSB = 1, count++

## Count bits
- Problem: Given a no. return an ans array where ans[i] contains the no. of #1 in i.
- 1. Count 1s using hammingweight and add the result to ans array.

## Sum of 2 integers without +/-
- Problem: Add 2 numbers without using + and -
- 1. Use AND op and << 1 to preserve carry.
- 2. Apply above with XOR input.
- 3. Repeat above until carry/remainder is 0.
