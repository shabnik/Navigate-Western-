/**
 * favorite class
 *
 * @author team7
 */
public class Favorite {


    public static Room[] favoriteRoom = new Room[100];
    public static Room[] tempList = new Room[100];


    public static void newFaveList() {
        favoriteRoom = new Room[5];
    }

    /**
     * add the target room into favourite list
     *
     * @param room
     */


    public static void addFave(Room room) {
        for (int i = 0; i < favoriteRoom.length; i++) {
            if (favoriteRoom[i] == room) {
                System.out.println("room exists");
            }
            if (favoriteRoom[i] == null) {
                favoriteRoom[i] = room;

                i = favoriteRoom.length;
            }


        }

    }

    /**
     * delete the target room from favorite list
     *
     * @param room
     */
    public static void delete(String room) {

        if (favoriteRoom.length == 0) {
            System.out.println("empty favourite list");

        }

        for (int i = 0; i < favoriteRoom.length; i++) {
            if (favoriteRoom[i].getName() == room) {
                for (int j = i + 1; j < favoriteRoom.length; j++) {
                    favoriteRoom[i] = favoriteRoom[j];
                    i++;
                }

            }

        }

    }

    /**
     * sort the favorite room
     *
     * @param room
     */

    public static void sortFave(String room) {

        for (int i = 0; i < favoriteRoom.length; i++) {
            if (favoriteRoom[i].getName() == room) {
                delete(room);
                for (int j = 1; j < favoriteRoom.length; j++) {
                    tempList[j] = favoriteRoom[i];
                }
            }

        }
        favoriteRoom = tempList;

    }

    /**
     * change the room name
     *
     * @param room
     * @param remarks
     */
    public void editName(Room room, String remarks) {
        room.changeName(remarks);
    }
}



