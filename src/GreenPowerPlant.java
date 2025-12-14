public class GreenPowerPlant extends Buildings {

    public GreenPowerPlant() {
        this.buildingName = "Solar Power Plant";
        this.energyBalance = 6;
        this.pollution = -3;
        this.happiness = 1;
        this.wellBeing = 2;
    }

    @Override
    public String toString() {
        return "---- Green Power Plant ----\n" +
                "Energy Balance Impact: +" + energyBalance + "\n" +
                "Pollution Impact: " + pollution + "\n" +
                "Happiness Impact: +" + happiness + "\n" +
                "Well-being Impact: +" + wellBeing + "\n";
    }


}
