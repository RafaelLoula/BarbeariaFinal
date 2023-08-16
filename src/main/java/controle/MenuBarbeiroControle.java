package controle;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.internal.build.AllowSysOut;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Agendamento;
import modelo.Barbeiro;
import modelo.Cliente;
import modelo.Corte;
import modelo.Horario;

public class MenuBarbeiroControle implements Initializable {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");

	@FXML
	TextField txtNome;
	@FXML
	TextField txtEndereco;
	@FXML
	TextField txtCelular;
	@FXML
	TextField txtCliente;

	@FXML
	TextField txtBuscarAgendamento;

	@FXML
	Button btnPesquisarAgendamento;

	@FXML
	Pane pnCadastrarUsuario = new Pane();
	@FXML
	Pane pnAgendamento = new Pane();
	@FXML
	Pane pnCadastraUsuarioBlur = new Pane();

	@FXML
	TableView<Agendamento> tbAgendamento = new TableView<>();
	@FXML
	TableColumn<Agendamento, String> tcCliente = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcData = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcBarbeiro = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcCorte = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcHorario = new TableColumn<>();

	@FXML
	TableView<Agendamento> tbAgendamentoCliente = new TableView<>();
	@FXML
	TableColumn<Agendamento, String> tcBuscaAgendamentoCliente = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcBuscaAgendamentoData = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcBuscaAgendamentoBarbeiro = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcBuscaAgendamentoCorte = new TableColumn<>();
	@FXML
	TableColumn<Agendamento, String> tcBuscaAgendamentoHorario = new TableColumn<>();

	@FXML
	ComboBox<Cliente> cbCliente = new ComboBox<>();
	@FXML
	ComboBox<Barbeiro> cbBarbeiro = new ComboBox<>();
	@FXML
	ComboBox<Object> cbHorario = new ComboBox<Object>();
	@FXML
	DatePicker dtAgendamento = new DatePicker();
	@FXML
	ComboBox<Object> cbCorte = new ComboBox<>();

	public void menuBarbeiro() {
		String arquivoCSS = getClass().getResource("/visao/Login.css").toExternalForm();
		URL loginFXML = getClass().getResource("/visao/MenuBarbeiro.fxml");
		Pane raiz = null;
		carregarHorario();
		try {
			raiz = FXMLLoader.load(loginFXML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene cena = new Scene(raiz, 810, 500);
		cena.getStylesheets().add(arquivoCSS);

		Stage primaryStage = new Stage();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Sistema");
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public void cadastrarUsuario() throws ParseException {
		if (pnCadastrarUsuario.isVisible()) {
			pnCadastrarUsuario.setVisible(false);
		} else {
			pnCadastrarUsuario.setVisible(true);
			pnCadastraUsuarioBlur.setVisible(true);
		}
	}

	public void criarAgendamento() {
		if (pnAgendamento.isVisible()) {
			pnAgendamento.setVisible(false);

		} else {
			pnAgendamento.setVisible(true);
		}
	}

	public void consultarHorario() {
		carregarHorario();
		LocalDate localDate = dtAgendamento.getValue();
		String dataFormato = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select a from Agendamento a where a.data ='" + dataFormato.toString() + "'";
		TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);

		List<Agendamento> agendamentos = query.getResultList();
		List<Horario> horarios = carregarHorario();

		if (!agendamentos.isEmpty()) {
			for (Agendamento agendamento : agendamentos) {
				if (!agendamento.getData().isEmpty()) {
					for (Horario horas : horarios) {
						if (agendamento.getHora().equals(horas.getHorario())) {
							horas.setHorario(horas.getHorario() + " ˜Indisponível˜ ");
						}
					}
				}
			}
		} else {
			System.out.println("Lista Vazia");
		}
		carregarHorarioLista(horarios);
	}

	public void pesquisarAgendamentoCliente() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select a from Agendamento a where a.cliente like '%" + txtBuscarAgendamento.getText().toString()
				+ "%'";
		TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);

		List<Agendamento> agendamentos = query.getResultList();

		ObservableList<Agendamento> obsAgendamento = FXCollections.observableArrayList(agendamentos);

		tcBuscaAgendamentoCliente.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Cliente"));
		tcBuscaAgendamentoData.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Data"));
		tcBuscaAgendamentoBarbeiro.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Barbeiro"));
		tcBuscaAgendamentoCorte.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Corte"));
		tcBuscaAgendamentoHorario.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Hora"));
		tbAgendamentoCliente.setItems(obsAgendamento);

	}

