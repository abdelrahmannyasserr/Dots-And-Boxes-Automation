package utils;

public class Constants {

    // Valid board sizes
    public static final int[][] VALID_BOARD_SIZES = {
            {3, 2}, {5, 4}, {8, 6}, {11, 9}
    };

    // Player count range
    public static final int MIN_PLAYERS = 1;
    public static final int MAX_PLAYERS = 4;

    // Error messages
    public static final String INVALID_BOARD_SIZE_MSG = "Invalid board size! Allowed sizes are: 3x2, 5x4, 8x6, 11x9.";
    public static final String INVALID_PLAYER_COUNT_MSG = "Invalid number of players! Must be between %s and %s.";
    public static final String INVALID_PLAYER_NAME_MSG = "Invalid name. Player name cannot be a number only or Empty.";
    public static final String INVALID_LINETYPE_MSG = "Invalid line type. Line type must be start with h or v";
    public static final String INVALID_INTEGER_MSG = "Invalid input. Please enter a valid integer.";
    public static final String INVALID_OCCUPIED_HORIZONTAL_MSG = "Position already occupied! Cannot add a horizontal line here.";
    public static final String INVALID_OCCUPIED_VERTICAL_MSG = "Position already occupied! Cannot add a vertical line here.";
    public static final String INVALID_BOUNDS_MSG = "Invalid position! Row or column is out of bounds.";

    public static String getInvalidPlayerCountMessage() {
        return String.format(INVALID_PLAYER_COUNT_MSG, MIN_PLAYERS, MAX_PLAYERS);
    }
}
