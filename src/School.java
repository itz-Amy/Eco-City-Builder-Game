public class School extends Buildings {

    public School() {
        this.buildingName = "School";
        this.energyBalance = -1;
        this.pollution = 1;
        this.happiness = 3;
        this.wellBeing = 4;
    }


    @Override
    public String toString() {
        return "---- School ----\n" +
                "Energy Balance Impact: " + energyBalance + "\n" +
                "Pollution Impact: +" + pollution + "\n" +
                "Happiness Impact: +" + happiness + "\n" +
                "Well-being Impact: +" + wellBeing + "\n";
    }

}
