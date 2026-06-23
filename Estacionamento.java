package est;

import java.io.*;

public class Estacionamento {

	char ativo;
	String codEst;
	String placa;
	String dataOperacao;
	char tipoOperacao;
	String modeloCor;
	String codMarca;
	String categoriaVeiculo;
	String horaEntrada;
	String horaSaida;
	float valorPago;

	// *********************** AÇOES *********************** //

	public long pesquisarVeiculo(String codEstPesq) {
		long posicaoCursorArquivo = 0;

		try {
			RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw");
			while (true) {
				posicaoCursorArquivo = arqEst.getFilePointer();
				ativo = arqEst.readChar();
				codEst = arqEst.readUTF();
				placa = arqEst.readUTF();
				dataOperacao = arqEst.readUTF();
				tipoOperacao = arqEst.readChar();
				modeloCor = arqEst.readUTF();
				codMarca = arqEst.readUTF();
				categoriaVeiculo = arqEst.readUTF();
				horaEntrada = arqEst.readUTF();
				horaSaida = arqEst.readUTF();
				valorPago = arqEst.readFloat();

				if (codEstPesq.equals(codEst) && ativo == 'S') {
					arqEst.close();
					return posicaoCursorArquivo;
				}

			}
		} catch (EOFException e) {
			return -1;
		} catch (IOException e) {
			System.out.print("Erro na abertura do arquivo, o programa sera encerrado");
			System.exit(0);
			return -1;
		}

	}

	// ----------------------------------------------------------//

	public void salvarVeiculo() {
		try {
			RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw");
			arqEst.seek(arqEst.length());
			arqEst.writeChar(ativo);
			arqEst.writeUTF(codEst);
			arqEst.writeUTF(placa);
			arqEst.writeUTF(dataOperacao);
			arqEst.writeChar(tipoOperacao);
			arqEst.writeUTF(modeloCor);
			arqEst.writeUTF(codMarca);
			arqEst.writeUTF(categoriaVeiculo);
			arqEst.writeUTF(horaEntrada);
			arqEst.writeUTF(horaSaida);
			arqEst.writeFloat(valorPago);
			arqEst.close();
			System.out.println("Dados gravados com sucesso");
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo, o programa sera encerrado");
			System.exit(0);
		}
	}

	// ----------------------------------------------------------//

	public void desativarVeiculo(long posicao) {
		try {
			RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw");
			arqEst.seek(posicao);
			arqEst.writeChar('N');
			arqEst.close();
		} catch (IOException e) {
			System.out.print("Erro na abertura do arquivo, programa será encerrado");
			System.exit(0);
		}
	}

	// ----------------------------------------------------------//

	public void imprimirCabecalhoConsultar() {
		System.out.println("-PLACA- -------- -OP- --------  -MODELO- -------- "
				+ "-MARCA- -------- -CATEGORIA- -------- -DATA- -------- "
				+ "-HORA DA ENTRADA- -------- -HORA DA SAÍDA- -------- -VALOR PAGO- ");
	}

	// ----------------------------------------------------------//

	public void imprimirCabecalhoRelatorio() {
		// Linha separadora
		System.out.println("====================================================================================");
		// Nomes das colunas
		System.out.println(formatarString("PLACA", 10) + "  " + formatarString("MODELO E COR", 20) + "  "
				+ formatarString("DATA", 12) + "  " + formatarString("HORA ENTR", 7) + "  "
				+ formatarString("HORA SAIDA", 7) + "  " + formatarString("VLR. PAGO", 10));
		System.out.println("====================================================================================");
	}

	// ----------------------------------------------------------//

	public void imprimirConsultarVeiculos() {
		System.out.println(formatarString(placa, 17) + "  " + formatarString(Character.toString(tipoOperacao), 13)
				+ formatarString(modeloCor, 20) + formatarString(codMarca, 20) + formatarString(categoriaVeiculo, 15)
				+ formatarString(dataOperacao, 20) + "  " + formatarString(horaEntrada, 25)
				+ formatarString(horaSaida, 25) + formatarString(String.valueOf(valorPago), 15));
	}

	// ----------------------------------------------------------//

