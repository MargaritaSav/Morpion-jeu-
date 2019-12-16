import javax.swing.*;
import java.awt.*;

public class BattleMap extends JPanel {
    JButton[][] btns;
    public BattleMap() {
        init();
        repaintMap(Logic.SIZE);
    }

    void repaintMap(int size){
        this.removeAll();
        this.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.add(btns[i][j]);
            }
        }
        this.repaint(1);
        this.setVisible(false);
        this.setVisible(true);
    }

    void init(){
        Font font = new Font("Arial", Font.BOLD, 24);
        btns = new JButton[Logic.maxSize][Logic.maxSize];
        for (int i = 0; i < Logic.maxSize; i++) {
            for (int j = 0; j < Logic.maxSize; j++) {
                btns[i][j] = new JButton();
                btns[i][j].setFont(font);
                final int y = i;
                final int x = j;
                btns[i][j].addActionListener(e -> {
                    if (Logic.isCellValid(x, y)) {
                        System.out.println(x + " " + y);//
                        Logic.map[y][x] = Logic.playerOne;
                        Logic.playerOneFinished = true;
                    }
                });
            }
        }
    }
}