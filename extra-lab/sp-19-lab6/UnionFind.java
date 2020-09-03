public class UnionFind {

    int[] disjointSets;
    int size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        size = n;
        disjointSets = new int[n];
        for (int i = 0; i < n; i++) {
            disjointSets[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= size || vertex < 0) {
            throw new IllegalArgumentException("your element is out of bound");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return - disjointSets[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return disjointSets[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        int sizeOf1 = sizeOf(v1);
        int sizeOf2 = sizeOf(v2);
        if (root1 == root2) {
            return;
        }
        if (sizeOf1 <= sizeOf2) {
            disjointSets[root1] = v2;
            disjointSets[root2] = - sizeOf1 - sizeOf2;
        } else {
            disjointSets[root2] = v1;
            disjointSets[root1] = - sizeOf1 - sizeOf2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parent(vertex) < 0) {
            return vertex;
        }
        int root = find(parent(vertex));
        disjointSets[vertex] = root;
        return root;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(100);
        System.out.println(uf.connected(1, 3));
        uf.union(1,3);
        System.out.println(uf.connected(1, 3));
        System.out.println(uf.connected(1, 2));
        uf.union(1, 2);
        System.out.println(uf.connected(1, 2));
    }
}
