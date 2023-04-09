import org.jetbrains.annotations.NotNull;

public class dijkstra {
    public int[][] calculate(Graph[] @NotNull [] graph, int x, int y, int end_x, int end_y) {
        int x_max = graph.length;
        int y_max = graph[0].length;
        int u,v, start_x = x, start_y = y;
        for (int i = 0; i < x_max; i++) {
            for (int j = 0; j < y_max; j++) {
                if(graph[i][j] == null)continue;

                graph[i][j].distance = 999;
                graph[i][j].visited = 0;
            }
        }
        graph[x][y].distance = 0;
        int count =1;
        while (count < x_max*y_max){
            graph[x][y].visited = 1;
            Graph current = graph[x][y];
            u = current.x;
            v = current.y;
            int min = 999;
            if (current.left != null && current.left.visited == 0) {
                if (current.left.distance > current.distance + 1) {
                    current.left.distance = current.distance + 1;
                    current.left.parent = current;
                }
                if (current.left.distance < min) {
                    min = current.left.distance;
                    x = current.left.x;
                    y = current.left.y;
                }
            }
            if (current.up != null && current.up.visited == 0) {
                if (current.up.distance > current.distance + 1) {
                    current.up.distance = current.distance + 1;
                    current.up.parent = current;
                }
                if (current.up.distance < min) {
                    min = current.up.distance;
                    x = current.up.x;
                    y = current.up.y;
                }
            }
            if (current.down != null && current.down.visited == 0) {
                if (current.down.distance > current.distance + 1) {
                    current.down.distance = current.distance + 1;
                    current.down.parent = current;
                }
                if (current.down.distance < min) {
                    min = current.down.distance;
                    x = current.down.x;
                    y = current.down.y;
                }
            }

            if (current.right != null && current.right.visited == 0) {
                if (current.right.distance > current.distance + 1) {
                    current.right.distance = current.distance + 1;
                    current.right.parent = current;
                }
                if (current.right.distance < min) {
                    min = current.right.distance;
                    x = current.right.x;
                    y = current.right.y;
                }
            }

            if(u == x && v == y){
                x = start_x;
                y = start_y;
            }
            count++;
        }

        for (int i = 0; i < y_max; i++) {
            for (int j = 0; j < x_max; j++) {
                if(graph[j][i] == null)System.out.print("X \t\t");
                else System.out.print(graph[j][i].distance + " \t\t");
            }
            System.out.println();
        }

        Graph end = graph[end_x][end_y];
        int[][] path = new int[x_max][y_max];
        while (end != null){
            path[end.x][end.y] = 1;


            end = end.parent;
        }
        return path;
    }
}
