/**

 * May 4th 2022
 * Purpose: Calculator using JavaFx to create a savings calculator using compound
 *  interest to show future value of a monthly savings plan assuming an annual interest rate 
 *  and number of years
 */
import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Program10 extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	// computes futurevalue
	double getFutureValue(double monthlyDeposit, double interestRate , double frequency , double years) {

		double term = frequency * years;
		double rateFactor = 1.0 + (interestRate / frequency);	
		double futureValue = 0.0;	

		for (int i = 0 ; i < term; i++) {
			futureValue += monthlyDeposit * Math.pow( rateFactor, i);
		}

		// returns future value 
		return futureValue;
	}





	@Override
	public void start(Stage primaryStage) throws Exception {

		// display a title in the title bar
		primaryStage.setTitle("Savings Calculator");

		// savings calculator program

		// monthly deposit input controls.
		TextField monthlyDepositInput = new TextField("100.0");
		VBox monthlyDepositGroup = new VBox(2,new Label("Monthly Deposit:$"), monthlyDepositInput);
		monthlyDepositGroup.setPadding(new Insets(5));
		// interest rate input controls
		TextField interestRateInput = new TextField("6.9");
		VBox interestRateGroup = new VBox(2,new Label("Interest Rate(Annual):%"), interestRateInput);
		interestRateGroup.setPadding(new Insets(5));
		// term input controls
		TextField yearsInput = new TextField("20");
		VBox yearsGroup = new VBox(2,new Label("Term(Years)"), yearsInput);
		yearsGroup.setPadding(new Insets(5));


		// Creates 3  VBox's for all the input groups
		VBox inputGroup = new VBox(8, monthlyDepositGroup);

		VBox rateinputGroup = new VBox(8, interestRateGroup);

		VBox yearsinputGroup = new VBox(8, yearsGroup);



		// Output section
		// Create future value  output
		Label futureValueOutput = new Label("51,464.74");
		VBox futureValueGroup = new VBox(2,new Label("Future Value:$ "), futureValueOutput);
		futureValueGroup.setPadding(new Insets(5));

		// create total deposits value output
		Label totalDepositsOutput = new Label("24,000.00");
		VBox totalDepositsGroup = new VBox(2,new Label("Total Deposits:$ "), totalDepositsOutput);
		totalDepositsGroup.setPadding(new Insets(5));

		// create entire interest value output
		Label earnedInterestOutput = new Label("27,464.74");
		VBox earnedInterestGroup = new VBox(2,new Label("Earned Interest:$ "), earnedInterestOutput);
		earnedInterestGroup.setPadding(new Insets(5));

		// Create 3 VBox's for all the output groups
		VBox outputGroup = new VBox(8, futureValueGroup);

		// creates total Vbox
		VBox totaloutputGroup = new VBox(8, totalDepositsGroup);

		// creates earned Vbox
		VBox earnedoutputGroup = new VBox(8, earnedInterestGroup);


		// Create an input/output group to display the input and output side-by-side
		HBox inputOutputGroup = new HBox(40, inputGroup, outputGroup);	
		inputOutputGroup.setAlignment(Pos.CENTER);

		// creates rate input  total out put group to display 
		HBox rateinputtotalOutputGroup = new HBox(40, rateinputGroup, totaloutputGroup);	
		rateinputtotalOutputGroup.setAlignment(Pos.CENTER);

		// creates years input and earned output group to display
		HBox yearsinputearnedOutputGroup = new HBox(40, yearsinputGroup, earnedoutputGroup);
		yearsinputearnedOutputGroup.setAlignment(Pos.CENTER);


		// Create the Solve button
		Button solveButton = new Button("Solve");

		// Event Hander Method 3:
		// Using a Lambda Expression
		// This gives direct access to the local variable we need for the conversion
		// Lambda expression notation
		// parameterName -> {// TODO method code goes here }
		solveButton.setOnAction((event)->{

			// Captures the input of deposit and convert to double
			double deposit = Double.parseDouble(monthlyDepositInput.getText());
			// captures input of rate and converts to a double
			double rate = Double.parseDouble(interestRateInput.getText())/100;
			// captures input of years and converts to a double
			double years = Double.parseDouble(yearsInput.getText());
			// Captures input of frequency and converts to a double
			double frequency = 12;

			// Compute future value, earned interest , first year interest
			double futureValue = getFutureValue(deposit,rate, frequency,years);
			double totalDeposits = deposit * years * frequency ;
			double earnedInterest = futureValue - totalDeposits;
			// Convert the future value to a String then put in the future valueOutput 
			futureValueOutput.setText(String.format("%.1f", futureValue));

			System.out.println("Lambda Expression: solve event fired.");	
			// Convert the future value to a String then put in the total deposit Output
			totalDepositsOutput.setText(String.format("%.1f", totalDeposits));

			System.out.println("Lambda Expression: solve event fired.");	

			// Convert the earned interest  to a String then put in the earned interest Output
			earnedInterestOutput.setText(String.format("%.1f", earnedInterest));

			System.out.println("Lambda Expression: solve event fired.");	

		});


		// Create the root node of the scene graph
		VBox root = new VBox(20,inputOutputGroup,rateinputtotalOutputGroup,yearsinputearnedOutputGroup, solveButton);
		root.setAlignment(Pos.CENTER);


		// create scene
		Scene scene = new Scene(root,400,260);


		// set scene and show
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	// tests if string is double if its nut passes an exception so that t is returned true
	private static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}	


}
