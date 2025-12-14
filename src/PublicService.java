public class PublicService extends Buildings {

    public PublicService() {
        this.buildingName = "Public Service";
        // Public services (hospitals, clinics, etc.) typically consume energy but strongly boost wellbeing
        this.energyBalance = -2; // consumes energy
        this.pollution = 1;      // some pollution from operations/waste
        this.happiness = 4;
        this.wellBeing = 5;      // strong wellbeing boost
    }



    @Override
    public String toString() {
        return "\n[ Public Service ]\n" +
                "Energy Balance: " + energyBalance + "\n" +
                "Pollution: +" + pollution + "\n" +
                "Happiness: +" + happiness + "\n" +
                "Well-being: +" + wellBeing + "\n";
    }
}
