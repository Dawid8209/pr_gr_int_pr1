import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Saper extends JFrame {

    private static final int ROWS = 4;
    private static final int COLS = 3;
    private static final int NUM_MINES = 3;

    private JButton[][] buttons = new JButton[ROWS][COLS];
    private boolean[][] mines = new boolean[ROWS][COLS];
    private JLabel gameState;
    private boolean gameOver = false;
    private int resetCount = 0;

    public Saper() {
        setTitle("Saper");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        initializeGameBoard();
        placeMines();
        setVisible(true);
    }

    private void initializeGameBoard() {
        gameState = new JLabel(":D", SwingConstants.CENTER);
        gameState.setFont(new Font("Arial", Font.BOLD, 24));
        add(gameState, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS));
        add(panel, BorderLayout.CENTER);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(e -> handleButtonClick(r, c));
                panel.add(buttons[row][col]);
            }
        }

        JPanel resetPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.addActionListener(e -> resetGame());
        resetPanel.add(resetButton);
        add(resetPanel, BorderLayout.SOUTH);
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < NUM_MINES) {
            int row = rand.nextInt(ROWS);
            int col = rand.nextInt(COLS);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        if (gameOver) return;
        if (mines[row][col]) {
            handleMineClick(row, col);
        } else {
            handleEmptyClick(row, col);
        }
    }

    private void handleMineClick(int row, int col) {
        buttons[row][col].setText("X");
        buttons[row][col].setBackground(Color.RED);
        gameState.setText("XD");
        gameOver = true;
        revealAllMines();
    }

    private void handleEmptyClick(int row, int col) {
        buttons[row][col].setText("");
        buttons[row][col].setEnabled(false);
        buttons[row][col].setBackground(Color.GREEN);
        gameState.setText("BD");
    }

    private void revealAllMines() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (mines[row][col]) {
                    buttons[row][col].setText("X");
                    buttons[row][col].setBackground(Color.RED);
                }
            }
        }
    }

    private void resetGame() {
        resetCount++;
        if (resetCount == 7) {
            triggerEasterEgg();
            resetCount = 0;
        } else {
            gameOver = false;
            gameState.setText(":D");
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    buttons[row][col].setText("");
                    buttons[row][col].setBackground(null);
                    buttons[row][col].setEnabled(true);
                    mines[row][col] = false;
                }
            }
            placeMines();
        }
    }
    private void triggerEasterEgg() {
        gameState.setText("BOOM!");
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                buttons[row][col].setBackground(Color.BLACK);
                buttons[row][col].setEnabled(false);
            }
        }
    }
}