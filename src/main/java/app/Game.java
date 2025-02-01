package app;

import errors.ErrorMessage;
import utils.Constants;
import utils.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Game {
    public boolean isTestingEnv;

    public Game(String env) {
        isTestingEnv = "test".equals(env);
    }

    private final ErrorMessage errorMessage = new ErrorMessage();
    private Board board;
    private Player[] players;
    private int currentPlayer;
    // Define valid column-row pairs
    private final Scanner scanner = new Scanner(System.in);
    private int columns;
    private int rows;
    private int numberOfPlayers;
    private String[] playerNames;
    private boolean gameRunning = true;

    public void initGameInfo() {
        setBoardSize(null);
        setPlayersNumber(null);
        setPlayesNames(null);

        InitGame(columns, rows, playerNames);
        start('\0', -1, -1);
    }

    public String setBoardSize(String boardSize) {
        while (true) {
            if (boardSize == null || boardSize.isEmpty()) {
                out.println("Enter Board Size (Allowed sizes: 3x2, 5x4, 8x6, 11x9): ");
                Scanner scanner = new Scanner(System.in);
                boardSize = scanner.nextLine(); // Read input dynamically
            }

            String[] sizes = boardSize.split("x");

            if (sizes.length == 2) {
                try {
                    int currentColumns = Integer.parseInt(sizes[0].trim());
                    int currentRows = Integer.parseInt(sizes[1].trim());

                    // Validate board size
                    if (InputValidator.isValidBoardSize(boardSize, Constants.VALID_BOARD_SIZES)) {
                        columns = currentColumns;
                        rows = currentRows;
                        out.println("Board size set to: " + columns + "x" + rows);
                        break; // Exit loop after successful validation
                    } else {
                        String error = errorMessage.genericErrorMessage(Constants.INVALID_BOARD_SIZE_MSG, !isTestingEnv);
                        if (isTestingEnv) return error;
                    }
                } catch (NumberFormatException e) {
                    String error = errorMessage.genericErrorMessage(Constants.INVALID_BOARD_SIZE_MSG, !isTestingEnv);
                    if (isTestingEnv) return error;
                }
            } else {
                String error = errorMessage.genericErrorMessage(Constants.INVALID_BOARD_SIZE_MSG, !isTestingEnv);
                if (isTestingEnv) return error;
            }

            // Clear input for subsequent retries
            boardSize = null;
        }
        return "";
    }


    public String setPlayersNumber(String input) {
        while (true) {
            if (input == null || input.isEmpty()) {
                out.println("Enter Number of Players (1-4): ");
                input = scanner.nextLine(); // Prompt for input dynamically
            }

            try {
                numberOfPlayers = Integer.parseInt(input);
                // Validate the player count
                if (InputValidator.isValidPlayerCount(numberOfPlayers, Constants.MIN_PLAYERS, Constants.MAX_PLAYERS)) {
                    break; // Valid number of players, exit the loop
                } else {
                    String error = errorMessage.genericErrorMessage(Constants.getInvalidPlayerCountMessage(), !isTestingEnv);
                    if (isTestingEnv) return error;
                }
            } catch (NumberFormatException e) {
                return errorMessage.genericErrorMessage(Constants.INVALID_INTEGER_MSG);
            }
            input = null;
        }
        return "";
    }

    public String setPlayesNames(List<String> inputNames) {
        // Validate and Get Player Names
        playerNames = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            while (true) {
                String playerName;

                if (i < inputNames.size()) {
                    // Use input from the provided list
                    playerName = inputNames.get(i) != null ? inputNames.get(i).trim() : "";
                } else {
                    // Prompt user for player name
                    out.println("Enter Player " + (i + 1) + " Name: ");
                    playerName = scanner.nextLine().trim();
                }
                if (InputValidator.isValidPlayerName(playerName)) {
                    playerNames[i] = playerName.isEmpty() ? "Player " + (i + 1) : playerName;
                    break; // Valid name, exit the loop
                } else {
                    String error = errorMessage.genericErrorMessage(Constants.INVALID_PLAYER_NAME_MSG, !isTestingEnv);
                    if (isTestingEnv) return error;
                }
            }
        }
        return "";
    }

    public void InitGame(int columns, int rows, String[] playerNames) {
        // Initialize the board with the specified size
        board = new Board(columns, rows);

        // Initialize players based on the provided names
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
        }

        // Set the current player to the first player
        currentPlayer = 0;
        out.println("Game initialized successfully!");
        out.println("Board size: " + columns + "x" + rows);
        out.println("Players: " + String.join(", ", playerNames));
    }

    public String start(char lineType, int row, int col) {

        while (gameRunning && (!isTestingEnv || (lineType != '\0' && row != -1 && col != -1))) {
            board.drawBoard();

            Player player = players[currentPlayer];
            out.println(player.getName() + "'s turn!");
            if (!isTestingEnv) {
                out.print("Enter line type (h for horizontal, v for vertical): ");
                lineType = scanner.next().charAt(0);
                out.print("Enter row: ");
                row = scanner.nextInt();
                out.print("Enter column: ");
                col = scanner.nextInt();
            }


            boolean validMove = false;
            if (lineType == 'h') {
                validMove = board.addHorizontalLine(row, col);
            } else if (lineType == 'v') {
                validMove = board.addVerticalLine(row, col);
            } else {
                errorMessage.genericErrorMessage(Constants.INVALID_LINETYPE_MSG);
                validMove = false;
                sleep(100);
            }

            if (validMove) {
                int completedBoxes = board.checkBoxes(player);
                player.addScore(completedBoxes);

                if (completedBoxes == 0) {
                    currentPlayer = (currentPlayer + 1) % players.length;
                }
            } else {
                sleep(100);
            }

            if (isGameOver()) {
                gameRunning = false;
                board.drawBoard();
                return player.announceWinner(players);
            }
            if (isTestingEnv) {
                lineType = '\0';
                row = -1;
                col = -1;
            }
        }
        return "";
    }

    private boolean isGameOver() {
        for (char[] row : board.getBoxes()) {
            for (char box : row) {
                if (box == ' ') return false;
            }
        }
        return true;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}
