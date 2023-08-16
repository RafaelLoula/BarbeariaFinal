package controle;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Barbeiro;

public class LoginControle extends Application {
	
	@FXML TextField txtLogin = null;
	@FXML PasswordField pwSenha = null;
	
	private static Stage primaryStage;
	
	
	public void entrar() throws IOException {
		if(txtLogin.getText().equals("") || pwSenha.getText().equals("")) {
			System.out.println("Login inválido");
		}else {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
			EntityManager em = emf.createEntityManager();
			
			String jpql = "select u from Barbeiro u";
			TypedQuery<Barbeiro> query = em.createQuery(jpql, Barbeiro.class);
			
			List<Barbeiro>barbeiros = query.getResultList();
			
			for(Barbeiro barbeiro: barbeiros) {
				
				if(txtLogin.getText().equals(barbeiro.getLogin()) && pwSenha.getText().equals(barbeiro.getSenha())) {
					System.out.println("Passou!");
					MenuBarbeiroControle mb = new MenuBarbeiroControle();
					mb.menuBarbeiro();
					primaryStage.close();
					break;
				}else {
					System.out.println("Login incorreto");
					
				}
			}
			
			em.close();
			emf.close();
			
		}
	}
	
	public void sair() {
		System.exit(0);
		
	}
	
	public void login() {
		String arquivoCSS = getClass().getResource("/visao/Login.css").toExternalForm();
		URL loginFXML = getClass().getResource("/visao/Login.fxml");
		GridPane raiz = null;
		try {
			raiz = FXMLLoader.load(loginFXML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene cena = new Scene(raiz, 250, 220);
		cena.getStylesheets().add(arquivoCSS);

		primaryStage = new Stage();
		primaryStage .setResizable(false);
		primaryStage.setTitle("Tela de Login");
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
