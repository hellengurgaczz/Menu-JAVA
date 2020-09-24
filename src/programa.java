import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class programa {
	
	public static void main (String[] Args) throws FileNotFoundException {
		
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
			case 1 -> Pedidos.novo();
			case 2 -> Pedidos.pedido();
			case 3 -> listagem();
			case 4 -> configuracoes();
			}
			
		} while(retorno != 0);

		
	}

	private static void configuracoes() {
		
		Scanner leitor = new Scanner(System.in);
		int retorno = 1;
		do {
			System.out.println("\n\t----CONFIGURAÇÕES DO CARDÁPIO----\n");
			System.out.println("1 - Para adicionar item a pratos\n2 -  Para adicionar item a bebidas\n 3 - Para adicionar"
					+ " item aos vinhos\n4 - Para atualizar item de pratos; \n5 - Para atualizar item de bebidas"
					+ "\n6 - Para atualizar item de vinhos\n7 - Para remover item de pratos\n8 - Para remover item de "
					+ "bebidas\n9 - Para remover item de vinhos\n0 - Sair");
			retorno = leitor.nextInt();
			
			switch (retorno) {
			case 1 -> Cardapio.adicionar("PRATOS");
			case 2 -> Cardapio.adicionar("BEBIDAS");
			case 3 -> Cardapio.adicionar("VINHOS");
			case 4 -> Cardapio.identificarLista("PRATOS").atualizar();
			case 5 -> Cardapio.identificarLista("PRATOS").atualizar();
			case 6 -> Cardapio.atualizar("vinhos");
			case 7 -> Cardapio.remover("pratos");
			case 8 -> Cardapio.remover("bebidas");
			case 9 -> Cardapio.remover("vinhos");
			}
			
		}while (retorno != 0);
	}

	private static void listagem() throws FileNotFoundException {
		
		Scanner leitor = new Scanner(System.in);
		System.out.println("\n\t----LISTAGEM----\n");
		System.out.println("1 - Para pratos");
		System.out.println("2 - Para bebidas");
		System.out.println("3 - Para vinhos");
		int retorno = leitor.nextInt();
		List<Item>lista = new ArrayList<>();
		String titulo = null;
		
		switch (retorno) {
		case 1 -> lista = Cardapio.listar(titulo = "PRATOS");
		case 2 -> lista = Cardapio.listar(titulo = "BEBIDAS");
		case 3 -> lista = Cardapio.listar(titulo = "VINHOS");
		}
		
		System.out.println("\nPREÇO\t" + titulo);
		for (Item item : lista) {
			System.out.println(item.getPreco() + "\t" + item.getNome());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
