/**
 * Nome: FabricaConexao.java
 * Fun��o: Prover a conex�o e persist�ncia � base de dados 'agenda'
 * @author: Clayton S. Ridrigues
 * Data: 10/09/2014	
 */
package init;

import java.sql.Connection;
import java.sql.DriverManager;	
import java.sql.PreparedStatement;	
import java.sql.ResultSet;	
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FabricaConexao
{ 
	// Declarando os dados para a conex�o
	private static final String usuario        = "java";
	private static final String senha          = "cursodejava";
	private static final String banco          = "agenda";
	private static final String driver_conexao = "com.mysql.jdbc.Driver";	
	private static final String str_conexao    = "jdbc:mysql://localhost:3306/";
	
	/**
	 * getConexao
	 * M�todo respons�vel por conectar a base de dados 	
	 */
	public static Connection getConexao() throws SQLException, ClassNotFoundException
	{
		Connection conn = null;

		try
		{ 
			Class.forName(driver_conexao);
			conn = DriverManager.getConnection(str_conexao + banco, usuario, senha);

			return conn;
		}
		catch(ClassNotFoundException e)
		{ 
			throw new ClassNotFoundException("Driver MySQL n�o foi encontrado." + e.getMessage());
		}
		catch(SQLException e)
		{ 
			throw new SQLException("Erro ao conectar a base de dados." + e.getMessage());
		}
	} // fim do m�todo getConex�o

	/**
	 * fechaConexao(conn)
	 * fecha somente � conex�o 'conn'
	 */
	public static void fechaConexao(Connection conn)
	{ 
		try
		{ 
			if(conn != null)
			{ 
				conn.close();
			}
		}
		catch(Exception e)
		{ 
			JOptionPane.showMessageDialog(null,
				"N�o foi poss�vel fechar a conex�o" +
				"com o banco de dados." + e.getMessage() + "",
				"Fecha Conex�o - ERRO", JOptionPane.ERROR_MESSAGE, null
			);
		}
	} // fim fechaConexao(conn)

	/**
	 * fechaConexao(conn, stmt)
	 * fecha somente � conex�o 'conn'
	 */
	public static void fechaConexao(Connection conn, PreparedStatement stmt)
	{ 
		try
		{ 
			if(conn != null)
			{ 
				fechaConexao(conn);
			}
			if( stmt != null )
			{ 
				stmt.close();
			}
		}
		catch(Exception e)
		{ 
			JOptionPane.showMessageDialog(null,
				"N�o foi poss�vel fechar o Statement" + e.getMessage() + "",
				"Fecha Conex�o - ERRO ", JOptionPane.ERROR_MESSAGE, null
			);
		}
	} // fim fechaConexao(conn, stmt)

	/**
	 * fechaConexao(conn, stmt, rs)
	 * fecha somente � conex�o 'conn'
	 */
	public static void fechaConexao(Connection conn, PreparedStatement stmt, ResultSet rs)
	{ 
		try
		{ 
			if(conn != null || stmt != null )
			{ 
				fechaConexao(conn, stmt);
			}
			if( rs != null )
			{ 
				rs.close();
			}
		}
		catch(Exception e)
		{ 
			JOptionPane.showMessageDialog(null,
			"N�o foi poss�vel fechar o ResultSet" + e.getMessage() + "",
				"Fecha Conex�o - ERRO ", JOptionPane.ERROR_MESSAGE, null
			);
		}

	} // fim fechaConexao(conn, stmt, rs) 
	
}	
