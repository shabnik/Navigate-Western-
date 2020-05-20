import java.io.IOException;

public class testing {
    static Map Test = new Map();
    static Room RoomTest;

    public static void main(String[] args) throws IOException {
        TestMapClass();

    }

    public static void TestMapClass(){
        System.out.println("\n\nMAP CLASS\n\n");
        System.out.println("\nChecking Adding method\n");
        int num = 1;
        while (num <= 8) {
            //Insert the values
            Room head = Test.getHead();
            RoomTest = new Room("Name", null, num, null, null, "Description", true);
            Test.addExistingRoom(head, RoomTest);
            num++;
        }
        Room head = Test.getHead();
        // Print the LinkedList
        System.out.println("List After Adding Rooms");
        Test.printList(Test.getHead());

        System.out.println("\n\nTesting the deleting methods");

        // The head is removed
        Room Checking = Test.removeRoom(head, 1);
        if(Checking == null)
            System.out.println("The room was not found");
        // Print the LinkedList
        Test.printList(Test.getHead());

        // removing a room from the middle
        Checking = Test.removeRoom(head, 4);
        if(Checking == null)
            System.out.println("The room was not found");
        // Print the LinkedList
        Test.printList(head);

        // Removing a room that does not exist
        Checking = Test.removeRoom(head, 10);

        // Print the LinkedList
        Test.printList(head);

        System.out.println("\n\nTesting the FindRoom Method\n");
        Test.printList(head);
        Checking = Test.findRoom(head, 3);
        System.out.println("Room Found: " + Checking.getRoom());

        Test.printList(head);
        Checking = Test.findRoom(head, 8);
        System.out.println("Room Found: " + Checking.getRoom());

        //Testing a room that does no exist
        Test.printList(head);
        Checking = Test.findRoom(head, 14);
    }

    public static void TestRoomClass(){

    }
}