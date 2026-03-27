import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

class Room {
    int number;
    String type;
    double price;
    boolean available = true;

    Room(int n, String t, double p) {
        number = n;
        type = t;
        price = p;
    }
}

public class HotelGUI extends Application {

    ArrayList<Room> rooms = new ArrayList<>();
    TextArea display = new TextArea();

    @Override
    public void start(Stage stage) {

        // Input fields
        TextField tfNo = new TextField();
        TextField tfType = new TextField();
        TextField tfPrice = new TextField();

        Button btnAdd = new Button("Add Room");
        Button btnShow = new Button("Show Rooms");
        Button btnAvail = new Button("Available Rooms");

        // Add Room
        btnAdd.setOnAction(e -> {
            int no = Integer.parseInt(tfNo.getText());
            String type = tfType.getText();
            double price = Double.parseDouble(tfPrice.getText());

            rooms.add(new Room(no, type, price));
            display.setText("Room Added!");
        });

        // Show all rooms
        btnShow.setOnAction(e -> {
            String out = "";
            for (Room r : rooms)
                out += r.number + " " + r.type + " " + r.price + "\n";
            display.setText(out);
        });

        // Show available rooms
        btnAvail.setOnAction(e -> {
            String out = "";
            for (Room r : rooms)
                if (r.available)
                    out += r.number + " " + r.type + "\n";
            display.setText(out);
        });

        // Layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Room No:"), 0, 0);
        grid.add(tfNo, 1, 0);

        grid.add(new Label("Type:"), 0, 1);
        grid.add(tfType, 1, 1);

        grid.add(new Label("Price:"), 0, 2);
        grid.add(tfPrice, 1, 2);

        VBox root = new VBox(10, grid, btnAdd, btnShow, btnAvail, display);

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Hotel Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}