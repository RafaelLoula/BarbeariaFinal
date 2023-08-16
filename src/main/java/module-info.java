module BarbeariaFinal {
	exports sistema;
	exports controle;
	exports modelo;
	exports visao;
	
	opens sistema;
	opens controle;
	opens modelo;
	opens visao;
	
	requires java.persistence;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires java.transaction;
	requires java.xml;
	requires net.bytebuddy;
	requires java.xml.bind;
	requires java.management;
	requires org.hibernate.orm.core;
	requires java.desktop;
	
}