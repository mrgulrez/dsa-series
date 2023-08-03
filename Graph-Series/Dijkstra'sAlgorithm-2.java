class Pair implements Comparable<Pair> {
    int vertex;
    int distance;

    public Pair(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.distance, other.distance);
    }
}

class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
 public static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int source) {
        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currVertex = current.vertex;
            int currDistance = current.distance;

            if (visited.contains(currVertex))
                continue;

            visited.add(currVertex);

            for (ArrayList<Integer> neighbor : adj.get(currVertex)) {
                int nextVertex = neighbor.get(0); // First integer in the list is the neighbor vertex
                int weight = neighbor.get(1); // Second integer in the list is the weight
                int totalDistance = currDistance + weight;

                if (!visited.contains(nextVertex) && totalDistance < distances[nextVertex]) {
                    distances[nextVertex] = totalDistance;
                    pq.offer(new Pair(nextVertex, totalDistance));
                }
            }
        }

        return distances;
    }

}