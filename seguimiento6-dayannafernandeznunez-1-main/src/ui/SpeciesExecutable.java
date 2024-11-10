package ui;

import java.util.Scanner;
import model.*;

public class SpeciesExecutable {

    private Scanner reader;
    private SpeciesController speciesController;

    public static void main(String[] args) {
        SpeciesExecutable exe = new SpeciesExecutable();
        exe.showMainMenu();
    }

    public SpeciesExecutable() {
        reader = new Scanner(System.in);
        speciesController = new SpeciesController();
    }

    public void showMainMenu() {
        System.out.println("Welcome to Icesi Species");

        boolean stopFlag = false;

        while (!stopFlag) {
            System.out.println("\nType an option");
            System.out.println("1. Register a General Species");
            System.out.println("2. Register a Flora Species (Herbarium)");
            System.out.println("3. Register a Fauna Species (Zoological Collection)");
            System.out.println("4. Edit a Species");
            System.out.println("5. Delete a Species");
            System.out.println("6. Consult a Species");
            System.out.println("0. Exit");

            int mainOption = reader.nextInt();

            switch (mainOption) {
                case 1:
                    registerSpecies();
                    break;
                case 2:
                    registerFloraSpecies();
                    break;
                case 3:
                    registerFaunaSpecies();
                    break;
                case 4:
                    editSpecies();
                    break;
                case 5:
                    deleteSpecies();
                    break;
                case 6:
                    consultSpecies();
                    break;
                case 0:
                    System.out.println("Thanks for using our system");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("You must type a valid option");
                    break;
            }
        }
    }

    public void registerSpecies() {
        System.out.println("Type the new Species' name: ");
        String name = reader.next();
        System.out.println("Type the new Species' scientific name: ");
        String scientificName = reader.next();

        if (speciesController.registerSpecies(name, scientificName)) {
            System.out.println("Species registered successfully");
        } else {
            System.out.println("Error, Species couldn't be registered");
        }
    }

    public void registerFloraSpecies() {
        System.out.println("Type the new Flora Species' name: ");
        String name = reader.next();
        System.out.println("Type the new Flora Species' scientific name: ");
        String scientificName = reader.next();
        System.out.println("Does it have flowers? (true/false): ");
        boolean hasFlowers = reader.nextBoolean();
        System.out.println("Does it have fruits? (true/false): ");
        boolean hasFruits = reader.nextBoolean();
        System.out.println("Type the maximum height in meters: ");
        double maxHeight = reader.nextDouble();

        if (speciesController.registerFloraSpecies(name, scientificName, hasFlowers, hasFruits, maxHeight)) {
            System.out.println("Flora species registered successfully");
        } else {
            System.out.println("Error, Flora species couldn't be registered");
        }
    }

    public void registerFaunaSpecies() {
        System.out.println("Type the new Fauna Species' name: ");
        String name = reader.next();
        System.out.println("Type the new Fauna Species' scientific name: ");
        String scientificName = reader.next();
        System.out.println("Is it migratory? (true/false): ");
        boolean isMigratory = reader.nextBoolean();
        System.out.println("Type the maximum weight in kg: ");
        double maxWeight = reader.nextDouble();

        if (speciesController.registerFaunaSpecies(name, scientificName, isMigratory, maxWeight)) {
            System.out.println("Fauna species registered successfully");
        } else {
            System.out.println("Error, Fauna species couldn't be registered");
        }
    }

    public void editSpecies() {
        System.out.println("Which Species do you want to edit?");
        String query = speciesController.showSpeciesList();

        if (!query.equals("")) {
            System.out.println(query);
            System.out.println("Choose the species number to edit: ");
            int speciesIndex = reader.nextInt() - 1;

            if (speciesIndex >= 0 && speciesIndex < speciesController.getSpeciesCount()) {
                Species selectedSpecies = speciesController.getSpecies(speciesIndex);
                System.out.println("Editing " + selectedSpecies.getName());

                // Example for editing: we can edit name or scientific name, or species-specific data.
                System.out.println("Type new name (leave blank to keep current): ");
                String newName = reader.nextLine();
                if (!newName.isEmpty()) {
                    selectedSpecies.setName(newName);
                }

                System.out.println("Type new scientific name (leave blank to keep current): ");
                String newScientificName = reader.nextLine();
                if (!newScientificName.isEmpty()) {
                    selectedSpecies.setScientificName(newScientificName);
                }

                // Implement specific edits based on species type (e.g., Flora, Fauna)
                if (selectedSpecies instanceof FloraSpecies) {
                    FloraSpecies flora = (FloraSpecies) selectedSpecies;
                    System.out.println("Edit Flora species attributes (flowers, fruits, height):");
                    System.out.println("New max height (leave blank for no change): ");
                    String maxHeightInput = reader.nextLine();
                    if (!maxHeightInput.isEmpty()) {
                        flora.setMaxHeight(Double.parseDouble(maxHeightInput));
                    }
                    System.out.println("Does it have flowers? (true/false): ");
                    flora.setHasFlowers(reader.nextBoolean());
                    System.out.println("Does it have fruits? (true/false): ");
                    flora.setHasFruits(reader.nextBoolean());
                } else if (selectedSpecies instanceof FaunaSpecies) {
                    FaunaSpecies fauna = (FaunaSpecies) selectedSpecies;
                    System.out.println("Edit Fauna species attributes (migratory, max weight):");
                    System.out.println("Is it migratory? (true/false): ");
                    fauna.setMigratory(reader.nextBoolean());
                    System.out.println("New max weight (kg): ");
                    fauna.setMaxWeight(reader.nextDouble());
                }

                System.out.println("Species edited successfully!");
            } else {
                System.out.println("Invalid species number.");
            }
        } else {
            System.out.println("There aren't any species registered yet");
        }
    }

    public void deleteSpecies() {
        System.out.println("Which Species do you want to delete?");
        String query = speciesController.showSpeciesList();

        if (!query.equals("")) {
            System.out.println(query);
            System.out.println("Choose the species number to delete: ");
            int speciesIndex = reader.nextInt() - 1;

            if (speciesController.deleteSpecies(speciesIndex)) {
                System.out.println("Species deleted successfully");
            } else {
                System.out.println("Error, Species couldn't be deleted");
            }
        } else {
            System.out.println("There aren't any species registered yet");
        }
    }

    public void consultSpecies() {
        System.out.println("Which Species do you want to review?");
        String query = speciesController.showSpeciesList();

        if (!query.equals("")) {
            System.out.println(query);
            System.out.println("Choose the species number to consult: ");
            int speciesIndex = reader.nextInt() - 1;

            String speciesDetails = speciesController.consultSpecies(speciesIndex);
            System.out.println(speciesDetails);
        } else {
            System.out.println("There aren't any species registered yet");
        }
    }
}
