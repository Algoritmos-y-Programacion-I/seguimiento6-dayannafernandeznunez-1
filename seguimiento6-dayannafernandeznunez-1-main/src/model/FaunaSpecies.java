package model;

public class FaunaSpecies extends Species {

    private boolean isMigratory;
    private double maxWeight;

    public FaunaSpecies(String name, String scientificName, boolean isMigratory, double maxWeight) {
        super(name, scientificName);
        this.isMigratory = isMigratory;
        this.maxWeight = maxWeight;
    }

    public boolean isMigratory() {
        return isMigratory;
    }

    public void setMigratory(boolean isMigratory) {
        this.isMigratory = isMigratory;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nIs Migratory: " + (isMigratory ? "Yes" : "No") +
                "\nMax Weight: " + maxWeight + " kg";
    }
}
