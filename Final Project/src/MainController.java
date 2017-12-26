import static java.awt.SystemColor.window;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.System.out;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main controller class for the entire layout.
 */
public class MainController {

    Scene scene1, scene2;

    /**
     * Holder of a switchable vista.
     */
    @FXML
    private StackPane vistaHolder;
    private PieChart piechartHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }
    @FXML
    private MenuBar menuBar;

    /**
     * Handle action related to "About" menu item.
     *
     * @param event Event on "About" menu item.
     */
    @FXML
    private void handleAboutAction(final ActionEvent event) {
        //provideAboutFunctionality();
        VistaNavigator.loadVista(VistaNavigator.VISTA);
    }

    @FXML
    private void handleRamAction(final ActionEvent event) throws IOException {
        VistaNavigator.loadVista(VistaNavigator.VISTA);
    }

     @FXML
    private void welcomeButtonAction(final ActionEvent event) throws IOException {
        VistaNavigator.loadVista(VistaNavigator.WELCOME);
    }   
    

    
    @FXML
    private void handleStackedAreaChartAction(ActionEvent event) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("7 Day Interval");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        StackedAreaChart stackedAreaChart = new StackedAreaChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Desktop");

        dataSeries1.getData().add(new XYChart.Data(0, 567));
        dataSeries1.getData().add(new XYChart.Data(1, 612));
        dataSeries1.getData().add(new XYChart.Data(2, 800));
        dataSeries1.getData().add(new XYChart.Data(3, 780));
        dataSeries1.getData().add(new XYChart.Data(4, 650));
        dataSeries1.getData().add(new XYChart.Data(5, 610));
        dataSeries1.getData().add(new XYChart.Data(6, 590));

        stackedAreaChart.getData().add(dataSeries1);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Mobile");

        dataSeries2.getData().add(new XYChart.Data(0, 101));
        dataSeries2.getData().add(new XYChart.Data(1, 110));
        dataSeries2.getData().add(new XYChart.Data(2, 140));
        dataSeries2.getData().add(new XYChart.Data(3, 132));
        dataSeries2.getData().add(new XYChart.Data(4, 115));
        dataSeries2.getData().add(new XYChart.Data(5, 109));
        dataSeries2.getData().add(new XYChart.Data(6, 105));

        stackedAreaChart.getData().add(dataSeries2);

        VBox vbox = new VBox(stackedAreaChart);
        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

    @FXML
    private void handlePieChartAction(ActionEvent event) {

        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone", 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet", 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.setTitle("Devices Chart");
        VBox vbox = new VBox(pieChart);

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

    @FXML
    private void handlePieChart1Action(ActionEvent event) {

        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone", 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet", 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        ObservableList<PieChart.Data> pieData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Rent", 500),
                        new PieChart.Data("Electric", 125),
                        new PieChart.Data("Groceries", 235),
                        new PieChart.Data("Entertainment", 200),
                        new PieChart.Data("Cell Phone", 80));

        PieChart budget = new PieChart(pieData);
        budget.setTitle("Monthly Expenses");

        VBox vbox = new VBox(budget);

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().add(vbox);

    }

    @FXML
    private void handleMonthlyRecordAction(ActionEvent event) {
        PieChart piechart = new PieChart();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("January", 100),
                        new PieChart.Data("February", 200),
                        new PieChart.Data("March", 50),
                        new PieChart.Data("April", 75),
                        new PieChart.Data("May", 110),
                        new PieChart.Data("June", 300),
                        new PieChart.Data("July", 111),
                        new PieChart.Data("August", 30),
                        new PieChart.Data("September", 75),
                        new PieChart.Data("October", 55),
                        new PieChart.Data("November", 225),
                        new PieChart.Data("December", 99));

        piechart.setTitle("Monthly Record");
        piechart.setData(pieChartData);

        VBox vbox = new VBox(piechart);

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

    @FXML
    private void handleLineChartAction(ActionEvent event) {

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data(1, 567));
        dataSeries1.getData().add(new XYChart.Data(5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);
        lineChart.setTitle("Revenue per Employee Chart");
        VBox vbox = new VBox(lineChart);

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

    @FXML
    private void handleQuarterlySalesAction(ActionEvent event) {
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel("SalesPerson");

        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel("New Car Sales");

        BarChart<String, Number> bcSales = new BarChart<>(hAxis, vAxis);
        bcSales.setTitle("1st Quarter Sales Report");

        XYChart.Series<String, Number> jan = new XYChart.Series<>();
        XYChart.Series<String, Number> feb = new XYChart.Series<>();
        XYChart.Series<String, Number> mar = new XYChart.Series<>();

        jan.setName("January");
        feb.setName("February");
        mar.setName("March");

        jan.getData().add(new XYChart.Data<String, Number>("Mary", 120000));
        jan.getData().add(new XYChart.Data<String, Number>("Tom", 100000));

        feb.getData().add(new XYChart.Data<String, Number>("Mary", 90000));
        feb.getData().add(new XYChart.Data<String, Number>("Tom", 50000));

        mar.getData().add(new XYChart.Data<String, Number>("Mary", 55000));
        mar.getData().add(new XYChart.Data<String, Number>("Tom", 130000));

        bcSales.getData().add(jan);
        bcSales.getData().add(feb);
        bcSales.getData().add(mar);

        VBox vbox = new VBox(bcSales);

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

    @FXML
    private void handleStockMonitoringleAction(ActionEvent event) {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart
                = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        lineChart.getData().add(series);

        VBox vbox = new VBox(lineChart);

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

    @FXML
    private void handleTimelineAnimation(ActionEvent event) {

        Group group = new Group();

        Rectangle rect = new Rectangle(20, 20, 200, 200);

        FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        group.getChildren().add(rect);
        VBox vbox = new VBox(group);
        // play 40s of animation

        vistaHolder.getChildren().clear();
        vistaHolder.getChildren().addAll(vbox);
    }

      @FXML
    private void handleCustomersAction(final ActionEvent event) throws IOException {
        VistaNavigator.loadVista(VistaNavigator.CUSTOMERS);
    }
    
        @FXML
    private void handleTransactionsAction(final ActionEvent event) throws IOException {
        VistaNavigator.loadVista(VistaNavigator.TRANSACTIONS);
    }  
    
     @FXML
    private void handleProductsAction(final ActionEvent event) throws IOException {
        VistaNavigator.loadVista(VistaNavigator.PRODUCTS);
    }  
    
    @FXML
    private void handleTipCalculatorAction(final ActionEvent event) throws IOException {
        VistaNavigator.loadVista(VistaNavigator.TIP_CALCULATOR);
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        PieChart piechart = new PieChart();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        piechart.setTitle("");
        piechart.setData(pieChartData);
    }

    @FXML
    private void CloseApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    private void provideAboutFunctionality() {
        out.println("You clicked on About!");
    }

    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    @FXML
    void provideOtherFunctionality() {
        out.println("You clicked me!");

    }

    public void initialize(final URL url, final ResourceBundle rb) {
        menuBar.setFocusTraversable(true);
    }

}
