package main;
import logica.ControleJogo;

public class Main {
	public static void main(String[] args) {
		ControleJogo jogo = new ControleJogo();
		//criando pecas, jogadores e embaralhando
		jogo.configurarJogo();
		//iniciando partida
		jogo.iniciarPartida();
	}
}
