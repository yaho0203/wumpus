package wumpus;

import java.util.Scanner;

/**
 * Represents the world environment.
 */
public class Environment {
    /**
     * The elements that can be found at the tiles.
     */
    public enum Element {
        WUMPUS, PIT, HUNTER, GOLD
    }

    /**
     * The perceptions that can be sensed by the player.
     */
    public enum Perception {
        SCREAM, STENCH, BREEZE, GLITTER, BUMP, NO_ARROWS
    }

    /**
     * The actions that the player can take.
     */
    public enum Action {
        GO_FORWARD, TURN_LEFT, TURN_RIGHT, GRAB, SHOOT_ARROW, NOOP, EXIT
    }

    /**
     * The final outcome of the game.
     */
    public enum Result {
        WIN, LOSE
    }

    /**
     * Blocks the current execution until user hits the ENTER. This method is usefull to
     * get the step by step actions from the player.
     */
    public static void trace() {
        try {
            System.out.println("계속하려면 Enter 키를 누르십시오...");
            Scanner scanner = new Scanner(System.in).useDelimiter("");
            scanner.next();
        } catch (Exception error) {
            // Continue...
        }
    }

    /**
     * Returns the player score based on his list of actions and current state.
     * @param player The player instance
     * @return The current score
     */
    protected static int getScore(Player player) {
        int sum = 0;
        // Score if have deceased
        if (player.isDead()) sum += -1000;
        // Score if have picked the gold
        if (player.hasGold()) sum += +1000;
        // Calculate the score for each action
        for(Action action : player.getActions()) {
            switch (action) {
                case GO_FORWARD:
                case TURN_LEFT:
                case TURN_RIGHT:
                case GRAB:
                    sum += -1;
                    break;
                case SHOOT_ARROW:
                    sum += -10;
                    break;
            }
        }
        return sum;
    }

    /**
     * Returns the icon for a environment element.
     * @param element The element
     * @return The icon
     */
    protected static String getIcon(Element element) {
        switch (element) {
            case WUMPUS: return "W";
            case HUNTER: return "H";
            case PIT: return "P";
            case GOLD: return "$";
        }
        return " ";
    }

    /**
     * Returns the icon for a perceptions.
     * @param perception The perception
     * @return The icon
     */
    protected static String getIcon(Perception perception) {
        switch (perception) {
            case GLITTER: return "*";
            case STENCH: return "~";
            case BREEZE: return "≈ "; 
        }
        return " ";
    }

    /**
     * Return the icon of the player based in its direction o if its dead.
     * @param player The player instance
     * @return The icon
     */
    protected static String getIcon(Player player) {
        if (player.isDead()) return "†";

        switch (player.getDirection()) {
            case N: return  "↑";
            case E: return "→";
            case S: return "↓";
            case W: return  "←";
        }

        return "H";
    }
}
