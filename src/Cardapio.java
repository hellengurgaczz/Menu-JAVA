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
	
	
	private static List<Item> listaPratos = new ArrayList<>();
	private static String caminhoPratos = "C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\arquivos\\pratos.csv";
	private static List<Item> listaBebidas =  new ArrayList<>();
	private static String caminhoBebidas = "C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\arquivos\\bebidas-tabuladas.txt";
	private static List<Item> listaVinhos =  new ArrayList<>();
	private static String caminhoVinhos = "C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\arquivos\\vinhos-tabulados.txt";
	
	
	static {
		
		try {
			listaPratos = carregarLista("PRATOS");
			listaBebidas = carregarLista("BEBIDAS");
			listaVinhos = carregarLista("VINHOS");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static List<Item> listar(String categoria) throws FileNotFoundException {
		
		List<Item> lista = carregarLista(categoria);
		return lista;
		
	}

	private static List<Item> carregarLista(String categoria) throws FileNotFoundException {
		
		if(categoria.equals("PRATOS")) {
			File arquivo = new File(caminhoPratos);
			Scanner leitor = new Scanner(new FileInputStream(arquivo),"UTF-8");
			leitor.nextLine();
			while(leitor.hasNext()) {
				String[] partes = leitor.nextLine().split(";");
				Item item = new Item(partes[0],partes[1]);
				listaPratos.add(item);
			}
			
			return listaPratos;
		}else if(categoria.equals("BEBIDAS")){
			File arquivo = new File(caminhoBebidas);
			Scanner leitor = new Scanner(new FileInputStream(arquivo),"UTF-8");
			leitor.nextLine();
			while(leitor.hasNext()) {
				String[] partes = leitor.nextLine().split("\t");
				Item item = new Item(partes[1], partes[0].replace(",", "."));
				listaBebidas.add(item);
			}
			
			return listaBebidas;
		}else {
			File arquivo = new File(caminhoVinhos);
			Scanner leitor = new Scanner(new FileInputStream(arquivo),"UTF-8");
			leitor.nextLine();
			while(leitor.hasNext()) {
				String[] partes = leitor.nextLine().split("\t");
				Item item = new Item(partes[1],partes[0]);
				listaVinhos.add(item);
			}
			
			return listaVinhos;
		}
	}

	public static void atualizar(String categoria) {
		
	
		Scanner leitor = new Scanner(System.in);
		System.out.println("\nInforme o nome do item de " + categoria + "que deseja atualizar: ");
		String nomeItem = leitor.nextLine();
		System.out.println("\n1- Para atualizar o nome de " + nomeItem + "\n2 - Para atualizar o preço de " + nomeItem);
		int retorno =leitor.nextInt();
		
		
		
	}

	private static void buscarporNome(String nomeItem) {
		
		
	}

	public  static void adicionar(String categoria) throws IOException {
		
		Scanner leitor = new Scanner(System.in);
		System.out.println("\nInforme o nome do item que deseja incluir em " + categoria + ": ");
		String itemNome = leitor.nextLine();
		System.out.println("\nInforme o preço do item " + itemNome + " que deseja incluir em " + categoria + ": ");
		Double itemPreco = leitor.nextDouble();
		Item item = new Item(itemNome, itemPreco);
		
		if(categoria.equals("PRATOS")) {
			listaPratos.add(item);
			FileWriter arquivo = new FileWriter(caminhoPratos);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.print("\n" + item.getNome() + ";" + item.getPreco());
			gravador.close();
			arquivo.close();
		}else if(categoria.equals("BEBIDAS")){
			listaPratos.add(item);
			FileWriter arquivo = new FileWriter(caminhoBebidas);
			PrintWriter gravador = new PrintWriter(arquivo);
//			gravador.print("\n" + itemPreco.replace(".",",") + "\t" + item.getNome());
			gravador.close();
			arquivo.close();
			
		}
	}

	public static Object remover(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Cardapio identificarLista(String categoria) {
		
		return null;
	}

}
