package est;
import java.util.*;
public class Main {

	static Scanner leia = new Scanner(System.in);

	static String vetCodMarca[] = { "BM", "VW", "FO", "MB", "CV", "FI", "AU", "TO", "HO", "HY" };
	static String vetDescricaoMarca[] = { "BMW", "Volkswagen", "Ford", "Mercedes Benz", "Chevrolet", "Fiat", "Audi",
			"Toyota", "Honda", "Hyundai" };

	public static void main(String[] args) {

		Estacionamento estacionamento = new Estacionamento();
		byte opcao = -1;

		do {
			do {
				System.out.println("** ESTACIONAMENTO **");
				System.out.println("[1] ENTRADA DE VEICULOS");
				System.out.println("[2] SAIDA DE VEICULOS..");
				System.out.println("[3] CONSULTA...........");
				System.out.println("[4] EXCLUSÃO...........");
				System.out.println("[5] RELATORIO..........");
				System.out.println("[0] SAIR...............");
				System.out.println("Escolha uma das opções.");
				try {
					opcao = leia.nextByte();
				}catch(InputMismatchException E) {
					System.out.println("Erro na escolha, tente novamente");
					leia.nextLine();
				}
				if(opcao < 0 || opcao > 5) {
					System.out.println("Digite um dos valores possiveis");
				}
			}while(opcao < 0 || opcao > 5);

			switch(opcao) {
			case 0:
				System.out.print("** PROGRAMA ENCERRADO ***");
				break;
			case 1: 
				estacionamento.registrarEntradaVeiculo();
				break;
			case 2: 
				estacionamento.registrarSaidaVeiculos();
				break;
			case 3: 
				estacionamento.consultar();
				break;
			case 4: 
				estacionamento.excluir();
				break;
			case 5:
				estacionamento.emitirRelatorio();
				break;

			}
		} while(opcao != 0);
	}

}