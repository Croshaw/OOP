package me.croshaw.pz3.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.croshaw.pz3.MainFormApplication;
import me.croshaw.pz3.models.Chocolate;
import me.croshaw.pz3.models.Cookie;
import me.croshaw.pz3.models.Food;
import me.croshaw.pz3.models.Fruit;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public TableView mainTable;
    ObservableList<Food> foodList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainTable.setItems(foodList);

        // заполнили список данными
        foodList.add(new Fruit(100,"Яблоко",true));
        foodList.add(new Chocolate(200,"шоколад Аленка",Chocolate.Type.milk));
        foodList.add(new Cookie(300, "сладкая булочка с Маком", true, true, false));

        TableColumn<Food, String> titleColumn = new TableColumn<>("Название");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));


        TableColumn<Food, String> kkalColumn = new TableColumn<>("Калорийность");
        kkalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKkal())));

        TableColumn<Food, String> descColumn = new TableColumn<>("Описание");
        descColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDescription())));

        mainTable.getColumns().addAll(titleColumn, kkalColumn, descColumn);

    }

    public void onEditClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFormApplication.class.getResource("food-editor-form.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        // указываем что оно модальное
        stage.initModality(Modality.WINDOW_MODAL);
        // указываем что оно должно блокировать главное окно
        // ну если точнее, то окно, на котором мы нажали на кнопку
        stage.initOwner(this.mainTable.getScene().getWindow());

        FoodEditorController controller = loader.getController();
        controller.setFood((Food) this.mainTable.getSelectionModel().getSelectedItem());
        // открываем окно и ждем пока его закроют
        stage.showAndWait();

        if (controller.getModalResult()) {
            // узнаем индекс выбранной в таблице строки
            int index = this.mainTable.getSelectionModel().getSelectedIndex();
            // подменяем строку в таблице данными на форме
            this.mainTable.getItems().set(index, controller.getFood());
        }

    }
    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFormApplication.class.getResource("food-editor-form.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        // указываем что оно модальное
        stage.initModality(Modality.WINDOW_MODAL);
        // указываем что оно должно блокировать главное окно
        // ну если точнее, то окно, на котором мы нажали на кнопку
        stage.initOwner(this.mainTable.getScene().getWindow());

        // открываем окно и ждем пока его закроют
        stage.showAndWait();

        FoodEditorController controller = loader.getController();
        // проверяем что наали кнопку save
        if (controller.getModalResult()) {
            // собираем еду с формы
            Food newFood = controller.getFood();
            // добавляем в список
            this.foodList.add(newFood);
        }
    }
    public void onDeleteClick(ActionEvent actionEvent) {
        Food food = (Food) this.mainTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(String.format("Точно удалить %s?", food.getTitle()));

        // если пользователь нажал OK
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            // удаляем строку из таблицы
            this.mainTable.getItems().remove(food);
        }

    }
}