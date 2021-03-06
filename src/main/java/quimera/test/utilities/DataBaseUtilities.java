package quimera.test.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import quimera.test.core.TestCoreCentralizer;
import quimera.test.core.connection.TestConnection;
import quimera.test.core.environment.TestEnvironment;
import quimera.test.core.log.TestLogger;

/**	
 * <b> Definição: </b> <br>
 * O DataBaseUtilities tem como objetivo auxiliar a comunicação com o banco de dados, utilizando as subclasses: <b> Select </b> <b> Update </b> <br>
 * Estas sub classes possuí algumas funções facilitadoras, como métodos para efetuar updates, deletes e selects mais facilitados.
**/
public class DataBaseUtilities extends TestCoreCentralizer {

	private static void logInfoError(String error) {
		TestLogger.logInfo("Ocorreu erro ao tentar efetuar Select ou Update na base de dados: " + error);
	}
	private static ResultSet getResultSet(TestConnection con, String query) throws SQLException {
		return con.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
	}
	private static TestConnection connectionDatabase(TestEnvironment.DataBasesConfig configuracaoDeConexao){
		TestEnvironment.DataBasesConfig db = configuracaoDeConexao;
		return new TestConnection(db.getDatabaseType(), db.getHost(), db.getPort(), db.getBase(), db.getSchemaName(), db.getUsuario(), db.getSenha());
	}
	private static void update(TestConnection con, String query) throws SQLException{
		con.connect();
		PreparedStatement update = con.getCon().prepareStatement(query);
		update.executeUpdate();
		con.disconect();
	}

	/**	
	 * <b> Definição: </b> <br>
	 * DataBaseUtilities.Select tem como o principal objetivo facilitar o uso do select nos testes.
	**/
	public static class Select{

		/**	
		 * <b> Definição: </b> <br>
		 * Busca um resultado de algum statement que possua um select.
		 * 	@param query [String] = Statement para a busca de informações.
		 *	@return Retorna um ResultSet contendo os dados obtidos do banco de dados.
		**/
		public static ResultSet generico(String query) {
			TestConnection con = connectionDatabase(environment.getDataBasesConfig());
	    	ResultSet result = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return result;
		}

		/**	
		 * <b> Definição: </b> <br>
		 * Busca um resultado de algum statement que possua um select.
		 * @param query [String] = Statement para a busca de informações.
		 * @param configuracaoDeConexao [AmbientDefault.dataBasesConfig] = Classe com as configurações do banco de dados.
		 * @return Retorna um ResultSet contendo os dados obtidos do banco de dados.
		**/
		public static ResultSet generico(String query, TestEnvironment.DataBasesConfig configuracaoDeConexao) {
			TestConnection con = connectionDatabase(configuracaoDeConexao);
	    	ResultSet result = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return result;
		}

		/**	
		 * <b> Definição: </b> <br>
		 * Busca o primeiro registro de uma coluna do resultado de algum statement sql que possua um select.
		 * @param query [String] = Statement para a busca de informações.
		 * @param coluna [String] = Coluna buscada no statement.
		 * @return Retorna um valor único, um texto da primeira linha encontrada.
		**/
		public static String textoEmColuna(String query, String coluna) {
			TestConnection con = connectionDatabase(environment.getDataBasesConfig());
	    	ResultSet result = null;
	    	String valor = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		valor = result.getString(coluna);
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return valor;
		}
		
		/**	
		 * <b> Definição: </b> <br>
		 * Busca o primeiro registro de uma coluna do resultado de algum statement sql que possua um select.
		 * @param query [String] = Statement para a busca de informações.
		 * @param coluna [String] = Coluna buscada no statement.
		 * @param configuracaoDeConexao [AmbientDefault.dataBasesConfig] = Classe com as configurações do banco de dados.
		 * @return Retorna um valor único, um texto da primeira linha encontrada.
		**/
		public static String textoEmColuna(String query, String coluna, TestEnvironment.DataBasesConfig configuracaoDeConexao) {
			TestConnection con = connectionDatabase(configuracaoDeConexao);
	    	ResultSet result = null;
	    	String valor = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		valor = result.getString(coluna);
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return valor;
		}

		/**	
		 * <b> Definição: </b> <br>
		 * Busca o primeiro registro de uma coluna do resultado de algum statement sql que possua algum select.
		 * @param query [String] = Statement para a busca de informações.
		 * @param coluna [String] = Coluna buscada no statement.
		 * @return Retorna um valor único, um integer da primeira linha encontrada.
		**/
		public static Integer numeroEmColuna(String query, String coluna) {
			TestConnection con = connectionDatabase(environment.getDataBasesConfig());
	    	ResultSet result = null;
	    	Integer valor = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		valor = result.getInt(coluna);
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return valor;
		}
		
