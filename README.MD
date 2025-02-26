# Ant World Simulation
# Marcel Rojewski
## About

Ant World Simulation is a Java-based program that models a dynamic environment where red and blue ants compete for limited food resources (larvae). The simulation represents the struggle for survival, requiring ants to transport larvae to their respective anthills before resources run out.
  
Users can interact with the simulation by adding or removing ants through a side panel. The simulation ends when all larvae are depleted, and the winner is determined based on the number of larvae successfully collected.

![Image](https://github.com/user-attachments/assets/31c87731-06e5-4a94-8be5-43723317d7c5)  

## Features
- A simulated world composed of interconnected vertices where ants navigate.
- Two competing ant colonies: red and blue. 
- Five distinct types of ants, each with unique roles:  
    - Soldier (Red Ant): Attacks enemy ants.  
    - Collector (Red Ant): Collects and transports larvae.  
    - Blunderer (Red Ant): A flawed Collector that occasionally drops larvae.  
    - Worker (Blue Ant): Can both attack enemies and collect larvae.  
    - Drone (Blue Ant): Serves as a shield, absorbing hits.  
- Real-time user interaction: Add or remove ants dynamically.

## Technical Details
- Multi-threaded Execution:  
Each ant operates as an independent thread, leading to race conditions in collecting larvae and attacking enemies. Proper synchronization mechanisms were implemented to handle these conflicts.
- Graph-Based Navigation:  
The world is structured as a graph where vertices represent locations. Ants move between vertices according to predefined movement logic.
- Collision & Combat System:  
When ants from opposing colonies encounter each other, combat may occur based on predefined attack mechanics.
- Simulation Control:  
The simulation dynamically tracks resource depletion and concludes when larvae are exhausted.
## Usage
- Executable JAR:  
Run AntWorld.jar from out/artifacts/AntWorld_jar to launch the simulation.
- Source Code & Development:
The main entry point is Main.java, located in src/main/java/com/yourpackage/gui. Open this file in an IDE like IntelliJ IDEA to modify or run the program.
- Documentation:  
Full documentation is available in the Documentation folder. Open index.html for a detailed project overview.
