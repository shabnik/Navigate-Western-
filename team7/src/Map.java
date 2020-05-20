import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Map Class
 * @author team7
 */
public class Map {
    private Room head;//head of the linked list.
    private String buildingName;
    private PointOfInterest poiHead;//head of the linked list for the POI list
    private BufferedImage mapImage;
    private Image mapScaled;
    private int width;
    private int height;
    private Boolean visibility;
    private Map next;
    private Map prev;
    private String name;
    private int floor;
    private String fileLocation;

    /**
     * Default way of creating a Map object
     */
    public Map() {
        head = null;
        poiHead = null;
        mapImage = null;
        mapScaled = null;
        width = 899;
        height = 610;
        visibility = true;
        name = null;
        floor = 0;
        fileLocation = null;
    }

    /**
     * Creates a Map Object taking several variables as inputs
     */
    public Map(int floorMap, String name, Room head, PointOfInterest poiHead, BufferedImage mapImage, Image mapScaled, int width, int height, Boolean visibility, Map next, Map prev) {
        this.name = name;
        this.head = head;
        this.poiHead = poiHead;
        this.mapImage = mapImage;
        this.mapScaled = mapScaled;
        this.width = width;
        this.height = height;
        this.visibility = visibility;
        this.next = next;
        this.prev = prev;
        this.floor = floorMap;
    }

    /**
     * getter method for head map
     *
     * @return head map
     */
    //returns the head of the list, used to access the rooms
    public Room getHead() {
        return head;
    }

    /**
     * getter method for next map
     *
     * @return the next map
     */
    public Map getNext() {
        return next;
    }

    /**
     * getter method for previous map
     *
     * @return the previous map
     */
    public Map getPrev() {
        return prev;
    }

    /**
     * Setter Method for next map
     *
     * @param next
     */

    public void setNext(Map next) {
        this.next = next;
    }

    /**
     * Setter method for previous map
     *
     * @param prev
     */
    public void setPrev(Map prev) {
        this.prev = prev;
    }

    /**
     * Setter method for setting which floor to display
     *
     * @param num
     */
    public void setFloorMap(int num) {
        this.floor = num;
    }

    /**
     * getter method for the floor map
     *
     * @return the floor map
     */
    public int getFloorMap() {
        return floor;
    }

    /**
     * getter method for the file's location
     *
     * @return file location
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * Setter method for setting the width
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setter method for setting the height
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * getter method for height
     *
     * @return height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter method for the width
     *
     * @return the width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter method for Point of Interests
     *
     * @return the head of the POI list
     */
    public PointOfInterest getPOI() {
        return poiHead;
    }

    /**
     * Getter method for the scaled image
     *
     * @return the map scaled correctly
     */
    //returns th scaled image
    public Image getScaledImage() {
        return mapScaled;
    }

    /**
     * Setter method for setting the visibility of a map
     *
     * @param vis boolean
     */
    //Set Visibility
    public void setVisibility(Boolean vis) {
        visibility = vis;
    }

    /**
     * Getter method for returning the visibility of a map
     *
     * @return boolean
     */
    public Boolean getVisibility() {
        return visibility;
    }

    /**
     * Method for loading the map
     *
     * @param mapFileLocation file location of map
     * @return the processed map image
     */
    // importing the map
    public BufferedImage loadMap(String mapFileLocation) {
        try {
            fileLocation = mapFileLocation;
            mapImage = ImageIO.read(new File(mapFileLocation));
            System.out.println("A success!");
        } catch (IOException e) {
            System.out.println("Image could not be uploaded");
        }
        return mapImage;
    }


