package modelo;
import java.util.Scanner;
import excecoes.JogadaInvalidaException;

//HERANÇA!! Humano vai herdar de PArticipante(recebendo os atributos nome e mao e tendo que implementar os metodos)
public class Humano extends Participante{
	private static Scanner input = new Scanner(System.in);
	
	public Humano(String nome) {
		super(nome);
	}
	
	@Override
	public Peca escolhePeca() {
		//alterar depois
		return null;
	}
	
	
	//POLIMORFISMO!!! implementa o metodo para o Humano( é polimorfico pq poderia ser implementado para o bot)
	@Override
	public void jogarPeca(Mesa mesa, Peca peca) throws JogadaInvalidaException {
		if(!mao.contains(peca)) {
			throw new JogadaInvalidaException("Voce não possui essa peça " + peca + "! ");
		}
		
		System.out.println("Você deseja jogar na Esquerda('E') ou Direita('D')?");
		char lado = input.next().charAt(0);
		boolean funcionou = mesa.jogarPeca(peca, lado);
		if(funcionou) {
			mao.remove(peca);
		}else {
			//TRATAMENTO DE ERRO!!! - instanciamos a classe que herda de Exception para erros do JOgo
			throw new JogadaInvalidaException(" A peca "+peca+"nao encaixa no lado "+ lado+"!!!");
		}
	}
}


