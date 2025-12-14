import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        City city = new City();
        boolean playing = true;

        System.out.println("Welcome to Eco City Builder!");
        System.out.println("Each building type has its own maximum limit.\n");


        while (playing) {

            System.out.println("\nChoose a building:");
            System.out.println("1. Solar Power Plant (" + city.getCount("Solar Power Plant") + "/3)");
            System.out.println("2. Coal Power Plant (" + city.getCount("Coal Power Plant") + "/3)");
            System.out.println("3. Park (" + city.getCount("Park") + "/3)");
            System.out.println("4. School (" + city.getCount("School") + "/3)");
            System.out.println("5. Factory (" + city.getCount("Factory") + "/3)");
            System.out.println("6. Public Transport (" + city.getCount("Public Transport") + "/3)");
            System.out.println("7. Shop (" + city.getCount("Shop") + "/3)");
            System.out.println("8. Public Service (" + city.getCount("Public Service") + "/3)");
            System.out.println("0. Finish City");

            System.out.print("\nYour choice: ");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Please enter a valid number.");
                continue;
            }

            int choice = sc.nextInt();
            if (choice == 0) {
                playing = false;
                break;
            }

            Buildings prototype = null;
            String name = "";

            switch (choice) {
                case 1 -> { prototype = new GreenPowerPlant(); name = "Solar Power Plant"; }
                case 2 -> { prototype = new CoalPowerPlant(); name = "Coal Power Plant"; }
                case 3 -> { prototype = new Park(); name = "Park"; }
                case 4 -> { prototype = new School(); name = "School"; }
                case 5 -> { prototype = new Factory(); name = "Factory"; }
                case 6 -> { prototype = new PublicTransport(); name = "Public Transport"; }
                case 7 -> { prototype = new Shop(); name = "Shop"; }
                case 8 -> { prototype = new PublicService(); name = "Public Service"; }
                default -> {
                    System.out.println("Invalid choice.");
                    continue;
                }
            }

            // ADD or REMOVE?
            System.out.println("\nDo you want to:");
            System.out.println("1. Add " + name);
            System.out.println("2. Remove " + name);
            System.out.print("Your choice: ");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid choice.");
                continue;
            }

            int action = sc.nextInt();

            if (action == 1) {
                // ADD
                int remaining = city.remainingFor(name);
                if (remaining <= 0) {
                    System.out.println("You cannot add more " + name + ". Limit reached.");
                    continue;
                }

                int qty = getValidCount(sc, 1, remaining, "Enter how many to ADD (1–" + remaining + "): ");

                // Apply effects
                city.addBuilding(prototype, qty);

                // Show building effects
                printAddEffects(prototype, qty);

                // Show updated totals
                printCityTotals(city);

            } else if (action == 2) {
                // REMOVE
                int current = city.getCount(name);
                if (current <= 0) {
                    System.out.println("There are no " + name + " buildings to remove.");
                    continue;
                }

                int qty = getValidCount(sc, 1, current, "Enter how many to REMOVE (1–" + current + "): ");

                // REMOVE: Reverse every metric
                city.removeBuilding(prototype, qty);

                // Show building removal effects
                printRemoveEffects(prototype, qty);

                // Show updated totals
                printCityTotals(city);

            } else {
                System.out.println("Invalid action.");
            }
        }

        finish(city);
        sc.close();
    }


    public static int getValidCount(Scanner sc, int min, int max, String message) {
        int qty;
        while (true) {
            System.out.print(message);
            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Enter a valid number.");
                continue;
            }
            qty = sc.nextInt();
            if (qty < min) {
                System.out.println("Cannot be negative or zero.");
            } else if (qty > max) {
                System.out.println("Maximum allowed is " + max + ".");
            } else {
                break;
            }
        }
        return qty;
    }

    // PRINT EFFECTS AFTER ADD
    public static void printAddEffects(Buildings b, int qty) {
        System.out.println("\n=== ADDED BUILDING EFFECTS ===");
        System.out.println("Energy Balance: " + (b.getEnergyBalance() * qty));
        System.out.println("Pollution: " + Math.max(0, b.getPollution() * qty));
        System.out.println("Happiness: " + (b.getHappiness() * qty));
        System.out.println("Well-being: " + (b.getWellBeing() * qty));
    }

    //  PRINT EFFECTS AFTER REMOVE
    public static void printRemoveEffects(Buildings b, int qty) {
        System.out.println("\n=== REMOVED BUILDING EFFECTS ===");
        System.out.println("Energy Balance: " + -(b.getEnergyBalance() * qty));
        System.out.println("Pollution: " + Math.max(0, -(b.getPollution() * qty)));
        System.out.println("Happiness: " + -(b.getHappiness() * qty));
        System.out.println("Well-being: " + -(b.getWellBeing() * qty));
    }

    // CITY TOTALS WITH POLLUTION CAPPED
    public static void printCityTotals(City city) {
        int pollShown = Math.max(0, city.getTotalPollution());
        System.out.println("\n----- UPDATED CITY STATUS -----");
        System.out.println("Total Pollution: " + pollShown + " (negative shown as 0)");
        System.out.println("Total Energy Balance: " + city.getTotalEnergy());
        System.out.println("Total Happiness: " + city.getTotalHappiness());
        System.out.println("Total Well-being: " + city.getTotalWellBeing());
        System.out.println("--------------------------------");
    }

    //  FINAL PERCENTAGES
    public static void finish(City city) {
        int rawP = city.getTotalPollution();
        int P = Math.max(0, rawP);
        int E = city.getTotalEnergy();
        int H = city.getTotalHappiness();
        int W = city.getTotalWellBeing();

        System.out.println("\n===== FINAL CITY REPORT =====");
        System.out.println("Pollution: " + P);
        System.out.println("Energy Balance: " + E);
        System.out.println("Happiness: " + H);
        System.out.println("Well-being: " + W);

        final int TARGET = 30;

        double pollutionHealthPct = clampPercent((TARGET - P) / (double) TARGET * 100);
        double energyPct = clampPercent(E / (double) TARGET * 100);
        double happinessPct = clampPercent(H / (double) TARGET * 100);
        double wellbeingPct = clampPercent(W / (double) TARGET * 100);

        System.out.println("\nConverted to Percentages:");
        System.out.printf("Pollution Health: %.1f%%\n", pollutionHealthPct);
        System.out.printf("Energy balance: %.1f%%\n", energyPct);
        System.out.printf("Happiness: %.1f%%\n", happinessPct);
        System.out.printf("Well-being: %.1f%%\n", wellbeingPct);

        //  CITY ENDINGS
        System.out.println("\n===== CITY ENDING =====");

        if (pollutionHealthPct >= 90 && energyPct >= 80 && happinessPct >= 85 && wellbeingPct >= 85) {
            System.out.println("UTOPIA ");
            System.out.println("Your city has become a perfect harmony of clean energy, happy citizens, and thriving well-being. A shining example for the world!");

        } else if (pollutionHealthPct >= 80 && energyPct >= 70 && happinessPct >= 70 && wellbeingPct >= 70) {
            System.out.println("ECO PARADISE ");
            System.out.println("Your city is a sustainable haven with strong infrastructure and satisfied citizens.");

        } else if (pollutionHealthPct < 40 && happinessPct >= 80 && wellbeingPct >= 80 && energyPct >= 60) {
            System.out.println("JOYFUL CHAOS ");
            System.out.println("Despite environmental struggles, your citizens remain incredibly happy and socially fulfilled.");

        } else if (pollutionHealthPct < 50 && energyPct < 40) {
            System.out.println("INDUSTRIAL COLLAPSE ");
            System.out.println("The city has been overwhelmed by pollution and energy shortages, leading to declining living conditions.");

        } else if (happinessPct < 40 && wellbeingPct < 40 && pollutionHealthPct >= 60) {
            System.out.println("EMPTY SHELL ");
            System.out.println("Your city is clean and efficient — but emotionally lifeless. Something important is missing.");

        } else if (energyPct >= 85 && (happinessPct < 50 || wellbeingPct < 50) ) {
            System.out.println("TECHNOCRATIC STATE ");
            System.out.println("Your city runs like a machine: powerful, efficient, but lacking warmth and community spirit.");

        } else {
            System.out.println("BALANCED REALITY ");
            System.out.println("Your city has grown into a realistic society with strengths, weaknesses, and room to evolve.");
        }
    }

    private static double clampPercent(double v) {
        if (v < 0) return 0;
        if (v > 100) return 100;
        return v;
    }

}