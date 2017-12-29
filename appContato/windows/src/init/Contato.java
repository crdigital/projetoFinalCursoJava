/**
 * Nome: Contato.java
 * Fun��o: Prover as entidades da classe Contato e os m�todos acessores( set e get  )
 * @author: Clayton S. Rodrigues
 * Data: 12/09/2014	
 */

package init;

public class Contato
{ 
	// entidades da classe
	private int id;
	private String nome;
	private String telefone;
	private String email;

	// m�todo construtor
	public Contato()
	{ 

	}
	
	/**
	 * m�todos acessores
	 */
	// id	
	public void setId(int id)
	{ 
		this.id = id;
	}

	public int getId()
	{ 
		return id;
	}
	// nome	
	public void setNome(String nome)
	{ 
		this.nome = nome;
	}

	public String getNome()
	{ 
		return nome;
	}
	// telefone	
	public void setTelefone(String telefone)
	{ 
		this.telefone = telefone;
	}

	public String getTelefone()
	{ 
		return telefone;
	}
	// email	
	public void setEmail(String email)
	{ 
		this.email = email;
	}

	public String getEmail()
	{ 
		return email;
	}

}
