import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Panel extends JPanel implements ActionListener {
     static final int SCREEN_WIDTH = 600;
     static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 60;
    static final int DELAY = 75;
    static final Plot[][] plot = new Plot[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final Color[][] colors = new Color[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    public static final int[][] mapWall = new int[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    public static final ArrayList<Integer[]> mapTarget = new ArrayList<>();
    public final int mapPath[][] = new int[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];

    boolean running = true;
    Timer timer;
    public Panel() {
        initComponents();
    }
    private void initComponents() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT+200));
        this.setBackground(Color.black);
        this.setFocusable(true);
        defineColor();

        this.addMouseListener(new MyMouseAdapter() );
        startGame();
    }

   public void defineColor() {
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                colors[i][j] = Color.white;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }
    public void draw(Graphics g) {
        if (running) {
            button(g);
            g.setColor(Color.red);
            for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            makePlot(g);
           }
    }
    public void makePlot(Graphics g) {
        for (int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++) {
            for (int j = 0; j < SCREEN_HEIGHT/UNIT_SIZE; j++) {
                Plot temp = new Plot(g, i*UNIT_SIZE, j*UNIT_SIZE, colors[i][j]);
            }
        }
    }
    public void button(Graphics g){
        g.setColor(Color.red);
        g.fillRect(SCREEN_WIDTH-SCREEN_WIDTH/2 -250, SCREEN_HEIGHT+50, 200, 100);
        g.setColor(Color.white);
        Font f = new Font("Times New Roman",Font.BOLD, 20);
        g.setFont(f);
        g.drawString("Clear",SCREEN_WIDTH-SCREEN_WIDTH/2 -200, SCREEN_HEIGHT+100);
        g.drawString("Press 'C' to clear", SCREEN_WIDTH-SCREEN_WIDTH/2 -200, SCREEN_HEIGHT+100);

        g.setColor(Color.green);
        g.fillRect(SCREEN_WIDTH-SCREEN_WIDTH/2+50, SCREEN_HEIGHT+50, 200, 100);
        g.setColor(Color.white);
        Font f1 = new Font("Times New Roman",Font.BOLD, 20);
        g.setFont(f1);
        g.drawString("Run", SCREEN_WIDTH-SCREEN_WIDTH/2+100, SCREEN_HEIGHT+100);
        g.drawString("Press 'R' to run", SCREEN_WIDTH-SCREEN_WIDTH/2+100, SCREEN_HEIGHT+100);

    }


    public static int[][] Run(){
        Main m = new Main();
        int x_max = SCREEN_WIDTH/UNIT_SIZE;
        int y_max = SCREEN_HEIGHT/UNIT_SIZE;
        Graph[][] grap = new Graph[x_max][y_max];
        int[][] map = new int[x_max][y_max];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        m.initGraph(grap, map);
        for (int i = 0; i < mapWall.length; i++) {
            for (int j = 0; j < mapWall[0].length; j++) {
                if(mapWall[i][j] == 1){
                    m.setNull(grap, i, j);
                }
            }
        }
        dijkstra dij = new dijkstra();
        int[][] path = dij.calculate(grap, mapTarget.get(0)[0], mapTarget.get(0)[1], mapTarget.get(1)[0], mapTarget.get(1)[1]);
        return path;
    }
    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            repaint();
        }
    }

    public static class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            if (SwingUtilities.isRightMouseButton(e)) {
                if (e.getX() < SCREEN_WIDTH && e.getY() < SCREEN_HEIGHT) {
                    int x = e.getX() / UNIT_SIZE;
                    int y = e.getY() / UNIT_SIZE;
                    colors[x][y] = Color.green;
                    mapTarget.add(new Integer[]{x, y});
                    if(mapTarget.size() == 3){
                        colors[mapTarget.get(0)[0]][mapTarget.get(0)[1]] = Color.white;
                        mapTarget.remove(0);
                    }
//                    for(int i = 0; i < mapTarget.size(); i++){
//                        System.out.println(mapTarget.get(i)[0] + " " + mapTarget.get(i)[1]);
//                    }
                }

            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (e.getX() < SCREEN_WIDTH && e.getY() < SCREEN_HEIGHT) {
                    int x = e.getX() / UNIT_SIZE;
                    int y = e.getY() / UNIT_SIZE;
                    if (colors[x][y] == Color.red) {
                        colors[x][y] = Color.white;
                        mapWall[x][y] = 0;
                    }
                    else {
                        colors[x][y] = Color.red;
                        mapWall[x][y] = 1;
                    }
                }
                if (e.getX() > SCREEN_WIDTH - SCREEN_WIDTH / 2 - 250 && e.getX() < SCREEN_WIDTH - SCREEN_WIDTH / 2 - 50 && e.getY() > SCREEN_HEIGHT + 50 && e.getY() < SCREEN_HEIGHT + 150) {
                    for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                        for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                            colors[i][j] = Color.white;
                            mapWall[i][j] = 0;

                        }
                    }
                    mapTarget.clear();
                    System.out.println("Clear");
                }
                if (e.getX() > SCREEN_WIDTH - SCREEN_WIDTH / 2 + 50 && e.getX() < SCREEN_WIDTH - SCREEN_WIDTH / 2 + 250 && e.getY() > SCREEN_HEIGHT + 50 && e.getY() < SCREEN_HEIGHT + 150) {
                    if(mapTarget.size() == 2){
                        int[][] path = Run();
                        for (int i = 0; i < path.length; i++) {
                            for (int j = 0; j < path[0].length; j++) {
                                if(path[i][j] == 1){
                                    colors[i][j] = Color.blue;
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("Please choose 2 target");
                    }
                }
            }

        }
    }
}