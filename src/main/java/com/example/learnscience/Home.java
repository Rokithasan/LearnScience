package com.example.learnscience;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private ImageView bodyPartsId;

    @FXML
    private ImageView findKidImagesId;

    @FXML
    private ImageView growThingsImageId;

    @FXML
    private ImageView livingAndNonLivingImage;

    @FXML
    private ImageView mammalsImagesId;

    @FXML
    private ImageView planetsImagesId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bodyPartsId.setImage(new Image(getClass().getResourceAsStream("/com/example/learnscience/PhotosData/bodypart.jpeg")));
        findKidImagesId.setImage(new Image(getClass().getResourceAsStream("/com/example/learnscience/PhotosData/findkid.jpeg")));
        growThingsImageId.setImage(new Image(getClass().getResourceAsStream("/com/example/learnscience/PhotosData/growthings.jpeg")));
        livingAndNonLivingImage.setImage(new Image(getClass().getResourceAsStream("/com/example/learnscience/PhotosData/livingandnonliving.jpeg")));
        mammalsImagesId.setImage(new Image(getClass().getResourceAsStream("/com/example/learnscience/PhotosData/mammals.jpeg")));
        planetsImagesId.setImage(new Image(getClass().getResourceAsStream("/com/example/learnscience/PhotosData/planet.jpeg")));


        bodyPartsId.setOnMouseClicked(event-> {
            try {
                loadFXML("parts_of_the_body.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        findKidImagesId.setOnMouseClicked(event->{
            try {
                loadFXML("find_the_correct_kid.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        growThingsImageId.setOnMouseClicked(event->{
            try {
                loadFXML("grow_things.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        livingAndNonLivingImage.setOnMouseClicked(event->{
            try {
                loadFXML("living_and_non_living.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mammalsImagesId.setOnMouseClicked(event->{
            try {
                loadFXML("mammals.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        planetsImagesId.setOnMouseClicked(event->{
            try {
                loadFXML("palnets.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void loadFXML(String fxmlFileName) throws IOException {
        Stage stage = (Stage) bodyPartsId.getScene().getWindow(); // Get the current stage
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/example/learnscience/" + fxmlFileName))));
    }


}

