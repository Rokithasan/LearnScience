package com.example.learnscience;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Mammals  implements Initializable {

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

    @FXML
    private ImageView mamalsImage;

    @FXML
    private ImageView mamalsImage1;

    @FXML
    private ImageView mamalsImage2;

    @FXML
    private ImageView mamalsImage3;

    @FXML
    private ImageView nonMamalsImage;

    @FXML
    private ImageView nonMamalsImage1;

    @FXML
    private ImageView nonMamalsImage2;

    @FXML
    private ImageView nonMamalsImage3;

    @FXML
    private Label timerLabel;

    private Timeline timer;
    private int timeSeconds = 60; // 1 minute
    private int correctMatches = 0;
    private int totalImages = 8; // Total number of images

    // List to store living images
    private List<Image> mammalsImages;

    // List to store non-living images
    private List<Image> nonMammalImages;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize lists
        mammalsImages = new ArrayList<>();
        nonMammalImages = new ArrayList<>();

        // Load mammals images
        mammalsImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Mammals/2.jpeg").toExternalForm()));
        mammalsImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Mammals/8.jpeg").toExternalForm()));
        mammalsImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Mammals/cat.jpeg").toExternalForm()));
        mammalsImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/Mammals/tiger.jpeg").toExternalForm()));

        // Load non-mammals images
        nonMammalImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonMammals/9.jpeg").toExternalForm()));
        nonMammalImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonMammals/butterfly.jpeg").toExternalForm()));
        nonMammalImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonMammals/hen.jpeg").toExternalForm()));
        nonMammalImages.add(new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonMammals/parrot.jpeg").toExternalForm()));

        // Shuffle the living images
        Collections.shuffle(mammalsImages);
        // Shuffle the non-living images
        Collections.shuffle(nonMammalImages);

        // Set images to ImageViews
        images.setImage(mammalsImages.get(0));
        images1.setImage(mammalsImages.get(1));
        images2.setImage(mammalsImages.get(2));
        images3.setImage(mammalsImages.get(3));
        images4.setImage(nonMammalImages.get(0));
        images5.setImage(nonMammalImages.get(1));
        images6.setImage(nonMammalImages.get(2));
        images7.setImage(nonMammalImages.get(3));

        // Add drag-and-drop handlers for the images ImageView
        addDragAndDropHandlersForImages(images, images1, images2, images3, images4, images5, images6, images7);

        // Add drag-and-drop handlers for the living image category
        addDragAndDropHandlersForDestinationImages(mamalsImage, mammalsImages);
        addDragAndDropHandlersForDestinationImages(mamalsImage1, mammalsImages);
        addDragAndDropHandlersForDestinationImages(mamalsImage2, mammalsImages);
        addDragAndDropHandlersForDestinationImages(mamalsImage3, mammalsImages);

        // Add drag-and-drop handlers for the non-living image category
        addDragAndDropHandlersForDestinationImages(nonMamalsImage, nonMammalImages);
        addDragAndDropHandlersForDestinationImages(nonMamalsImage1, nonMammalImages);
        addDragAndDropHandlersForDestinationImages(nonMamalsImage2, nonMammalImages);
        addDragAndDropHandlersForDestinationImages(nonMamalsImage3, nonMammalImages);

        // Set up the timer
        timer = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText("Time: " + timeSeconds);
            if (timeSeconds <= 0) {
                timer.stop();
                handleGameEnd();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    // Method to add drag-and-drop handlers for the images ImageView
    private void addDragAndDropHandlersForImages(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setOnDragDetected(event -> {
                Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(imageView.getImage());
                db.setContent(content);
                event.consume();
            });
        }
    }

    private void addDragAndDropHandlersForDestinationImages(ImageView imageView, List<Image> categoryList) {
        imageView.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                categoryList.add(db.getImage());
                imageView.setImage(db.getImage());
                success = true;
                correctMatches++;
                if (correctMatches == totalImages) {
                    timer.stop();
                    handleGameEnd();
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void handleGameEnd() {
        if (isCategoryFilled(mamalsImage) && isCategoryFilled(mamalsImage1) && isCategoryFilled(mamalsImage2) && isCategoryFilled(mamalsImage3) &&
                isCategoryFilled(nonMamalsImage) && isCategoryFilled(nonMamalsImage1) && isCategoryFilled(nonMamalsImage2) && isCategoryFilled(nonMamalsImage3)) {
            // All target areas are filled with the correct number of images
            int score = calculateScore();
            showSuccessMessage(score);
        } else {
            // Not all target areas are filled with the correct number of images
            showFailureMessage();
        }
    }

    private boolean isCategoryFilled(ImageView imageView) {
        List<Image> categoryList;
        if (isLivingCategory(imageView)) {
            categoryList = mammalsImages;
        } else {
            categoryList = nonMammalImages;
        }
        return categoryList.contains(imageView.getImage());
    }

    private boolean isLivingCategory(ImageView imageView) {
        return imageView == mamalsImage || imageView == mamalsImage1 || imageView == mamalsImage2 || imageView == mamalsImage3;
    }
    private int calculateScore() {
        // Example scoring logic, you can adjust this according to your requirements
        return (int) ((double) correctMatches / totalImages * 100);
    }
    private void showSuccessMessage(int score) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("You matched all images!");
        alert.setContentText("Your score: " + score + "%");
        alert.showAndWait();
    }

    private void showFailureMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Time's Up!");
        alert.setHeaderText("You didn't match all images in time.");
        alert.setContentText("Please try again.");
        alert.showAndWait();
    }
}
