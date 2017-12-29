/**
 * Nome: FrmMain.java
 * Função: Interface principal que gerencia a aplicação
 * @author: Clayton S. Rodrigues
 * Data: 25/09/2014	
 */

package init;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrmMain extends JFrame
{ 
	// menu 
	private JMenuBar barra;
	private JMenu arquivo;
	private JMenu ajuda;
	private JMenuItem contato;
	private JMenuItem sair;
	private JMenuItem sobre;
	// barra de ferramentas
	private JToolBar toolBar;
	private ImageIcon[] images;
	private JButton btnContato;
	private JButton btnSair;
	private JButton btnSobre;

	// construtor
	public FrmMain()
	{ 
		super("Contatos vs 1.0");
		criaJanela();
	} // fim do construtor

	// criaJanela()
	public void criaJanela()
	{ 
		// menu
		barra  = new JMenuBar();
		arquivo = new JMenu("Arquivo");
		ajuda   = new JMenu("Ajuda");
		contato = new JMenuItem("Gerenciar Contatos - Alt+N");
		sair    = new JMenuItem("Sair - Alt+Q");
		sobre   = new JMenuItem("Sobre - Alt+I");
		arquivo.add(contato);
		arquivo.addSeparator();
		arquivo.add(sair);
		ajuda.add(sobre);
		barra.add(arquivo);
		barra.add(ajuda);
		this.setJMenuBar(barra);

		// barra de ferramentas
		String icones[] = {"init/images/Add.png","init/images/Info.png","init/images/Delete.png"};
		
		images = new ImageIcon[3];

		// loop para carregar as imagens nos itens da barra de ferramentas
		for(int i = 0; i < 3; i++)
		{ 
			images[i] = new ImageIcon(icones[i]);
		}

		// instanciando os botões da barra
		btnContato = new JButton(images[0]);
		btnSobre   = new JButton(images[1]);
		btnSair    = new JButton(images[2]);
		// toolbar
		toolBar = new JToolBar("Barra de Ferramentas");
		toolBar.setRollover(true);
		toolBar.add(btnContato);		
		toolBar.add(btnSobre);		
		toolBar.addSeparator();
		toolBar.add(btnSair);		
		toolBar.setBounds(1, 1, 180, 50);

		Container tela = getContentPane();
		setLayout(null);
		tela.add(toolBar);

		// tooltips menu
		contato.setToolTipText("Abre o gerenciador de contatos");
		sair.setToolTipText("Encerra o programa");
		sobre.setToolTipText("Mostra informaçõe sobre o programa");
		// tooltips barra
		btnContato.setToolTipText("Abre o gerenciador de contatos");
		btnSair.setToolTipText("Encerra o programa");
		btnSobre.setToolTipText("Mostra informaçõe sobre o programa");
		// configurando tooltips
		UIManager.put("ToolTip.background", SystemColor.info);
		UIManager.put("ToolTip.foreground", Color.blue);

		// configurando a janela
		setVisible(true);
		setSize(500, 350);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// teclas de atalho da barra de ferramentas
		btnContato.setMnemonic(KeyEvent.VK_N);
		btnSair.setMnemonic(KeyEvent.VK_Q);
		btnSobre.setMnemonic(KeyEvent.VK_I);

		// ações dos itens de menu
		contato.addActionListener(new MenuContatoListener());
		sair.addActionListener(new MenuSairListener());
		sobre.addActionListener(new MenuSobreListener());

		// ações dos itens da barra de ferramentas
		btnContato.addActionListener(new MenuContatoListener());
		btnSair.addActionListener(new MenuSairListener());
		btnSobre.addActionListener(new MenuSobreListener());
			
	}	// fim de criaJanela()

	// classe MenuContatoListener
	private class MenuContatoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			ListarContato lc = new ListarContato();
			lc.setVisible(true);
		}
	} // fim MenuContatoListener

	// classe MenuSairListener
	private class MenuSairListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			int opcao;

			opcao = JOptionPane.showConfirmDialog(null,
				"Deseja realmente encerrar o programa?",
				"Sair",JOptionPane.YES_NO_OPTION
			);

			if(opcao == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}

		// classe MenuContatoListener
	private class MenuSobreListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			JOptionPane.showMessageDialog(null,
				"Informações Sobre o Programa\n"  +
				"=============================\n" +
				"\n Gerenciador de Contatos vs 1.0\n" +
				"Empresa: CR Digital - www.crdigital.com.br\n\n" +
				"\n 2014 - Todos os Direitos Reservados.",
				"Sobre", JOptionPane.INFORMATION_MESSAGE, null
			);
		}
	}
}
