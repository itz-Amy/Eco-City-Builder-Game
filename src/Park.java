public class Park extends Buildings {

    public Park() {
        this.buildingName = "Park";
        this.energyBalance = 0;
        this.pollution = -4;
        this.happiness = 4;
        this.wellBeing = 5;
    }

    @Override
    public String toString() {
        return "---- Park ----\n" +
                "Energy Balance Impact: +" + energyBalance + "\n" +
                "Pollution Impact: " + pollution + "\n" +
                "Happiness Impact: +" + happiness + "\n" +
                "Well-being Impact: +" + wellBeing + "\n";
    }
}

