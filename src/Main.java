public class Main {
    public static void main(String[] args) {
        // Initialize the Model
        City city = new City();

        // Initialize the View/Controller (GUI)
        // This replaces the console loop in Game.java
        new GameFrame(city);
    }
}
