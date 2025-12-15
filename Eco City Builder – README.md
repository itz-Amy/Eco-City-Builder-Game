# Eco City Builder – README

Project Description
Eco City Builder is a command-line Java simulation game where players build a city by adding and removing various types of buildings. Each building affects the city’s Energy, Pollution, Happiness, and Well-being. The goal is to create a balanced, sustainable city and unlock different endings based on performance.

System Requirements
• Java Development Kit (JDK) 8 or higher  
• A command-line interface (Command Prompt, PowerShell, Terminal, or Bash)

No external libraries or frameworks are required.

File Structure
All Java files must be in the same folder:

Game.java  
City.java  
Buildings.java  
GreenPowerPlant.java  
CoalPowerPlant.java  
Park.java  
Residences.java
School.java  
Factory.java  
PublicTransport.java  
Shop.java  
PublicService.java  


How to Compile the Game (Command Line)
1. Open Command Prompt or Terminal.
2. Navigate to the src file in the project folder.

Example: cd path/to/EcoCity/src

3. Compile all Java files with:
 javac *.java

If there are no errors, “.class” files will be generated.


How to Run the Game
After compiling, start the game by typing:
java Game

This should launch the game in the terminal.


How to Play
Select a building type from the menu.
Select whether to ADD or REMOVE the building.
Type the number of buildings you select.
View your  city metrics and decide on any other changes you want to make
When finished, select the finish option to end the game and to view your city statistics alongside your final ending.

Dependencies
This project uses only standard Java libraries (java.util.Scanner).
No external dependencies are required.


Assumptions and Notes
• All game files must be in the same folder.
• Building limits are enforced by the City class.
• Pollution is clamped to never display negative values.
• Percentages are capped between 0% and 100%.
• The game is designed to run in a terminal (text-based).


