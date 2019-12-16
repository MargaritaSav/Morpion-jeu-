import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class Setting extends JFrame {
    int size = 3;
    int dotsToWin = 3;
    char playerOne = 'X';
    char playerTwo='O';


    public Setting(int x,int y) {
        setBounds(x, y, 300, 300);
        setTitle("Chose settings");
        JCheckBox XBox = new JCheckBox("X");
        XBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

            }
        });
        JCheckBox OBox = new JCheckBox("O");
        OBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                            }
        });
        JPanel boxesPanel = new JPanel();

        boxesPanel.add(XBox);
        boxesPanel.add(OBox);
        add(boxesPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        JLabel sizeLabel = new JLabel("size = 3");
        panel.add(sizeLabel);
        JSlider slSize = new JSlider(3,Logic.maxSize,3);
        panel.add(slSize);
        JLabel dotsToWinLabel = new JLabel("dotsToWin = 3");
        panel.add(dotsToWinLabel);
        JSlider slDotsToWin = new JSlider(3,3,3);
        panel.add(slDotsToWin);

        ChangeListener changeListener = e -> {
            slDotsToWin.setMaximum(slSize.getValue());
            sizeLabel.setText("size = "+ slSize.getValue());
            dotsToWinLabel.setText("dotsToWin = "+ slDotsToWin.getValue());
        };

        slSize.addChangeListener(changeListener);
        slSize.setPaintTicks(true);
        slSize.setMajorTickSpacing(1);
        slDotsToWin.addChangeListener(changeListener);
        slDotsToWin.setPaintTicks(true);
        slDotsToWin.setMajorTickSpacing(1);

        JButton btnNewGame = new JButton("Start");
        btnNewGame.setBackground(Color.cyan);
        panel.add(btnNewGame);
        add(panel,BorderLayout.CENTER);

        btnNewGame.addActionListener(e -> {
            size = slSize.getValue();
            dotsToWin = slDotsToWin.getValue();
            if (OBox.isSelected()){
                Logic.playerOne=playerTwo;
                Logic.playerTwo=playerOne;
            } else if (XBox.isSelected()) {
                Logic.playerOne = playerOne;
                Logic.playerTwo = playerTwo;
            }

            setVisible(false);
        });


        setVisible(true);
    }

}
