package sistema;

import java.net.URL;
import java.util.ResourceBundle;

import controle.LoginControle;
import controle.MenuBarbeiroControle;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class Menu extends Application implements Initializable{
	
	
	
	@Override public void start(Stage primaryStage) throws Exception {
		MenuBarbeiroControle mb = new MenuBarbeiroControle();
		LoginControle lc = new LoginControle();
		
		System.out.println("Teste 1");
		System.out.println("Teste 2");
		System.out.println("Teste 3");
		System.out.println("Teste 4");
		
		
		//lc.login();
		mb.menuBarbeiro();
	}
	public static void main(String[] args) {
		launch(args);
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}
