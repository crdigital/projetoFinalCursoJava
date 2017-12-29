/**
 * Nome: ContatoDao.java
 * Função: Gerenciar a conexao e a persistência dos contatos no banco 'agenda'
 * @author: Clayton S. Rodrigues
 * Data: 12/09/2014	
 */

package init;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ContatoDao
{ 
	private final String insert    = "INSERT INTO contato (nome, telefone, email)VALUES(?,?,?)";
	private final String update    = "UPDATE contato SET nome=?, telefone=?, email=? WHERE id=?";
	private final String delete    = "DELETE FROM contato WHERE id=?";
	private final String list      = "SELECT * FROM contato";
	private final String listById  = "SELECT * FROM contato WHERE id=?";

	// método inserir()
	public void inserir(Contato contato)
 	{
		if(contato != null)
		{ 
			Connection conn = null;

			try
			{ 
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(insert);

				pstm.setString(1, contato.getNome());
				pstm.setString(2, contato.getTelefone());
				pstm.setString(3, contato.getEmail());

				pstm.execute();

				JOptionPane.showMessageDialog(null,
					"Contato cadastrado com sucesso!",
					"Inserir - Sucesso!",JOptionPane.INFORMATION_MESSAGE, null
				);
			}
			catch(Exception e)
			{ 
				JOptionPane.showMessageDialog(null,
					"Erro ao inserir contato.",
					"Inserir - Erro",JOptionPane.ERROR_MESSAGE, null
				);
			}
		}
		else
		{ 
				JOptionPane.showMessageDialog(null,
					"O contato enviado por parâmetro está vazio.",
					"Inserir - Aviso",JOptionPane.WARNING_MESSAGE, null
				);
		}
	} // fim do método inserir()

	// método atualizar()
	public void atualizar(Contato contato)
 	{
		if(contato != null)
		{ 
			Connection conn = null;

			try
			{ 
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(update);

				pstm.setString(1, contato.getNome());
				pstm.setString(2, contato.getTelefone());
				pstm.setString(3, contato.getEmail());
				pstm.setInt(4, contato.getId());

				pstm.execute();

				JOptionPane.showMessageDialog(null,
					"Contato alterado com sucesso!",
					"Atualizar - Sucesso!",JOptionPane.INFORMATION_MESSAGE, null
				);
			}
			catch(Exception e)
			{ 
				JOptionPane.showMessageDialog(null,
					"Erro ao atualizar contato.",
					"Atualizar - Erro",JOptionPane.ERROR_MESSAGE, null
				);
			}
		}
		else
		{ 
				JOptionPane.showMessageDialog(null,
					"O contato enviado por parâmetro está vazio.",
					"Atualizar - Aviso",JOptionPane.WARNING_MESSAGE, null
				);
		}
	} // fim do método atualizar()

	// Método remover()
	public void remover(int id)
	{ 
		Connection conn = null;
		
		try
		{ 
			conn = FabricaConexao.getConexao();
			PreparedStatement pstm;
			pstm = conn.prepareStatement(delete);

			pstm.setInt(1, id);

			pstm.execute();

			FabricaConexao.fechaConexao(conn, pstm);
		}
		catch(Exception e)
		{ 
				JOptionPane.showMessageDialog(null,
					"Erro ao excluir contato.",
					"Excluir - Erro",JOptionPane.ERROR_MESSAGE, null
				);
		}
	}// fim do método remover()

	// método getContatos()
	public List<Contato> getContatos()
	{ 
		Connection conn        = null;
		PreparedStatement pstm = null;
		ResultSet rs           = null;

		ArrayList<Contato> contatos = new ArrayList<Contato>();

		try
		{ 
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(list);
			rs   = pstm.executeQuery();

			while(rs.next())
			{ 
				Contato contato = new Contato();
					
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				contatos.add(contato);
				
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		}
		catch(Exception e)
		{ 
				JOptionPane.showMessageDialog(null,
					"Erro ao listar contatos.",
					"Listar - Erro",JOptionPane.ERROR_MESSAGE, null
				);
		}
		return contatos;	
	}//fim do método getContatos()		

	// método getContatoById()
	public Contato getContatoById(int id)
	{ 
		Connection conn        = null;
		PreparedStatement pstm = null;
		ResultSet rs           = null;
		
		
		Contato contato = new Contato();

		try
		{ 
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(listById);
			pstm.setInt(1, id);
			rs   = pstm.executeQuery();

			while(rs.next())
			{ 
					
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		}
		catch(Exception e)
		{ 
				JOptionPane.showMessageDialog(null,
					"Erro ao listar contato.",
					"Listar - Erro",JOptionPane.ERROR_MESSAGE, null
				);
		}
		return contato;	
	}//fim do método getContatoById()		

} // fim da classe
