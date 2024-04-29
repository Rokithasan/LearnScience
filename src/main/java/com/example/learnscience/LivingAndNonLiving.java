package com.example.learnscience;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class LivingAndNonLiving implements Initializable {

    @FXML
    private ImageView images;

    @FXML
    private ImageView images1;

    @FXML
    private ImageView images2;

    @FXML
    private ImageView images3;

    @FXML
    private ImageView images4;

    @FXML
    private ImageView images5;

    @FXML
    private ImageView images6;

    @FXML
    private ImageView images7;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Image> allImages = new ArrayList<>();
        // Load living images
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/2.jpeg").toExternalForm()));
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/butterfly.jpeg").toExternalForm()));
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/cat.jpeg").toExternalForm()));
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/tiger.jpeg").toExternalForm()));
        // Load non-living images
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/doll.jpeg").toExternalForm()));
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/football.jpeg").toExternalForm()));
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/rocket.jpeg").toExternalForm()));
        allImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/train.jpeg").toExternalForm()));

        // Shuffle the images for random showing
        Collections.shuffle(allImages);

        // Set images to ImageViews
        images.setImage(allImages.get(0));
        images1.setImage(allImages.get(1));
        images2.setImage(allImages.get(2));
        images3.setImage(allImages.get(3));
        images4.setImage(allImages.get(4));
        images5.setImage(allImages.get(5));
        images6.setImage(allImages.get(6));
        images7.setImage(allImages.get(7));
    }
}