	public void confirmarAgendamento() throws ParseException {

		LocalDate localDate = dtAgendamento.getValue();
		String dataFormato = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if (!cbHorario.getValue().toString().contains("Indisponível")) {
			Agendamento agendamento = new Agendamento(cbCliente.getValue().toString(), dataFormato,
					cbBarbeiro.getValue().toString(), cbCorte.getValue().toString(), cbHorario.getValue().toString());
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.persist(agendamento);
			em.getTransaction().commit();

			em.close();
			emf.close();
			carregarTabelaAgendamento();
			limparAgendamento();
		} else {
			System.out.println("Horário Indisponível!");
		}

	}

	public void confirmarCliente() {
		Cliente c = new Cliente();
		c.setNome(txtNome.getText());
		c.setEndereco(txtEndereco.getText());
		c.setCelular(txtCelular.getText());

		if (!c.getNome().isEmpty() && !c.getCelular().isEmpty()) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			em.close();
			emf.close();
			limpar();
			carregarTodos();

		} else {
			System.out.println("Vazio");
		}
	}

	public void limpar() {
		txtNome.setText(null);
		txtEndereco.setText(null);
		txtCelular.setText(null);
	}

	public void limparAgendamento() {
		cbCliente.setValue(null);
		cbBarbeiro.setValue(null);
		cbCorte.setValue(null);
		cbHorario.setValue(null);
		dtAgendamento.setValue(null);
	}

	public void carregarHorarioLista(List<Horario> a) {
		cbHorario.setItems(FXCollections.observableArrayList(a));
	}

	public List<Horario> carregarHorario() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select u from Horario u";
		TypedQuery<Horario> query = em.createQuery(jpql, Horario.class);

		List<Horario> horarios = query.getResultList();
		cbHorario.setItems(FXCollections.observableArrayList(horarios));

		em.close();
		emf.close();

		return horarios;

	}

	public void carregarBarbeiro() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select u from Barbeiro u";
		TypedQuery<Barbeiro> query = em.createQuery(jpql, Barbeiro.class);

		List<Barbeiro> barbeiros = query.getResultList();
		cbBarbeiro.setItems(FXCollections.observableArrayList(barbeiros));

		em.close();
		emf.close();
	}

	public void carregarCliente() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select u from Cliente u";
		TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);

		List<Cliente> clientes = query.getResultList();
		cbCliente.setItems(FXCollections.observableArrayList(clientes));

		em.close();
		emf.close();
	}

	public void carregarCorte() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select u from Corte u";
		TypedQuery<Corte> query = em.createQuery(jpql, Corte.class);

		List<Corte> cortes = query.getResultList();
		cbCorte.setItems(FXCollections.observableArrayList(cortes));
	}

	public void carregarTodos() {
		carregarHorario();
		carregarBarbeiro();
		carregarCliente();
		carregarCorte();
		carregarTabelaAgendamento();
	}

	public void carregarTabelaAgendamento() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
		EntityManager em = emf.createEntityManager();

		String jpql = "select u from Agendamento u";
		TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);

		List<Agendamento> agendamentos = query.getResultList();

		ObservableList<Agendamento> obsAgendamento = FXCollections.observableArrayList(agendamentos);
		tcCliente.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Cliente"));
		System.out.println(tcData.getCellValueFactory());
		tcData.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Data"));
		tcBarbeiro.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Barbeiro"));
		tcCorte.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Corte"));
		tcHorario.setCellValueFactory(new PropertyValueFactory<Agendamento, String>("Hora"));
		tbAgendamento.setItems(obsAgendamento);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTabelaAgendamento();
		carregarHorario();
		carregarBarbeiro();
		carregarCliente();
		carregarCorte();
//		Corte corte = new Corte("Corte + Barba", 25f);
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("barbearia-jpa");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(corte);
//		em.getTransaction().commit();
//		em.close();
//		emf.close();

	}

}
