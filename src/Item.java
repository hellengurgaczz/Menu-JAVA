

public class Item {
	
	private String nome;
	private double preco;
	

	public Item(String nome, double preco) {
		
		this.nome = nome;
		this.preco = preco;
		
	}
	
	public Item(String titulo) {

		this.nome = titulo;
	
	}

	
	public Item() {
		
	}


	public Item(String nome, String preco) {
		
		this.nome = nome;
		this.preco = Double.parseDouble(preco);
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
