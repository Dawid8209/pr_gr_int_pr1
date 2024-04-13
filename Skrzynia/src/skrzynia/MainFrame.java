import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JLabel statusLabel;
    private JButton lever1, lever2, lever3, lever4;
    private boolean lever1Down, lever2Down, lever3Down, lever4Down;
    private JLabel lever1Indicator, lever2Indicator, lever3Indicator, lever4Indicator;

    public MainFrame() {
        setTitle("Otwieranie skrzyni");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Skrzynia jest zamknięta", JLabel.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        JPanel leverPanel = new JPanel(new GridLayout(2, 2));

        lever1 = new JButton("Dźwignia 1");
        lever2 = new JButton("Dźwignia 2");
        lever3 = new JButton("Dźwignia 3");
        lever4 = new JButton("Dźwignia 4");

        lever1Indicator = new JLabel("", JLabel.CENTER);
        lever2Indicator = new JLabel("", JLabel.CENTER);
        lever3Indicator = new JLabel("", JLabel.CENTER);
        lever4Indicator = new JLabel("", JLabel.CENTER);

        updateIndicatorColors();

        lever1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lever1Down = !lever1Down;
                updateIndicatorColors();
                checkBoxes();
            }
        });

        lever2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lever3Down) {
                    lever2Down = !lever2Down;
                    updateIndicatorColors();
                    checkBoxes();
                }
            }
        });

        lever3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lever4Down) {
                    lever3Down = !lever3Down;
                    updateIndicatorColors();
                    checkBoxes();
                }
            }
        });

        lever4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!lever1Down) {
                    lever4Down = !lever4Down;
                    updateIndicatorColors();
                    checkBoxes();
                }
            }
        });

        leverPanel.add(lever1);
        leverPanel.add(lever1Indicator);
        leverPanel.add(lever2);
        leverPanel.add(lever2Indicator);
        leverPanel.add(lever3);
        leverPanel.add(lever3Indicator);
        leverPanel.add(lever4);
        leverPanel.add(lever4Indicator);

        add(leverPanel, BorderLayout.CENTER);
    }

    private void checkBoxes() {
        if (lever1Down && lever2Down && lever3Down && lever4Down) {
            statusLabel.setText("Skrzynia jest otwarta");
        } else {
            statusLabel.setText("Skrzynia jest zamknięta");
        }
    }

    private void updateIndicatorColors() {
        lever1Indicator.setOpaque(true);
        lever2Indicator.setOpaque(true);
        lever3Indicator.setOpaque(true);
        lever4Indicator.setOpaque(true);

        lever1Indicator.setBackground(lever1Down ? Color.GREEN : Color.RED);
        lever2Indicator.setBackground(lever2Down ? Color.GREEN : Color.RED);
        lever3Indicator.setBackground(lever3Down ? Color.GREEN : Color.RED);
        lever4Indicator.setBackground(lever4Down ? Color.GREEN : Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
