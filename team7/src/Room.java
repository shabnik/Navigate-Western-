/**
 * Represents  a room belonging to a Map
 * A map can hold many individual rooms
 *
 * @author NickPehme
 */
public class Room {

        /**
         * The Name of the Room
         */
        private String name;

        /**
         * An Optional Alias of the Room
         */
        private String alias;

        /**
         * The Rooms Number
         */
        private Integer roomNumber;

        /**
         * The Rooms X coordinate on the map
         */
        private Float xCoordinate;

        /**
         * The Rooms Y coordinate on the map
         */
        private Float yCoordinate;

        /**
         * An optional Description for the Room
         */
        private String description;

        /**
         * A Boolean true if this room is a favorite, false if the room is not
         */
        private Boolean faveStatus;

        /**
         * Holds the next room in the linked list
         */
        private Room next = null;

        /**
         * Default constructor for the Room class
         */
        public Room() {
                name = "Default Name";
                alias = "Default Alias";
                roomNumber = -1;
                xCoordinate = 0.0f;
                yCoordinate = 0.0f;
                description = "Default Description";
                faveStatus = false;

        }

        /**
         * Constructor for the Room class, creates a new Room object from the input parameters
         *
         * @param name        The name of the Room
         * @param alias       An alternate name for the room
         * @param roomNumber  The Rooms number
         * @param xCoordinate           The Xcoordinate for the new Room
         * @param yCoordinate           The Ycoordinate for the new Room
         * @param description An optional description for the new room
         * @param faveStatus  True if the room is a favorite room, otherwise false
         */
        public Room(String name, String alias, Integer roomNumber, Float xCoordinate, Float yCoordinate, String description, Boolean faveStatus) {
                this.name = name;
                this.alias = alias;
                this.roomNumber = roomNumber;
                this.xCoordinate = xCoordinate;
                this.yCoordinate = yCoordinate;
                this.description = description;
                this.faveStatus = faveStatus;
        }

        /**
         *
         */
        public void changeName(String newName) {
                name = newName;
        }

        /**
         * @param newAlias
         */
        public void changeAlias(String newAlias) {
                alias = newAlias;
        }

        /**
         * @param newRoom
         */
        public void changeRoom(int newRoom) {
                roomNumber = newRoom;
        }

        /**
         * @param newX
         */
        public void changeX(Float newX) {
                xCoordinate = newX;
        }

        /**
         * @param newY
         */
        public void changeY(Float newY) {
                yCoordinate = newY;
        }

        /**
         * @param newD
         */
        public void changeDescription(String newD) {
                description = newD;
        }

        /**
         * @param newStatus
         */
        public void changeStatus(Boolean newStatus) {
                faveStatus = newStatus;
        }

        /**
         * @param Next
         */
        public void setNext(Room Next) {
                this.next = Next;
        }

        /**
         * @return
         */
        public Room getNext() {
                return next;
        }

        /**
         * @return
         */
        public String getName() {
                if (name.equals("Default Name")) {
                        return "";
                } else {
                        return name;
                }
        }

        /**
         * @return
         */
        public String getAlias() {
                if (alias.equals("Default Alias")) {
                        return "";
                } else {
                        return alias;
                }
        }

        /**
         * @return
         */
        public int getRoom() {
                return roomNumber;
        }

        /**
         * @return
         */
        public Float getX() {
                return xCoordinate;
        }

        /**
         * @return
         */
        public Float getY() {
                return yCoordinate;
        }

        /**
         * GetDescription returns the Rooms optional Description
         *
         * @return Returns the String Description if there is one, returns a black string otherwise
         */
        public String getDescription() {
                if (description.equals("Default Description")) {
                        return "";
                } else {
                        return description;
                }
        }

        /**
         * CheckStatus returns the rooms FaveStatus
         *
         * @return Returns true if the Room is a favorite, false otherwise
         */
        public Boolean checkStatus() {
                return faveStatus;
        }


}