	public void imprimirVeiculo() {

		// Variaveis
		String valorFormatado;

		// Montar o valor pago no formato 999,99 (trocar ponto por virgula)
		valorFormatado = String.format("%.2f", valorPago).replace(".", ",");

		// Imprimir uma linha com todos os campos alinhados
		System.out.println(formatarString(placa, 10) + "  " + formatarString(modeloCor, 20) + "  "
				+ formatarString(dataOperacao, 12) + "  " + formatarString(horaEntrada, 7) + "  "
				+ formatarString(horaSaida, 7) + "  " + formatarString(valorFormatado, 10));
	}

	// ----------------------------------------------------------//

	public static String formatarString(String texto, int tamanho) {
		// retorna uma string com o numero de caracteres passado como parametro em
		// TAMANHO
		if (texto.length() > tamanho) {
			texto = texto.substring(0, tamanho);
		} else {
			while (texto.length() < tamanho) {
				texto = texto + " ";
			}
		}
		return texto;
	}

	// ************************* METODOS ************************* //

	public void registrarEntradaVeiculo() {
		String codEstChave = "000001";
		String placaPlaceHolder = "";
		int maiorCodEst = 0;
		byte VetMarcaLocal;
		char confirmacao;
		boolean veiculoSaiu = true;

		do {

			System.out.print("Deseja continuar (S/N) ? ");
			if (Main.leia.next().equalsIgnoreCase("N")) {
				break;
			}

			try {
				RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw");
				while (true) {
					ativo = arqEst.readChar();
					codEst = arqEst.readUTF();
					placa = arqEst.readUTF();
					dataOperacao = arqEst.readUTF();
					tipoOperacao = arqEst.readChar();
					modeloCor = arqEst.readUTF();
					codMarca = arqEst.readUTF();
					categoriaVeiculo = arqEst.readUTF();
					horaEntrada = arqEst.readUTF();
					horaSaida = arqEst.readUTF();
					valorPago = arqEst.readFloat();

					if (Integer.parseInt(codEst) > maiorCodEst && ativo == 'S') {
						maiorCodEst = Integer.parseInt(codEst);
					}

				}
			} catch (EOFException e) {
				if (maiorCodEst > 0) {
					maiorCodEst++;
					codEstChave = String.valueOf(maiorCodEst);
					while (codEstChave.length() < 6) {
						codEstChave = "0" + codEstChave;
					}
				}
			} catch (IOException e) {
				System.out.println("Erro na abertura do arquivo, o programa sera encerrado");
				System.exit(0);
			}

			Main.leia.nextLine();

			System.out.println("Código do estacionamento: " + codEstChave);

			do {
				do {
					System.out.print("Digite a placa do veiculo: ");
					placaPlaceHolder = Main.leia.next();
				} while (!verificarPlacaVeiculo(placaPlaceHolder));

				placaPlaceHolder = placaPlaceHolder.substring(0, 3).toUpperCase() + placaPlaceHolder.substring(3);

				try {
					RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw");
					while (true) {
						ativo = arqEst.readChar();
						codEst = arqEst.readUTF();
						placa = arqEst.readUTF();
						dataOperacao = arqEst.readUTF();
						tipoOperacao = arqEst.readChar();
						modeloCor = arqEst.readUTF();
						codMarca = arqEst.readUTF();
						categoriaVeiculo = arqEst.readUTF();
						horaEntrada = arqEst.readUTF();
						horaSaida = arqEst.readUTF();
						valorPago = arqEst.readFloat();
						if (placa.equals(placaPlaceHolder) && ativo == 'S' && tipoOperacao == 'E') {
							System.out.println("Este veiculo ainda não saiu");
							veiculoSaiu = false;
							break;
						}
					}
				} catch (EOFException e) {
					veiculoSaiu = true;
				} catch (IOException e) {
					System.out.println("Erro na abertura do arquivo, o programa sera encerrado");
					System.exit(0);
				}
			} while (!veiculoSaiu);

			ativo = 'S';
			codEst = codEstChave;
			placa = placaPlaceHolder;

			do {
				System.out.print("Digite a data da operação: ");
				dataOperacao = Main.leia.next();
			} while (!verificarDataOperacao(dataOperacao));

			tipoOperacao = 'E';

			Main.leia.nextLine();

			do {
				System.out.print("Digite o modelo e a cor do carro: ");
				modeloCor = Main.leia.nextLine();
				if (modeloCor.length() < 10) {
					System.out.println("Esse campo deve ter pelo menos 10 caracteres.");
				}
			} while (modeloCor.length() < 10);

			do {
				System.out.print("Digite a marca do veiculo: ");
				codMarca = Main.leia.next().toUpperCase();
				VetMarcaLocal = verificarCodigoDeVeiculo(codMarca);
			} while (VetMarcaLocal == -1);

			do {
				System.out.print("Digite a categoria do veiculo: ");
				categoriaVeiculo = Main.leia.next().toUpperCase();
				categoriaVeiculo = verificarCateoriaVeiculo(categoriaVeiculo);
			} while (categoriaVeiculo.equals("ERRO"));

			do {
				System.out.print("Digite a hora de entrada (HH/MM): ");
				horaEntrada = Main.leia.next();
			} while (!verificarHora(horaEntrada));

			horaSaida = "";
			valorPago = 0;

			do {
				System.out.print("Confirma o registro do veiculo ? (S/N) ");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao != 'S' && confirmacao != 'N') {
					System.out.println("Digite um valor valido");
				} else if (confirmacao == 'S') {
					salvarVeiculo();
				}
			} while (confirmacao != 'S' && confirmacao != 'N');

		} while (!codEst.equals("FIM"));
	}

	// ---------------------------------------------------------- //

	public void registrarSaidaVeiculos() {
		String codEstacionamento;
		char confirmacao;
		long posicaoRegistro = 0;
		boolean veiculoEntrou = false;

		do {
			do {
				System.out.print("Digite o codigo do estacionamento (FIM para terminar): ");
				codEstacionamento = Main.leia.next();
				if (codEstacionamento.equalsIgnoreCase("FIM")) {
					break;
				}

				try {
					RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw");
					while (true) {
						ativo = arqEst.readChar();
						codEst = arqEst.readUTF();
						placa = arqEst.readUTF();
						dataOperacao = arqEst.readUTF();
						tipoOperacao = arqEst.readChar();
						modeloCor = arqEst.readUTF();
						codMarca = arqEst.readUTF();
						categoriaVeiculo = arqEst.readUTF();
						horaEntrada = arqEst.readUTF();
						horaSaida = arqEst.readUTF();
						valorPago = arqEst.readFloat();
						if (codEst.equals(codEstacionamento) && ativo == 'S' && tipoOperacao == 'E') {
							veiculoEntrou = true;
							break;
						}
					}
				} catch (EOFException e) {
					System.out.println("Este veiculo ainda não entrou");
				} catch (IOException e) {
					System.out.println("Erro na abertura do arquivo, o programa sera encerrado");
					System.exit(0);
				}

			} while (!veiculoEntrou);

			System.out.println("Placa........: " + placa);
			System.out.println("Data.........: " + dataOperacao);
			System.out.println("Tipo operação: S");
			System.out.println("Modelo cor...: " + modeloCor);
			for (byte cont = 0; cont < Main.vetCodMarca.length - 1; cont++) {
				if (Main.vetCodMarca[cont].equals(codMarca)) {
					System.out.println("Marca........: " + Main.vetDescricaoMarca[cont]);
				}
			}
			String cat = "";
			switch (categoriaVeiculo) {
			case "GI":
				cat = "Grande Importado";
				break;
			case "PI":
				cat = "Pequeno Importado";
				break;
			case "GN":
				cat = "Grande Nacional";
				break;
			case "PN":
				cat = "Pequeno Importado";
				break;
			}
			System.out.println("Categoria....: " + cat);
			System.out.println("Hora entrada.: " + horaEntrada);
			System.out.println("Modelo cor...: " + modeloCor);

			ativo = 'S';
			tipoOperacao = 'S';

			if (codEstacionamento.equalsIgnoreCase("FIM")) {
				break;
			}

			do {
				do {
					System.out.print("Digite a hora da saida: ");
					horaSaida = Main.leia.next();
				} while (!verificarHora(horaSaida));
				if (horaSaida.compareTo(horaEntrada) < 0 || horaSaida.compareTo(horaEntrada) == 0) {
					System.out.println("A hora de saida deve ser maior que a de entrada");
				}
			} while (horaSaida.compareTo(horaEntrada) < 0 || horaSaida.compareTo(horaEntrada) == 0);

			valorPago = calcularPagamento(horaEntrada, horaSaida, categoriaVeiculo);

			do {
				System.out.print("O valor pago sera de R$" + valorPago + ", deseja confirmar ? (S/N)");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					desativarVeiculo(posicaoRegistro);
					salvarVeiculo();
				}
			} while (confirmacao != 'S' && confirmacao != 'N');

		} while (!codEstacionamento.equalsIgnoreCase("FIM"));

	}

	// ---------------------------------------------------------- //

	public void consultar() {
		byte opcao;
		String dataOP;

		do {
			do {
				System.out.println(" ***************  CONSULTA DE VEÍCULOS  ***************** ");
				System.out.println(" [1] EXIBIR TODOS OS REGISTROS DE VÉICULOS ");
				System.out.println(" [2] EXIBIR VÉICULOS QUE AINDA NÃO SAIRAM DO ESTACIONAMENTO ");
				System.out.println(" [3] EXIBIR REGISTROS DE VÉICULOS CADASTRADOS EM UMA DATA ");
				System.out.println(" [0] SAIR");
				System.out.print("\nDigite a opcao desejada: ");
				opcao = Main.leia.nextByte();
				if (opcao < 0 || opcao > 3) {
					System.out.println("opcao Invalida, digite novamente.\n");
				}
			} while (opcao < 0 || opcao > 3);

			switch (opcao) {
			case 0:
				System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
				break;

			case 1: // imprime todos os veiculos
				try (RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw")) {
					imprimirCabecalhoConsultar();
					while (true) {
						ativo = arqEst.readChar();
						codEst = arqEst.readUTF();
						placa = arqEst.readUTF();
						dataOperacao = arqEst.readUTF();
						tipoOperacao = arqEst.readChar();
						modeloCor = arqEst.readUTF();
						codMarca = arqEst.readUTF();
						categoriaVeiculo = arqEst.readUTF();
						horaEntrada = arqEst.readUTF();
						horaSaida = arqEst.readUTF();
						valorPago = arqEst.readFloat();
						if (ativo == 'S') { // caso seja imprimir todos os veiculos so remover o if
							imprimirConsultarVeiculos();
						}
					}
					// arqEst.close();
				} catch (EOFException e) {
					System.out.print("\n FIM DE RELATORIO - ENTER para continuar...\n");
					Main.leia.nextLine();
					Main.leia.nextLine();
				} catch (IOException e) {
					System.out.print("Erro na abertura do arquivo - programa sera finalizado");
					System.exit(0);
				}
				break;

			case 2: // imprime veiculos que ainda não sairam do estacionamento
				try (RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw")) {
					imprimirCabecalhoConsultar();
					while (true) {
						ativo = arqEst.readChar();
						codEst = arqEst.readUTF();
						placa = arqEst.readUTF();
						dataOperacao = arqEst.readUTF();
						tipoOperacao = arqEst.readChar();
						modeloCor = arqEst.readUTF();
						codMarca = arqEst.readUTF();
						categoriaVeiculo = arqEst.readUTF();
						horaEntrada = arqEst.readUTF();
						horaSaida = arqEst.readUTF();
						valorPago = arqEst.readFloat();
						if (tipoOperacao == 'E') {
							imprimirConsultarVeiculos();
						}
					}
				} catch (EOFException e) {
					System.out.print("\n FIM DE RELATORIO - ENTER para continuar...\n");
					Main.leia.nextLine();
					Main.leia.nextLine();
				} catch (IOException e) {
					System.out.print("Erro na abertura do arquivo - programa sera finalizado");
					System.exit(0);
				}
				break;

			case 3: // imprime veiculos cadastrados eum uma data
				System.out.print("Digite a data de operação desejada: ");
				Main.leia.nextLine();
				dataOP = Main.leia.nextLine();
				// if ( ! dataOP.equals(dataOperacao)) {
				// System.out.println("Data de operação não compatível! Escreva novamente.");
				// }

				try (RandomAccessFile arqEst = new RandomAccessFile("EST.DAT", "rw")) {
					imprimirCabecalhoConsultar();
					while (true) {
						ativo = arqEst.readChar();
						codEst = arqEst.readUTF();
						placa = arqEst.readUTF();
						dataOperacao = arqEst.readUTF();
						tipoOperacao = arqEst.readChar();
						modeloCor = arqEst.readUTF();
						codMarca = arqEst.readUTF();
						categoriaVeiculo = arqEst.readUTF();
						horaEntrada = arqEst.readUTF();
						horaSaida = arqEst.readUTF();
						valorPago = arqEst.readFloat();

						if (dataOP.equals(dataOperacao) && ativo == 'S') {
							imprimirConsultarVeiculos();
						}
					}
				} catch (EOFException e) {
					System.out.print("\n FIM DE RELATORIO - ENTER para continuar...\n");
					Main.leia.nextLine();
					Main.leia.nextLine();
				} catch (IOException e) {
					System.out.print("Erro na abertura do arquivo - programa sera finalizado");
					System.exit(0);
				}
				break;
			}

		} while (opcao != 0);
	}

	// ---------------------------------------------------------- //

	public void excluir() {
		String codigo;
		char confirmacao;
		long posicaoRegistro = 0;

		do {
			do {
				Main.leia.nextLine();
				System.out.println(" **  EXCLUSAO DE CARROS  ** ");
				System.out
						.print("Digite o codigo de estacionamento do carro que deseja excluir ( FIM para encerrar ): ");
				codigo = Main.leia.nextLine();
				if (codigo.equalsIgnoreCase("FIM")) {
					break;
				}

				posicaoRegistro = pesquisarVeiculo(codigo);
				if (posicaoRegistro == -1) {
					System.out.println("Codigo não encontrado no estacionamento!\n");
				}
			} while (posicaoRegistro == -1);

			if (codigo.equalsIgnoreCase("FIM")) {
				System.out.println("\n **  PROGRAMA ENCERRADO  ** \n");
				break;
			}

			System.out.println("\n ------ DADOS DO VEÍCULO ENCONTRADO ------ ");
			System.out.println("Código........: " + codEst);
			System.out.println("Placa.........: " + placa);
			System.out.println("Data operação.: " + dataOperacao);
			System.out.println("Modelo/Cor....: " + modeloCor);
			for (byte cont = 0; cont < Main.vetCodMarca.length - 1; cont++) {
				if (Main.vetCodMarca[cont].equals(codMarca)) {
					System.out.println("Marca........: " + Main.vetDescricaoMarca[cont]);
				}
			}
			String cat = "";
			switch (categoriaVeiculo) {
			case "GI":
				cat = "Grande Importado";
				break;
			case "PI":
				cat = "Pequeno Importado";
				break;
			case "GN":
				cat = "Grande Nacional";
				break;
			case "PN":
				cat = "Pequeno Importado";
				break;
			}
			System.out.println("Categoria....: " + cat);
			System.out.println("Hora entrada..: " + horaEntrada);
			System.out.println("Hora saída....: " + horaSaida);
			System.out.println("Valor pago....: R$ " + valorPago);
			System.out.println(" ----------------------------------------- ");

			do {
				System.out.print("\nConfirma a exclusao deste carro (S/N) ? ");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					desativarVeiculo(posicaoRegistro);
				}
			} while (confirmacao != 'S' && confirmacao != 'N');

		} while (codigo.equalsIgnoreCase("FIM"));
	}

	// ---------------------------------------------------------- //

	public void emitirRelatorio() {

		// Variavais
		char escolhaRelatorio;
		String placaEsp;
		float totalFaturado;
		boolean achouAlgum;
		boolean registroAtivo;
		boolean temSaida;
		boolean placaBate;
		String totalFormatado;
		RandomAccessFile arqEst; // declarei aq para usar no cacth mas n sei se pode

		// Iniciar variaveis
		placaEsp = "";
		totalFaturado = 0;
		achouAlgum = false;
		arqEst = null;

		// Perguntar qual tipo de relatorio o usuario quer
		do {
			System.out.println("\nQual relatorio deseja imprimir?");
			System.out.println("E - Especifico (apenas uma placa)");
			System.out.println("G - Geral (todos os veiculos)");
			System.out.print("Digite sua opcao: ");

			// Pegar so a primeira letra e converter para maiusculo
			escolhaRelatorio = Main.leia.next().toUpperCase().charAt(0);

			// Se nao digitou E nem G, avisar e repetir
			if (escolhaRelatorio != 'E' && escolhaRelatorio != 'G') {
				System.out.println("Opcao invalida! Digite E ou G.");
			}
		} while (escolhaRelatorio != 'E' && escolhaRelatorio != 'G');

		// Se escolheu especifico, pedir a placa
		if (escolhaRelatorio == 'E') {
			System.out.print("Digite a placa do veiculo: ");
			Main.leia.nextLine(); // limpar o buffer do teclado
			placaEsp = Main.leia.nextLine().toUpperCase(); // guardar a placa em maiusculo
		}

		// Abrir o arquivo e ler registro por registro
		try {
			arqEst = new RandomAccessFile("EST.DAT", "rw");

			// Imprimir o cabecalho antes de comecar a listar
			imprimirCabecalhoRelatorio();

			while (true) {
				// Ler todos os campos do registro atual do arquivo
				ativo = arqEst.readChar();
				codEst = arqEst.readUTF();
				placa = arqEst.readUTF();
				dataOperacao = arqEst.readUTF();
				tipoOperacao = arqEst.readChar();
				modeloCor = arqEst.readUTF();
				codMarca = arqEst.readUTF();
				categoriaVeiculo = arqEst.readUTF();
				horaEntrada = arqEst.readUTF();
				horaSaida = arqEst.readUTF();
				valorPago = arqEst.readFloat();

				// Verificar cada caso

				// Condicao 1: o registro precisa estar ativo
				registroAtivo = (ativo == 'S');

				// Condicao 2: so mostrar veiculos que ja tiveram saida registrada
				temSaida = (tipoOperacao == 'S');

				// Condicao 3: verificar se a placa bate com o filtro
				if (escolhaRelatorio == 'G') {
					placaBate = true; // geral: aceita todos
				} else {
					placaBate = placa.toUpperCase().equals(placaEsp); // especifico: compara a placa
				}

				// So imprime se passar nas 3 condicoes
				if (registroAtivo && temSaida && placaBate) {

					// Calcular o valor a pagar
					valorPago = calcularPagamento(horaEntrada, horaSaida, categoriaVeiculo); // mudei para o metodo do
					// Felipe

					// Somar no total geral
					totalFaturado = totalFaturado + valorPago;

					// Imprimir a linha deste veiculo
					imprimirVeiculo();

					// Marcar que pelo menos um registro foi impresso
					achouAlgum = true;
				}
			}

		} catch (EOFException e) {
			// fim

			System.out.println("====================================================================================");

			if (achouAlgum == false) {
				// Nao encontrou nenhum registro com os filtros especificados
				System.out.println("Nenhum registro encontrado.");
			} else {
				// Mostrar o total faturado final
				totalFormatado = String.format("%.2f", totalFaturado).replace(".", ","); // %.2f arruma virgula
				System.out.println(formatarString("TOTAL FATURADO: R$ " + totalFormatado, 84));
			}

			System.out.println("\n FIM DE RELATORIO - ENTER para continuar...");
			Main.leia.nextLine();
			Main.leia.nextLine();

		} catch (IOException e) {

			if (arqEst != null) {
				try {
					arqEst.close();
				} catch (IOException erroFechar) {
				}
			}
			// Erro
			System.out.println("Erro na abertura do arquivo - programa sera finalizado.");
			System.exit(0);
		}
	}

	// ************************* VALIDAÇÕES ************************* //

	public boolean verificarPlacaVeiculo(String placaVeiculo) {

		if (tipoOperacao == 'E' && ativo == 'S' && placaVeiculo.equals(placa)) {
			System.out.println("Este veiculo ainda não saiu");
			Main.leia.nextLine();
			return false;
		}

		if (placaVeiculo.length() != 7) {

			System.out.println("A placa deve ter 7 caracteres!");
			return false;
		}

		for (byte cont = 0; cont < 3; cont++) {
			if (!Character.isLetter(placaVeiculo.charAt(cont))) {
				System.out.println("Os três primeiros caracteres devem ser letras");
				return false;
			}
		}

		try {
			Integer.parseInt(placaVeiculo.substring(3));
		} catch (NumberFormatException e) {
			System.out.println("Os quatro ultimos caracteres devem ser números!");
			return false;
		}

		return true;
	}

	// ---------------------------------------------------------- //

	public boolean verificarDataOperacao(String data) {

		if (data.length() != 10) {
			System.out.println("A data deve ter 10 caracteres!");
			return false;
		}

		if (data.charAt(2) != '/' || data.charAt(5) != '/') {
			System.out.println("O tereceiro e sexto caracteres devem ser / !");
			return false;
		}

		try {
			if (Integer.parseInt(data.substring(0, 2)) > 31 || Integer.parseInt(data.substring(0, 2)) < 1) {
				System.out.println("Digite um dia valido!");
				return false;
			}
			if (Integer.parseInt(data.substring(3, 5)) > 12 || Integer.parseInt(data.substring(3, 5)) < 1) {
				System.out.println("Digite um mês valido!");
				return false;
			}
			Integer.parseInt(data.substring(7));
		} catch (NumberFormatException e) {
			System.out.println("As datas devem ser números!");
			return false;
		}

		return true;
	}

	// -----------------------------------------------------------//

	public byte verificarCodigoDeVeiculo(String codigo) {

		for (byte cont = 0; cont < (Main.vetCodMarca.length - 1); cont++) {
			if (codigo.equalsIgnoreCase(Main.vetCodMarca[cont])) {
				return cont;
			}
		}

		System.out.println("Marca não encontrada, tente novamente");
		return -1;

	}

	// ---------------------------------------------------------- //

	public String verificarCateoriaVeiculo(String catVeiculo) {

		if (catVeiculo.equalsIgnoreCase("GI")) {
			return "GI";
		} else if (catVeiculo.equalsIgnoreCase("PI")) {
			return "PI";
		} else if (catVeiculo.equalsIgnoreCase("GN")) {
			return "GN";
		} else if (catVeiculo.equalsIgnoreCase("PN")) {
			return "PN";
		}

		System.out.println("ERRO");
		return "ERRO";
	}

	// ---------------------------------------------------------- //

	public boolean verificarHora(String horario) {
		byte horas = 0;
		byte minutos = 0;
		if (horario.length() != 5) {
			System.out.println("A hora deve ter 5 caracteres");
			return false;
		}
		if (!horario.substring(2, 3).equals(":")) {
			System.out.println("A hora deve ter uma :");
			return false;
		}
		try {
			horas = Byte.parseByte(horario.substring(0, 2));
			minutos = Byte.parseByte(horario.substring(3));
		} catch (NumberFormatException e) {
			System.out.println("Use caracteres validos");
			return false;
		}
		if (horas < 0 || horas > 24) {
			System.out.println("Digite um horario valido");
			return false;
		}
		if (minutos < 0 || minutos > 59) {
			System.out.println("Digite um horario valido");
			return false;
		}

		return true;
	}

	// ************************* CALCULO ************************* //

	public float calcularPagamento(String horaEntrada, String horaSaida, String catVeiculo) {

		byte horaE = Byte.parseByte(horaEntrada.substring(0, 2));
		byte horaS = Byte.parseByte(horaSaida.substring(0, 2));
		int minE = Integer.parseInt(horaEntrada.substring(3));
		int minS = Integer.parseInt(horaSaida.substring(3));
		float tempo = (horaS - horaE) + ((minS - minE) / 60);

		switch (catVeiculo) {
		case "GI":
			if (horaE <= 18) {
				;
				return tempo * 10;
			}

			return tempo * 8;

		case "PI":
			if (horaE <= 18) {
				;
				return (float) (tempo * 8.2);
			}

			return (float) (tempo * 6.5);

		case "GN":
			if (horaE <= 18) {
				;
				return tempo * 9;
			}

			return (float) (tempo * 7.5);

		case "PN":
			if (horaE <= 18) {
				;
				return tempo * 7;
			}

			return tempo * 6;
		}

		return 1;
	}
}
