package modelo;
import java.util.Scanner;
import excecoes.JogadaInvalidaException;

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
			throw new JogadaInvalidaException(" A peca "+peca+"nao encaixa no lado "+ lado+"!!!");
		}
	}
}
