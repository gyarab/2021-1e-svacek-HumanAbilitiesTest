package application;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;

public class Controller {
	@FXML
	public Label label;
	private Stage stage;
	private Scene scene; // setup prepinani mezi scenami
	private Parent root;
	public static long currentTime = 0; // aktualni cas
	public static int i = 0; // pomocna promena ke kontrole poctu pokusù
	public static int pocetPokusu = 0;
	public static double prumerReactionTime = 0;

	
	

	public void switchToMenu(ActionEvent e) throws IOException { // zapína hlavni menu ( Main.fxml )
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void switchToReactionTest(ActionEvent e) throws IOException, InterruptedException { // zapina reakcni test (
																								// Main.fxml2 )
		root = FXMLLoader.load(getClass().getResource("Main2.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void switchToMemoryTest(ActionEvent e) throws IOException { // zapína memory test ( Main.fxml3 )
		root = FXMLLoader.load(getClass().getResource("Main3.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void memoryTest(ActionEvent e) throws IOException, InterruptedException {
		boolean game = true;
		int size = 3;
		int score = 0;
		for (; game == true;) {
			Scanner sc = new Scanner(System.in);					// Tvorba Scanu
			int[] scanNum = new int[size];							
			int[] arr = new int[size];								// Tvorba Náhodného Pole
			int randomNum = 0;
			for (int i = 0; i <= arr.length - 1; i++) {
				Random num = new Random();
				randomNum = num.nextInt(9);
				arr[i] = randomNum;

			}
			System.out.println("Hádané èíslo mùžete vidìt níže");	// Výpis Náhodného Pole
			for (int x = 0; x <= arr.length - 1; x++) {
				System.out.print(arr[x]);							//vypis random cisla 
			}
			System.out.println("  Napište èíslo:");
			for (int i = 0; i < size; i++) {						// Pøeètení Elementu
				scanNum[i] = sc.nextInt();
			}
			boolean controlNum = true;
			for (int f = 0; f < size; f++) {						// Kontrola Náhodného Pole s Inputem od Usera
				if (arr[f] == scanNum[f]) {
					controlNum = true;
				} else {
					controlNum = false;
					break;
				}
			}
			if (controlNum == true) {
				size++;
				score++;
			} else {
				game = false;
				System.out.println("Udìlali jste chybu, vaše skore je" +" " + score + " " + ",mùžete to zkusit znovu.");
				System.exit(0);
				
			}
		}
	}

	public void switchToReaction2(ActionEvent e) throws IOException, InterruptedException { 
		System.out.println("Napište takové èíslo, kolik chcete mít pokusù a stisknìte enter");
		Scanner scanner = new Scanner(System.in);
		pocetPokusu = scanner.nextInt();
		Random random = new Random();
		long randomnum = random.nextInt(4000) + 1500;
		Thread.sleep(randomnum);
		currentTime = System.currentTimeMillis(); 
	
		root = FXMLLoader.load(getClass().getResource("Reaction.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void startWatch2(ActionEvent e) throws IOException, InterruptedException { 
		
			long stopTime = System.currentTimeMillis(); 
			root = FXMLLoader.load(getClass().getResource("reactionEnd.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			long reactionTime = (stopTime - currentTime); 
			prumerReactionTime += reactionTime;
			System.out.println(reactionTime + " " + "milisekund");
			i++;

		}

	public void tryAgain(ActionEvent e) throws IOException, InterruptedException {
		if (i != pocetPokusu) {
		Random random = new Random();
		long randomnum = random.nextInt(4000) + 1500;
		Thread.sleep(randomnum);
		currentTime = System.currentTimeMillis(); 

		root = FXMLLoader.load(getClass().getResource("Reaction.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		else {
			System.out.println("Hotovo");
			System.out.println("Vaše prùmìrná reakèní doba je" + " " + prumerReactionTime/pocetPokusu+ " " + "milisekund");
			root = FXMLLoader.load(getClass().getResource("Main2.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
	}

}
