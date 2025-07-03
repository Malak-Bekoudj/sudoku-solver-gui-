package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * SudokuGui.java
 *
 * A simple Java Swing GUI to interactively solve Sudoku puzzles using a
 * backtracking algorithm with step-by-step visualization.
 *
 * Author: Malak Bekoudj
 */
public class SudokuGui extends JFrame {

    public static final int SUDOKU_DIMENSION = 9;
    public static final JTextField[][] cell = new JTextField[SUDOKU_DIMENSION][SUDOKU_DIMENSION];

    // Colors for consistent styling
    private static final Color CELL_BG_DEFAULT = new Color(250, 255, 255);
    private static final Color CELL_BG_SOLVED = new Color(200, 255, 200);

    public SudokuGui() {
        initComponents();
        setTitle("Sudoku Solver");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initGrid();
    }

    /**
     * Initializes the Sudoku grid.
     */
    private void initGrid() {
        jPanel1.setLayout(new GridLayout(SUDOKU_DIMENSION, SUDOKU_DIMENSION));
        for (int i = 0; i < SUDOKU_DIMENSION; i++) {
            for (int j = 0; j < SUDOKU_DIMENSION; j++) {
                cell[i][j] = new JTextField();
                cell[i][j].setHorizontalAlignment(JTextField.CENTER);
                cell[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                cell[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell[i][j].setBackground(CELL_BG_DEFAULT);
                jPanel1.add(cell[i][j]);
            }
        }
        updateBorders();
    }

    /**
     * Adds bold borders between 3x3 subgrids.
     */
    public static void updateBorders() {
        for (int j = 0; j < SUDOKU_DIMENSION; j++) {
            cell[2][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
            cell[5][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
        }
        for (int i = 0; i < SUDOKU_DIMENSION; i++) {
            cell[i][2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
            cell[i][5].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
        }
        cell[2][2].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
        cell[5][5].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
        cell[5][2].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
        cell[2][5].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        okButton = new JButton();
        resetButton = new JButton();
        solverStatus = new JTextField();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new Color(0, 102, 102));

        okButton.setBackground(new Color(0, 204, 204));
        okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        okButton.setText("OK");
        okButton.addActionListener(evt -> okButtonActionPerformed());

        resetButton.setBackground(new Color(0, 204, 204));
        resetButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        resetButton.setText("Reset");
        resetButton.addActionListener(evt -> resetButtonActionPerformed());

        solverStatus.setEditable(false);
        solverStatus.setBackground(new Color(0, 102, 102));
        solverStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
        solverStatus.setForeground(new Color(102, 255, 102));
        solverStatus.setBorder(null);

        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 18));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Begin:");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(solverStatus, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(resetButton)
                    .addGap(70, 70, 70)
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(okButton)
                    .addGap(97, 97, 97)
                    .addComponent(solverStatus, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(185, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Handles OK button click: reads the board and starts solving.
     */
    private void okButtonActionPerformed() {
        int[][] board = new int[SUDOKU_DIMENSION][SUDOKU_DIMENSION];

        for (int i = 0; i < SUDOKU_DIMENSION; i++) {
            for (int j = 0; j < SUDOKU_DIMENSION; j++) {
                cell[i][j].setEditable(false);
                if (cell[i][j].getText().isEmpty()) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = Integer.parseInt(cell[i][j].getText());
                    cell[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        new Thread(() -> {
            if (solveSudoku(board)) {
                solverStatus.setText("Solved");
            } else {
                solverStatus.setText("Unsolved");
            }
        }).start();
    }

    /**
     * Handles Reset button click.
     */
    private void resetButtonActionPerformed() {
        for (int i = 0; i < SUDOKU_DIMENSION; i++) {
            for (int j = 0; j < SUDOKU_DIMENSION; j++) {
                cell[i][j].setText("");
                cell[i][j].setBackground(CELL_BG_DEFAULT);
                cell[i][j].setEditable(true);
            }
        }
    }

    /**
     * Solves the Sudoku board with backtracking.
     */
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SUDOKU_DIMENSION; row++) {
            for (int col = 0; col < SUDOKU_DIMENSION; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SUDOKU_DIMENSION; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            updateCell(row, col, num);

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0; // Backtrack
                            updateCell(row, col, 0);
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // All cells filled
    }

    /**
     * Checks if placing a number is valid.
     */
    public static boolean isValidMove(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SUDOKU_DIMENSION; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Updates the GUI cell with value and colors for visualization.
     */
    private static void updateCell(int row, int col, int value) {
        SwingUtilities.invokeLater(() -> {
            cell[row][col].setText(value == 0 ? "" : String.valueOf(value));
            cell[row][col].setBackground(value == 0 ? Color.WHITE : CELL_BG_SOLVED);
        });
        try {
            Thread.sleep(50); // delay for visualization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        java.awt.EventQueue.invokeLater(() -> new SudokuGui().setVisible(true));
    }

    // Swing components
    private JButton okButton;
    private JButton resetButton;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JTextField solverStatus;
}
