import org.jetbrains.annotations.NotNull;
import java.util.*;

public class dijkstra {
    public int[][] calculate(Graph[] @NotNull [] graph, int x, int y, int end_x, int end_y) {
        LinkedList<Graph> queue = new LinkedList<>();
        int x_max = graph.length;
        int y_max = graph[0].length;
        int u,v;
        for (int i = 0; i < x_max; i++) {
            for (int j = 0; j < y_max; j++) {
                if(graph[i][j] == null)continue;

                graph[i][j].distance = 999;
                graph[i][j].visited = 0;
            }
        }
        graph[x][y].distance = 0;
        queue.add(graph[x][y]);
        while(!queue.isEmpty()){
            graph[x][y].visited = 1;
            graph[x][y].parent = null;
            Graph current = queue.poll();
            x = current.x;
            y = current.y;

            if(graph[x][y].up != null && graph[x][y].up.visited == 0){
                u = graph[x][y].up.x;
                v = graph[x][y].up.y;
                if(graph[u][v].distance > graph[x][y].distance + 1){
                    graph[u][v].distance = graph[x][y].distance + 1;
                }
                queue.add(graph[u][v]);
            }
            if(graph[x][y].down != null && graph[x][y].down.visited == 0){
                u = graph[x][y].down.x;
                v = graph[x][y].down.y;
                if(graph[u][v].distance > graph[x][y].distance + 1){
                    graph[u][v].distance = graph[x][y].distance + 1;
                }
                queue.add(graph[u][v]);
            }
            if(graph[x][y].left != null && graph[x][y].left.visited == 0){
                u = graph[x][y].left.x;
                v = graph[x][y].left.y;
                if(graph[u][v].distance > graph[x][y].distance + 1){
                    graph[u][v].distance = graph[x][y].distance + 1;
                }
                queue.add(graph[u][v]);
            }
            if(graph[x][y].right != null && graph[x][y].right.visited == 0){
                u = graph[x][y].right.x;
                v = graph[x][y].right.y;
                if(graph[u][v].distance > graph[x][y].distance + 1){
                    graph[u][v].distance = graph[x][y].distance + 1;
                }
                queue.add(graph[u][v]);
            }
        }

        for (int i = 0; i < y_max; i++) {
            for (int j = 0; j < x_max; j++) {
                if(graph[j][i] == null)System.out.print("X \t\t");
                else System.out.print(graph[j][i].distance +" \t\t");
                if(graph[j][i] != null && graph[j][i].parent == null){
                    if( j > 0 && graph[j-1][i] != null && graph[j-1][i].distance < graph[j][i].distance) graph[j][i].parent = graph[j-1][i];
                    else if( j < x_max-1 && graph[j+1][i] != null && graph[j+1][i].distance < graph[j][i].distance)graph[j][i].parent = graph[j+1][i];
                    else if( i > 0 && graph[j][i-1] != null && graph[j][i-1].distance < graph[j][i].distance)graph[j][i].parent = graph[j][i-1];
                    else if( i < y_max-1 && graph[j][i+1] != null && graph[j][i+1].distance < graph[j][i].distance)graph[j][i].parent = graph[j][i+1];
                }
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
