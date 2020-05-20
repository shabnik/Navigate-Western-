
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.*;
import javax.swing.event.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * This the main class that builds the program and creates a nice user interface
 */

public class UserInterface extends JComponent implements ChangeListener {
    private static String currentEditBuilding = "";
    private static String currentViewBuilding = "";
    private static String currentViewMap = "";
    private static String currentEditMap = "";
    private static String currentSelectedRoom = "";
    private static JButton descriptionButton = new JButton();

    private static int MAPSCALE = 40;

    private static int MAPHEIGHT = 20 * MAPSCALE;
    private static int MAPWIDTH = 32 * MAPSCALE;

    private static  int ROOMWIDTH = 50;
    private static int ROOMHEIGHT = 30;

    private static int POIHEIGHT = 30;
    private static int POIWIDTH = 30;

    private static JButton[] navButtons;
    private static JButton[] eatButtons;

    /**
     * Method to show POIs
     * @param newLabel takes a label object as input
     * @throws IOException
     */
    public static void showPOI(JLabel newLabel) throws IOException {

        for (int i = 0; i < BuildingList.allBuildings.length; i++){
            if (BuildingList.allBuildings[i] != null){
                if (BuildingList.allBuildings[i].getName().equals(currentViewBuilding)){

                    Map currentMap = BuildingList.allBuildings[i].getHeadMap();

                    while (currentMap != null){
                        if ((currentMap.getFloorMap() == Integer.parseInt(currentViewMap))){

                            Room currentRoom = currentMap.getHead();
                            PointOfInterest currentPOI = currentMap.getPOI();

                            int navCount = 0;
                            int eatCount = 0;

                            while (currentPOI != null){
                                if (currentPOI.getVisible()) {

                                    Float xPOI = currentPOI.getX();
                                    Float yPOI = currentPOI.getY();
                                    String typePOI = currentPOI.getType();
                                    String namePOI = currentPOI.getName();
                                    String descriptionPOI = currentPOI.getDescription();

                                    JButton poiButton = new JButton();

                                    if (typePOI.equals("w")){
                                        Image smallNav = ImageIO.read(new File("ToiletIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                    }else if(typePOI.equals("a")){
                                        Image smallNav = ImageIO.read(new File("AccessIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                    }else if (typePOI.equals("e")){
                                        Image smallNav = ImageIO.read(new File("EateryIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                        eatCount++;

                                    }else if (typePOI.equals("n")){
                                        Image smallNav = ImageIO.read(new File("NavigationIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                        navCount++;

                                    }

                                    poiButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            descriptionButton.setText(descriptionPOI);
                                            descriptionButton.setVisible(true);

                                        }
                                    });

                                    poiButton.setVisible(true);
                                    poiButton.setBounds( Math.round(xPOI) - POIWIDTH/2 ,  Math.round(yPOI) , POIWIDTH, POIHEIGHT);

                                    newLabel.add(poiButton);

                                    if (typePOI.equals("n")) {
                                        navButtons[navCount - 1] = poiButton;
                                    } else if(typePOI.equals("e")){
                                        eatButtons[eatCount -1] = poiButton;
                                    }

                                }
                                currentPOI = currentPOI.getNext();
                            }

                            while (currentRoom != null) {

                                Float xRoom = currentRoom.getX();
                                Float yRoom = currentRoom.getY();
                                String nameRoom = currentRoom.getName();
                                String descriptionRoom = currentRoom.getDescription();
                                int numRoom = currentRoom.getRoom();

                                JButton roomButton = new JButton(Integer.toString(numRoom));

                                if(currentRoom.getName().equals(currentSelectedRoom)){
                                    roomButton.setBackground(Color.YELLOW);
                                } else if(currentRoom.checkStatus() == true){
                                    roomButton.setBackground(Color.PINK);
                                }

                                roomButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        descriptionButton.setText(nameRoom + ":" + descriptionRoom);
                                        descriptionButton.setVisible(true);

                                    }
                                });

                                roomButton.setVisible(true);
                                roomButton.setBounds(Math.round(xRoom) - ROOMWIDTH/2, Math.round(yRoom) - ROOMHEIGHT/2 , ROOMWIDTH, ROOMHEIGHT);
                                roomButton.setFont(new Font("Arial", Font.PLAIN, 8));

                                newLabel.add(roomButton);

                                currentRoom = currentRoom.getNext();
                            }

                            currentMap = null;
                        } else {
                            currentMap = currentMap.getNext();
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to show the map when edit POI is clicked
     * @param newLabel takes label object as input
     * @throws IOException
     */
    public static void showEditPOI(JLabel newLabel) throws IOException {
        for (int i = 0; i < BuildingList.allBuildings.length; i++){
            if (BuildingList.allBuildings[i] != null){
                if (BuildingList.allBuildings[i].getName().equals(currentEditBuilding)){
                    Map currentMap = BuildingList.allBuildings[i].getHeadMap();
                    while (currentMap != null){

                        if ((currentMap.getFloorMap() == Integer.parseInt(currentEditMap))){
                            PointOfInterest currentPOI = currentMap.getPOI();
                            int navCount = 0;
                            int eatCount = 0;

                            while (currentPOI != null){

                                if (currentPOI.getVisible()) {

                                    Float xPOI = currentPOI.getX();
                                    Float yPOI = currentPOI.getY();
                                    String typePOI = currentPOI.getType();
                                    String namePOI = currentPOI.getName();
                                    String descriptionPOI = currentPOI.getDescription();
                                    int numPOI = currentPOI.getRoom();

                                    JButton poiButton = new JButton();

                                    if (typePOI.equals("w")){
                                        Image smallNav = ImageIO.read(new File("ToiletIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                    }else if(typePOI.equals("a")){
                                        Image smallNav = ImageIO.read(new File("AccessIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                    }else if (typePOI.equals("e")){
                                        Image smallNav = ImageIO.read(new File("EateryIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                        eatCount++;

                                    }else if (typePOI.equals("n")){
                                        Image smallNav = ImageIO.read(new File("NavigationIcon.png"));
                                        smallNav = smallNav.getScaledInstance(POIWIDTH, POIHEIGHT, Image.SCALE_SMOOTH);
                                        poiButton.setIcon(new ImageIcon(smallNav));

                                        navCount++;

                                    }

                                    PointOfInterest finalCurrentPOI = currentPOI;
                                    poiButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            poiButton.setVisible(false);
                                            for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                                                if (BuildingList.allBuildings[i] != null) {
                                                    if (BuildingList.allBuildings[i].getName().equals(currentEditBuilding)) {
                                                        Map currentMap = BuildingList.allBuildings[i].getHeadMap();
                                                        while (currentMap != null) {

                                                            if (currentMap.getFloorMap() == Integer.parseInt(currentEditMap)){

                                                                BuildingList.allBuildings[i].findMap(currentMap.getFloorMap()).removePOI(currentMap.getPOI(), numPOI);

                                                            }
                                                            currentMap=currentMap.getNext();

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    });

                                    poiButton.setVisible(true);
                                    poiButton.setBounds( Math.round(xPOI) - POIWIDTH/2,  Math.round(yPOI) , POIWIDTH, POIHEIGHT);

                                    newLabel.add(poiButton);

                                    if (typePOI.equals("n")) {
                                        navButtons[navCount - 1] = poiButton;
                                    } else if(typePOI.equals("e")){
                                        eatButtons[eatCount -1] = poiButton;
                                    }

                                }
                                currentPOI = currentPOI.getNext();
                            }
                            currentMap = null;
                        } else {
                            currentMap = currentMap.getNext();
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to show the map when edit Room is clicked
     * @param newLabel takes a label object as input
     * @throws IOException
     */
    public static void showEditRoom(JLabel newLabel) throws IOException {
        for (int i = 0; i < BuildingList.allBuildings.length; i++){
            if (BuildingList.allBuildings[i] != null){
                if (BuildingList.allBuildings[i].getName().equals(currentEditBuilding)){
                    Map currentMap = BuildingList.allBuildings[i].getHeadMap();
                    while (currentMap != null){
                        if ((currentMap.getFloorMap() == Integer.parseInt(currentEditMap))){
                            Room currentRoom = currentMap.getHead();
                            while (currentRoom != null){
                                Float xRoom = currentRoom.getX();
                                Float yRoom = currentRoom.getY();

                                String namePOI = currentRoom.getName();
                                String descriptionPOI = currentRoom.getDescription();
                                int numRoom = currentRoom.getRoom();

                                JButton roomButton = new JButton(Integer.toString(numRoom));

                                roomButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        roomButton.setVisible(false);
                                        for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                                            if (BuildingList.allBuildings[i] != null) {
                                                if (BuildingList.allBuildings[i].getName().equals(currentEditBuilding)) {
                                                    Map currentMap = BuildingList.allBuildings[i].getHeadMap();
                                                    while (currentMap != null) {

                                                        if (currentMap.getFloorMap() == Integer.parseInt(currentEditMap)){

                                                            BuildingList.allBuildings[i].findMap(currentMap.getFloorMap()).removeRoom(currentMap.getHead(), numRoom);

                                                        }
                                                        currentMap=currentMap.getNext();

                                                    }
                                                }
                                            }
                                        }
                                    }
                                });

                                if (currentRoom.checkStatus()){
                                    roomButton.setBackground(Color.PINK);
                                }

                                roomButton.setVisible(true);
                                roomButton.setBounds( Math.round(xRoom) - ROOMWIDTH/2,  Math.round(yRoom) - ROOMHEIGHT, ROOMWIDTH, ROOMHEIGHT);
                                roomButton.setFont(new Font("Arial", Font.PLAIN, 8));

                                newLabel.add(roomButton);

                                currentRoom = currentRoom.getNext();
                            }
                            currentMap = null;
                        } else {
                            currentMap = currentMap.getNext();
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to update the building List
     * @return the updated building list
     */
    public static String[] updateBuildingList() {
        int count = 0;

        for (int i = 0; i < BuildingList.allBuildings.length; i++) {
            if (BuildingList.allBuildings[i] != null) {
                count++;
            }
        }
        String[] newBuildingList = new String[count + 1];
        newBuildingList[0] = "Pick a Building";

        for (int j = 0; j < count; j++) {
            newBuildingList[j + 1] = BuildingList.allBuildings[j].getName();

        }
        return newBuildingList;
    }

    /**
     * Method to update the map list
     * @param buildingName the building that needs it's mapped updated
     * @return updated map list
     */
    public static String[] updateMapList(String buildingName) {
        int position = 0;
        int counter = 1;
        String[] tempString = new String[100];
        tempString[0] = "Pick a Floor";

        for (int i = 0; i < BuildingList.allBuildings.length; i++) {
            if (BuildingList.allBuildings[i] != null) {
                if (BuildingList.allBuildings[i].getName() == buildingName) {
                    position = i;
                }
            }
        }
        Map currentMap = BuildingList.allBuildings[position].getHeadMap();

        while (currentMap != null) {
            tempString[counter] = Integer.toString(currentMap.getFloorMap());
            counter++;
            currentMap = currentMap.getNext();
        }

        String[] realMaps = new String[counter];

        for (int j = 0; j < counter; j++) {
            realMaps[j] = tempString[j];
        }

        return realMaps;
    }

    /**
     * Method to update the favorite list
     * @return the updated favorite list
     */
    public static String[] updateFavoriteList() {
        int count = 0;
        for (int i = 0; i < Favorite.favoriteRoom.length; i++) {
            if (Favorite.favoriteRoom[i] != null) {
                count++;
            }
        }
        String[] favList = new String[count + 1];
        favList[0] = "Pick a favorite room";
        for (int j = 0; j < count; j++) {
            favList[j + 1] = Favorite.favoriteRoom[j].getName();
        }
        return favList;
    }

    /**
     * Main method that creates the user interface
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BuildingList.newBuildingList();
        Favorite.newFaveList();

        new ReadJson("BuildingFile.txt");

        new ReadJson("MapFile.txt");

        new ReadJson("RoomFile.txt");

        new ReadJson("POIFile.txt");

        int topButtonsY = 75;
        int topButtonsX = 1000;
        int topButtonsW = 100;
        int topButtonsH = 100;

        JFrame window = new JFrame();
        JButton start = new JButton();
        try {
            String Filename = "C:NavigateWesternLOGO.png";
            Image NavigateLogo = ImageIO.read(new File(Filename));
            start.setIcon(new ImageIcon(NavigateLogo));

            window.setExtendedState(JFrame.MAXIMIZED_BOTH);


        } catch (Exception ex) {
            System.out.println(ex);
        }

        start.setBounds(100, 20, 604, 481);

        JFrame addFloorWindow = new JFrame();

        JFrame mapWindow = new JFrame();
        mapWindow.setSize(1500, 800);
        mapWindow.setLocation(200, 200);
        mapWindow.setVisible(false);

        JButton map = new JButton("Map");

        map.setBounds(500, 200, 400, 400);

        map.setVisible(false);

        JButton edit = new JButton("Edit");
        edit.setBounds(topButtonsX, topButtonsY, topButtonsW, topButtonsH);
        edit.setVisible(false);

        JFrame editWindow = new JFrame();
        editWindow.setVisible(false);

        JButton help = new JButton("Help");
        help.setBounds(topButtonsX + topButtonsW + 10, topButtonsY, topButtonsW, topButtonsH);
        help.setVisible(false);

        JButton exit = new JButton("Exit");
        exit.setBounds(topButtonsX + topButtonsW * 2 + 20, topButtonsY, topButtonsW, topButtonsH);
        exit.setVisible(false);

        String[] sampleBuildings = UserInterface.updateBuildingList();

        JComboBox buildingList = new JComboBox(sampleBuildings);
        buildingList.setBounds(100, 100, 200, 25);
        buildingList.setVisible(false);

        JComboBox editBuildingList = new JComboBox(sampleBuildings);
        editBuildingList.setBounds(0, 200, 200, 25);
        buildingList.setVisible(true);

        JComboBox mapViewList = new JComboBox();
        mapViewList.setBounds(100, 400, 200, 25);
        mapViewList.setVisible(false);

        String[] sampleFavorites = UserInterface.updateFavoriteList();
        JComboBox favoriteList = new JComboBox(sampleFavorites);
        favoriteList.setBounds(100, 600, 200, 25);
        favoriteList.setVisible(false);

        descriptionButton.setVisible(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu poiMenu = new JMenu("Points Of Interest");

        JCheckBoxMenuItem showWashroomItem = new JCheckBoxMenuItem("Washrooms");
        showWashroomItem.setEnabled(false);
        showWashroomItem.setState(true);
        poiMenu.add(showWashroomItem);

        JCheckBoxMenuItem showAccessItem = new JCheckBoxMenuItem("Accessibility");
        showAccessItem.setState(true);
        showAccessItem.setEnabled(false);
        poiMenu.add(showAccessItem);

        JCheckBoxMenuItem showEateriesItem = new JCheckBoxMenuItem("Eateries/Shopping");
        showEateriesItem.setState(true);
        poiMenu.add(showEateriesItem);

        JButton searchBTN = new JButton("Search");
        searchBTN.setBounds(360, 20, 100, 20);
        searchBTN.setVisible(false);

        JLabel enterRoomNum = new JLabel("Enter Room Number:");
        enterRoomNum.setBounds(20, 20, 150, 20);
        enterRoomNum.setVisible(false);

        JTextField roomNum = new JTextField(20);
        roomNum.setBounds(160, 20, 200, 20);
        roomNum.setVisible(false);

        JButton editFavo = new JButton("Edit favorite list");
        editFavo.setBounds(100, 650, 200, 25);
        editFavo.setVisible(false);

        JFrame editFscreen = new JFrame();
        editFscreen.setSize(500, 500);
        editFscreen.setLayout(null);
        editFscreen.setVisible(false);

        editFavo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFscreen.setVisible(true);
            }
        });

        JButton addFave = new JButton("add your favorite room");
        JButton deleteFave = new JButton("delete your favorite room");
        addFave.setBounds(100, 50, 200, 25);
        deleteFave.setBounds(100, 100, 200, 25);

        editFscreen.add(addFave);
        editFscreen.add(deleteFave);
        editFscreen.add(favoriteList);

        addFave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog("enter the room name");

                favoriteList.addItem(result);

                editFscreen.setVisible(false);
            }
        });

        deleteFave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenFavo = (String) favoriteList.getSelectedItem();
                if (chosenFavo != "Pick a favorite room") {
                    Favorite.delete(chosenFavo);

                    favoriteList.removeAllItems();
                    String[] updatedFavo = updateFavoriteList();

                    for(int i = 0; i<updatedFavo.length;i++){
                        favoriteList.addItem(updatedFavo[i]);
                    }

                }
                editFscreen.setVisible(false);
            }
        });

        showEateriesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showEateriesItem.isSelected()) {
                    for (int i = 0; i < eatButtons.length; i++) {
                        if (eatButtons[i] != null) {
                            eatButtons[i].setVisible(true);
                        }
                    }

                } else {
                    for (int j = 0; j < eatButtons.length; j++) {
                        if (eatButtons[j] != null) {
                            eatButtons[j].setVisible(false);
                        }
                    }
                }
            }
        });

        JCheckBoxMenuItem showNavigationItem = new JCheckBoxMenuItem("Navigation");
        showNavigationItem.setState(true);
        poiMenu.add(showNavigationItem);

        showNavigationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showNavigationItem.isSelected()) {
                    for (int i = 0; i < navButtons.length; i++) {
                        if (navButtons[i] != null) {
                            navButtons[i].setVisible(true);
                        }
                    }
                } else {
                    for (int j = 0; j < navButtons.length; j++) {
                        if (navButtons[j] != null) {
                            navButtons[j].setVisible(false);
                        }
                    }
                }
            }
        });

        JLabel currentMap = new JLabel();
        menuBar.add(poiMenu);

        descriptionButton.setBounds(300,20,600,50);
        descriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descriptionButton.setVisible(false);
            }
        });

        map.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                descriptionButton.setVisible(false);

                eatButtons = new JButton[100];
                navButtons = new JButton[100];
                currentMap.removeAll();
                mapWindow.repaint();

                for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                    if (BuildingList.allBuildings[i] != null) {
                        if (BuildingList.allBuildings[i].getName() == currentViewBuilding) {

                            int mapWidth = MAPWIDTH;
                            int mapHeight = MAPHEIGHT;
                            mapWindow.setSize(mapWidth, mapHeight);
                            mapWindow.setLocation(0, 0);

                            JPanel ViewPanel = (JPanel) mapWindow.getContentPane();

                            try {
                                Image mapIcon = ImageIO.read(new File(BuildingList.allBuildings[i].findMap(Integer.parseInt(currentViewMap)).getFileLocation()));

                                mapIcon = mapIcon.getScaledInstance(mapWidth - 20, mapHeight - 20, Image.SCALE_SMOOTH);

                                currentMap.setIcon(new ImageIcon(mapIcon));

                                currentMap.setBounds(0, 0, mapWidth , mapHeight );

                                showAccessItem.setState(true);
                                showEateriesItem.setState(true);
                                showNavigationItem.setState(true);
                                showWashroomItem.setState(true);

                                ViewPanel.add(currentMap);
                                mapWindow.add(currentMap);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            try {
                                showPOI(currentMap);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            currentMap.add(descriptionButton);
                            currentMap.setVisible(false);
                            currentMap.setVisible(true);
                            mapWindow.setVisible(true);

                        }
                    }
                }
            }
        });

        //If the start button is pushed
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.setSize(1500, 1000);
                start.setVisible(false);
                edit.setVisible(true);
                help.setVisible(true);
                exit.setVisible(true);
                buildingList.setVisible(true);
                favoriteList.setVisible(true);
                editFavo.setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JFrame HelpScreen = new JFrame();
        HelpScreen.setSize(540, 650);
        HelpScreen.setLayout(null);
        HelpScreen.setVisible(false);

        JScrollBar verticalScroll=new JScrollBar(JScrollBar.VERTICAL,30, 20, 0, 500);
        HelpScreen.getContentPane().add(verticalScroll, BorderLayout.EAST);

        String text = "This program is designed to help Western students find rooms in a building."+"<br/>"+"<br/>"+
                "The program has two modes:"+"<br/>"+"<br/>"+
                "***User Mode:***"+"<br/>"+"In this mode the user can browse through the different buildings using the drop down menu, look through each floor (again using the drop down menu)" +
                "and see every room" +"<br/>"+ "on that map. The user can then select a room and add it to favourites."+"<br/>"+ "Users also have the option of searching for a room in a specific building by using the search engine."+
                " The search engine will find the desired room and return the corresponding map with it; if no match is found a popup will let the user know the"
                +"<br/>"+ "room does not exist in the database."
                +"<br/>"+"A selected Room will be yellow, a favourited room will be pink and a normal room will have no color."+
                "<br/>"+"<br/>"
                +"***Editing Mode:***"+"<br/>"+"In this mode the editor can add/remove/edit Rooms, Maps, Points of Interest and Buildings"
                +"<br/>"+"<br/>"+"-To add a new building:"+"<br/>"
                +"Select 'Add Building' and type the new building's name (The new building should now appear in the 'Pick A Building' dropdown menu." +"<br/>" +
                "Once a building has been selected, the editor has the option to 'Remove Building' or "
                +"<br/>"+"'Add floor'."+"<br/>"+"<br/>"+"-To add a new floor:"+"<br/>"
                +"Select 'Add Floor' and type the name of the floor and the address to the map file."
                +"<br/>" +"<br/>"+ "-To add a new Room or Point of Interest"+"<br/>"
                +"Select Building>Floor>Add Room (or Point of Interest), this will open a new window which will allow the editor "+
                "to select the location of the new Room/PointOfInterest. Clicking on the map will open a series of popup windows"
                + " that must be answered to create the new Room."
                +"<br/>"+"<br/>"+"-To delete a Room/PointOfInterest:"+"<br/>"+"Select >Building>Floor>Remove Room/PointOfInterest," +
                " a map will open with all the rooms shown."
                +" Simply clicking on a room will permanently delete them from the map.";

        JLabel helpText = new JLabel("<html>"+ text + "</html");
        helpText.setBounds(0, 0, 500, 595);


        //If the start button is pushed
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HelpScreen.setVisible(true);

            }
        });


        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);

                editBuildingList.removeAllItems();

                String[] newBuildings = updateBuildingList();
                for (int i = 0; i < newBuildings.length; i++) {
                    editBuildingList.addItem(newBuildings[i]);
                }
                editWindow.setVisible(true);



            }
        });

        buildingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pickedBuilding = (String) buildingList.getSelectedItem();
                currentViewBuilding = pickedBuilding;
                if (currentViewBuilding != "Pick a Building") {
                    searchBTN.setVisible(true);
                    roomNum.setVisible(true);
                    enterRoomNum.setVisible(true);
                    mapViewList.removeAllItems();
                    String[] newMaps = updateMapList(currentViewBuilding);
                    for (int i = 0; i < newMaps.length; i++) {
                        mapViewList.addItem(newMaps[i]);
                    }
                    mapViewList.setVisible(true);
                } else {
                    mapViewList.setVisible(false);
                    map.setVisible(false);
                    searchBTN.setVisible(false);
                    roomNum.setVisible(false);
                    enterRoomNum.setVisible(false);
                }


            }
        });

        mapViewList.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String pickedMap = (String) mapViewList.getSelectedItem();
                currentViewMap = pickedMap;
                if (currentViewMap != "Pick a Floor") {
                    map.setVisible(true);
                } else {
                    map.setVisible(false);
                }

            }
        });

        JButton exitEdit = new JButton("Viewing Mode");
        exitEdit.setVisible(true);
        exitEdit.setBounds(0, 0, 250, 100);

        JButton removeBuilding = new JButton("Remove Building");
        removeBuilding.setVisible(false);
        removeBuilding.setBounds(300, 200, 200, 25);

        JButton removeFloor = new JButton("Remove Floor");
        removeFloor.setVisible(false);
        removeFloor.setBounds(300, 400, 200, 25);

        JButton addFloor = new JButton("Add Floor");
        addFloor.setVisible(false);
        addFloor.setBounds(525, 200, 200, 25);

        JComboBox mapEditList = new JComboBox();
        mapEditList.setBounds(0, 400, 200, 25);
        mapEditList.setVisible(false);

        JButton addRoom = new JButton("Add Room");
        addRoom.setVisible(false);
        addRoom.setBounds(525, 400, 200, 25);

        JButton addPOI = new JButton("Add Point of Interest");
        addPOI.setVisible(false);
        addPOI.setBounds(525, 375, 200, 25);

        JButton addBuilding = new JButton("Add Building");
        addBuilding.setVisible(true);
        addBuilding.setBounds(300, 50, 200, 25);

        JButton createRoom = new JButton("ADD FLOOR");
        createRoom.setVisible(false);

        JFrame addRoomWindow = new JFrame();
        addRoomWindow.setVisible(false);

        JPanel editRoomPanel = (JPanel) addRoomWindow.getContentPane();
        JLabel editMapIcon = new JLabel();

        JButton removePOI = new JButton("Remove Point of Interest");
        removePOI.setVisible(true);
        removePOI.setBounds(300,375,200,25);

        JFrame removePOIWindow = new JFrame();
        removePOIWindow.setVisible(false);

        JButton removeRoom = new JButton("Remove Room");
        removeRoom.setVisible(true);
        removeRoom.setBounds(300,350,200,25);

        JFrame removeRoomWindow = new JFrame();
        removeRoomWindow.setVisible(false);

        JLabel editPOIImage = new JLabel();
        JLabel editRoomImage = new JLabel();

        removeRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                descriptionButton.setVisible(false);

                editRoomImage.removeAll();
                removeRoomWindow.repaint();


                for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                    if (BuildingList.allBuildings[i] != null) {
                        if (BuildingList.allBuildings[i].getName() == currentEditBuilding) {

                            int mapWidth = MAPWIDTH;
                            int mapHeight = MAPHEIGHT;
                            removeRoomWindow.setSize(mapWidth, mapHeight);
                            removeRoomWindow.setLocation(0, 0);

                            JPanel viewPanel = (JPanel) removeRoomWindow.getContentPane();

                            try {
                                Image mapIcon = ImageIO.read(new File(BuildingList.allBuildings[i].findMap(Integer.parseInt(currentEditMap)).getFileLocation()));

                                mapIcon = mapIcon.getScaledInstance(mapWidth - 20, mapHeight - 20, Image.SCALE_SMOOTH);

                                editRoomImage.setIcon(new ImageIcon(mapIcon));

                                editRoomImage.setBounds(0, 0, mapWidth , mapHeight );

                                viewPanel.add(editRoomImage);
                                removeRoomWindow.add(editRoomImage);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            try {
                                showEditRoom(editRoomImage);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


                            editRoomImage.setVisible(false);
                            editRoomImage.setVisible(true);
                            removeRoomWindow.setVisible(true);

                        }
                    }
                }
            }
        });

        removePOI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                descriptionButton.setVisible(false);
                eatButtons = new JButton[100];
                navButtons = new JButton[100];
                editPOIImage.removeAll();
                removePOIWindow.repaint();

                for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                    if (BuildingList.allBuildings[i] != null) {
                        if (BuildingList.allBuildings[i].getName() == currentEditBuilding) {

                            int mapWidth = MAPWIDTH;
                            int mapHeight = MAPHEIGHT;
                            removePOIWindow.setSize(mapWidth, mapHeight);
                            removePOIWindow.setLocation(0, 0);

                            JPanel viewPanel = (JPanel) removePOIWindow.getContentPane();

                            try {
                                Image mapIcon = ImageIO.read(new File(BuildingList.allBuildings[i].findMap(Integer.parseInt(currentEditMap)).getFileLocation()));

                                mapIcon = mapIcon.getScaledInstance(mapWidth - 20, mapHeight - 20, Image.SCALE_SMOOTH);

                                editPOIImage.setIcon(new ImageIcon(mapIcon));

                                editPOIImage.setBounds(0, 0, mapWidth , mapHeight );

                                showAccessItem.setState(true);
                                showEateriesItem.setState(true);
                                showNavigationItem.setState(true);
                                showWashroomItem.setState(true);

                                viewPanel.add(editPOIImage);
                                removePOIWindow.add(editPOIImage);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            try {
                                showEditPOI(editPOIImage);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            editPOIImage.setVisible(false);
                            editPOIImage.setVisible(true);
                            removePOIWindow.setVisible(true);

                        }
                    }
                }
            }
        });

        addRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                    if (BuildingList.allBuildings[i] != null) {
                        if (BuildingList.allBuildings[i].getName() == currentEditBuilding) {
                            int mapWidth = MAPWIDTH;
                            int mapHeight = MAPHEIGHT;

                            addRoomWindow.setSize(mapWidth, mapHeight);
                            addRoomWindow.setLocation(0, 0);
                            addRoomWindow.setVisible(true);
                            try {
                                Image mapIcon2 = ImageIO.read(new File(BuildingList.allBuildings[i].findMap(Integer.parseInt(currentEditMap)).getFileLocation()));
                                mapIcon2 = mapIcon2.getScaledInstance(mapWidth - 20, mapHeight - 20, Image.SCALE_SMOOTH);
                                editMapIcon.setIcon(new ImageIcon(mapIcon2));
                                editMapIcon.setVisible(true);
                                editMapIcon.setBounds(0, 0, mapWidth, mapHeight);
                                editRoomPanel.add(editMapIcon);
                                editRoomPanel.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        String name = JOptionPane.showInputDialog("What is the name of this room?");
                                        String alias = JOptionPane.showInputDialog("What is the alias of this room?");
                                        String roomNum = JOptionPane.showInputDialog("What is the room number?");
                                        String description = JOptionPane.showInputDialog("Please enter a description of this room.");
                                        String fav = JOptionPane.showInputDialog("Is this room a favorite? **Please enter either true or false");
                                        try {
                                            if (!fav.equals("true") && !fav.equals("false")) {
                                                JOptionPane.showMessageDialog(editRoomPanel, "INCORRECT favorite value.");
                                                throw new Exception("Favorite value cannot be resolved");
                                            }
                                            Room newRoom = new Room(name, alias, Integer.parseInt(roomNum), (float) e.getX(), (float) e.getY(), description, Boolean.parseBoolean(fav));

                                            for (int j = 0; j < BuildingList.allBuildings.length; j++) {
                                                if (BuildingList.allBuildings[j] != null) {
                                                    if (BuildingList.allBuildings[j].getName().equals(currentEditBuilding)) {
                                                        BuildingList.allBuildings[j].findMap(Integer.parseInt(currentEditMap)).addExistingRoom(BuildingList.allBuildings[j].findMap(Integer.parseInt(currentEditMap)).getHead(), newRoom);
                                                    }
                                                }
                                            }
                                            JOptionPane.showMessageDialog(editRoomPanel, "Room was added.");

                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(editRoomPanel, "Room was not added.");
                                            ex.printStackTrace();
                                        }
                                    }
                                });
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }
                }
            }
        });

        JFrame addPOIWindow = new JFrame();
        addPOIWindow.setVisible(false);

        JPanel editPOIPanel = (JPanel) addPOIWindow.getContentPane();

        addPOI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                    if (BuildingList.allBuildings[i] != null) {
                        if (BuildingList.allBuildings[i].getName() == currentEditBuilding) {
                            int mapWidth = MAPWIDTH;
                            int mapHeight = MAPHEIGHT;

                            addPOIWindow.setSize(mapWidth, mapHeight);
                            addPOIWindow.setLocation(0, 0);
                            addPOIWindow.setVisible(true);
                            try {
                                Image mapIcon2 = ImageIO.read(new File(BuildingList.allBuildings[i].findMap(Integer.parseInt(currentEditMap)).getFileLocation()));
                                mapIcon2 = mapIcon2.getScaledInstance(mapWidth - 20, mapHeight - 20, Image.SCALE_SMOOTH);
                                editMapIcon.setIcon(new ImageIcon(mapIcon2));
                                editMapIcon.setVisible(true);
                                editMapIcon.setBounds(0, 0, mapWidth, mapHeight);
                                editPOIPanel.add(editMapIcon);
                                editPOIPanel.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        String name = JOptionPane.showInputDialog("What is the name of this POI?");
                                        String roomNum = JOptionPane.showInputDialog("What is the room number?");
                                        try {
                                            String type = JOptionPane.showInputDialog("What type of POI is it? **Please enter either a, w, e, or n. (accessibility, washroom, eatery, navigation)");
                                            if (!type.equals("a") && !type.equals("w") && !type.equals("e") && !type.equals("n")) {
                                                JOptionPane.showMessageDialog(editPOIPanel, "INCORRECT type.");
                                                throw new Exception("Type cannot be resolved");
                                            }
                                            String description = JOptionPane.showInputDialog("Please enter a description of this POI.");

                                            PointOfInterest newPOI = new PointOfInterest(name, type, Integer.parseInt(roomNum), (float) e.getX(), (float) e.getY(), description, true);
                                            for (int j = 0; j < BuildingList.allBuildings.length; j++) {
                                                if (BuildingList.allBuildings[j] != null) {
                                                    if (BuildingList.allBuildings[j].getName().equals(currentEditBuilding)) {
                                                        BuildingList.allBuildings[j].findMap(Integer.parseInt(currentEditMap)).addExistingPOI(BuildingList.allBuildings[j].findMap(Integer.parseInt(currentEditMap)).getPOI(), newPOI);
                                                    }
                                                }
                                            }

                                            JOptionPane.showMessageDialog(editPOIPanel, "Point of Interest was added.");
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(editPOIPanel, "Point of Interest was not added.");
                                            ex.printStackTrace();
                                        }
                                    }
                                });
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        mapEditList.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String pickedMap = (String) mapEditList.getSelectedItem();
                currentEditMap = pickedMap;

                if (currentEditMap != "Pick a Floor") {
                    removeFloor.setVisible(true);
                    addRoom.setVisible(true);
                    addPOI.setVisible(true);
                    removePOI.setVisible(true);
                    removeRoom.setVisible(true);

                } else {
                    removeFloor.setVisible(false);
                    addRoom.setVisible(false);
                    addPOI.setVisible(false);
                    removePOI.setVisible(false);
                    removeRoom.setVisible(false);
                }
            }
        });

        exitEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.setVisible(true);
                editWindow.setVisible(false);
                buildingList.removeAllItems();

                String[] newBuildings = updateBuildingList();
                for (int i = 0; i < newBuildings.length; i++) {
                    buildingList.addItem(newBuildings[i]);
                }
                try {
                    BuildingList.saveBuildings();
                } catch (IOException ex) {
                }
            }
        });

        removeBuilding.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                BuildingList.removeBuilding(currentEditBuilding);
                editBuildingList.removeAllItems();
                String[] updatedBuildings = updateBuildingList();
                for (int i = 0; i < updatedBuildings.length; i++) {
                    editBuildingList.addItem(updatedBuildings[i]);
                }
            }
        });

        editBuildingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String chosenBuilding = (String) editBuildingList.getSelectedItem();
                currentEditBuilding = chosenBuilding;
                if (currentEditBuilding != "Pick a Building") {
                    mapEditList.removeAllItems();
                    String[] allMaps = updateMapList(currentEditBuilding);
                    for (int i = 0; i < allMaps.length; i++) {
                        mapEditList.addItem(allMaps[i]);
                    }
                    addFloor.setVisible(true);
                    mapEditList.setVisible(true);
                    removeBuilding.setVisible(true);
                } else {
                    mapEditList.setVisible(false);
                    removeBuilding.setVisible(false);
                    removePOI.setVisible(false);
                    removeFloor.setVisible(false);
                    addRoom.setVisible(false);
                    addFloor.setVisible(false);
                    addPOI.setVisible(false);
                    removeRoom.setVisible(false);
                }
            }
        });

        removeFloor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                    if (BuildingList.allBuildings[i] != null) {
                        if (BuildingList.allBuildings[i].getName() == currentEditBuilding) {
                            BuildingList.allBuildings[i].removeMap(BuildingList.allBuildings[i].getHeadMap(), BuildingList.allBuildings[i].findMap(Integer.parseInt(currentEditMap)));
                            mapEditList.removeAllItems();
                            String[] updatedFloors = updateMapList(currentEditBuilding);
                            for (int j = 0; j < updatedFloors.length; j++) {
                                mapEditList.addItem(updatedFloors[j]);
                            }
                        }
                    }
                }
            }
        });

        addFloor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputFloorName = JOptionPane.showInputDialog("Enter Floor Number:");

                try {
                    int newFloor = Integer.parseInt(inputFloorName);
                    String inputFileName = JOptionPane.showInputDialog("Input the PNG File Location");
                    File inputFile = new File(inputFileName);

                    if (inputFile.exists()) {
                        Map newMap = new Map();
                        newMap.setFloorMap(newFloor);
                        newMap.loadMap(inputFileName);
                        for (int i = 0; i < BuildingList.allBuildings.length; i++) {
                            if (BuildingList.allBuildings[i].getName().equals(currentEditBuilding)) {
                                BuildingList.allBuildings[i].addMap(newMap);
                                i = BuildingList.allBuildings.length;
                            }
                        }

                        mapEditList.removeAllItems();
                        String[] allMaps = updateMapList(currentEditBuilding);

                        for (int i = 0; i < allMaps.length; i++) {
                            mapEditList.addItem(allMaps[i]);
                        }

                    } else {
                        System.out.println("File does not exist");

                    }

                } catch (NumberFormatException y) {
                    System.out.println("PUT IN INTEGER");

                }

            }
        });

        addBuilding.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputBuildingName = JOptionPane.showInputDialog("Enter Building Name");
                Building toAdd = new Building();
                toAdd.changeName(inputBuildingName);
                BuildingList.addBuilding(toAdd);

                editBuildingList.removeAllItems();
                String[] updatedBuildings = updateBuildingList();
                for (int i = 0; i < updatedBuildings.length; i++) {
                    editBuildingList.addItem(updatedBuildings[i]);
                }
            }
        });

        /**
         * Search for a room in a building, if room is found returns updates currentviewmap and the new map will display room that was searched
         * If room is not found a popup will let the user know room was not found
         */
        searchBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean roomIsInt = true;
                String roomFoundSTR = roomNum.getText();
                Room roomToDisplay = null;
                int i;
                try {
                    Integer.parseInt(roomFoundSTR);
                } catch (NumberFormatException nfe) {
                    roomIsInt = false;
                }

                if (BuildingList.allBuildings.length == 0)
                    JOptionPane.showMessageDialog(null, "There aren't any rooms to search from!");

                if (roomIsInt == true) {
                    int roomFound = Integer.parseInt(roomFoundSTR);
                    for (i = 0; i < BuildingList.allBuildings.length; i++) {
                        if (BuildingList.allBuildings[i].getName() == currentViewBuilding) {
                            roomToDisplay = BuildingList.allBuildings[i].findRoomInBuilding(roomFound); //the room to be displayed
                            break;
                        }
                    }
                } else {
                    for (i = 0; i < BuildingList.allBuildings.length; i++) {
                        if (BuildingList.allBuildings[i].getName() == currentViewBuilding) {
                            roomToDisplay = BuildingList.allBuildings[i].findRoomNameInBuilding(roomFoundSTR); //the room to be displayed
                            break;
                        }
                    }
                }

                if (roomToDisplay == null) {
                    JOptionPane.showMessageDialog(null, "Room not Found");
                } else {
                    JOptionPane.showMessageDialog(null, "Room Found!");
                    Map mapToDisplay = BuildingList.allBuildings[i].currentMap();//the map to be displayed
                    currentViewMap = String.valueOf(mapToDisplay.getFloorMap());
                    currentSelectedRoom = roomToDisplay.getName();
                    map.setVisible(true);
                }
            }
        });

        window.add(searchBTN);
        HelpScreen.add(helpText);
        window.add(start);
        window.add(edit);
        window.add(help);
        window.add(exit);
        window.add(buildingList);
        window.add(favoriteList);
        window.add(roomNum);
        window.add(enterRoomNum);
        window.add(mapViewList);
        window.setSize(1000, 500);
        window.add(editFavo);
        window.setLayout(null);
        window.setVisible(true);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(map);

        editWindow.setSize(750, 500);
        editWindow.setLayout(null);
        editWindow.add(exitEdit);
        editWindow.add(editBuildingList);
        editWindow.add(removeBuilding);
        editWindow.add(mapEditList);
        editWindow.add(removeFloor);
        editWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editWindow.add(addFloor);
        editWindow.add(addRoom);
        editWindow.add(addBuilding);
        editWindow.add(addPOI);
        editWindow.add(removePOI);
        editWindow.add(removeRoom);

        mapWindow.setJMenuBar(menuBar);
        mapWindow.setLayout(null);
    }

    /**
     * Method needed to be implemented to use ChangeEvent class
     * @param changeEvent
     */
    @Override
    public void stateChanged(ChangeEvent changeEvent) {

    }
}