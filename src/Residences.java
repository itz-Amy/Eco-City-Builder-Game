class Residences extends Buildings {

    public Residences() {
        this.buildingName = "Residence";

        this.energyBalance = -2; // many residents
        this.pollution = 1;
        this.happiness = 3; // housing increases happiness
        this.wellBeing = 1;
    }



    @Override
    public String toString() {
        return "\n[ Residences ]" +
                "\nEffects:" +
                "\n• Pollution: +" + pollution +
                "\n• Energy Production: +" + energyBalance +
                "\n• Happiness: +" + happiness +
                "\n• Well-being: +" + wellBeing +
                "\n--------------------------------------";
    }
}