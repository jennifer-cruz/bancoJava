package bancoJava;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import bancoJava.model.ContaCorrente;
import bancoJava.model.ContaPoupanca;
import bancoJava.util.Cores;
import controller.ContaController;

public class Menu {
	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);

		ContaController contas = new ContaController();

		System.out.println("\nCriar Contas\n");

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		contas.listarTodas();

		int opcao, numero, agencia = 0, tipo, aniversario, numeroDestino;
		float saldo, limite, valor;
		String titular;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + "*******************************");
			System.out.println("      BANCO DO JAVINHA         ");
			System.out.println("*******************************");
			System.out.println(Cores.TEXT_WHITE + Cores.ANSI_BLACK_BACKGROUND + "   ");
			System.out.println("1 - Criar Conta ");
			System.out.println("2 - Listar todas as Contas");
			System.out.println("3 - Buscar Conta por Numero");
			System.out.println("4 - Atualizar Dados da Conta");
			System.out.println("5 - Apagar Conta");
			System.out.println("6 - Sacar");
			System.out.println("7 - Depositar");
			System.out.println("8 - Transferir valores entre Contas ");
			System.out.println("9 - Sair ");
			System.out.println("     ");
			System.out.println("*******************************");
			System.out.println("Entre com a opção desejada: ");
			System.out.println("     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Digite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Javinha - Pode confiar!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println("Criar Conta\n\n");

				System.out.println("Número da Agencia: ");
				agencia = leia.nextInt();

				System.out.println("Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println("Tipo da Conta (1-CC / 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Saldo da Conta: ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Limite da Conta Corrente: ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					// cc.visualizar();
				}
				case 2 -> {
					System.out.println("Aniversário da Conta Poupança: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					// cp.visualizar();
				}
				}

				keyPress();

				break;
			case 2:
				System.out.println("Listar todas as Contas\n\n");

				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println("Consultar dados da Conta - por número\n\n");

				System.out.println("Número da Conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");

				System.out.println("Número da Conta: ");
				numero = leia.nextInt();

				if (contas.buscarNaColletion(numero) != null) {
					// Condicional para checar se a conta existe

					System.out.println("Número da Agencia: ");
					agencia = leia.nextInt();

					System.out.println("Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();

					// Busca do tipo
					tipo = contas.retornaTipo(numero);

					System.out.println("Saldo da Conta: ");
					saldo = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Limite da Conta Corrente: ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						// cc.visualizar();
					}
					case 2 -> {
						System.out.println("Aniversário da Conta Poupança: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						// cp.visualizar();
					}
					}
				} else
					System.out.println("A conta não foi encontrada!");

				keyPress();
				break;
			case 5:
				System.out.println("Apagar a Conta\n\n");

				System.out.println("Número da Conta: ");
				numero = leia.nextInt();

				contas.deletar(numero);

				// Chamada para o Metodo Deletar

				keyPress();
				break;
			case 6:
				System.out.println("Saque\n\n");

				System.out.println("Número da Conta: ");
				numero = leia.nextInt();

				System.out.println("Valor do Saque: ");
				valor = leia.nextFloat();

				// Chamada para o Metodo Sacar

				contas.sacar(numero, valor);

				keyPress();
				break;
			case 7:
				System.out.println("Deposito\n\n");

				System.out.println("Número da Conta: ");
				numero = leia.nextInt();

				System.out.println("Valor do Deposito: ");
				valor = leia.nextFloat();

				// Chamada para o Metodo Depositar

				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println("Transferencia entre Contas\n\n");

				System.out.println("Número da Conta - Origem: ");
				numero = leia.nextInt();

				System.out.println("Número da Conta - Destino: ");
				numeroDestino = leia.nextInt();

				System.out.println("Valor da Transferencia: ");
				valor = leia.nextFloat();

				// Chamada para o Metodo Transferir

				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida!\n");
				keyPress();
				break;
			}
		}

	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}

}