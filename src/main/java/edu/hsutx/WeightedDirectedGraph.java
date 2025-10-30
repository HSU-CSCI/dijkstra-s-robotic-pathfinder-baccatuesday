package edu.hsutx;

import java.util.List;

public class WeightedDirectedGraph {
    // TODO - Implement storage of the graph, including either adjacency list, adjacency matrix, or both.
    // Going with adjacency matrix
    // hold the edge weights and vertex count
    protected static double[][] adjMatrix;
    protected static int verCount;

    /***
     * fills the adjacency matrix with vertices and their weights.
     *
     * @param vertexQuantity: Total number of vertices, as an int.  We will start counting at vertex 1, not 0.
     * @param edgeList: an List of Edges containing start and end vertex # and weight.
     ***/
    public WeightedDirectedGraph(int vertexQuantity, List<Edge> edgeList) {
        // create matrix and keep track of vertices
        adjMatrix = new double[vertexQuantity + 1][vertexQuantity + 1];
        verCount = vertexQuantity;

        // loop through edgeList and put start, end, weight into Matrix
        for (Edge edge : edgeList) {
            adjMatrix[edge.getStart()][edge.getEnd()] = edge.getWeight();
        }
    }

    /***
     * returns true if vertex[start] has an edge to vertex[end], otherwise returns false
     * @param start starting vertex
     * @param end ending vertex
     */
    public static boolean isAdjacent(int start, int end) {
        // 0 is no edge, since vertex count starts at 1
        return adjMatrix[start][end] != 0;
    }

    /***
     * returns a 2d matrix of adjacency weights, with 0 values for non-adjacent vertices.
     * @return matrix of doubles representing adjacent edge weights
     */
    public static double[][] adjacencyMatrix() {
        return adjMatrix;
    }

    /***
     * Conducts a Breadth First Search and returns the path from start to end, or null if not connected.
     * For accurate testing reproduction, add new vertices to the queue from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public static int[] getBFSPath(int start, int end) {

        // array to keep track of vertices visited
        boolean[] visited = new boolean[verCount + 1];

        // array to keep track of the path taken
        // initialize all as zero
        int road[] = new int[verCount+1];
        for (int i = 0; i <= verCount; i++) {
            road[i] = 0;
        }

        // create queue for next vertex to visit
        // establish front and back of the line
        int[] queue = new int[verCount];
        int front = 0;
        int back = 0;

        // start the queue by inserting the first vertex
        // move the back pointer to the next available spot
        // mark down first vertex as visited
        queue[back] = start;
        back++;
        visited[start] = true;

        // loop until Queue is empty
        while (front != back) {
            // read the first element of the queue
            // then pop it
            int current = queue[front];
            front++;

            // check if current is the end
            // return path taken
            if (current == end) {
                // create an array big enough to hold the path
                // record how many vertices are in trace
                int[] tracePath = new int[verCount];
                int pathLength = 0;

                // reconstruct the path taken
                while (current != start) {
                    // record path at the current vertex
                    // update number of vertices traced back
                    // update current to previous vertex
                    tracePath[pathLength] = current;
                    pathLength++;
                    current = road[current];
                }
                // add start to the traced path
                tracePath[pathLength] = start;

                // create path array
                // loop through path length and reverse the order
                int[] path = new int[pathLength + 1];
                for (int i=0; i<= pathLength; i++) {
                    path[i] = tracePath[pathLength - i];
                }

                return path;
            }

            // check all vertices for an edge
            for (int i = 1; i <= verCount; i++) {
                // check if a vertex is an edge and
                // if it has not been visited before
                if (isAdjacent(current, i) && !visited[i]) {
                    // record that i-vertex has been visited
                    // record the path and add vertex to the queue
                    visited[i] = true;
                    road[i] = current;
                    queue[back] = i;
                    back ++;
                }
            }
        }

        return null;
    }

    /***
     * Conducts a Depth First Search, and returns the path from start to end, or null if not connected.
     * Again, for accurate testing reproduction, add new vertices to the stack from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public static int[] getDFSPath(int start, int end) {

        // array to keep track of vertices visited
        boolean[] visited = new boolean[verCount + 1];

        // array to keep track of the path taken
        // initialize all as zero
        int road[] = new int[verCount+1];
        for (int i = 0; i <= verCount; i++) {
            road[i] = 0;
        }

        // create stack for next vertex to visit
        // establish top of the stack
        int[] stack = new int[verCount];
        int top = 0;

        // start the stack by inserting the first vertex
        // mark down first vertex as visited
        stack[top] = start;
        top++;
        visited[start] = true;


        // loop until stack is empty
        while (top != 0) {
            // read the top element of the stack
            // pop it
            top--;
            int current = stack[top];

            // check if current is the end
            // return path taken
            if (current == end) {
                // create an array big enough to hold the path
                // record how many vertices are in trace
                int[] tracePath = new int[verCount];
                int pathLength = 0;

                // reconstruct path taken
                while (current != start) {
                    // record path at the current vertex
                    // update number of vertices traced back
                    // update current to previous vertex
                    tracePath[pathLength] = current;
                    pathLength++;
                    current = road[current];
                }
                // add start to the traced path
                tracePath[pathLength] = start;

                // create path array
                // loop through path length and reverse the order
                int[] path = new int[pathLength + 1];
                for (int i=0; i<= pathLength; i++) {
                    path[i] = tracePath[pathLength - i];
                }

                return path;
            }

            // check all vertices for an edge
            for (int i = 1; i <= verCount; i++) {
                // check if a vertex is an edge and
                // if it has not been visited before
                if (isAdjacent(current, i) && !visited[i]) {
                    // record that i-vertex has been visited
                    // record the path and add vertex to the stack
                    visited[i] = true;
                    road[i] = current;
                    stack[top] = i;
                    top++;
                }
            }

        }

        return null;
    }
}

