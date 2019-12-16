import javax.swing.*;
import java.awt.*;


public class Result extends JFrame {
    private JButton btnNewGame;
    private JButton btnExitGame;
    private JLabel result;
      Result(int x,int y){
        setBounds(x, y, 400, 200);
        setTitle("XOGame result");
        result = new JLabel();
        add(result, BorderLayout.CENTER);
        btnNewGame = new JButton("Start new game");
        btnExitGame = new JButton("Exit game");
        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1, 2));
        downPanel.add(btnNewGame);
        downPanel.add(btnExitGame);
        add(downPanel, BorderLayout.SOUTH);
        btnNewGame.addActionListener(e -> setVisible(false));
        btnExitGame.addActionListener(e-> System.exit(0));
        setVisible(false);
    }

    public void setText(String text){ result.setText(text);
    result.setHorizontalAlignment(SwingConstants.CENTER);
    }

}