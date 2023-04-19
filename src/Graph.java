public class Graph{
    Graph up, down, left, right;
    Graph parent;
    int distance=1,visited=0;
    int x, y;
    public Graph(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void set(Graph up, Graph right, Graph down, Graph left){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    public void setLeft(Graph left){
        this.left = left;
    }
    public void setRight(Graph right){
        this.right = right;
    }
    public void setUp(Graph up){
        this.up = up;
    }
    public void setDown(Graph down){
        this.down = down;
    }

    public void get(){
        if (up != null) {
            System.out.println("up: " + up.x + " " + up.y);
        }
        if (down != null) {
            System.out.println("down: " + down.x + " " + down.y);
        }
        if (left != null) {
            System.out.println("left: " + left.x + " " + left.y);
        }
        if (right != null) {
            System.out.println("right: " + right.x + " " + right.y);
        }
    }

}