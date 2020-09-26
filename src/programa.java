import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class programa {
	
	public static void main (String[] Args) throws IOException {
		
		int retorno =  1;
		Scanner leitor = new Scanner(System.in);
		
		do {
			
			System.out.println("\n\t----CARDÁPIO----\n");
			System.out.println("1 - Acessar novo pedido");
			System.out.println("2 - Acessar pedido existente");
			System.out.println("3 - Listar itens do cardápio");
			System.out.println("4 - Configurações do cardápio");
			System.out.println("0 - Sair");
			retorno = leitor.nextInt();
			
			switch (retorno) {
			case 1 -> Pedidos.novoPedido();
			case 2 -> Pedidos.pedidoExistente();
			case 3 -> listagem();
			case 4 -> configuracoes();
			}
			
		} while(retorno != 0);

		leitor.close();
	}

	private static void configuracoes() throws IOException {
		
		Scanner leitor = new Scanner(System.in);
		int retorno = 1;
		do {
			System.out.println("\n\t----CONFIGURAÇÕES DO CARDÁPIO----\n");
			System.out.println("1 - Para adicionar item a pratos\n2 - Para adicionar item a bebidas\n3 - Para adicionar"
					+ " item aos vinhos\n4 - Para atualizar item de pratos\n5 - Para atualizar item de bebidas"
					+ "\n6 - Para atualizar item de vinhos\n7 - Para remover item de pratos\n8 - Para remover item de "
					+ "bebidas\n9 - Para remover item de vinhos\n0 - Sair");
			retorno = leitor.nextInt();
			
			switch (retorno) {
			case 1 -> Cardapio.adicionarItemArquivo(Cardapio.caminhoPratos);
			case 2 -> Cardapio.adicionarItemArquivo(Cardapio.caminhoBebidas);
			case 3 -> Cardapio.adicionarItemArquivo(Cardapio.caminhoVinhos);
			case 4 -> Cardapio.atualizar(Cardapio.caminhoPratos);
			case 5 -> Cardapio.atualizar(Cardapio.caminhoBebidas);
			case 6 -> Cardapio.atualizar(Cardapio.caminhoVinhos);
			case 7 -> Cardapio.removerItemArquivo(Cardapio.caminhoPratos);
			case 8 -> Cardapio.removerItemArquivo(Cardapio.caminhoBebidas);
			case 9 -> Cardapio.removerItemArquivo(Cardapio.caminhoVinhos);
			}
			
		}while (retorno != 0);
	}

	public static void listagem() throws FileNotFoundException {
		
		Scanner leitor = new Scanner(System.in);
		
		System.out.println("\n----LISTAGEM----\n");
		System.out.println("1 - Para pratos");
		System.out.println("2 - Para bebidas");
		System.out.println("3 - Para vinhos");
		
		switch (leitor.nextInt()) {
		case 1 -> Cardapio.listar(Cardapio.caminhoPratos);
		case 2 -> Cardapio.listar(Cardapio.caminhoBebidas);
		case 3 -> Cardapio.listar(Cardapio.caminhoVinhos);
		}
	}
}
