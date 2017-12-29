/**
 * Nome: AtualizarContato.java
 * Função: Gerenciar as alterações nos registros da base agenda na tabela contato
 * @author: Clayton S. Rodrigues
 * Data: 24/09/2014	
 */

package init;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class AtualizarContato extends JFrame
{ 
	private DefaultTableModel modelo = new DefaultTableModel();
	private JPanel painelFundo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JLabel lblId;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	private JTextField txtId;	
	private JTextField txtNome;	
	private JTextField txtTelefone;	
	private JTextField txtEmail;	
	Contato contato;
	private int linhaSelecionada;

	// construtor
	public AtualizarContato( DefaultTableModel md, int id, int linha  )
	{ 
		super("Contatos - Atualizar");
		
		criaJanela();
		modelo = md;
		
		ContatoDao dao = new ContatoDao();
		contato = dao.getContatoById(id);
		txtId.setText(Integer.toString(contato.getId()));
		txtNome.setText(contato.getNome());
		txtTelefone.setText(contato.getTelefone());
		txtEmail.setText(contato.getEmail());
		linhaSelecionada = linha;
	} // fim do construtor	
	

	// criaJanela()
	public void criaJanela()
	{ 
		btnSalvar    = new JButton("Salvar");
		btnCancelar  = new JButton("Cancelar");
		lblId        = new JLabel("  Id:  ");
		lblNome      = new JLabel("  Nome:  ");
		lblTelefone  = new JLabel("  Telefone:  ");
		lblEmail     = new JLabel("  Email:  ");
		txtId        = new JTextField();
		txtId.setEditable(false);
		txtNome      = new JTextField();
		txtTelefone  = new JTextField();
		txtEmail     = new JTextField();
		
		painelFundo  = new JPanel();
		painelFundo.setLayout(new GridLayout(5, 2, 2, 4));
		painelFundo.add(lblId);
		painelFundo.add(txtId);
		painelFundo.add(lblNome);
		painelFundo.add(txtNome);
		painelFundo.add(lblTelefone);
		painelFundo.add(txtTelefone);
		painelFundo.add(lblEmail);
		painelFundo.add(txtEmail);
		painelFundo.add(btnSalvar);
		painelFundo.add(btnCancelar);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 150);
		setResizable(false);
		setVisible(true);

		btnSalvar.addActionListener(new BtnSalvarListener());
		btnCancelar.addActionListener(new BtnCancelarListener());
		
	} // fim criaJanela()

	// classe privada BtnSalvarListener
	private class BtnSalvarListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{ 
			// confirmar se deseja realmente alterar o contato
			int opcao;

			opcao = JOptionPane.showConfirmDialog(null,
				"Deseja realmente alterar o contato?",
				"Alterar", JOptionPane.YES_NO_OPTION
			);		

			if( opcao == JOptionPane.YES_OPTION )
			{ 
				Contato c = new Contato();

				c.setId(Integer.parseInt(txtId.getText()));
				c.setNome(txtNome.getText());
				c.setTelefone(txtTelefone.getText());
				c.setEmail(txtEmail.getText());

				ContatoDao dao = new ContatoDao();
				dao.atualizar(c);
				modelo.removeRow(linhaSelecionada);
				modelo.addRow(new Object[]{c.getId(),c.getNome(),c.getTelefone(),c.getEmail()});

				setVisible(false);
			}
		}
	} // fim da classe BtnSalvarListener

	// classe privada BtnCancelarListener
	private class BtnCancelarListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{ 
			setVisible(false);
		}
	} // fim da classe BtnCancelarListener
}

