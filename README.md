# Java Data Structures and Algorithms Lab

A hands-on Java learning repository for implementing data structures and algorithms from first principles.

The goal is not to memorize code. Each phase focuses on understanding:

- the problem a structure solves
- its memory and reference model
- structural invariants
- operation flow
- time and space complexity
- boundary conditions
- implementation trade-offs
- unit-test validation
- practical problem-solving applications

## Project Status

| Phase | Topic | Status |
|---|---|---|
| 1 | Dynamic Array | Complete |
| 2 | Singly Linked List basics | Complete |
| 3 | Doubly Linked List | Complete |
| 4 | Circular Linked List | Complete |
| 5 | Stack, Queue, Circular Queue, and Deque | Next |

## Repository Architecture

```text
java-dsa-lab/
├── pom.xml
├── README.md
├── data-structures/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/ariv/dsa/datastructure/
│       └── test/java/com/ariv/dsa/datastructure/
├── algorithms/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/ariv/dsa/algorithm/
│       └── test/java/com/ariv/dsa/algorithm/
└── problem-solving/
    ├── pom.xml
    └── src/
        ├── main/java/com/ariv/dsa/problem/
        └── test/java/com/ariv/dsa/problem/
```
---
# Phase 1: Dynamic Array

## Objective

Implement a generic, `ArrayList`-like dynamic array without using `ArrayList` internally.

## Memory Model

```text
Logical elements: [A][B][C]
Backing storage:   [A][B][C][ ][ ]
Size:              3
Capacity:          5
```

When the backing storage becomes full, allocate a larger array and copy active elements into it.

```text
Before: [A][B][C]
After:  [A][B][C][ ][ ][ ]
```

## Implemented API

```java
public int size();
public boolean isEmpty();
public int capacity();
public void add(T value);
public void add(int index, T value);
public T get(int index);
public void set(int index, T value);
public T remove(int index);
public boolean remove(T value);
public boolean contains(T value);
public int indexOf(T value);
public int lastIndexOf(T value);
public void clear();
```

## Important Invariants

```text
0 <= size <= elements.length
```

Only positions in the range `[0, size)` contain logical elements. Remaining positions represent unused capacity.

## Insert at Index

```text
Before add(1, X): [A][B][C][ ]
Shift right:       [A][B][B][C]
Insert:            [A][X][B][C]
```

Insertion validation allows `index == size`, because appending at the logical end is valid.

## Remove at Index

```text
Before remove(1): [A][B][C][D]
Shift left:        [A][C][D][D]
Clear old tail:    [A][C][D][ ]
```

## Complexity

| Operation | Complexity |
|---|---:|
| `get(index)` | O(1) |
| `set(index, value)` | O(1) |
| `add(value)` without resize | O(1) |
| `add(value)` with resize | O(n) |
| `add(index, value)` | O(n) |
| `indexOf(value)` | O(n) |
| `lastIndexOf(value)` | O(n) |
| `remove(index)` | O(n) |
| `remove(value)` | O(n) |
| `clear()` | O(n) |

## Main Lesson

```text
Arrays provide direct indexed access, but middle insertions and removals move data.
```

---

# Phase 2: Singly Linked List

## Objective

Learn reference-based storage and traversal without moving existing elements in contiguous backing storage.

## Node Model

```text
[data | next]
```

```text
head
  |
  v
[A|*] -> [B|*] -> [C|null]
```

A node stores its value and a reference to the next node.

## Phase 2 API

```java
public void addFirst(T value);
public void addLast(T value);
public T removeFirst();
public T removeLast();
public T getFirst();
public T getLast();
public boolean contains(T value);
public int size();
public boolean isEmpty();
public void clear();
public void add(int index, T value);
public T remove(int index);
public T get(int index);
public int indexOf(T value);
public void reverse();
```

## Head and Temporary References

`head` is the persistent root reference owned by the list.

```text
head = permanent entry point into the structure
current = temporary traversal cursor
```

During traversal:

```java
Node<T> current = head;
```

Both references initially point to the same node. Reassigning `current` does not reassign `head` because Java copies the reference value.

```text
head ------> A -> B -> C
current ---> A
```

After `current = current.next`:

```text
head ------> A -> B -> C
current --------> B
```

