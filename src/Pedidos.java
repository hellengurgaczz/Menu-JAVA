import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedidos {
	
	static int codigoPedido = 1;
	static List<String> listaPedidos = new ArrayList<>();
	
	static {
		System.out.println("\n----PEDIDOS----\n");
	}

	public static void novoPedido() throws IOException {
		
		FileWriter arquivo = new FileWriter("C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\Pedidos\\pedido " + codigoPedido + ".txt");
		PrintWriter gravador = new PrintWriter(arquivo);
		
		gravador.println("PEDIDO " + codigoPedido);
		gravador.println("------------------------------------------------------------------");
		gravador.println("Preço\t\tPedido\t\t\t\tObservação");
		gravador.println("------------------------------------------------------------------");
		
		pedindo(gravador,0, codigoPedido);

		codigoPedido++;
	}

	public static void pedidoExistente() throws IOException {
		
		listaPedidos.clear();
		Scanner leitor = new Scanner(System.in);
		
		System.out.println("\nInforme o código do pedido do cliente: ");
		int pedidoNumero = leitor.nextInt();
		File arquivo = new File("C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\Pedidos\\pedido " + pedidoNumero + ".txt");
		leitor = new Scanner(arquivo);
		double total = 0;
		
		while(leitor.hasNext()) {
			listaPedidos.add(leitor.nextLine());
		}
		
		FileWriter arquivo2 = new FileWriter(arquivo);
		PrintWriter gravador = new PrintWriter(arquivo);
		
		int i = 0;
		for(String linha : listaPedidos) {
				
			if(linha.contains("TOTAL")) {
				String [] partes = linha.split("\t");
				total = Double.parseDouble(partes[1]);	
			}else if(i == (listaPedidos.size() - 2)  ||  i == (listaPedidos.size() - 3)) {
				
			}else {
				gravador.println(linha);
			}
			
			i++;
		}

		pedindo(gravador,total,pedidoNumero);
		
		arquivo2.close();
	}
	
	public static void pedindo(PrintWriter gravador, double total, int pedidoNumero) throws FileNotFoundException {
		
		Scanner leitor = new Scanner(System.in);
		int codigo;
	
		do {
			
			System.out.println("-> Pedido " + pedidoNumero);
			System.out.println("\n1 - Adicionar itens\n0 - Finalizar");
			codigo = leitor.nextInt();
			
			if(codigo != 0) {
				
				programa.listagem();
				System.out.println("\nInforme o ID do item que deseja adicionar: ");
				Item itemAddPedido = Cardapio.buscarPorId(leitor.nextInt());
				
				leitor.nextLine();
				System.out.println("\nAdicionar observação(caso não, digite 'N'): ");
				String obs = leitor.nextLine();
				
				if(obs.equals("N") || obs.equals("n")) {
					gravador.println("R$" + itemAddPedido.getPreco() + "\t\t" + itemAddPedido.getNome());
				}else {
					gravador.println("R$" + itemAddPedido.getPreco() + "\t\t" + itemAddPedido.getNome() +
							 "\t\t\t" + obs);
				}
				
				total += itemAddPedido.getPreco();
				
				System.out.println("\n" + itemAddPedido.getNome() + " adicionado com sucesso!\n\n");
				
				}else {
					
				break;
			}
			
		} while(codigo != 0);
		
		gravador.println("\n------------------------------------------------------------------");
		gravador.println("TOTAL: R$\t" + total);
		gravador.close();

	}
}

	
	
