public class Shop extends Buildings {

    public Shop() {
        this.buildingName = "Shop";
        this.energyBalance = -1;
        this.pollution = 1;
        this.happiness = 2;
        this.wellBeing = 1;
    }


    @Override
    public String toString() {
        return "---- Shop ----\n" +
                "Energy Balance Impact: " + energyBalance + "\n" +
                "Pollution Impact: +" + pollution + "\n" +
                "Happiness Impact: +" + happiness + "\n" +
                "Well-being Impact: +" + wellBeing + "\n";
    }
}

