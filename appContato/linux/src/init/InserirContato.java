/**
 * Nome: InserirContato.java
 * Função: Gerenciar a inserção de novos registros na base agenda(contato)
 * @author: Clayton S. Rodrigues
 * Data: 22/09/2014	
 */

package init;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class InserirContato extends JFrame
{ 
	private DefaultTableModel modelo = new DefaultTableModel();
	private JPanel painelFundo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;

	// construtor
	public InserirContato(DefaultTableModel md)
	{ 
		super("Contatos - Inserir");
		criaJanela();
		modelo = md;
	} // fim do construtor

	//método criJanela()
	public void criaJanela()
	{ 
		btnSalvar   = new JButton("Salvar");
		btnCancelar = new JButton("Cancelar");
		lblNome     = new JLabel("Nome: ");
		lblTelefone = new JLabel("Telefone: ");
		lblEmail    = new JLabel("Email: ");
		txtNome     = new JTextField(10);
		txtTelefone = new JTextField(10);
		txtEmail    = new JTextField(10);

		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(4,2,2,4));
		painelFundo.add(lblNome);
		painelFundo.add(txtNome);
		painelFundo.add(lblTelefone);
		painelFundo.add(txtTelefone);
		painelFundo.add(lblEmail);
		painelFundo.add(txtEmail);
		painelFundo.add(btnCancelar);
		painelFundo.add(btnSalvar);
			
		getContentPane().add(painelFundo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 150);
		setVisible(true);
		setResizable(false);

		btnCancelar.addActionListener(new BtnCancelarListener());
		btnSalvar.addActionListener(new BtnSalvarListener());
	}// fim do método criaJanela

	// classe privada BtnSalvarListener
	private class BtnSalvarListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{ 
			Contato c = new Contato();
			c.setNome(txtNome.getText());
			c.setTelefone(txtTelefone.getText());
			c.setEmail(txtEmail.getText());

			ContatoDao dao = new ContatoDao();
			dao.inserir(c);
			ListarContato.pesquisar(modelo);

			setVisible(false);
		} // fim de actionPerformed()
	}// fim da classe BtnInserirlIstener

	// classe privada BrtnCancelarListener()
	private class BtnCancelarListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{ 
			setVisible(false);
		}
	} // fim da classe BtnCancelatListener	

} // fim da classe InserirContato
