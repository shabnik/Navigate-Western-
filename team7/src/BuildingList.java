import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class represents the list of buildings
 *
 * @author team7
 */
public class BuildingList {
    public static Building[] allBuildings;

    public static void newBuildingList() {
        allBuildings = new Building[5];
    }


    /**
     * Method to add a building
     *
     * @param toAddBuilding building we want to add
     */
    public static void addBuilding(Building toAddBuilding) {
        if (findBuilding(toAddBuilding.getName()) == null) {
            Boolean checkFull = true;

            for (int i = 0; i < allBuildings.length; i++) {
                if (allBuildings[i] == null) {

                    checkFull = false;

                    allBuildings[i] = toAddBuilding;

                    i = allBuildings.length;

                }
            }

            if (checkFull == true) {
                Building[] newBuildingList = new Building[allBuildings.length * 2];
                for (int j = 0; j < allBuildings.length; j++) {
                    newBuildingList[j] = allBuildings[j];
                }
                newBuildingList[allBuildings.length + 1] = toAddBuilding;
                allBuildings = newBuildingList;

            }
        }
    }


    /**
     * Method to remove a building
     *
     * @param oldBuilding building we want to remove
     */
    public static void removeBuilding(String oldBuilding) {
        for (int i = 0; i < allBuildings.length; i++) {
            if (allBuildings[i] != null) {
                if (allBuildings[i].getName() == oldBuilding) {

                    for (int j = i + 1; j < allBuildings.length; j++) {
                        allBuildings[i] = allBuildings[j];
                        i++;
                    }
                }
            }
        }

    }

    /**
     * Method to save the list of buildings
     */
    public static void saveBuildings() throws IOException {
        FileWriter buildingFileWriter = new FileWriter("BuildingFile.txt", false);
        PrintWriter buildingPrintWriter = new PrintWriter(buildingFileWriter, false);
        buildingPrintWriter.flush();
        buildingPrintWriter.close();
        buildingFileWriter.close();

        FileWriter mapFileWriter = new FileWriter("MapFile.txt", false);
        PrintWriter mapPrintWriter = new PrintWriter(mapFileWriter, false);
        mapPrintWriter.flush();
        mapPrintWriter.close();
        mapFileWriter.close();

        FileWriter roomFileWriter = new FileWriter("RoomFile.txt", false);
        PrintWriter roomPrintWriter = new PrintWriter(roomFileWriter, false);
        roomPrintWriter.flush();
        roomPrintWriter.close();
        roomFileWriter.close();

        FileWriter poiFileWriter = new FileWriter("POIFile.txt", false);
        PrintWriter poiPrintWriter = new PrintWriter(poiFileWriter, false);
        poiPrintWriter.flush();
        poiPrintWriter.close();
        poiFileWriter.close();

        for (int i = 0; i < allBuildings.length; i++) {

            if (allBuildings[i] != null) {
                allBuildings[i].buildingSave();
            }

        }


    }

    /**
     * Method to print all buildings in the list
     */
    public static void printBuildings() {
        for (int i = 0; i < allBuildings.length; i++) {
            if (allBuildings[i] != null) {
                System.out.println(i);
                System.out.println(allBuildings[i].getName());

            }

        }
    }


    /**
     * @param buildingName the name of the building we are looking for
     * @return the building or null if it doesnt exist
     */
    public static Building findBuilding(String buildingName) {
        for (int i = 0; i < allBuildings.length; i++) {
            if (allBuildings[i] != null) {
                if (allBuildings[i].getName().equalsIgnoreCase(buildingName)) {
                    return allBuildings[i];
                }
            }
        }
        return null;
    }
}