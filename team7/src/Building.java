import java.io.*;

/**
 * This class represents one building
 *
 * @author team7
 */
public class Building {
    private String name;
    private Building currrentBuilding; //head of the linked list for the maps that belong in this building.
    private Map nextMap;
    private Map headMap;
    private Map prevMap;
    private Map tail;
    private Map current;

    /**
     * Default way of creating a building object
     */
    public Building() {
        name = null;
        currrentBuilding = null;
        nextMap = null;
        headMap = null;
        prevMap = null;
    }

    /**
     * A specific way of create a building
     *
     * @Param Name: Name of the building
     * @Param CurBuilding: The Current building that is being displayed
     * @Param nextMap: The next Map in the building after Current
     * @Param headMap: The head of the map list
     * @Param prevMap: The previous map in map list
     */
    public Building(String name, Building currrentBuilding, Map nextMap, Map headMap, Map prevMap) {
        this.name = name;
        this.currrentBuilding = currrentBuilding;
        this.nextMap = nextMap;
        this.headMap = headMap;
        this.prevMap = prevMap;
    }


    /**
     * Method to add maps to the map list.
     *
     * @Param newMap:The new map to be added to the map list
     */
    public void addMap(Map newMap) {
        if (findMap(newMap.getFloorMap()) == null) {
            Map toAdd = newMap;

            if (headMap == null || headMap.getFloorMap() > newMap.getFloorMap()) {
                addHead(toAdd);
            } else {
                addTail(toAdd);
            }
        }
    }


    /**
     * Adds a Map to the front of the map list
     *
     * @param toAdd: The new Map to be added to Map list.
     */
    public void addHead(Map toAdd) {
        Map newMap = toAdd;

        newMap.setPrev(null);

        if (headMap != null) {
            headMap.setPrev(newMap);
            newMap.setNext(headMap);
        }

        headMap = newMap;
    }


    /**
     * The Map is added to the end of the map List.
     *
     * @param toAdd: The new map to be added
     */
    public void addTail(Map toAdd) {
        Map newMap = toAdd;
        Map curr = headMap;
        newMap.setNext(null);


        while (curr != null) {
            if (curr.getNext() == null) {
                curr.setNext(newMap);
                newMap.setNext(null);
                newMap.setPrev(curr);
                break;
            }
            curr = curr.getNext();
        }
    }