		/**	
		 * <b> Definição: </b> <br>
		 * Busca o primeiro registro de uma coluna do resultado de algum statement sql que possua algum select.
		 * @param query [String] = Statement para a busca de informações.
		 * @param coluna [String] = Coluna buscada no statement.
		 * @param configuracaoDeConexao [AmbientDefault.dataBasesConfig] = Classe com as configurações do banco de dados.
		 * @return Retorna um valor único, um integer da primeira linha encontrada.
		**/
		public static Integer numeroEmColuna(String query, String coluna, TestEnvironment.DataBasesConfig configuracaoDeConexao) {
			TestConnection con = connectionDatabase(configuracaoDeConexao);
	    	ResultSet result = null;
	    	Integer valor = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		valor = result.getInt(coluna);
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return valor;
		}
	
		/**	
		 * <b> Definição: </b> <br>
		 * Busca o primeiro registro de uma coluna do resultado de algum statement sql que possua algum select.
		 * 	@param query [String] = Statement para a busca de informações.
		 * 	@param coluna [String] = Coluna buscada no statement.
		 *	@return Retorna um valor único, um Double da primeira linha encontrada.
		**/
		public static Double doubleEmColuna(String query, String coluna) {
			TestConnection con = connectionDatabase(environment.getDataBasesConfig());
	    	ResultSet result = null;
	    	Double valor = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		valor = result.getDouble(coluna);
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return valor;
		}
		
		/**	
		 * <b> Definição: </b> <br>
		 * Busca o primeiro registro de uma coluna do resultado de algum statement sql.
		 * @param query [String] = Statement para a busca de informações.
		 * @param coluna [String] = Coluna buscada no statement.
		 * @param configuracaoDeConexao [AmbientDefault.dataBasesConfig] = Classe com as configurações do banco de dados.
		 * @return Retorna um valor único, um Double da primeira linha encontrada.
		**/
		public static Double doubleEmColuna(String query, String coluna, TestEnvironment.DataBasesConfig configuracaoDeConexao) {
			TestConnection con = connectionDatabase(configuracaoDeConexao);
	    	ResultSet result = null;
	    	Double valor = null;
	    	try {
	    		con.connect();
	    		result = getResultSet(con, query);
	    		result.first();
	    		valor = result.getDouble(coluna);
	    		con.disconect();
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    	return valor;
		}

	}

	/**	
	 * <b> Definição: </b> <br>
	 * DataBaseUtilities.Update tem como o principal objetivo facilitar o uso do Statements de ação como: <b> Update </b> <b> Delete </b>
	**/
	public static class Update{

		/**	
		 * <b> Definição: </b> <br>
		 * Efetuar uma ação no banco de dados, podendo ser updates, inserts e deletes, utilizando um Statement sql.
		 * @param query [String] = Query que será executada no banco de dados.
		**/
	    public static void generico(String query) {
	    	TestConnection con = connectionDatabase(environment.getDataBasesConfig());
	    	try {
	    		update(con, query);
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}    	
	    }
	    
		/**	
		 * <b> Definição: </b> <br>
		 * Efetuar uma ação no banco de dados, podendo ser updates, inserts e deletes, utilizando um Statement sql.
		 * @param query [String] = Query que será executada no banco de dados.
		 * @param configuracaoDeConexao [AmbientDefault.dataBasesConfig] = Classe de contendo as configurações de conexão com o banco de dados.
		**/
	    public static void generico(String query, TestEnvironment.DataBasesConfig configuracaoDeConexao) {
	    	TestConnection con = connectionDatabase(configuracaoDeConexao);
	    	try {
	    		update(con, query);
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}    	
	    }
	    
		/**	
		 * <b> Definição: </b> <br>
		 * Efetuar uma ação no banco de dados, podendo ser Update, Insert e Delete, utilizando uma lista de Statement sql.
		 * @param query [List (String)] = Lista de Statements que serão executados no banco de dados.
		**/
	    public static void generico(List<String> query) {
	    	TestConnection con = connectionDatabase(environment.getDataBasesConfig());
	    	try {
	    		for (String statement : query) {
	    			if(!statement.endsWith(";")) {
	    				statement = statement + ";";
	    			}
	    			update(con, statement);
				}
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}
	    }
	    
		/**	
		 * <b> Definição: </b> <br>
		 * Efetuar uma ação no banco de dados, podendo ser Update, Insert e Delete, utilizando uma lista de Statement sql.
		 * @param query [List (String)] = Lista de Statements que serão executados no banco de dados.
		 * @param configuracaoDeConexao [AmbientDefault.dataBasesConfig] = Classe de contendo as configurações de conexão com o banco de dados.
		**/
	    public static void generico(List<String> query, TestEnvironment.DataBasesConfig configuracaoDeConexao) {
	    	TestConnection con = connectionDatabase(configuracaoDeConexao);
	    	try {
	    		for (String statement : query) {
	    			if(!statement.endsWith(";")) {
	    				statement = statement + ";";
	    			}
	    			update(con, statement);
				}
	    	}catch (Exception e) {
	    		con.disconect();
	    		logInfoError(e.getMessage());
			}    	
	    }

	}

}