package hellofx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

import static hellofx.DBConector.DBName;

public class Diagram implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diagram();
    }

    private void diagram() {
        lineChart.setTitle("Monthly");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series1.setName("Печалба");
        series2.setName("Купено Злато.");
        series3.setName("Купено Сребро.");

        try {
            Connection con = DBConector.getConections();
            ResultSet rs = con.createStatement().executeQuery("select * from "+DBName+".updateAllTable;");
            while (rs.next()) {
                String date = rs.getString(2);
                double b = Double.parseDouble(rs.getString(4));
                double c = Double.parseDouble(rs.getString(9));
                double d = Double.parseDouble(rs.getString(10));
                series1.getData().add(new XYChart.Data<>(date, b));
                series2.getData().add(new XYChart.Data<>(date, c));
                series3.getData().add(new XYChart.Data<>(date, d));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        /*
        series1.getData().add(new XYChart.Data<>("Feb", 33));
        series1.getData().add(new XYChart.Data<>("Mar", 43));
        series1.getData().add(new XYChart.Data<>("Apr", 63));
        */

        lineChart.getData().addAll(series1, series2, series3);
        tooltipCreate(series1, "Печалба");
        tooltipCreate(series2,"Купено Зл.");
        tooltipCreate(series3,"Купено Ср.");



    }

    private void tooltipCreate(XYChart.Series<String, Number> series, String str) {

        for (final XYChart.Data<String, Number> data : series.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, t ->
                    Tooltip.install(data.getNode(), new Tooltip("Month: " + data.getXValue() + "\n" + str + ": "+ data.getYValue() + " лв.")));
        }
    }

}