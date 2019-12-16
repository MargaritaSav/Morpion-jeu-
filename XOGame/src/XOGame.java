import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class XOGame extends JFrame {

    Result result;
    BattleMap battleMap;
    Setting settingWindow;

    public XOGame() {
        setBounds(300, 200, 600, 630);
        setTitle("XOGame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        result = new Result(500, 300);

        battleMap = new BattleMap();
        add(battleMap, BorderLayout.CENTER);


        JLabel infoLabel = new JLabel();
        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoLabel, BorderLayout.NORTH);

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1, 1));
        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(Color.pink);
        downPanel.add(btnExit);
        add(downPanel, BorderLayout.SOUTH);


        btnExit.addActionListener(e -> System.exit(0));
        settingWindow = new Setting(500,300);

        while (true) {
            while (result.isVisible()){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!result.isVisible()) {
                settingWindow.setVisible(true);
                while (settingWindow.isVisible()){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setVisible(true);
                infoLabel.setText("You need aline "+settingWindow.dotsToWin +" X or O to win" );
                start(settingWindow.size, settingWindow.dotsToWin);
            }
        }

    }

    public void start(int size, int dotsToWin) {
        Logic.SIZE = size;
        Logic.DOTS_TO_WIN = dotsToWin;
        Logic.initMap();
        battleMap.repaintMap(size);
        Logic.printMap();
        printMap(Logic.SIZE);
        System.out.println("start...");
        while (true) {
            do {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!Logic.playerOneFinished);
            Logic.playerOneFinished = false;
            Logic.printMap();
            printMap(Logic.SIZE);
            if (Logic.checkWin(Logic.playerOne)) {
                System.out.println("Игрок победил!!!");
                showResult("You won!!!");
                return;
            }
            if (Logic.isFull()) {
                System.out.println("Ничья, не осталось места ходить!");
                showResult("Dead heat, the map is full");
                return;
            }

            Logic.aiTurn();
            Logic.printMap();
            printMap(Logic.SIZE);
            if (Logic.checkWin(Logic.playerTwo)) {
                System.out.println("Компьютер победил!!!");
                showResult("Artificial intelligence has won");
                return;
            }
            if (Logic.isFull()) {
                System.out.println("Ничья, не осталось места ходить!");
                showResult("Dead heat, the map is full");
                return;
            }
        }
    }

    public void printMap(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                battleMap.btns[i][j].setText("" + Logic.map[i][j]);
            }
        }
    }

    void showResult(String text) {
        result.setText(text);
        result.setVisible(true);
    }
}
