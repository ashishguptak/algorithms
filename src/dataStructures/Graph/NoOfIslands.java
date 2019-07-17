package dataStructures.Graph;
/**
 *
 * island formation - connecting adj lands horizontally or vertically
 *
 * edges of grid are water
 *
 * 2d matrix
 *
 * Treat the grid as a adj matrix representation of nodes,
 * undirected/unwieghted graph, with an edge between two nodes expressed as 1
 * while linear scan, we encounter a 1 it becomes the root node for DFS search and mark #times root node was found
 * one approach is to traverse the matrix row/col wise and first time we encounter a land,
 * mark it as visited and then recurse helper func in all directions till the lands connected are visited.
 *
 * Start with one node and exhaust all the connected nodes wrt to it, DFS approach for adj matrix
 *
 * Increment the island global/class variable count by 1
 *
 * Continue the matrix traversal till end of the matrix.
 *
 * Time complexity - O(n*m)
 * Space complexity - O(n*m) recursive stack depth
 *
 *
 *
 * BFS approach
 * Linear scan the matrix and first time we encounter an island,
 * iteratively search the neighbors until the queue becomes empty
 *
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class NoOfIslands {
    public static int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;

        int countOfIslands = 0;
        GridIndex obj = new GridIndex('0','0',false);
        while(true) {
            obj = fullGridIsDone(grid, obj);
            if(obj.done) break;

            countOfIslands++;

            recurse(grid, obj.i, obj.j);
        }

        return countOfIslands;
    }

    public static void recurse(char[][] grid, int i, int j) {

        if( i < 0 || i >= grid.length ||
                j<0 || j>= grid[0].length) return;

        if( grid[i][j] == '1') {
            grid[i][j] = 'x';
            recurse(grid, i+1, j);
            recurse(grid, i, j+1);
            recurse(grid, i-1, j);
            recurse(grid, i, j-1);
        }
    }

    public static GridIndex fullGridIsDone(char[][] grid, GridIndex obj) {
        for( int i=0; i < grid.length; i++) {
            for( int j=0; j< grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    obj.i = i;
                    obj.j = j;
                    obj.done = false;
                    return obj;
                }
            }
        }
        obj.done = true;
        return obj;
    }

    static class GridIndex {
        int i =0 ; int j =0;
        boolean done =false;

        public GridIndex(int i, int j, boolean done) {
            this.i = i;
            this.j = j;
            this.done = done;
        }
    }

    public static void main(String[] args) {

        //char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};

        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','1'},{'0','0','1','0','0'},{'0','0','0','1','1'}};

        System.out.println(numIslands(grid));
    }

    //Nicer soln
    public int numIslands2(char[][] grid) {
        int count=0;
        for(int i =0; i < grid.length; i++) {
            for(int j=0; j < grid[0].length; j++) {
                if(grid[i][j] == '1')  {
                    count++;
                    recurse2(grid, i, j);
                }
            }
        }
        return count;
    }

    public void recurse2(char[][] grid, int i, int j) {
        if( i < 0 || i > grid.length -1 || j < 0 || j > grid[0].length -1
                || grid[i][j] == '2' ||  grid[i][j] == '0')
            return;

        grid[i][j] = '2';
        recurse(grid, i+1, j);
        recurse(grid, i, j-1);
        recurse(grid, i, j+1);
        recurse(grid, i-1, j);
    }
}