Using `head` itself as the traversal cursor would lose the list's starting reference.

## Multiple Temporary References

Algorithms can maintain any number of temporary references:

```java
Node<T> previous;
Node<T> current;
Node<T> next;
Node<T> slow;
Node<T> fast;
```

These variables are temporary views into the same object graph. They do not copy nodes.

## Insert at Index

```text
Before: A -> B -> C
Insert X at index 1
After:  A -> X -> B -> C
```

Reference updates:

```java
newNode.next = previous.next;
previous.next = newNode;
```

The assignment order matters. The original remainder of the list must be saved before replacing `previous.next`.

## Remove at Index

```text
Before: A -> B -> C -> D
Remove index 2
After:  A -> B -> D
```

Reference update:

```java
previous.next = removed.next;
```

The removed node becomes unreachable from `head` and is eligible for garbage collection.

## Reverse Using Three References

Initial structure:

```text
A -> B -> C -> D -> null
```

Working references:

```text
previous = null
current  = A
next     = B
```

For every node:

1. Save the original next node.
2. Reverse the current node's link.
3. Move `previous` forward.
4. Move `current` forward using the saved reference.

Final structure:

```text
D -> C -> B -> A -> null
```

Then assign:

```java
head = previous;
```

## Complexity

| Operation | Complexity |
|---|---:|
| `addFirst(value)` | O(1) |
| `addLast(value)` without tail | O(n) |
| `removeFirst()` | O(1) |
| `removeLast()` without tail | O(n) |
| `get(index)` | O(n) |
| `add(index, value)` | O(n) |
| `remove(index)` | O(n) |
| `contains(value)` | O(n) |
| `reverse()` | O(n) time, O(1) extra space |

## Main Lesson

```text
Arrays move data.
Linked lists change references.
```

Linked lists gain constant-time operations at the head, but lose constant-time indexed access.

---

# Phase 3: Doubly Linked List

## Objective

Support traversal and structural updates in both directions by adding a previous-node reference and maintaining a tail pointer.

## Node Model

```text
[previous | data | next]
```

```text
head                         tail
  |                            |
  v                            v
null <- A <-> B <-> C <-> D -> null
```

## State

```java
private DoublyNode<T> head;
private DoublyNode<T> tail;
private int size;
```

## Implemented API

```java
public void addFirst(T value);
public void addLast(T value);
public T removeFirst();
public T removeLast();
public T getFirst();
public T getLast();
public boolean contains(T value);
public int size();
public boolean isEmpty();
public void clear();
```

## Important Invariants

For an empty list:

```text
head == null
tail == null
size == 0
```

For a non-empty list:

```text
head.prev == null
tail.next == null
```

For each pair of adjacent nodes:

```text
node.next.prev == node
node.prev.next == node
```

where the referenced neighbor exists.

For a single-element list:

```text
head == tail
head.prev == null
head.next == null
```

## Add Last

```text
Before: null <- A <-> B <-> C -> null
After:  null <- A <-> B <-> C <-> D -> null
```

Reference updates:

```java
newNode.prev = tail;
tail.next = newNode;
tail = newNode;
```

## Remove Last

With a tail and previous reference, the node before the tail is available directly:

```java
tail = tail.prev;
tail.next = null;
```

The single-node case must set both `head` and `tail` to `null`.

## Complexity Improvement

| Operation | Singly Linked List without tail | Doubly Linked List with tail |
|---|---:|---:|
| `addFirst` | O(1) | O(1) |
| `addLast` | O(n) | O(1) |
| `removeFirst` | O(1) | O(1) |
| `removeLast` | O(n) | O(1) |
| `getLast` | O(n) | O(1) |

## Trade-off

```text
Additional previous reference per node
                  ↓
More memory, but faster tail operations and backward traversal
```

## Main Lesson

This phase demonstrates a space-versus-time trade-off. Additional metadata in each node enables operations that are expensive in a singly linked list.

---

# Phase 4: Circular Linked List

## Objective

Build a linked structure whose final node links back to the first node rather than terminating at `null`.

## Structure

```text
head
  |
  v
  A -> B -> C
  ^         |
  |_________|
            ^
            |
           tail
```

## State

```java
private Node<T> head;
private Node<T> tail;
private int size;
```

