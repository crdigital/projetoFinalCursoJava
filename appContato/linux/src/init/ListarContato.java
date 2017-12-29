/**
 * Nome: ListarContato.java 
 * Função: Criar uma Janela gráfica para gerenciar os contatos do banco 'agenda'
 * @author: Clayton S. Rodrigues
 * Data: 16/09/2014	
 */

package init;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarContato extends JFrame
{ 
	// propriedades
	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btnInserir;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnFechar;
	private DefaultTableModel modelo = new DefaultTableModel();

	// método construtor
	public ListarContato()
	{ 
		super("Contatos");
		criaJTable();
		criaJanela();
	} // fim método construtor

	// método cria janela
	public void criaJanela()
	{ 
		btnInserir = new JButton("Inserir");
		btnExcluir = new JButton("Excluir");
		btnEditar  = new JButton("Editar"); 
		btnFechar  = new JButton("Fechar");

		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo  = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelBotoes.add(btnInserir);
		painelBotoes.add(btnEditar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnFechar);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);		 
		
		// configuração da janela
		getContentPane().add(painelFundo);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 320);
		setVisible(true);
		setLocationRelativeTo(null);
		// ações dos botões
		btnInserir.addActionListener(new BtnInserirListener());
		btnEditar.addActionListener(new BtnEditarListener());
		btnExcluir.addActionListener(new BtnExcluirListener());
		btnFechar.addActionListener(new BtnFecharListener());
	} // fim método criaJanela	

	// método criaJTable
	private void criaJTable()
	{ 
		tabela = new JTable(modelo);
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("Telefone");
		modelo.addColumn("Email");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		pesquisar(modelo);
		
	} // fim criaJTable

	// método pesquisar
	public static void pesquisar(DefaultTableModel modelo)
	{ 
		modelo.setNumRows(0);
		ContatoDao dao = new ContatoDao();

		for(Contato c : dao.getContatos())
		{ 
			modelo.addRow(new Object[]{c.getId(),c.getNome(),c.getTelefone(),c.getEmail()});
		}
	} // fim pesquisar

	// classe privada BtnInserirListener
	private class BtnInserirListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			InserirContato ic = new InserirContato(modelo);
			ic.setVisible(true);	
		}
	} // fim classe BtnInserirListener

	// classe privada BtnEditarListener
	private class BtnEditarListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{ 
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if(linhaSelecionada >= 0)
			{ 
				int idContato = (int) tabela.getValueAt(linhaSelecionada,0);
				AtualizarContato ac = new AtualizarContato(modelo, idContato, linhaSelecionada);
				ac.setVisible(true);
			}
			else
			{ 
				JOptionPane.showMessageDialog(null,
					"É necessário selecionar uma linha.",
					"Editar - Alerta",JOptionPane.WARNING_MESSAGE,null
				);
			}
		}
	} // fim BtnEditarListener

	// classe privada BtnExcluirListener
	private class BtnExcluirListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{ 
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if(linhaSelecionada >= 0)
			{
				// confirma se quer realmente excluir
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
					"Deseja realmente excluir este contato?",
					"Excluir",JOptionPane.YES_NO_OPTION
				);	
				
				if(opcao == JOptionPane.YES_OPTION)
				{	
					int idContato = (int) tabela.getValueAt(linhaSelecionada, 0 );
					ContatoDao  dao =  new ContatoDao();
					dao.remover(idContato);
					modelo.removeRow(linhaSelecionada);
				}
			}
			else
			{ 
				JOptionPane.showMessageDialog(null,
					"É necessário selecionar uma linha.",
					"Excluir - Alerta",JOptionPane.WARNING_MESSAGE,null	
				);
			}	
		}
	} // fim BtnExcluirListener		

	// classe privada BtnFecharListener
	private class BtnFecharListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			setVisible(false);
		}
	}// fim classe BtnFecharListener	

	
} // fim da classe
