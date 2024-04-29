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
import java.util.*;

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
    @FXML
    private ImageView livingImage;
    @FXML
    private ImageView livingImage1;
    @FXML
    private ImageView livingImage2;
    @FXML
    private ImageView livingImage3;
    @FXML
    private ImageView nonLivingImage;
    @FXML
    private ImageView nonLivingImage1;
    @FXML
    private ImageView nonLivingImage2;
    @FXML
    private ImageView nonLivingImage3;
    @FXML
    private Label timerLabel;

    private Timeline timer;
    private int timeSeconds = 60; // 1 minute
    private int correctMatches = 0;
    private int totalImages = 8; // Total number of images

    // List to store living images
    private List<Image> livingImages;
    // List to store non-living images
    private List<Image> nonLivingImages;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize lists
        livingImages = new ArrayList<>();
        nonLivingImages = new ArrayList<>();

        // Load living images
        livingImages.addAll(Arrays.asList(
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/2.jpeg").toExternalForm()),
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/butterfly.jpeg").toExternalForm()),
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/cat.jpeg").toExternalForm()),
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/Living/tiger.jpeg").toExternalForm())
        ));

        // Load non-living images
        nonLivingImages.addAll(Arrays.asList(
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/doll.jpeg").toExternalForm()),
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/football.jpeg").toExternalForm()),
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/rocket.jpeg").toExternalForm()),
                new Image(getClass().getResource("/com/example/learnscience/PhotosData/NonLiving/train.jpeg").toExternalForm())
        ));

        // Shuffle the living images
        Collections.shuffle(livingImages);
        // Shuffle the non-living images
        Collections.shuffle(nonLivingImages);

        // Set images to ImageViews
        images.setImage(livingImages.get(0));
        images1.setImage(livingImages.get(1));
        images2.setImage(livingImages.get(2));
        images3.setImage(livingImages.get(3));
        images4.setImage(nonLivingImages.get(0));
        images5.setImage(nonLivingImages.get(1));
        images6.setImage(nonLivingImages.get(2));
        images7.setImage(nonLivingImages.get(3));

        // Add drag-and-drop handlers for the images ImageView
        addDragAndDropHandlersForImages(images, images1, images2, images3, images4, images5, images6, images7);

        // Add drag-and-drop handlers for the living image category
        addDragAndDropHandlersForDestinationImages(livingImage, livingImages);
        addDragAndDropHandlersForDestinationImages(livingImage1, livingImages);
        addDragAndDropHandlersForDestinationImages(livingImage2, livingImages);
        addDragAndDropHandlersForDestinationImages(livingImage3, livingImages);

        // Add drag-and-drop handlers for the non-living image category
        addDragAndDropHandlersForDestinationImages(nonLivingImage, nonLivingImages);
        addDragAndDropHandlersForDestinationImages(nonLivingImage1, nonLivingImages);
        addDragAndDropHandlersForDestinationImages(nonLivingImage2, nonLivingImages);
        addDragAndDropHandlersForDestinationImages(nonLivingImage3, nonLivingImages);

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

    // Method to add drag-and-drop handlers for the destination image category
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

    // Method to handle game end
    private void handleGameEnd() {
        // Stop the timer
        timer.stop();

        // Check if all living images are in the correct target areas
        boolean allLivingMatched = true;
        for (ImageView imageView : Arrays.asList(livingImage, livingImage1, livingImage2, livingImage3)) {
            if (!livingImages.contains(imageView.getImage())) {
                allLivingMatched = false;
                break;
            }
        }

        // Check if all non-living images are in the correct target areas
        boolean allNonLivingMatched = true;
        for (ImageView imageView : Arrays.asList(nonLivingImage, nonLivingImage1, nonLivingImage2, nonLivingImage3)) {
            if (!nonLivingImages.contains(imageView.getImage())) {
                allNonLivingMatched = false;
                break;
            }
        }

        // If all living and non-living images are matched, show success message
        if (allLivingMatched && allNonLivingMatched) {
            int score = calculateScore();
            showSuccessMessage(score);
        } else {
            // If not all images are matched, show a failure message
            showFailureMessage();
        }
    }

    // Method to calculate score
    private int calculateScore() {
        // Example scoring logic, you can adjust this according to your requirements
        return (int) ((double) correctMatches / totalImages * 100);
    }

    // Method to show success message with score
    private void showSuccessMessage(int score) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("You matched all images!");
        alert.setContentText("Your score: " + score + "%");
        alert.showAndWait();
    }

    // Method to show failure message
    private void showFailureMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Time's Up!");
        alert.setHeaderText("You didn't match all images in time.");
        alert.setContentText("Please try again.");
        alert.showAndWait();
    }
}
