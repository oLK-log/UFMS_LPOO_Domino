package logica;
import java.util.Scanner;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.*;
import excecoes.JogadaInvalidaException;

public class ControleJogo {
	private List<Participante> jogadores;
	private List<Peca> monte;
	private Mesa mesa;
	private Scanner input;
	
	public ControleJogo() {
		this.jogadores = new ArrayList<>();
		this.monte = new ArrayList<>();
		this.mesa = new Mesa();
		this.input = new Scanner(System.in);
	}
	
	public void configurarJogo() {
		System.out.println("----------------------------------------------------------------------------------");
        System.out.println("------------------------------Seja bem Vindo ao Dominó----------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        
        //criando jogadores
        System.out.print("Nome do Jogador 1:");
        String nome1 = lerNome();
        jogadores.add(new Humano(nome1));
        System.out.print("Nome do Jogador 2:");
        String nome2 = lerNome();
        jogadores.add(new Humano(nome2));
        
        System.out.println("                                 Jogadores Criados!                               ");
        
        //criando pecas
        //teoricamente utilizamos Coleções(em add, lista...)
        for(int i=0; i<=6; i++) {
            for(int j=i; j<=6; j++) {
                monte.add(new Peca(i,j));
            }
        }
        System.out.println("                                  Peças criadas!                                  ");
        
        //embaralhar
  		Collections.shuffle(monte);
  		System.out.println("Embaralhando Peças...aguarde!");
  		
  		//Distribuir as pecas e ordena mao
  		//Como ja embaralhamos, então podemos distribuir em ordem(que na vdd n esta ordenado);
  		System.out.println("Distribuindo as Peças...aguarde!");
  		for(Participante p: jogadores) {
  			for(int i=0; i<7; i++) {
  				if(!monte.isEmpty()) {
  					p.adicionarPeca(monte.remove(0));
  				}
  			}
  			Collections.sort(p.getMao());
  		}
  		System.out.println("---------------------------------Iniciando o Jogo!--------------------------------");
	}
	
	public void iniciarPartida() {
		//Partida
		System.out.println("---------------------------------Iniciando partida!-------------------------------");
		//a partida vai iniciar pelo jogador com a bucha
		boolean jogoAcabou = false;
		int indiceJogadorAtual = definePrimeiraJogada();
		
		while(!jogoAcabou) {
			Participante jogadorAtual = jogadores.get(indiceJogadorAtual);
			System.out.println("\n==================================================================================");
			System.out.println("Mesa: "+ mesa);
			System.out.println("\n==================================================================================");
			System.out.println("Vez do: " + jogadorAtual.getNome());
			mostrarMao(jogadorAtual);
			menuEscolha();
			System.out.println("Digite a opção escolhida:");
			int op = lerOpcao();
			
			switch(op) {
			//comprar
			case 1:
				if(!monte.isEmpty()) {
					Peca nova = monte.remove(0);
					jogadorAtual.adicionarPeca(nova);
					System.out.println("Você comprou a peça "+ nova);
				}else {
					System.out.println("Não foi possível comprar Peças- Monte vazio");
				}
				break;
			
			//jogar
			case 2:
				System.out.println("Digite o número da peça que deseja usar:");
				int indicePeca = lerOpcao();
				
				if(indicePeca >= 0 && indicePeca < jogadorAtual.getMao().size()) {
					Peca pecaEscolhida = jogadorAtual.getMao().get(indicePeca);
					try {
						//Polimorfismo(usei o met jogarPeca)
						jogadorAtual.jogarPeca(mesa, pecaEscolhida);
						System.out.println("Jogada Feita!");
						
						if(jogadorAtual.getMao().isEmpty()) {
							System.out.println("O jogador "+ jogadorAtual.getNome()+" venceu!!!");
							jogoAcabou = true;
						} else {
							indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();
						}
						//TRATAMENTO DE ERROS!!1 
					} catch(JogadaInvalidaException e) {
						//capturas de erros específicos
						System.out.println("Jogada Inválida - "+ e.getMessage());
					} catch(Exception e) {
						//captura de erros genéricos
						System.out.println("Erro: "+ e.getMessage());
					}
				}
					break;
			
			//Passar a vez
			case 3:
				if(monte.isEmpty()) {
					System.out.println(jogadorAtual.getNome()+" passou a vez.");
					indiceJogadorAtual = (indiceJogadorAtual +1) % jogadores.size();
				} else {
					System.out.println("Ainda tem peças no monte! Compre ou Jogue antes de passar...");
				}
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			
			}
		}
		System.out.println("Fim do Jogo!");
	}
	
	private void mostrarMao(Participante p) {
		List<Peca> mao = p.getMao();
		System.out.println("Suas pecas");
		
		for(Peca peca:mao) {
			System.out.print(peca + "  "); 
		}
		System.out.println();
		for(int i=0; i<mao.size(); i++) {
			System.out.print("  " + i + "    "); 
		}
		System.out.println("\n");
		
	}
	
	private void menuEscolha() {
		System.out.println("Escolha uma opção:");
		System.out.println("[1] - Comprar Peça");
		System.out.println("[2] - Jogar Peça");
		System.out.println("[3] - Passar a vez");
	}
	
	//aqui da para implementar um metodo genérica
	//ideia: receber qualquer valor(String ou int)
	/*private int lerOpcao() {
		while(true) {
			try {
				String linha = input.nextLine();
				return Integer.parseInt(linha);
			} catch(NumberFormatException e) {
				System.out.println("Entrada inválida! Digite um valor numérico:");
			}
		}
	}*/ 
	//Esse metodo vai poder ser usando tanto para ler o opcao(int), quanto para ler o nome do usuario
	private <T> T lerGenerico(String mensagemErro, Function<String, T> conversor){
		while(true) {
			try {
				String linha = input.nextLine();
				return conversor.apply(linha);
			} catch(Exception e) {
				System.out.println(mensagemErro+"Digite um valor Numérico!");
			}
		}
	}
	//usando o método generico para ler a opcao e o nome do jogador
	private int lerOpcao() {
		return lerGenerico("Entrada inválida! Digite um valor numérico...", Integer::parseInt);
	}
	private String lerNome() {
		return lerGenerico("Erro na leitura!", str -> str);
	}
	
	private int definePrimeiraJogada() {
		for(int bucha = 6; bucha>= 0; bucha --) {
			for(int indiceJogador = 0; indiceJogador < jogadores.size(); indiceJogador++) {
				Participante p = jogadores.get(indiceJogador);
				
				for (Peca peca : p.getMao()) {
					if (peca.getLado1() == bucha && peca.getLado2() == bucha) {
						System.out.println(p.getNome() + " que tem a peça ["+peca.getLado1()+"-"+peca.getLado2()+"] começa.");
						mesa.jogarPeca(peca,  'E');
						p.getMao().remove(peca);
						System.out.println(peca+" jogada na mesa!");
						return (indiceJogador+1) % jogadores.size();
					}
				}
			}
		}
		System.out.println("Ninguém tem a bucha, o jogador 1 começa!");
		return 0;
	}
}

