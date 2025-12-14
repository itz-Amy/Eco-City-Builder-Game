public class CoalPowerPlant extends Buildings {

    public CoalPowerPlant() {
        this.buildingName = "Coal Power Plant";
        this.energyBalance = 8;
        this.pollution = 6;
        this.happiness = -2;
        this.wellBeing = -3;
    }

    @Override
    public String toString() {
        return "---- Coal Power Plant ----\n" +
                "Energy Balance Impact: +" + energyBalance + "\n" +
                "Pollution Impact: +" + pollution + "\n" +
                "Happiness Impact: " + happiness + "\n" +
                "Well-being Impact: " + wellBeing + "\n";
    }
}
