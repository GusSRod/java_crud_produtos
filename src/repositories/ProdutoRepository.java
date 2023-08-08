package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Produto;
import factories.ConnectionFactory;

//classe para realizar operações no banco de dados
//com a entidade produto
public class ProdutoRepository {

	// método para realizar o cadastro de um produto no banco de dados
	public void create(Produto produto) throws Exception {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		// abrindo conexão com o banco de dados
		Connection connection = connectionFactory.getConnection();

		// executar o comando SQL no banco de dados
		PreparedStatement statement = connection
				.prepareStatement("insert into produto(nome, descricao, preco, quantidade) values(?,?,?,?)");
		statement.setString(1, produto.getNome());
		statement.setString(2, produto.getDescricao());
		statement.setDouble(3, produto.getPreco());
		statement.setInt(4, produto.getQuantidade());
		statement.execute();

		// fechando a conexão com o banco de dados
		connection.close();

		System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");

	}

	// método para realizar a atualização de um produto no banco de dados
	public void update(Produto produto) throws Exception {

		// abrindo conexao com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// escrever o comando sql para poder atualizar um produto
		PreparedStatement statement = connection
				.prepareStatement("update produto set nome=?, descricao=?, preco=?, quantidade=? where id=?");

		statement.setString(1, produto.getNome());
		statement.setString(2, produto.getDescricao());
		statement.setDouble(3, produto.getPreco());
		statement.setInt(4, produto.getQuantidade());
		statement.setInt(5, produto.getId());

		statement.execute();

		// fechando a conexao
		connection.close();
	}
	// método para realizar a exclusao de um produto no banco de dados
	public void delete(Integer id) throws Exception{
		
		//abrindo conexao com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		//escrever o comando sql para excluir um produto
		PreparedStatement statement = connection
				.prepareStatement("delete from produto where id=?");
		statement.setInt(1, id);
		statement.execute();
		
		//fechando a conexao
		connection.close();		
	}
	
	//metodo para consultar e retornar todos os produtos cadastrado no banco de dados
	public List<Produto> findAll() throws Exception {
		
		//abrindo conexao com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		//escrever o comando SQL para consultar os produtos
		PreparedStatement statement = connection
				.prepareStatement("select * from produto order by id");

		//executar e capturar o retorno dessa consulta
		ResultSet resultSet = statement.executeQuery();
		
		//declarar uma lista de produto
		List<Produto> lista = new ArrayList<Produto>();
		
		//percorrer cada registro (linha) obtido na consulta
		while(resultSet.next()) {
			//capturar os dados do produto obtido do banco de dados
			Produto produto = new Produto();
			produto.setId(resultSet.getInt("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			
			//adicionando o produto à lista
			lista.add(produto);			
		}		
		
		
		//fechando a conexao
		connection.close();
		
		//retornando a lista
		return lista;
		
	}
	
	//método para consultar 1 produto atraves do id
	public Produto findById(Integer id) throws Exception {
		
		//anrindo conexao com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		//executando uma instrucao SQL no banco de dados
		PreparedStatement statement = connection
				.prepareStatement("select * from produto where id=?");
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		//criando um objeto Produto vazio
		Produto produto = null;
		
		//verificar se algum produto foi encontrado no banco de dados
		//if, pq tem só um
		if(resultSet.next()) {
			
			//instanciar o objeto produto
			produto = new Produto();
			
			produto.setId(resultSet.getInt("id"));
			produto.setNome(resultSet.getString("descricao"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
		}
		
		
		//fechando a conexao
		connection.close();
		
		//retornando o produto
		return produto;
	}

}
