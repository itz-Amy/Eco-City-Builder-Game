import java.util.HashMap;
import java.util.Map;

public class City {

    // CITY TOTAL METRICS
    private int totalPollution = 0;
    private int totalEnergy = 0;
    private int totalHappiness = 0;
    private int totalWellBeing = 0;

    // Building counts
    private Map<String, Integer> buildingCounts = new HashMap<>();

    // Building max limits
    private final Map<String, Integer> buildingLimits = new HashMap<>();

    public City() {
        buildingLimits.put("Solar Power Plant", 3);
        buildingLimits.put("Coal Power Plant", 2);
        buildingLimits.put("Park", 3);
        buildingLimits.put("School", 2);
        buildingLimits.put("Factory", 3);
        buildingLimits.put("Public Transport", 3);
        buildingLimits.put("Shop", 3);
        buildingLimits.put("Public Service", 3);
        buildingLimits.put("Residence", 3);
    }

    //  CHECK IF WE CAN ADD
    public boolean canAdd(String name, int qty) {
        int current = buildingCounts.getOrDefault(name, 0);
        int limit = buildingLimits.get(name);
        return current + qty <= limit;
    }

    // GET HOW MANY CAN STILL BE ADDED
    public int remainingFor(String name) {
        return buildingLimits.get(name) - buildingCounts.getOrDefault(name, 0);
    }

    //  ADD BUILDINGS (APPLY EFFECTS)
    public void addBuilding(Buildings b, int qty) {

        String name = b.getBuildingName();
        int current = buildingCounts.getOrDefault(name, 0);

        if (current + qty > buildingLimits.get(name)) {
            qty = buildingLimits.get(name) - current; // cap to limit
        }

        // Update count
        buildingCounts.put(name, current + qty);

        // Apply effects
        totalPollution += b.getPollution() * qty;
        totalEnergy += b.getEnergyBalance() * qty;
        totalHappiness += b.getHappiness() * qty;
        totalWellBeing += b.getWellBeing() * qty;
    }

    // REMOVE BUILDINGS (REVERSE EFFECTS)
    public void removeBuilding(Buildings b, int qty) {

        String name = b.getBuildingName();
        int current = buildingCounts.getOrDefault(name, 0);

        // Can't remove more than exist
        if (qty > current) {
            qty = current;
        }

        // Update count
        buildingCounts.put(name, current - qty);

        // Reverse effects
        totalPollution -= b.getPollution() * qty;
        totalEnergy -= b.getEnergyBalance() * qty;
        totalHappiness -= b.getHappiness() * qty;
        totalWellBeing -= b.getWellBeing() * qty;
    }

    // ---- GET COUNT OF A SPECIFIC BUILDING ----
    public int getCount(String name) {
        return buildingCounts.getOrDefault(name, 0);
    }

    // ---- TOTAL METRICS (raw values; Game handles pollution capping) ----
    public int getTotalPollution() {
        return totalPollution;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }

    public int getTotalHappiness() {
        return totalHappiness;
    }

    public int getTotalWellBeing() {
        return totalWellBeing;
    }
}
