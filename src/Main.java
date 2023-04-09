
public class Main {
    public void initGraph(Graph[][] graph, int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    graph[i][j] = new Graph(i, j);
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    if (i - 1 >= 0 && map[i - 1][j] == 1) {
                        graph[i][j].up = graph[i - 1][j];
                    }
                    if (i + 1 < map.length && map[i + 1][j] == 1) {
                        graph[i][j].down = graph[i + 1][j];
                    }
                    if (j - 1 >= 0 && map[i][j - 1] == 1) {
                        graph[i][j].left = graph[i][j - 1];
                    }
                    if (j + 1 < map[0].length && map[i][j + 1] == 1) {
                        graph[i][j].right = graph[i][j + 1];
                    }
                }
            }
        }
    }
    public void setNull(Graph[][] graph, int x, int y) {
        if(graph[x][y] == null)return;
        if(graph[x][y].up != null) graph[x][y].up.down = null;
        if(graph[x][y].down != null) graph[x][y].down.up = null;
        if(graph[x][y].left != null) graph[x][y].left.right = null;
        if(graph[x][y].right != null) graph[x][y].right.left = null;

        graph[x][y] = null;

    }
    public static void main(String[] args) {
//        int x = 0;
//        int y = 0;
//        int x_max = 10;
//        int y_max = 10;
//        Graph[][] grap = new Graph[x_max][y_max];
//        int[][] map = new int[x_max][y_max];
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[0].length; j++) {
//                map[i][j] = 1;
//            }
//        }
//        Main main = new Main();
//        main.initGraph(grap, map);
//
//        dijkstra dijkstra = new dijkstra();
//        main.setNull(grap,2,2);
//        main.setNull(grap,2,3);
//        main.setNull(grap,2,4);
//        dijkstra.calculate(grap, 0, 5,9,5);

        Frame frame = new Frame();
        frame.setVisible(true);

    }
}

