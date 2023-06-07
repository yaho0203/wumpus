package start;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import agents.HeuristicAgent;
import agents.RandomAgent;
import wumpus.Agent;
import wumpus.World;
import wumpus.Environment.Action;

/**
 * Entry point for the application.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Create a 4x4 world
            World world = new World(4, 4);

            // Print the game title
            System.out.println("Wumpus World!");

            // Start and execute the AI agent
            Agent agent = new HeuristicAgent(world.getWidth(), world.getHeight()); 
            world.execute(agent);

            // Print the board and score table
            System.out.println("Board:");
            System.out.println(world.renderAll());

            System.out.format("Results for %s:%n", world.getAgentName());
            System.out.println(world.renderScore());
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
