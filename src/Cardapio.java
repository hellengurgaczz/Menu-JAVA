import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Cardapio {
	
	public static String caminhoPratos = "C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\arquivos\\pratos.csv";
	public static String caminhoBebidas = "C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\arquivos\\bebidas-tabuladas.txt";
	public static String caminhoVinhos = "C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\arquivos\\vinhos-tabulados.txt";
	private static List<Item> lista =  new ArrayList<>();
	
	static {
		
		lista = new ArrayList<Item>();
		
	}
	
	public static void listar(String caminho) throws FileNotFoundException {
		
		lista = carregarLista(caminho);
		System.out.println("\n------------------------------------------------------");
		System.out.println("ID\tPREÇO\t\tITEM");
		for (Item item : lista) {
			System.out.println(item.getId() + "\t" + item.getPreco() + "\t" + item.getNome());
		}
		System.out.println("------------------------------------------------------");
	}

	public static List<Item> carregarLista(String caminho) throws FileNotFoundException {
		
		lista.clear();
		File arquivo = new File(caminho);
		Scanner leitor = new Scanner(new FileInputStream(arquivo),"UTF-8");
		String primeiraLinha = leitor.nextLine();
		
		int id = 1;
		if(primeiraLinha.contains("PRATO")) {
			while(leitor.hasNext()) {
				String[] partes = leitor.nextLine().split(";");
				Item item = new Item(id, partes[0],partes[1]);
				lista.add(item);
				id++;
			}
		
		}else if(primeiraLinha.contains("BEBIDA")) {
			while(leitor.hasNext()) {
				String[] partes = leitor.nextLine().split("\t");
				Item item = new Item(id, partes[1],partes[0].replace(",", "."));
				lista.add(item);
				id++;
			}
	
		}else if (primeiraLinha.contains("VINHO")) {
			while(leitor.hasNext()) {
				String[] partes = leitor.nextLine().split("\t");
				Item item = new Item(id,partes[1],partes[0]);
				lista.add(item);
				id++;
			}
		}
		
		return lista;
	}


	public static void atualizar(String caminho) throws IOException {
		
		lista = carregarLista(caminho);
		Scanner leitor = new Scanner(System.in);
		
		listar(caminho);
		System.out.println("\nInforme o ID do item que deseja atualizar: ");
		int idItemAtualizar = leitor.nextInt();
		Item itemAtualizar = buscarPorId(idItemAtualizar);
		System.out.println("\n1- Para atualizar o nome de " + itemAtualizar.getNome() + "\n2 - Para atualizar o preço de " + itemAtualizar.getNome());
		int escolha = leitor.nextInt();
		leitor.nextLine();
		
		switch (escolha) {
		case 1:
			System.out.println("Informe um novo nome para " + itemAtualizar.getNome() + ": ");
			for (Item item : lista) {
				if (item.getId() == idItemAtualizar) {
					item.setNome(leitor.nextLine());
					break;
				}
			}
			break;
		case 2:
			System.out.println("Informe um novo preço para " + itemAtualizar.getNome() + ": ");
			for (Item item : lista) {
				if (item.getId() == idItemAtualizar) {
					item.setPreco(Double.parseDouble(leitor.nextLine()));
					break;
				}
			}
		}
		
		atualizarArquivo(caminho);
		System.out.println("\n" + itemAtualizar.getNome() + " atualizado com sucesso!" );
	}

	public static void adicionarItemArquivo(String caminho) throws IOException {

		try {
			
			Scanner leitor = new Scanner(System.in);
			FileWriter arquivo = new FileWriter(caminho,true);
			PrintWriter gravador = new PrintWriter(arquivo);
			System.out.println("\nInforme o o item que deseja adicionar: ");
			String itemAddNome = leitor.nextLine();
			System.out.println("Informe o preço de " + itemAddNome + ": ");
			String itemAddPreco = leitor.nextLine();
			
			if(caminho.contains("pratos")) {
				gravador.print("\r" + itemAddNome + ";" + itemAddPreco);
			}else if (caminho.contains("bebidas")){
				gravador.print("\r" + itemAddPreco.replace(".",",") + "\t" + itemAddNome);
			}else {
				gravador.print("\r" + itemAddPreco + "\t" + itemAddNome);
			}
			
			System.out.println("\n" + itemAddNome + " por R$" + itemAddPreco + " adicionado com sucesso!");
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void removerItemArquivo(String caminho) throws IOException {
		
		Scanner leitor = new Scanner(System.in);
		listar(caminho);
		
		System.out.println("\nInforme o ID do item que deseja remover: ");
		int idRemover = leitor.nextInt();
		Item itemRemover = buscarPorId(idRemover);
		System.out.println("\nTem certeza que deseja remover " + itemRemover.getNome() + " da sua lista? (S/N)" );
		leitor.nextLine();
		if (leitor.nextLine().equals("S")) {
			lista.remove(idRemover - 1);
			System.out.println("\n" + itemRemover.getNome() + " removido com sucesso!!" );
		}else {
			System.out.println("\nAção cancelada!" );
		}
		
		atualizarArquivo(caminho);
	}
	

	public static void atualizarArquivo(String caminho) throws IOException {
		
		FileWriter arquivo = new FileWriter(caminho);
		PrintWriter gravador = new PrintWriter(arquivo);
		
		
		if(caminho.contains("prato")) {
			gravador.print("PRATO;PRECO");
			for (Item item : lista) {
				gravador.print("\n" + item.getNome() + ";" + item.getPreco());
			}
		}else if (caminho.contains("bebida")){
			gravador.print("PRECO\tBEBIDA");
			for (Item item : lista) {
				gravador.print("\n" + Double.toString(item.getPreco()).replace(".", ",") + "\t" + item.getNome());
			}
		}else if (caminho.contains("vinho")){
			gravador.print("PRECO\tVINHO");
			for (Item item : lista) {
				gravador.print("\n" + item.getPreco() + "\t" + item.getNome());
			}
		}
	
		gravador.close();
		arquivo.close();
	}

	public static Item buscarPorId(int idBuscar) {
		
		for (Item item : lista) {
			if(item.getId() == idBuscar) {
				return item;
			}
		}
		return null;
	}

}
