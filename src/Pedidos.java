import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pedidos {
	
	static int codigoPedido = 1;
	
	public static void novoPedido() throws IOException {

		FileWriter arquivo = new FileWriter("C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\Pedidos\\pedido " + codigoPedido + ".txt");
		PrintWriter gravador = new PrintWriter(arquivo);
		
		System.out.println("\t\n----Pedido " + codigoPedido + "----");
		gravador.println("PEDIDO " + codigoPedido + "\n");
		gravador.println("Preço\t\tPedido\t\t\t\tObservação");
		
		pedindo(gravador);
		
		arquivo.close();
		codigoPedido++;
	}

	public static void pedidoExistente() throws IOException {
		
		Scanner leitor = new Scanner(System.in);
		System.out.println("\n----PEDIDOS----");
		System.out.println("\nInforme o código do pedido do cliente: ");
		
		File arquivo = new File("C:\\Users\\fernandes\\Documents\\Faculdade\\Faculdade SI - Matérias\\2° Período\\1°Bimestre\\Desenvolvimento de Software I\\Eclipse\\Atividade 5\\Pedidos\\pedido " + leitor.nextInt() + ".txt");
		
	}
	
	public static void pedindo(PrintWriter gravador) throws FileNotFoundException {
		
		Scanner leitor = new Scanner(System.in);
		int codigo;
		double total = 0;
		
		do {
		
			System.out.println("\n1 - Adicionar itens\n0 - Finalizar ");
			codigo = leitor.nextInt();
			
			if(codigo != 0) {
				
				programa.listagem();
				System.out.println("\nInforme o ID do item que deseja adicionar: ");
				Item itemAddPedido = Cardapio.buscarPorId(leitor.nextInt());
				
				leitor.nextLine();
				System.out.println("\nAdicionar observação: (caso não, digite 'N')");
				String obs = leitor.nextLine();
				
				if(obs.equals("N") || obs.equals("n")) {
					gravador.println("R$" + itemAddPedido.getPreco() + "\t\t" + itemAddPedido.getNome());
				}else {
					gravador.println("R$" + itemAddPedido.getPreco() + "\t\t" + itemAddPedido.getNome() +
							 "\t\t\t" + obs);
				}
				
				total += itemAddPedido.getPreco();
				System.out.println("\n" + itemAddPedido.getNome() + " adicionado com sucesso!");
				
			}else {
				break;
			}
			
		} while(codigo != 0);
		
		gravador.println("\n\nTOTAL: R$" + total);
		gravador.close();

	}

	}
	
