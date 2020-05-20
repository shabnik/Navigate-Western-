import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading the JSON files
 *
 * @author team7
 */

public class ReadJson {

    /**
     * constructor for the class
     *
     * @param file to be read
     */
    public ReadJson(String file) {
        BufferedReader readFile;
        String type = "";
        try {
            readFile = new BufferedReader(new FileReader(file));
            String line = readFile.readLine();
            line = readFile.readLine();

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ':') {
                    i = i + 3;
                    while (line.charAt(i) != '"') {
                        type = type + line.charAt(i);
                        i++;
                    }
                    i = line.length();
                }

            }
            readFile.close();

            if (type.equals("Building")) {

                readBuilding(file);

            } else if (type.equals("Map")) {
                readMap(file);
            } else if (type.equals("Room")) {
                readRoom(file);
            } else if (type.equals(("POI"))) {
                readPOI(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading in buildings
     *
     * @param buildingFile containing building information
     */
    private static void readBuilding(String buildingFile) {
        BufferedReader buildingReader;
        String name = "";

        try {

            buildingReader = new BufferedReader(new FileReader(buildingFile));
            String buildingLine = buildingReader.readLine();

            while (buildingLine != null) {
                String key = "";

                for (int i = 0; i < buildingLine.length(); i++) {
                    if (buildingLine.charAt(i) == '}') {
                        key = "DONE";
                    }

                    if (buildingLine.charAt(i) == '"') {
                        i++;

                        while (buildingLine.charAt(i) != '"') {
                            key = key + Character.toString(buildingLine.charAt(i));
                            i++;
                        }
                    }


                    if (key.equals("Name")) {
                        i = i + 5;
                        while (buildingLine.charAt(i) != '"') {
                            name = name + Character.toString(buildingLine.charAt(i));
                            i++;

                        }
                        i = buildingLine.length();

                    } else if (key.equals("DONE")) {
                        //System.out.println(name);


                        Building newBuilding = new Building();
                        newBuilding.changeName(name);


                        BuildingList.addBuilding(newBuilding);


                        name = "";
                        key = "";

                    }
                }
                buildingLine = buildingReader.readLine();
            }

            buildingReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading in maps
     *
     * @param mapFile containing map information
     */
    private static void readMap(String mapFile) {
        BufferedReader mapReader;
        String mapBuilding = "";
        String floor = "";
        String image = "";
        String width = "";
        String height = "";
        String visibility = "";


        try {

            mapReader = new BufferedReader(new FileReader(mapFile));
            String mapLine = mapReader.readLine();

            while (mapLine != null) {
                String key = "";

                for (int i = 0; i < mapLine.length(); i++) {

                    if (mapLine.charAt(i) == '}') {
                        key = "DONE";
                    }
                    if (mapLine.charAt(i) == '"') {
                        i++;

                        while (mapLine.charAt(i) != '"') {
                            key = key + Character.toString(mapLine.charAt(i));
                            i++;
                        }
                    }


                    if (key.equals("Building")) {
                        i = i + 5;
                        while (mapLine.charAt(i) != '"') {
                            mapBuilding = mapBuilding + mapLine.charAt(i);
                            i++;
                        }
                        i = mapLine.length();
                    } else if (key.equals("Floor")) {
                        i = i + 4;
                        while (mapLine.charAt(i) != ',') {
                            floor = floor + mapLine.charAt(i);
                            i++;
                        }
                        i = mapLine.length();
                    } else if (key.equals("MapImage")) {
                        i = i + 5;
                        while (mapLine.charAt(i) != '"') {
                            image = image + mapLine.charAt(i);
                            i++;
                        }
                        i = mapLine.length();
                    } else if (key.equals("Width")) {
                        i = i + 4;
                        while (mapLine.charAt(i) != ',') {
                            width = width + mapLine.charAt(i);
                            i++;
                        }
                        i = mapLine.length();
                    } else if (key.equals("Height")) {
                        i = i + 4;
                        while (mapLine.charAt(i) != ',') {
                            height = height + mapLine.charAt(i);
                            i++;
                        }
                        i = mapLine.length();
                    } else if (key.equals("Visibility")) {
                        i = i + 4;
                        while (i < mapLine.length()) {
                            visibility = visibility + mapLine.charAt(i);
                            i++;
                        }
                        i = mapLine.length();
                    } else if (key.equals("DONE")) {

                        // System.out.println(mapBuilding+width+height + floor + image + visibility);

                        Map newMap = new Map();
                        newMap.setVisibility(Boolean.parseBoolean(visibility));
                        newMap.setFloorMap(Integer.parseInt(floor));
                        newMap.loadMap(image);
                        newMap.setHeight(Integer.parseInt(height));
                        newMap.setWidth(Integer.parseInt(width));


                        for (int j = 0; j < BuildingList.allBuildings.length; j++) {
                            if (BuildingList.allBuildings[j] != null) {
                                if (BuildingList.allBuildings[j].getName().equals(mapBuilding)) {

                                    BuildingList.allBuildings[j].addMap(newMap);
                                    j = BuildingList.allBuildings.length;

                                }
                            }
                        }


                        mapBuilding = "";
                        floor = "";
                        image = "";
                        width = "";
                        height = "";
                        visibility = "";
                    }


                }


                mapLine = mapReader.readLine();
            }

            mapReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method for reading in rooms
     *
     * @param roomFile containing room information
     */
    private static void readRoom(String roomFile) {
        BufferedReader roomReader;

        String building = "";
        String map = "";
        String name = "";
        String alias = "";
        String roomNumber = "";
        String xCoordinate = "";
        String yCoordinate = "";
        String description = "";
        String faveStatus = "";

        try {

            roomReader = new BufferedReader(new FileReader(roomFile));
            String roomLine = roomReader.readLine();


            while (roomLine != null) {
                String key = "";

                for (int i = 0; i < roomLine.length(); i++) {

                    if (roomLine.charAt(i) == '}') {
                        key = "DONE";
                    }

                    if (roomLine.charAt(i) == '"') {
                        i++;

                        while (roomLine.charAt(i) != '"') {
                            key = key + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                    }

                    if (key.equals("Building")) {
                        i = i + 5;
                        while (roomLine.charAt(i) != '"') {
                            building = building + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();


                    } else if (key.equals("Map")) {
                        i = i + 4;
                        while (roomLine.charAt(i) != ',') {
                            map = map + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();


                    } else if (key.equals("Name")) {
                        i = i + 5;
                        while (roomLine.charAt(i) != '"') {
                            name = name + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();

                    } else if (key.equals("Alias")) {
                        i = i + 5;
                        while (roomLine.charAt(i) != '"') {
                            alias = alias + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();

                    } else if (key.equals("Number")) {
                        i = i + 4;
                        while (roomLine.charAt(i) != ',') {
                            roomNumber = roomNumber + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();

                    } else if (key.equals("XValue")) {
                        i = i + 4;
                        while (roomLine.charAt(i) != ',') {
                            xCoordinate = xCoordinate + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();

                    } else if (key.equals("YValue")) {
                        i = i + 4;
                        while (roomLine.charAt(i) != ',') {
                            yCoordinate = yCoordinate + Character.toString(roomLine.charAt(i));
                            i++;
                        }

                    } else if (key.equals("Description")) {
                        i = i + 5;
                        while (roomLine.charAt(i) != '"') {
                            description = description + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();

                    } else if (key.equals("FaveStatus")) {
                        i = i + 4;

                        while (i < roomLine.length()) {
                            faveStatus = faveStatus + Character.toString(roomLine.charAt(i));
                            i++;
                        }
                        i = roomLine.length();

                    } else if (key.equals("DONE")) {

                        //CREATE A NEW ROOM WITH taKEN DATA
                        Room newRoom = new Room(name, alias, Integer.parseInt(roomNumber), Float.parseFloat(xCoordinate), Float.parseFloat(yCoordinate), description, Boolean.parseBoolean(faveStatus));

                        if (Boolean.parseBoolean(faveStatus) == true) {
                            Favorite.addFave(newRoom);
                        }


                        //ADD ROOM TO MAP - USING MAP STRING

                        for (int j = 0; j < BuildingList.allBuildings.length; j++) {


                            if (BuildingList.allBuildings[j] != null) {
                                if (BuildingList.allBuildings[j].getName().equals(building)) {

                                    BuildingList.allBuildings[j].findMap(Integer.parseInt(map)).addExistingRoom(BuildingList.allBuildings[j].findMap(Integer.parseInt(map)).getHead(), newRoom);
                                    j = BuildingList.allBuildings.length;

                                }
                            }
                        }


                        key = "";
                        building = "";
                        map = "";
                        name = "";
                        alias = "";
                        roomNumber = "";
                        xCoordinate = "";
                        yCoordinate = "";
                        description = "";
                        faveStatus = "";
                    }

                }


                roomLine = roomReader.readLine();
            }

            roomReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading in POIs
     *
     * @param poiFile containing POI information
     */
    private static void readPOI(String poiFile) {
        BufferedReader poiReader;
        String building = "";
        String map = "";
        String name = "";
        String poiType = "";
        String poiNumber = "";
        String xCoordinate = "";
        String yCoordinate = "";
        String description = "";
        String visible = "";


        try {

            poiReader = new BufferedReader(new FileReader(poiFile));
            String poiLine = poiReader.readLine();

            while (poiLine != null) {
                String key = "";

                for (int i = 0; i < poiLine.length(); i++) {

                    if (poiLine.charAt(i) == '}') {
                        key = "DONE";
                    }

                    if (poiLine.charAt(i) == '"') {
                        i++;

                        while (poiLine.charAt(i) != '"') {
                            key = key + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                    }
                    if (key.equals("Building")) {
                        i = i + 5;
                        while (poiLine.charAt(i) != '"') {
                            building = building + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();


                    } else if (key.equals("Map")) {
                        i = i + 4;
                        while (poiLine.charAt(i) != ',') {
                            map = map + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();


                    } else if (key.equals("Name")) {
                        i = i + 5;
                        while (poiLine.charAt(i) != '"') {
                            name = name + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();

                    } else if (key.equals("POIType")) {
                        i = i + 5;
                        while (poiLine.charAt(i) != '"') {
                            poiType = poiType + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();

                    } else if (key.equals("Number")) {
                        i = i + 4;
                        while (poiLine.charAt(i) != ',') {
                            poiNumber = poiNumber + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();

                    } else if (key.equals("XValue")) {
                        i = i + 4;
                        while (poiLine.charAt(i) != ',') {
                            xCoordinate = xCoordinate + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();

                    } else if (key.equals("YValue")) {
                        i = i + 4;
                        while (poiLine.charAt(i) != ',') {
                            yCoordinate = yCoordinate + Character.toString(poiLine.charAt(i));
                            i++;
                        }

                    } else if (key.equals("Description")) {
                        i = i + 5;
                        while (poiLine.charAt(i) != '"') {
                            description = description + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();

                    } else if (key.equals("Visible")) {
                        i = i + 4;

                        while (poiLine.charAt(i) != ',') {
                            visible = visible + Character.toString(poiLine.charAt(i));
                            i++;
                        }
                        i = poiLine.length();

                    } else if (key.equals("DONE")) {

                        //CREATE A NEW POI WITH taKEN DATA
                        PointOfInterest newPOI;
                        Boolean status;


                        if (visible.equals("true")) {
                            status = true;

                        } else {
                            status = false;

                        }

                        newPOI = new PointOfInterest(name, poiType, Integer.parseInt(poiNumber), Float.parseFloat(xCoordinate), Float.parseFloat(yCoordinate), description, status);

                        //ADD POI TO MAP - USING MAP STRING
                        for (int j = 0; j < BuildingList.allBuildings.length; j++) {
                            if (BuildingList.allBuildings[j] != null) {
                                if (BuildingList.allBuildings[j].getName().equals(building)) {

                                    BuildingList.allBuildings[j].findMap(Integer.parseInt(map)).addExistingPOI(BuildingList.allBuildings[j].findMap(Integer.parseInt(map)).getPOI(), newPOI);
                                    System.out.println();

                                    j = BuildingList.allBuildings.length;
                                }
                            }
                        }

                        key = "";
                        building = "";
                        map = "";
                        name = "";
                        poiType = "";
                        poiNumber = "";
                        xCoordinate = "";
                        yCoordinate = "";
                        description = "";
                        visible = "";
                    }

                }

                poiLine = poiReader.readLine();
            }

            poiReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}