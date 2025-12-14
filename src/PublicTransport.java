public class PublicTransport extends Buildings {

    public PublicTransport() {
        this.buildingName = "Public Transport";
        this.energyBalance = -2;
        this.pollution = -3;
        this.happiness = 2;
        this.wellBeing = 3;
    }

    @Override
    public String toString() {
        return "---- Public Transport ----\n" +
                "Energy Balance Impact: " + energyBalance + "\n" +
                "Pollution Impact: " + pollution + "\n" +
                "Happiness Impact: +" + happiness + "\n" +
                "Well-being Impact: +" + wellBeing + "\n";
    }



}
