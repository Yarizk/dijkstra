import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        initComponents();
    }
    private void initComponents() {
        this.add(new Panel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("dijkstra");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

}

