package factories;

import java.sql.Connection;
import java.sql.DriverManager;

//classe responsavel por gerar as conexoes como banco de dados do postgresql
//aqui estamos aplicando o design pattern chamado de factory method
public class ConnectionFactory {
	
	//declaração de 4 atributos
	private String driver = "org.postgresql.Driver";
	private String host = "jdbc:postgresql://localhost:5433/bd_java_projeto04";
	private String user = "postgres";
	private String password = "coti";
	
	//método para abri e retornar uma conexao
	//com banco de dados do postgresql
	public Connection getConnection() throws Exception{
		//carregando o drigver para conexao com o banco de dados
		Class.forName(driver);
		//abrindo e retornando uma conexao com o postgresql		
		return DriverManager.getConnection(host, user, password);
		
	}

}
