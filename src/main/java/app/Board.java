package app;

import errors.ErrorMessage;
import utils.Constants;

import java.util.Arrays;

import static java.lang.System.out;

public class Board {
    private ErrorMessage errorMessage = new ErrorMessage();
    private int rows;
    private int cols;
    private char[][] horizontalLines;
    private char[][] verticalLines;
    private char[][] boxes;

    public Board(int cols, int rows) {
        this.rows = rows;
        this.cols = cols;
        horizontalLines = new char[rows + 1][cols];
        verticalLines = new char[rows][cols + 1];
        boxes = new char[rows][cols];

        for (char[] row : horizontalLines) Arrays.fill(row, ' ');
        for (char[] row : verticalLines) Arrays.fill(row, ' ');
        for (char[] row : boxes) Arrays.fill(row, ' ');
    }

    public void drawBoard() {
        for (int i = 0; i < rows; i++) {
            // Draw horizontal lines
            for (int j = 0; j < cols; j++) {
                out.print("o");
                out.print(horizontalLines[i][j] == '-' ? "---" : "   ");
            }
            out.println("o");

            // Draw vertical lines and boxes
            for (int j = 0; j < cols; j++) {
                out.print(verticalLines[i][j] == '|' ? "|" : " ");
                out.print(" " + (boxes[i][j] == ' ' ? " " : boxes[i][j]) + " ");
            }
            out.println(verticalLines[i][cols] == '|' ? "|" : " ");
        }

        // Draw the last row of horizontal lines
        for (int j = 0; j < cols; j++) {
            out.print("o");
            out.print(horizontalLines[rows][j] == '-' ? "---" : "   ");
        }
        out.println("o");
    }

    public boolean addHorizontalLine(int row, int col) {
        // Validate row and column indices
        if (row < 0 || row >= horizontalLines.length || col < 0 || col >= horizontalLines[row].length) {
            errorMessage.genericErrorMessage(Constants.INVALID_BOUNDS_MSG);
            return false;
        }

        // Check if the cell is empty before adding a horizontal line
        else if (horizontalLines[row][col] == ' ') {
            horizontalLines[row][col] = '-';
            return true;
        } else {
            errorMessage.genericErrorMessage(Constants.INVALID_OCCUPIED_HORIZONTAL_MSG);
            return false;
        }

    }

    public boolean addVerticalLine(int row, int col) {
        // Validate row and column indices
        if (row < 0 || row >= verticalLines.length || col < 0 || col >= verticalLines[row].length) {
            errorMessage.genericErrorMessage(Constants.INVALID_BOUNDS_MSG);
            return false;
        }

        // Check if the cell is empty before adding a vertical line
        else if (verticalLines[row][col] == ' ') {
            verticalLines[row][col] = '|';
            return true;
        } else {
            errorMessage.genericErrorMessage(Constants.INVALID_OCCUPIED_VERTICAL_MSG);
            return false;
        }

    }

    public int checkBoxes(Player player) {
        int completedBoxes = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boxes[i][j] == ' ' &&
                        horizontalLines[i][j] == '-' &&
                        horizontalLines[i + 1][j] == '-' &&
                        verticalLines[i][j] == '|' &&
                        verticalLines[i][j + 1] == '|') {
                    boxes[i][j] = player.getName().charAt(0); // Mark the box as completed with first player char name
                    completedBoxes++;
                }
            }
        }
        return completedBoxes;
    }

    public char[][] getBoxes() {
        return boxes;
    }
}