    /**
     * method to display the image
     *
     * @param i   image we want to display
     * @param vis true or false
     * @throws IOException
     */
    public void displayImage(Image i, Boolean vis) throws IOException {

        Image map = i;
        ImageIcon icon = new ImageIcon(map);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(800, 500);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(vis);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * rescales the map
     *
     * @param width
     * @param height
     * @throws IOException
     */
    public void ChangeScale(int width, int height) throws IOException {
        mapScaled = mapImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }


    /**
     * Method to add a room to the list
     *
     * @param first   the original list of rooms
     * @param newRoom the new room
     */
    public void addExistingRoom(Room first, Room newRoom) {
        if (findRoom(first, newRoom.getRoom()) == null) {
            //If the First element in the linked list is empty
            if (first == null) {
                first = newRoom;
            } else {
                Room last = first;
                while (last.getNext() != null) {
                    last = last.getNext();
                }
                last.setNext(newRoom);
            }
            head = first;
        }
    }


    /**
     * Method to print the linked list
     *
     * @param first the first node
     */
    public void printList(Room first) {

        Room current = first;
        System.out.print("Map LinkedList: ");

        while (current != null) {
            System.out.print(current.getRoom() + " ");
            current = current.getNext();
        }
    }

    /**
     * Deletes a Room from the Map list. returns null if room does not exist in list
     *
     * @param first   the start of the list
     * @param roomNum the room we want to delete
     * @return the deleted room
     */

    public Room removeRoom(Room first, int roomNum) {
        Room current = first;
        Room previous = null;

        // if the head room is the room to be deleted
        if (current != null) {
            if (current.getRoom() == roomNum) {
                if (current.getNext() == null) {
                    head = null;
                    return null;
                } else {
                    first = current.getNext(); // Changed head
                    head = first;
                    System.out.println("\n" + roomNum + " found and deleted");
                    return first;
                }
            }
        }
        // if the room is not the head
        while (current != null && current.getRoom() != roomNum) {
            previous = current;
            current = current.getNext();
        }
        //if the room deleted wasn't the last room in the linked list
        if (current != null) {
            previous.setNext(current.getNext());
            System.out.println("\n" + roomNum + " found and deleted");
        }
        // if the room does not exist in the list
        else {
            current = null;
            System.out.println("\n" + roomNum + " not found");
        }
        // return the List
        return current;
    }

    /**
     * Method to fine a room in the map, will return null if room does not exit!
     *
     * @param first   first node in list
     * @param roomNum room number we are trying to find
     * @return the room
     */
    public Room findRoom(Room first, int roomNum) {
        Room current = first;

        // Traverse through the LinkedList
        while (current != null) {
            if (current.getRoom() == roomNum) {
                return current;
            }
            current = current.getNext();
        }
        System.out.println("\nRoom does not exist on this map");
        return null;
    }

    /**
     * Method to add a POI to the List
     *
     * @param first  first POI in POI's linked list
     * @param newPOI POI we are adding
     */
    public void addExistingPOI(PointOfInterest first, PointOfInterest newPOI) {
        //If the First POI in the list is empty
        if (first == null) {
            first = newPOI;
        } else {
            PointOfInterest last = first;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newPOI);
        }
        poiHead = first;
    }

    /**
     * Method to print the POIList.
     *
     * @param first node of list
     */
    public void printPOIList(PointOfInterest first) {

        PointOfInterest current = first;
        System.out.print("LinkedList: ");

        while (current != null) {
            System.out.print(current.getRoom() + " ");
            current = current.getNext();
        }
    }

    /**
     * Deletes a POI from the Map list. returns null if POI does not exist in list!!!!
     *
     * @param first  POI in list (head node)
     * @param poiNum POI we want to delete
     * @return the deleted POI
     */
    public PointOfInterest removePOI(PointOfInterest first, int poiNum) {
        PointOfInterest current = first;
        PointOfInterest previous = null;

        // if POIhead is the room to be deleted
        if (current != null) {
            if (current.getRoom() == poiNum) {
                if (current.getNext() == null) {
                    poiHead = null;
                    return null;
                } else {
                    first = current.getNext(); // Changed head
                    poiHead = first;
                    System.out.println("\n" + poiNum + " found and deleted");
                    return first;
                }

            }
        }
        // if the POI is not POIhead
        while (current != null && current.getRoom() != poiNum) {
            previous = current;
            current = current.getNext();
        }
        //if the POI deleted wasn't the last POI in the linked list
        if (current != null) {
            previous.setNext(current.getNext());
            System.out.println("\n" + poiNum + " found and deleted");
        }

        // if the POI does not exist in the list
        else {
            current = null;
            System.out.println("\n" + poiNum + " not found");
        }

        // return the List
        return current;
    }

    /**
     * Method to fine POI in the map, will return null if POI does not exit!
     *
     * @param first   head node in POI list
     * @param roomNum room number we are trying to find POI's for
     * @return
     */
    public PointOfInterest findPOI(PointOfInterest first, int roomNum) {
        PointOfInterest current = first;

        // Traverse through the LinkedList
        while (current != null) {
            if (current.getRoom() == roomNum) {
                return current;
            }
            current = current.getNext();
        }
        System.out.println("\nPOI does not exist on this map");
        return null;
    }
}
