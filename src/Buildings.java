public abstract class Buildings {
    protected int energyBalance = 0;
    protected int pollution = 0;
    protected int happiness = 0;
    protected int wellBeing = 0;
    protected String buildingName;

    // Getters
    public int getEnergyBalance() { return energyBalance; }
    public int getPollution() { return pollution; }
    public int getHappiness() { return happiness; }
    public int getWellBeing() { return wellBeing; }
    public String getBuildingName() { return buildingName; }


    @Override
    public String toString() {
        return "---- " + buildingName + " ----\n" +
                "EnergyBalance: " + energyBalance + "\n" +
                "Pollution: " + pollution + "\n" +
                "Happiness: " + happiness + "\n" +
                "Well-being: " + wellBeing + "\n";
    }
}
