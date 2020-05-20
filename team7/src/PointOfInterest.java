/**
 * this class is working on set, check and change point of interest.
 *
 * @author QiTan
 */
public class PointOfInterest {

    private String name;
    private Integer roomNumber;
    private Float xCoordinate;
    private Float yCoordinate;
    private String description;
    private String type;
    private Boolean visible;
    private PointOfInterest next = null;

    /**
     * This method define every vairable that POI needs
     */
    public PointOfInterest() {
        name = "Default Name";
        roomNumber = -1;
        xCoordinate = 0.0f;
        yCoordinate = 0.0f;
        description = "Default Description";
        type = "Default Description";
        visible = false;
    }

    /**
     * This method put value in each vairable that POI needs
     */
    public PointOfInterest(String name, String type, Integer roomNumber, Float xCoordinate, Float yCoordinate, String description, Boolean visible) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.description = description;
        this.type = type;
        this.visible = visible;

    }

    /**
     * This method returns the changed name
     *
     * @param newName The string to be changed.
     * @return name string.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * This method change the room number
     *
     * @param newRoom The integer to be changed.
     * @return newroom number integer.
     */
    public void setRoom(int newRoom) {
        roomNumber = newRoom;
    }

    /**
     * This method change the x coordinate
     *
     * @param newX The float x to be changed.
     * @return x float.
     */
    public void setX(Float newX) {
        xCoordinate = newX;
    }

    /**
     * This method change the x coordinate
     *
     * @param newY The float y to be changed.
     * @return y float.
     */
    public void setY(Float newY) {
        yCoordinate = newY;
    }

    /**
     * This method change the description
     *
     * @param newD descriptoin The string to be changed.
     * @return descriptoin string.
     */
    public void setDescription(String newD) {
        description = newD;
    }

    /**
     * This method change the type
     *
     * @param newT The string 'type' to be changed.
     * @return type string.
     */
    public void setType(String newT) {
        type = newT;
    }

    /**
     * This method change the visible status
     *
     * @param newStatus The boolean status to be changed.
     * @return visible status.
     */
    public void setVisible(Boolean newStatus) {
        visible = newStatus;
    }

    /**
     * This method set the value of next.
     *
     * @param next the next value want to put in
     * @return next value.
     */
    public void setNext(PointOfInterest next) {
        this.next = next;
    }

    /**
     * This method get the value next.
     *
     * @return return next.
     */
    public PointOfInterest getNext() {
        return next;
    }

    /**
     * This method get the value name.
     *
     * @return return name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method get the value room number.
     *
     * @return return room number.
     */
    public int getRoom() {
        return roomNumber;
    }

    /**
     * This method get the value type.
     *
     * @return return type.
     */
    public String getType() {
        return type;
    }

    /**
     * This method get the value x.
     *
     * @return return x.
     */
    public Float getX() {
        return xCoordinate;
    }

    /**
     * This method get the value y.
     *
     * @return return y.
     */
    public Float getY() {
        return yCoordinate;
    }

    /**
     * This method get the value description.
     *
     * @return return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method get the status of visible.
     *
     * @return return status.
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * This method change the visible status
     *
     * @return visible status.
     */
    public void setVisible() {
        if (visible) {
            visible = false;
        } else {
            visible = true;
        }
    }
}