## Fundamental Invariant

For every non-empty circular list:

```java
tail.next == head
```

For an empty list:

```text
head == null
tail == null
size == 0
```

For a single-node list:

```text
head == tail
head.next == head
tail.next == head
```

## Implemented API

```java
public void addFirst(T value);
public void addLast(T value);
public T removeFirst();
public boolean contains(T value);
public int size();
public boolean isEmpty();
public void clear();
```

Additional index and removal operations may be added as an extension after the invariant is fully understood.

## Add First

For a non-empty list:

```java
newNode.next = head;
head = newNode;
tail.next = head;
```

Updating `tail.next` is mandatory because the first node changed.

## Add Last

For a non-empty list:

```java
tail.next = newNode;
tail = newNode;
tail.next = head;
```

This preserves the ring after the tail changes.

## Circular Traversal

The ordinary linked-list condition is unsafe:

```java
while (current != null)
```

A valid circular list never reaches `null`, so that loop would not terminate.

Use a `do-while` traversal when the list is non-empty:

```java
Node<T> current = head;

do {
    // process current
    current = current.next;
} while (current != head);
```

A `do-while` loop is useful because the head must be processed before checking whether traversal has returned to it.

## Remove First

Single-node case:

```text
head = null
tail = null
```

Multi-node case:

```java
head = head.next;
tail.next = head;
```

The operation must preserve the ring after moving the head.

## Complexity

| Operation | Complexity |
|---|---:|
| `addFirst(value)` | O(1) |
| `addLast(value)` | O(1) |
| `removeFirst()` | O(1) |
| `contains(value)` | O(n) |
| Complete traversal | O(n) |

## Use Cases

- round-robin scheduling
- repeating playlists
- turn rotation
- cyclic task processing

## Main Lesson

```text
Traversal termination depends on the structure's invariant.
```

A normal linked list stops at `null`. A circular linked list stops when traversal returns to its starting node.

---

# Upcoming Roadmap

## Phase 5: Restricted Linear Structures

- stack using a dynamic array
- stack using a linked list
- queue using a linked list
- circular queue using an array
- deque
- overflow and underflow behavior

## Phase 6: Heap and Priority Queue

- binary heap representation
- min heap
- max heap
- priority queue
- heapify up
- heapify down
- heap sort

## Phase 7: Hash-based Structures

- hash table
- hash functions
- bucket indexing
- separate chaining
- collision handling
- load factor
- resizing
- hash set

## Phase 8: Tree Foundations

- binary tree
- binary tree traversal
- binary search tree
- insertion, search, and deletion
- height and depth
- balanced-tree concepts

## Phase 9: Self-balancing Trees

- AVL tree
- LL, RR, LR, and RL rotations
- red-black tree concepts
- insertion repair and recoloring

## Phase 10: Indexing Trees and Trie

- educational B+ tree
- internal and leaf nodes
- node splitting
- linked leaf level
- trie insertion
- exact-word and prefix search
- deletion and autocomplete

## Phase 11: Graph Foundations

- undirected graph
- directed graph
- weighted graph
- unweighted graph
- adjacency list
- adjacency matrix
- edge list

## Phase 12: Search Algorithms

- linear search
- iterative binary search
- recursive binary search
- first and last occurrence

## Phase 13: Elementary Sorting

- bubble sort
- selection sort
- insertion sort
- stability and in-place behavior

## Phase 14: Divide-and-Conquer Sorting

- merge sort
- quick sort
- partition strategies
- recursion trees

## Phase 15: Recursion and Backtracking

- base cases and recursive state
- subsets
- permutations
- combination sum
- maze traversal
- N-Queens

## Phase 16: Graph Traversal

- recursive DFS
- iterative DFS
- BFS
- disconnected graphs
- cycle detection
- topological sorting

## Phase 17: Shortest Paths

- BFS for unweighted graphs
- Dijkstra's algorithm
- Bellman-Ford algorithm
- Floyd-Warshall algorithm
- negative-edge and negative-cycle considerations

## Phase 18: Problem-solving Patterns

- two pointers
- sliding window
- prefix sum
- fast and slow pointers
- monotonic stack
- binary search on answer
- heap-based Top-K
- tree and graph traversal patterns

---
