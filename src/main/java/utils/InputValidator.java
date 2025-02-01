package utils;

public class InputValidator {

    // Validate board size in the format NxM (e.g., 3x2, 5x4)
    public static boolean isValidBoardSize(String boardSize, int[][] validSizes) {
        if (boardSize == null || !boardSize.matches("\\d+x\\d+")) {
            return false;
        }
        String[] sizes = boardSize.split("x");
        try {
            int columns = Integer.parseInt(sizes[0].trim());
            int rows = Integer.parseInt(sizes[1].trim());
            for (int[] size : validSizes) {
                if (size[0] == columns && size[1] == rows) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    // Validate number of players between a minimum and maximum range
    public static boolean isValidPlayerCount(int count, int minPlayers, int maxPlayers) {
        return count >= minPlayers && count <= maxPlayers;
    }

    // Validate player name (not empty and not numeric-only)
    public static boolean isValidPlayerName(String name) {
        return name != null && !name.isEmpty() && !name.matches("^\\d+$");
    }

    // Validate move input (row and column within bounds)
    public static boolean isValidMove(int row, int col, int maxRows, int maxCols) {
        return row >= 0 && row < maxRows && col >= 0 && col < maxCols;
    }
}