    /**
     * searches the map using the floor number
     *
     * @Param mapFloor: The floor number
     * @ReturnMap: If map is found it returns the map else returns null.
     */
    public Map findMap(int mapFloor) {

        Map current = headMap;
        while (current != null) {

            if (current.getFloorMap() == mapFloor) {

                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Getter for getting the start of the list
     *
     * @return the head node
     */
    public Map getHeadMap() {
        return headMap;
    }

    /**
     * Finds the Map to be deleted and removes it.
     *
     * @Param Head:The head of the Map list
     * @Param removeMap: The map to remove
     * @Return: Map Head or Null if map removeMap does not exist.
     */
    public Map removeMap(Map head, Map removeMap) {

        if (head == null || removeMap == null)
            return null;
        if (head == removeMap) {
            head = removeMap.getNext();
        }
        if (removeMap.getNext() != null) {
            removeMap.getNext().setPrev(removeMap.getPrev());
        }
        if (removeMap.getPrev() != null) {
            removeMap.getPrev().setNext(removeMap.getNext());
        }
        System.out.println("The Map found is:" + removeMap.getFloorMap());
        removeMap = null;
        return head;
    }

    /**
     * Searches all the maps in a building for a room number
     *
     * @Param head: the head of the map list
     * @Param toFine: Room to find
     * @Return: The Room found or null if it doesnt exist.
     */
    public Room findRoomInBuilding(int toFind) {
        Map current = headMap;

        while (current != null) {
            Room currentRoomHead = current.getHead();
            while (currentRoomHead != null) {
                if (currentRoomHead.getRoom() == toFind) {
                    this.current = current;
                    return currentRoomHead;
                }
                currentRoomHead = currentRoomHead.getNext();
            }
            current = current.getNext();
        }
        //if room does not exist.
        return null;
    }

    /**
     * Searches all the maps in a building for a room name or alias
     *
     * @Param head: the head of the map list
     * @Param toFine: Room to find
     * @Return: The Room found or null if it doesnt exist.
     */
    public Room findRoomNameInBuilding(String toFind) {
        Map current = headMap;

        while (current != null) {
            Room currentRoomHead = current.getHead();
            while (currentRoomHead != null) {
                if ((currentRoomHead.getName().equalsIgnoreCase(toFind)) || (currentRoomHead.getAlias().equalsIgnoreCase(toFind))) {
                    this.current = current;
                    return currentRoomHead;
                }
                currentRoomHead = currentRoomHead.getNext();
            }
            current = current.getNext();
        }
        //if room does not exist.
        return null;
    }


    /**
     * @return: The Current map
     */
    public Map currentMap() {
        return current;
    }

    /**
     * @Param map: Map to get the next map
     * @Return: The next map if its not null else null
     */
    public Map nextMap(Map map) {
        if (map.getNext() != null) {
            current = map.getNext();
            return current;
        } else return null;
    }

    /**
     * @Param map: Map to get the previous map of
     * @Return: The previous map or null if it doesn't exist
     */
    public Map prevMap(Map map) {
        current = map.getPrev();
        return current;
    }

    /**
     * @return The string name
     */
    public String getName() {
        return name;
    }

    /**
     * @Param newName: the new building name
     */
    public void changeName(String newName) {
        name = newName;
    }

    /**
     * Prints the Map list
     *
     * @Param head: The head of the map list
     */
    public void printList(Map head) {
        if (head == null)
            System.out.print("Map list is empty");

        while (head != null) {
            System.out.print("\nMap: " + head.getFloorMap());
            head = head.getNext();
        }
    }

    /**
     * Saves information about the building object so it can be reloaded later, using Json
     */
    public void buildingSave() {
        try (FileWriter buildingFile = new FileWriter("BuildingFile.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(buildingFile);
             PrintWriter buildingOut = new PrintWriter(bufferedWriter)) {

            buildingOut.println("{");
            buildingOut.println("\t\"type\" : \"Building\",");
            buildingOut.println("\t\"Name\" : \"" + name + "\"");
            buildingOut.println("}");

            buildingOut.close();
            buildingFile.close();

        } catch (IOException e) {
        }

        try (FileWriter mapFile = new FileWriter("MapFile.txt", true);
             BufferedWriter mapWriter = new BufferedWriter(mapFile);
             PrintWriter mapOut = new PrintWriter(mapWriter)) {

            Map currentMap = headMap;

            //Go through every map and add them to MapFile.txt
            while (currentMap != null) {
                System.out.println(currentMap.getWidth());
                mapOut.println("{");
                mapOut.println("\t\"type\" : \"Map\",");
                mapOut.println("\t\"Building\" : \"" + name + "\",");
                mapOut.println("\t\"Floor\" : " + Integer.toString(currentMap.getFloorMap()) + ",");
                mapOut.println("\t\"MapImage\" : \"" + currentMap.getFileLocation() + "\",");
                mapOut.println("\t\"Width\" : " + Integer.toString(currentMap.getWidth()) + ",");
                mapOut.println("\t\"Height\" : " + Integer.toString(currentMap.getHeight()) + ",");
                mapOut.println("\t\"Visibility\" : " + Boolean.toString(currentMap.getVisibility()));
                mapOut.println("}");


                try (FileWriter roomFile = new FileWriter("RoomFile.txt", true);
                     BufferedWriter roomWriter = new BufferedWriter(roomFile);
                     PrintWriter roomOut = new PrintWriter(roomWriter)) {

                    Room currentRoom = currentMap.getHead();


                    //Go Through every room and add them to roomFile
                    while (currentRoom != null) {
                        roomOut.println("{");
                        roomOut.println("\t\"type\" : \"Room\",");
                        roomOut.println("\t\"Building\" : \"" + name + "\",");
                        roomOut.println("\t\"Map\" : " + Integer.toString(currentMap.getFloorMap()) + ",");
                        roomOut.println("\t\"Name\" : \"" + currentRoom.getName() + "\",");
                        roomOut.println("\t\"Alias\" : \"" + currentRoom.getAlias() + "\",");
                        roomOut.println("\t\"Number\" : " + Integer.toString(currentRoom.getRoom()) + ",");
                        roomOut.println("\t\"XValue\" : " + Float.toString(currentRoom.getX()) + ",");
                        roomOut.println("\t\"YValue\" : " + Float.toString(currentRoom.getY()) + ",");
                        roomOut.println("\t\"Description\" : \"" + currentRoom.getDescription() + "\",");
                        roomOut.println("\t\"FaveStatus\" : " + Boolean.toString(currentRoom.checkStatus()));
                        roomOut.println("}");

                        currentRoom = currentRoom.getNext();
                    }
                    roomOut.close();
                    roomFile.close();


                } catch (IOException e) {
                }


                try (FileWriter poiFile = new FileWriter("POIFile.txt", true);
                     BufferedWriter poiWriter = new BufferedWriter(poiFile);
                     PrintWriter poiOut = new PrintWriter(poiWriter)) {

                    PointOfInterest currentPOI = currentMap.getPOI();

                    while (currentPOI != null) {


                        poiOut.println("{");
                        poiOut.println("\t\"type\" : \"POI\",");
                        poiOut.println("\t\"Building\" : \"" + name + "\",");
                        poiOut.println("\t\"Map\" : " + Integer.toString(currentMap.getFloorMap()) + ",");
                        poiOut.println("\t\"Name\" : \"" + currentPOI.getName() + "\",");
                        poiOut.println("\t\"POIType\" : \"" + currentPOI.getType() + "\",");
                        poiOut.println("\t\"Visible\" : " + Boolean.toString(currentPOI.getVisible()) + ",");
                        poiOut.println("\t\"Number\" : " + Integer.toString(currentPOI.getRoom()) + ",");
                        poiOut.println("\t\"XValue\" : " + Float.toString(currentPOI.getX()) + ",");
                        poiOut.println("\t\"YValue\" : " + Float.toString(currentPOI.getY()) + ",");
                        poiOut.println("\t\"Description\" : \"" + currentPOI.getDescription() + "\"");
                        poiOut.println("}");

                        currentPOI = currentPOI.getNext();
                    }
                    poiOut.close();
                    poiFile.close();

                } catch (IOException e) {
                }


                currentMap = currentMap.getNext();

            }


            mapOut.close();
            mapFile.close();

        } catch (IOException e) {

        }


    }
}
