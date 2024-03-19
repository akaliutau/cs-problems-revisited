Union Find
===========

This technique is used when there is a need to unite the growing parts of the biggest system
(fe. islands on 2D map or nodes on the graph)

The typical code for UnionFind:

```java
   class Graph {
        int[] parent;

        public Graph(int n) {
            parent = new int[n];
            for (int i = 0; i < n; ++i)
                parent[i] = i;
        }

        int find(int x) { // eventually returns the super-parent, and can be used to test the nodes are in the same set
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
```