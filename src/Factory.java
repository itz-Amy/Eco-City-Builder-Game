public class Factory extends Buildings {

    public Factory() {
        this.buildingName = "Factory";
        this.energyBalance = -2;
        this.pollution = 5;
        this.happiness = -2;
        this.wellBeing = -1;
    }

    @Override
    public String toString() {
        return "---- Factory ----\n" +
                "Energy Balance Impact: " + energyBalance + "\n" +
                "Pollution Impact: +" + pollution + "\n" +
                "Happiness Impact: " + happiness + "\n" +
                "Well-being Impact: " + wellBeing + "\n";
    }


}
