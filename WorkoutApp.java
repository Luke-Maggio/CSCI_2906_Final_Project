
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class WorkoutApp extends Application {
	public Stage primaryStage;
	public VBox layout;
	public Scene mainScene;
	public Scene WorkoutsInventoryScene;
	public String previous;
	
	
	public static void main(String args[]) {
		launch (args);
	}
	
	@Override
	public void start (Stage primaryStage) {
		primaryStage.setTitle("ActiveLife Fitness App");
		
		Button workoutsButton = new Button("Workouts Inventory");
		Button exerciseButton = new Button("Exercises Library");
		Button cardioButton = new Button("Cardio Library");
		Button dietButton = new Button("Diet Tracker");
		Button bmiButton = new Button("Body Mass Index");

		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10));
		layout.getChildren().addAll(workoutsButton, exerciseButton, cardioButton, dietButton, bmiButton);
		

		Scene scene = new Scene(layout, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		workoutsButton.setOnAction(e -> {
			openWorkoutsInventory(primaryStage, layout, scene);
		});
		exerciseButton.setOnAction(e -> {
			openExercisesLibrary(primaryStage, scene);
		});
		cardioButton.setOnAction(e ->{
			openCardioLibrary(primaryStage, scene);
		});
		dietButton.setOnAction(e -> {
			openDietTracker(primaryStage, scene);
		});
		bmiButton.setOnAction(e -> {
			openBodyMassIndex(primaryStage, scene);
		});
	}

	private void openBodyMassIndex(Stage primaryStage, Scene scene) {
		
		VBox bmiCalculatorLayout = new VBox(10);
		bmiCalculatorLayout.setPadding(new Insets(10));
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> { primaryStage.setScene(scene);});
		
		Label heightLabel = new Label("Height (in inches): ");
		TextField heightField = new TextField();
		bmiCalculatorLayout.getChildren().addAll(heightLabel, heightField);
		
		Label weightLabel = new Label("Weight (in pounds): ");
		TextField weightField = new TextField();
		bmiCalculatorLayout.getChildren().addAll(weightLabel, weightField);
		
		Label bmiLabel = new Label("Your BMI: ");
		bmiCalculatorLayout.getChildren().add(bmiLabel);

		
		Button calculateButton = new Button("Calculate BMI");
		calculateButton.setOnAction(e -> { 
			double heightInInches = Double.parseDouble(heightField.getText());
			double weightInPounds = Double.parseDouble(weightField.getText());
			
			double heightInMeters = heightInInches * 0.0254;
			double weightInKilograms = weightInPounds * 0.45359237;
			
			double bmi = weightInKilograms / (heightInMeters * heightInMeters);
			
			bmiLabel.setText("Your BMI: " + String.format("%2f", bmi));
			
		});
		
		bmiCalculatorLayout.getChildren().addAll(calculateButton, backButton);
		
		Scene bmiCalculatorScene = new Scene(bmiCalculatorLayout, 600, 400);
		
		primaryStage.setScene(bmiCalculatorScene);
		
	}

	private void openDietTracker(Stage primaryStage, Scene scene) {
		
		VBox dietTrackerLayout = new VBox(10);
		dietTrackerLayout.setPadding(new Insets(10));
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> { primaryStage.setScene(scene);});
		
		Label mealLabel = new Label("Enter meal: ");
		TextField mealField = new TextField();
		dietTrackerLayout.getChildren().addAll(mealLabel, mealField);
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> { String meal = mealField.getText();
			if (!meal.isEmpty()) {
				mealInventory.add(meal);
				updateMealList(primaryStage);
				mealField.clear();
			}
		});
		dietTrackerLayout.getChildren().add(addButton);
		ListView mealListView = new ListView();
		dietTrackerLayout.getChildren().add(mealListView);
		
		Button delete = new Button("Delete");
		delete.setOnAction(e -> { Object selectedMeal = mealListView.getSelectionModel().getSelectedItem();
		if (selectedMeal != null) {
			mealInventory.remove(selectedMeal);
			updateMealList(primaryStage);
		}
		});
		dietTrackerLayout.getChildren().addAll(delete, backButton);	
		
		Scene dietTrackerScene = new Scene(dietTrackerLayout, 600, 400);
		primaryStage.setScene(dietTrackerScene);
	}
		public List<String> mealInventory = new ArrayList<>();
		
		public void updateMealList(Stage primaryStage) {
			ListView mealListView = (ListView)primaryStage.getScene().getRoot().getChildrenUnmodifiable().get(3);
			mealListView.getItems().clear();
			mealListView.getItems().addAll(mealInventory);
	}

	private void openCardioLibrary(Stage primaryStage, Scene scene) {
		
		VBox CardioInventory = new VBox(10);
		CardioInventory.setPadding(new Insets(10));
		
		Label Treadmill = new Label("Run/walk on treadmill for 20-30 minutes at roughly 70 percent effort. ");
		Label runInPlace = new Label("Run in place for 2 minutes before increasing speed for 1 minute, then rest for 1 minute. Start again by jogging for 3 minutes then rest for 1 minute again. Repeat this process for 20 minutes. This exercise can be performed at home as well.");
		Label exerciseBike = new Label("Cycle on the bike for 20-30 minutes at 70 percent effort.");
		Label Elliptical = new Label("Run/walk on the elliptical for 20-30 minutes at roughly 70 percent effort");
		Label jumpRope = new Label("Jump rope for 15-20 minutes. Pause for no longer than one minute if needed.");
		
		Button backButton = new Button("Back");
		
		CardioInventory.getChildren().addAll(backButton, Treadmill, runInPlace, exerciseBike, Elliptical, jumpRope);
		Scene cardioLibraryScene = new Scene(CardioInventory, 600, 400);
		primaryStage.setScene(cardioLibraryScene);
		backButton.setOnAction(e -> { primaryStage.setScene(scene);});

		
		
	}

	private void openWorkoutsInventory(Stage primaryStage, VBox layout, Scene scene) {
		
		VBox WorkoutsInventoryLayout = new VBox(10);
		WorkoutsInventoryLayout.setPadding(new Insets(10));
		
		Button arnold = new Button("Arnold PPL Split");
		arnold.setOnAction(e -> { openWorkoutPage("Arnold PPL Split", layout, primaryStage, scene);});
		
		Button wholeBody = new Button("Whole Body Split");
		wholeBody.setOnAction(e -> { openWorkoutPage("Whole Body Split", layout, primaryStage, scene);});
		
		Button upperLower = new Button("Upper/Lower Body Split");
		upperLower.setOnAction(e -> { openWorkoutPage("Upper/Lower Body Split", layout, primaryStage, scene);});
		
		Button PPL = new Button("Push/Pull/Legs Split");
		PPL.setOnAction(e -> { openWorkoutPage("Push/Pull/Legs Split", layout, primaryStage, scene);});
		
		Button fiveDay = new Button("Five Day Split");
		fiveDay.setOnAction(e -> { openWorkoutPage("Five Day Split", layout, primaryStage, scene);});

		Button backButton = new Button("Back");
		backButton.setOnAction(e -> { primaryStage.setScene(scene);});
		
		WorkoutsInventoryLayout.getChildren().addAll(arnold, wholeBody, upperLower, PPL, fiveDay, backButton);
		WorkoutsInventoryScene = new Scene(WorkoutsInventoryLayout, 600, 400);
		primaryStage.setScene(WorkoutsInventoryScene);
	}
	private void openWorkoutPage(String pageName, VBox layout, Stage primaryStage, Scene scene) {
		
		VBox workoutPageLayout = new VBox(10);
		workoutPageLayout.setPadding(new Insets(10));
		
		
		if(pageName.equals("Arnold PPL Split")) {
			
			VBox arnoldPPL = new VBox(10);
			arnoldPPL.setPadding(new Insets(10));
			
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> { primaryStage.setScene(WorkoutsInventoryScene);});
			
			TextArea arnoldLabel = new TextArea("MONDAY = PUSH DAY, exercise ideas include: Incline dumbbell press 4 sets of 8-10 reps."
					+ " Lying dumbbell tricep extensions 3 sets of 12 reps."
					+ " Dumbbell incline fly 3 sets of 12 reps. "
					+ " Rope tricep pushdown 3 sets of 12 reps. "
					+ " Lateral Raise 3 sets of 12 reps. "
					+ " Decline chest press 4 sets of 8-10 reps. "
					+ " Single arm dumbbell tricep kick-back 3 sets of 12 reps. "
					+ " Cable fly 3 sets of 12 reps. "
		+ " TUESDAY = PULL DAY, exercise ideas include: Neutral grip pull-ups 4 sets of as many reps as possible. "
					+ " EZ bar preacher curls 3 sets of 10 reps. "
					+ " Seated cable rows 3 sets of 12 reps. "
					+ " Hammer Curls 3 sets of 10 reps. "
					+ " Seated lat pulldown 3 sets of 10 reps. "
					+ " Straight bar cable curls 3 sets of 10 reps. "
					+ " Single arm bent rows 3 sets of 10 reps. "
					+ " Incline dumbbell curl 3 sets of 10 reps. "
		+ " WEDNESDAY = LEG DAY, exercise ideas include, Back squat 4 sets of 8 reps."
					+ " Dumbbell RDL 3 sets of 10 reps."
					+ " Single leg hacksquat (assisted with band) 3 sets of 8-12 reps."
					+ " Leg extension machine 3 sets of 10 reps."
					+ " Hamstring curl machine 3 sets of 10 reps."
					+ " Calf raises 4 sets of 15 reps. "
					+ " Abduction/adduction machines 3 sets of 12 reps. "
		+ " THURSDAY = CHEST/BACK DAY, exercise ideas include: Incline dumbbell press 4 sets of 8-10 reps."
					+ " Dumbbell incline fly 3 sets of 12 reps. "
					+ " Decline chest press 4 sets of 8-10 reps. "
					+ " Cable fly 3 sets of 12 reps. "
					+ " Neutral grip pull-ups 4 sets of as many reps as possible."
					+ " Seated cable rows 3 sets of 12 reps. "
					+ " Seated lat pulldown 3 sets of 10 reps. "
					+ " Single arm bent rows 3 sets of 10 reps. "
		+ " FRIDAY = SHOULDERS/ARMS DAY, exercise ideas include: Lying dumbbell tricep extensions 3 sets of 12 reps."
					+ " Rope tricep pushdown 3 sets of 12 reps. "
					+ " Lateral Raise 3 sets of 12 reps. "
					+ " Single arm dumbbell tricep kick-back 3 sets of 12 reps. "
					+ " EZ bar preacher curls 3 sets of 10 reps. "
					+ " Hammer Curls 3 sets of 10 reps. "
					+ " Straight bar cable curls 3 sets of 10 reps. "
					+ " Incline dumbbell curl 3 sets of 10 reps. "
		+ " SATURDAY = LEG DAY, exercise ideas include, Back squat 4 sets of 8 reps. "
					+ " RDL variation 3 sets of 10 reps. "
					+ " Single leg hacksquat (assisted with band) 3 sets of 8-12 reps. "
					+ "	Leg extension machine 3 sets of 10 reps. "
					+ "	Hamstring curl machine 3 sets of 10 reps. "
					+ " Calf raises 4 sets of 15 reps. "
					+ " SUNDAY = REST DAY, ");
			arnoldLabel.setWrapText(true);
			arnoldLabel.setEditable(false);
			arnoldPPL.getChildren().addAll(arnoldLabel, backButton);
			
			Scene arnoldScene = new Scene(arnoldPPL ,600, 400);
			primaryStage.setScene(arnoldScene);
			
		}
		else if (pageName.equals("Whole Body Split")) {
			
			VBox wholeBody = new VBox(10);
			wholeBody.setPadding(new Insets(10));
			
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> { primaryStage.setScene(WorkoutsInventoryScene);});
			
			TextArea wholeBodyLabel = new TextArea("DAY 1. Exercises include: Squats: 4 sets of 6-12 reps. "
					+ " Overhead Press: 4 sets of 6-12 reps. "
					+ " Rows: 3 sets of 10-15 reps. "
					+ " Dips: 3 sets of 8-15 reps. "
					+ " DAY 2. Rest day. "
					+ " DAY 3. Exercises include: Bench Press: 4 sets of 6-12 reps. "
					+ " Pull Ups: 3 sets of max reps. "
					+ " Split Squats: 3 sets of 8-12 reps each side. "
					+ " Planks: 3 sets of 30-60 second holds. "
					+ " DAY 4. Rest day. "
					+ " DAY 5. Exercises include: Deadlifts: 4 sets of 6-10 reps. "
					+ " Seated DB Overhead Press: 4 sets of 10-15 reps. "
					+ " Push Ups: 3 sets of 15-20+ reps. "
					+ " Hanging Leg Raises: 3 sets of 8-12 reps. "
					+ " DAY 6. Rest Day. "
					+ " DAY 7. Rest Day. ");
			
			wholeBodyLabel.setWrapText(true);
			wholeBodyLabel.setEditable(false);
			wholeBody.getChildren().addAll(wholeBodyLabel, backButton);
			
			Scene wholeBodyScene = new Scene(wholeBody, 600, 400);
			primaryStage.setScene(wholeBodyScene);
		}
		else if (pageName.equals("Upper/Lower Body Split")) {
			
			VBox upperLower = new VBox(10);
			upperLower.setPadding(new Insets(10));
			
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> { primaryStage.setScene(WorkoutsInventoryScene);});
			
			TextArea upperLowerLabel = new TextArea("DAY 1 = Upper Workout. Exercises include: Incline Chest Press 4 sets 10 reps. "
					+ " Cable fly 3 sets of 12 reps. "
					+ " Lateral raise 3 sets of 12 reps. "
					+ " Lat pulldown 3 sets of 10-12 reps. "
					+ " Cable row variation 3 sets of 10-12 reps. "
					+ " DAY 2 = Lower Workout. Exercises include: Back Squat 4 sets of 8 reps. "
					+ " RDL variation 3 sets of 10 reps. "
					+ " Single-leg hacksquat machine (use a band for assistance) 3 sets of 10-12 reps. "
					+ " Leg extensions 4 sets of 10-12 reps. "
					+ " Calf raise 4 sets of 15 reps. "
					+ "	DAY 3 = Rest. "
					+ " Day 4: Upper Workout. Exercises include: Incline Chest Press 4 sets 10 reps. "
					+ " Cable fly 3 sets of 12 reps. "
					+ " Lateral raise 3 sets of 12 reps. "
					+ " Lat pulldown 3 sets of 10-12 reps. "
					+ " Cable row variation 3 sets of 10-12 reps. " 
					+ " DAY 5: Lower Workout. Exercises include: Back Squat 4 sets of 8 reps. "
					+ "	RDL variation 3 sets of 10 reps. "
					+ "	Single-leg hacksquat machine (use a band for assistance) 3 sets of 10-12 reps. "
					+ "	Leg extensions 4 sets of 10-12 reps. "
					+ "	Calf raise 4 sets of 15 reps. "
					+ " DAY 6-7: Rest. ");
			
			upperLowerLabel.setWrapText(true);
			upperLowerLabel.setEditable(false);
			upperLower.getChildren().addAll(upperLowerLabel, backButton);
			
			Scene upperLowerScene = new Scene(upperLower, 600, 400);
			primaryStage.setScene(upperLowerScene);
			
		}
		else if (pageName.equals("Push/Pull/Legs Split")) {
			
			VBox pplSplit = new VBox(10);
			pplSplit.setPadding(new Insets(10));
			
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> { primaryStage.setScene(WorkoutsInventoryScene);});
			
			TextArea pplLabel = new TextArea("DAY 1 = PUSH. Exercise ideas include: Incline dumbbell press 4 sets of 8-10 reps. "
					+ " Dumbbell tricep extensions 3 sets of 12 reps. "
					+ " Dumbbell incline fly 3 sets of 12 reps. "
					+ " Rope tricep pushdown 3 sets of 12 reps. "
					+ " Lateral Raise 3 sets of 12 reps. "
					+ " Decline chest press 4 sets of 8-10 reps. "
					+ " Single arm dumbbell tricep kick-back 3 sets of 12 reps. "
					+ " Cable fly 3 sets of 12 reps. "
					+ " DAY 2 = PULL. Exercise ideas include: Neutral grip pull-ups 4 sets of as many reps as possible. "
					+ " EZ bar preacher curls 3 sets of 10 reps. "
					+ "	Seated cable rows 3 sets of 12 reps. "
					+ " Hammer Curls 3 sets of 10 reps. "
					+ " Seated lat pulldown 3 sets of 10 reps. "
					+ " Straight bar cable curls 3 sets of 10 reps. "
					+ " Single arm bent rows 3 sets of 10 reps. "
					+ "	Incline dumbbell curl 3 sets of 10 reps. "
					+ " DAY 3 = LEGS. exercise ideas include, Back squat 4 sets of 8 reps. "
					+ " Dumbbell RDL 3 sets of 10 reps. "
					+ " Single leg hacksquat (assisted with band) 3 sets of 8-12 reps. "
					+ " Leg extension machine 3 sets of 10 reps. "
					+ " Hamstring curl machine 3 sets of 10 reps. "
					+ " Calf raises 4 sets of 15 reps. "
					+ " Abduction/adduction machines 3 sets of 12 reps. "
					+ " DAY 4 = REST. "
					+ " DAY 1 = PUSH. Exercise ideas include: Incline dumbbell press 4 sets of 8-10 reps. "
					+ "	Dumbbell tricep extensions 3 sets of 12 reps. "
					+ "	Dumbbell incline fly 3 sets of 12 reps. "
					+ "	Rope tricep pushdown 3 sets of 12 reps. "
					+ "	Lateral Raise 3 sets of 12 reps. "
					+ " Decline chest press 4 sets of 8-10 reps. "
					+ "	Single arm dumbbell tricep kick-back 3 sets of 12 reps. "
					+ " Cable fly 3 sets of 12 reps. "
					+ "	DAY 2 = PULL. Exercise ideas include: Neutral grip pull-ups 4 sets of as many reps as possible. "
					+ "	EZ bar preacher curls 3 sets of 10 reps. "
					+ "	Seated cable rows 3 sets of 12 reps. "
					+ "	Hammer Curls 3 sets of 10 reps. "
					+ "	Seated lat pulldown 3 sets of 10 reps. "
					+ "	Straight bar cable curls 3 sets of 10 reps. "
					+ "	Single arm bent rows 3 sets of 10 reps. "
					+ "	Incline dumbbell curl 3 sets of 10 reps. "
					+ "	DAY 3 = LEGS. exercise ideas include, Back squat 4 sets of 8 reps. "
					+ "	Dumbbell RDL 3 sets of 10 reps. "
					+ "	Single leg hacksquat (assisted with band) 3 sets of 8-12 reps. "
					+ "	Leg extension machine 3 sets of 10 reps. "
					+ "	Hamstring curl machine 3 sets of 10 reps. "
					+ "	Calf raises 4 sets of 15 reps. "
					+ " DAY 8: Rest day ");
			
			pplLabel.setWrapText(true);
			pplLabel.setEditable(false);
			pplSplit.getChildren().addAll(pplLabel, backButton);
			
			Scene pplScene = new Scene(pplSplit, 600, 400);
			primaryStage.setScene(pplScene);
		}
		else if (pageName.equals("Five Day Split")) {
			
			VBox fiveDaySplit = new VBox(10);
			fiveDaySplit.setPadding(new Insets(10));
			
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> { primaryStage.setScene(WorkoutsInventoryScene);});
			
			TextArea fiveDayLabel = new TextArea(" DAY 1 = CHEST DAY. Exercise ideas include: Incline dumbbell press 4 sets of 8-10 reps."
					+ " Dumbbell incline fly 3 sets of 12 reps. "
					+ " Decline chest press 4 sets of 8-10 reps. "
					+ " Cable fly 3 sets of 12 reps. "
					+ " DAY 2 = BACK DAY. Exercise ideas include: Neutral grip pull-ups 4 sets of as many reps as possible. "
					+ " Seated cable rows 3 sets of 12 reps. "
					+ " Seated lat pulldown 3 sets of 10 reps. "
					+ "	Single arm bent rows 3 sets of 10 reps. "
					+ " DAY 3 = ARMS & ABS DAY. Exercise ideas include: Lying dumbbell tricep extensions 3 sets of 12 reps. "
					+ " Rope tricep pushdown 3 sets of 12 reps. "
					+ " Single arm dumbbell tricep kick-back 3 sets of 12 reps. "
					+ " EZ bar preacher curls 3 sets of 10 reps. "
					+ " Hammer Curls 3 sets of 10 reps. "
					+ " Straight bar cable curls 3 sets of 10 reps. "
					+ "	Incline dumbbell curl 3 sets of 10 reps. "
					+ " Sit-ups 3 sets of as many as possible. "
					+ " Ab crunch machine 3 sets of 12. "
					+ " DAY 4 = LEG DAY. exercise ideas include, Back squat 4 sets of 8 reps. "
					+ "	RDL variation 3 sets of 10 reps. "
					+ "	Single leg hacksquat (assisted with band) 3 sets of 8-12 reps. "
					+ "	Leg extension machine 3 sets of 10 reps. "
					+ "	Hamstring curl machine 3 sets of 10 reps. "
					+ "	Calf raises 4 sets of 15 reps. \""
					+ " DAY 5 = SHOULDER DAY. Exercise ideas include: lateral raises 3 sets of 12 reps. "
					+ " Overhead press 3 sets of 8-10 reps. "
					+ " Dumbbell front raises 3 sets of 12 reps. "
					+ " Shrugs 3 sets of 15 reps. "
					+ " Rear delt fly 3 sets of 12 reps. "
					+ " DAY 6-7 = REST DAY. ");
			
			fiveDayLabel.setWrapText(true);
			fiveDayLabel.setEditable(false);
			fiveDaySplit.getChildren().addAll(fiveDayLabel, backButton);
			
			Scene fiveDayScene = new Scene(fiveDaySplit, 600, 400);
			primaryStage.setScene(fiveDayScene);
		}
	}

	private void openExercisesLibrary(Stage primaryStage, Scene scene) {
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> { primaryStage.setScene(scene);});
		
		VBox video1 = new VBox(10);
		video1.setPadding(new Insets(10));
		
		ScrollPane scroll = new ScrollPane();
		
		Label latLabel = new Label("Lat pulldown");
		Button latButton = new Button("Play");
		Media LatPulldown = new Media("file:///C:/Users/Maggi/Downloads/LatPulldown.mp4");
		MediaPlayer mediaPlayer1 = new MediaPlayer(LatPulldown);
		mediaPlayer1.setAutoPlay(false);
		mediaPlayer1.setCycleCount(99999);
		MediaView mediaView1 = new MediaView (mediaPlayer1);
		video1.getChildren().addAll(backButton, mediaView1, latButton, latLabel);  
		scroll.setContent(video1);
		Scene scene1 = new Scene(scroll,700,600);
		
		latButton.setOnAction(e -> { 
			if(mediaPlayer1.getStatus().equals(Status.PLAYING)) {
				mediaPlayer1.pause();
			} else {
				mediaPlayer1.play(); 

			}
		});

		primaryStage.setScene(scene1);
		
		Label hackLabel = new Label("Single leg hacksquat machine");
		Button hackButton = new Button("Play");
		Media singleLegHack = new Media("file:///C:/Users/Maggi/Downloads/singleLegHack.mp4");
		MediaPlayer mediaPlayer2 = new MediaPlayer(singleLegHack);
		mediaPlayer2.setAutoPlay(false);
		mediaPlayer2.setCycleCount(99999);
		MediaView mediaView2 = new MediaView (mediaPlayer2);
		video1.getChildren().addAll(mediaView2, hackButton, hackLabel);  

		hackButton.setOnAction(e -> { 
			if(mediaPlayer2.getStatus().equals(Status.PLAYING)) {
				mediaPlayer2.pause();
			} else {
				mediaPlayer2.play(); 

			}
		});
		
		primaryStage.setScene(scene1);
		
		Label lateralRaiseLabel = new Label("Lateral Raise");
		Button lateralRaiseButton = new Button("Play");
		Media lateralRaise = new Media("file:///C:/Users/Maggi/Downloads/lateralRaise.mp4");
		MediaPlayer mediaPlayer3 = new MediaPlayer(lateralRaise);
		mediaPlayer3.setAutoPlay(false);
		mediaPlayer3.setCycleCount(99999);
		MediaView mediaView3 = new MediaView (mediaPlayer3);
		video1.getChildren().addAll(mediaView3, lateralRaiseButton, lateralRaiseLabel);  

		lateralRaiseButton.setOnAction(e -> { 
			if(mediaPlayer3.getStatus().equals(Status.PLAYING)) {
				mediaPlayer3.pause();
			} else {
				mediaPlayer3.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label inclineDumbbellLabel = new Label("Incline dumbbell press");
		Button inclineDumbbellButton = new Button("Play");
		Media inclineDumbbell = new Media("file:///C:/Users/Maggi/Downloads/inclineDumbbell.mp4");
		MediaPlayer mediaPlayer4 = new MediaPlayer(inclineDumbbell);
		mediaPlayer4.setAutoPlay(false);
		mediaPlayer4.setCycleCount(99999);
		MediaView mediaView4 = new MediaView (mediaPlayer4);
		video1.getChildren().addAll(mediaView4, inclineDumbbellButton, inclineDumbbellLabel);  

		inclineDumbbellButton.setOnAction(e -> { 
			if(mediaPlayer4.getStatus().equals(Status.PLAYING)) {
				mediaPlayer4.pause();
			} else {
				mediaPlayer4.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label EZbarLabel = new Label("Ez bar preacher curl");
		Button EZbarButton = new Button("Play");
		Media EZbar = new Media("file:///C:/Users/Maggi/Downloads/EZbarPreacherCurl.mp4");
		MediaPlayer mediaPlayer5 = new MediaPlayer(EZbar);
		mediaPlayer5.setAutoPlay(false);
		mediaPlayer5.setCycleCount(99999);
		MediaView mediaView5 = new MediaView (mediaPlayer5);
		video1.getChildren().addAll(mediaView5, EZbarButton, EZbarLabel);  

		EZbarButton.setOnAction(e -> { 
			if(mediaPlayer5.getStatus().equals(Status.PLAYING)) {
				mediaPlayer5.pause();
			} else {
				mediaPlayer5.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label pullUpLabel = new Label("Pull-up");
		Button pullUpsButton = new Button("Play");
		Media pullUps = new Media("file:///C:/Users/Maggi/Downloads/PullUps.mp4");
		MediaPlayer mediaPlayer6 = new MediaPlayer(pullUps);
		mediaPlayer6.setAutoPlay(false);
		mediaPlayer6.setCycleCount(99999);
		MediaView mediaView6 = new MediaView (mediaPlayer6);
		video1.getChildren().addAll(mediaView6, pullUpsButton, pullUpLabel);  

		pullUpsButton.setOnAction(e -> { 
			if(mediaPlayer6.getStatus().equals(Status.PLAYING)) {
				mediaPlayer6.pause();
			} else {
				mediaPlayer6.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label rdlLabel = new Label("RDL");
		Button rdlButton = new Button("Play");
		Media RDL = new Media("file:///C:/Users/Maggi/Downloads/RDL.mp4");
		MediaPlayer mediaPlayer7 = new MediaPlayer(RDL);
		mediaPlayer7.setAutoPlay(false);
		mediaPlayer7.setCycleCount(99999);
		MediaView mediaView7 = new MediaView (mediaPlayer7);
		video1.getChildren().addAll(mediaView7, rdlButton, rdlLabel);  

		rdlButton.setOnAction(e -> { 
			if(mediaPlayer7.getStatus().equals(Status.PLAYING)) {
				mediaPlayer7.pause();
			} else {
				mediaPlayer7.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label backSquatLabel = new Label("Back Squat");
		Button backSquatButton = new Button("Play");
		Media backSquat = new Media("file:///C:/Users/Maggi/Downloads/BackSquat.mp4");
		MediaPlayer mediaPlayer8 = new MediaPlayer(backSquat);
		mediaPlayer8.setAutoPlay(false);
		mediaPlayer8.setCycleCount(99999);
		MediaView mediaView8 = new MediaView (mediaPlayer8);
		video1.getChildren().addAll(mediaView8, backSquatButton, backSquatLabel);  

		backSquatButton.setOnAction(e -> { 
			if(mediaPlayer8.getStatus().equals(Status.PLAYING)) {
				mediaPlayer8.pause();
			} else {
				mediaPlayer8.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label ropeTricepLabel = new Label("Rope Tricep Extension");
		Button ropeTricepButton = new Button("Play");
		Media ropeTricep = new Media("file:///C:/Users/Maggi/Downloads/ropeTricepExtensions.mp4");
		MediaPlayer mediaPlayer9 = new MediaPlayer(ropeTricep);
		mediaPlayer9.setAutoPlay(false);
		mediaPlayer9.setCycleCount(99999);
		MediaView mediaView9 = new MediaView (mediaPlayer9);
		video1.getChildren().addAll(mediaView9, ropeTricepButton, ropeTricepLabel);  

		ropeTricepButton.setOnAction(e -> { 
			if(mediaPlayer9.getStatus().equals(Status.PLAYING)) {
				mediaPlayer9.pause();
			} else {
				mediaPlayer9.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label cableLabel = new Label("Cable Fly");
		Button cableFlyButton = new Button("Play");
		Media cableFly = new Media("file:///C:/Users/Maggi/Downloads/cableFly.mp4");
		MediaPlayer mediaPlayer10 = new MediaPlayer(cableFly);
		mediaPlayer10.setAutoPlay(false);
		mediaPlayer10.setCycleCount(99999);
		MediaView mediaView10 = new MediaView (mediaPlayer10);
		video1.getChildren().addAll(mediaView10, cableFlyButton, cableLabel);  

		cableFlyButton.setOnAction(e -> { 
			if(mediaPlayer10.getStatus().equals(Status.PLAYING)) {
				mediaPlayer10.pause();
			} else {
				mediaPlayer10.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label rowLabel = new Label("Cable Row");
		Button cableRowButton = new Button("Play");
		Media cableRow = new Media("file:///C:/Users/Maggi/Downloads/cableRow.mp4");
		MediaPlayer mediaPlayer11 = new MediaPlayer(cableRow);
		mediaPlayer11.setAutoPlay(false);
		mediaPlayer11.setCycleCount(99999);
		MediaView mediaView11 = new MediaView (mediaPlayer11);
		video1.getChildren().addAll(mediaView11, cableRowButton, rowLabel);  

		cableRowButton.setOnAction(e -> { 
			if(mediaPlayer11.getStatus().equals(Status.PLAYING)) {
				mediaPlayer11.pause();
			} else {
				mediaPlayer11.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label calfRaiseLabel = new Label("Calf Raise");
		Button calfRaiseButton = new Button("Play");
		Media calfRaise = new Media("file:///C:/Users/Maggi/Downloads/calfRaises.mp4");
		MediaPlayer mediaPlayer12 = new MediaPlayer(calfRaise);
		mediaPlayer12.setAutoPlay(false);
		mediaPlayer12.setCycleCount(99999);
		MediaView mediaView12 = new MediaView (mediaPlayer12);
		video1.getChildren().addAll(mediaView12, calfRaiseButton, calfRaiseLabel);  

		calfRaiseButton.setOnAction(e -> { 
			if(mediaPlayer12.getStatus().equals(Status.PLAYING)) {
				mediaPlayer12.pause();
			} else {
				mediaPlayer12.play();
				
			}
		});
		
		primaryStage.setScene(scene1);
		
		Label legExtensionLabel = new Label("Leg Extension");
		Button legExtensionButton = new Button("Play");
		Media legExtension = new Media("file:///C:/Users/Maggi/Downloads/legExtensions.mp4");
		MediaPlayer mediaPlayer13 = new MediaPlayer(legExtension);
		mediaPlayer13.setAutoPlay(false);
		mediaPlayer13.setCycleCount(99999);
		MediaView mediaView13 = new MediaView (mediaPlayer13);
		video1.getChildren().addAll(mediaView13, legExtensionButton, legExtensionLabel);  

		legExtensionButton.setOnAction(e -> { 
			if(mediaPlayer13.getStatus().equals(Status.PLAYING)) {
				mediaPlayer13.pause();
			} else {
				mediaPlayer13.play();
				
			}
		});
	}
}