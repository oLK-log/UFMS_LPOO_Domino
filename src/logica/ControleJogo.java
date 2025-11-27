package logica;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.*;
import excecoes.JogadaInvalidaException;

public class ControleJogo {
	private List<Participante> jogadores;
	private List<Peca> monte;
	private Mesa mesa;
	
	public ControleJogo() {
		this.jogadores = new ArrayList<>();
		this.monte = new ArrayList<>();
		this.mesa = new Mesa();
	}
	
	public void configurarJogo() {
		System.out.println("----------------------------------------------------------------------------------");
        System.out.println("------------------------------Seja bem Vindo ao Dominó----------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        
        //criando jogadores
        jogadores.add(new Humano("Jogador 1"));
        jogadores.add(new Humano("Jogador 2"));
        System.out.println("                                 Jogadores Criados!                               ");
        
        //criando pecas
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
		//a partida vai iniciar pelo jogador 1
		boolean jogoAcabou = false;
		int indiceJogadorAtual = 0;
		
		while(!jogoAcabou) {
			Participante jogadorAtual = jogadores.get(indiceJogadorAtual);
			System.out.println("\n==================================================================================");
			System.out.println("Mesa: "+ mesa);
			System.out.println("\n==================================================================================");
			System.out.println("Vez do: " + jogadorAtual.getNome());
			System.out.println("Suas peças:[lado1-Lado2] " + jogadorAtual.getMao());
			boolean jogou = false;
			
			for(int i=0; i<jogadorAtual.getMao().size(); i++) {
				Peca pecaTentaJogar = jogadorAtual.getMao().get(i);
				try {
					System.out.println("Tentando jogar a peca: "+ pecaTentaJogar);
					jogadorAtual.jogarPeca(mesa, pecaTentaJogar);
					System.out.println("Jogada Feita! usou a peça "+pecaTentaJogar);
					jogou=true;
					break;
				} catch(JogadaInvalidaException e) {
					System.out.println("Erro crítico: "+ e.getMessage());
				} catch(Exception e) {
                    System.out.println("Erro genérico: " + e.getMessage());
                }
			}
			
			if(!jogou) {
				System.out.println("Jogador "+jogadorAtual.getNome() + " passou a vez");
			}
			
			if(jogadorAtual.getMao().isEmpty()) {
				System.out.println("Jogador "+ jogadorAtual.getNome()+" Venceu!!");
				jogoAcabou = true;
			} else {
				indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();
			}
		}
		System.out.println("Fim do Jogo!");
	}
}
