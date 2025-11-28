package modelo;
import java.util.ArrayList;
import java.util.List;

public class Mesa {
	private List<Peca> pecasNaMesa;
	private int pontaEsquerda;
	private int pontaDireita;
	public Mesa() {
		this.pecasNaMesa = new ArrayList<>();
		this.pontaEsquerda = -1;
		this.pontaDireita = -1;
		//aqui usamos o -1 para indicar que a mesa está vazia, ja que as pecas iniciam e 0
	}
		//gets
	public int getPontaEsquerda() {
		return pontaEsquerda;
	}
	public int getPontaDireita() {
		return pontaDireita;
	}
	//methods
	public boolean estaVazia() {
		//met para verificar se a mesa esta vazia
		return pecasNaMesa.isEmpty();
	}
	
	public boolean jogarPeca(Peca p, char lado) {
		/*esse metodo realiza uma jogada
		se a mesa vazia, recebe a peca
		se nao, tenta encaixar(um lado de cada vez)
		*/
		if(estaVazia()) {
			pecasNaMesa.add(p);
			pontaEsquerda = p.getLado1();
			pontaDireita = p.getLado2();
			return true;
		}
		
		if (lado == 'E' || lado == 'e') {
            if (p.getLado2() == pontaEsquerda) {
                pontaEsquerda = p.getLado1();
                pecasNaMesa.add(0, p);
                return true;
            } 
            else if (p.getLado1() == pontaEsquerda) {
                pontaEsquerda = p.getLado2();
                pecasNaMesa.add(0, new Peca(p.getLado2(), p.getLado1())); 
                return true;
            }
        }
        
        if (lado == 'D' || lado == 'd') {
            if (p.getLado1() == pontaDireita) {
                pontaDireita = p.getLado2();
                pecasNaMesa.add(p);
                return true;
            } 

            else if (p.getLado2() == pontaDireita) {
                pontaDireita = p.getLado1();
                pecasNaMesa.add(new Peca(p.getLado2(), p.getLado1()));
                return true;
            }
        }
		
		/*
		if(lado == 'E' || lado == 'e') {
			//E/e == esquerdo
			if(p.getLado1() == pontaEsquerda) {
				pontaEsquerda = p.getLado1();
				pecasNaMesa.add(0, p);
				return true;
			}else if(p.getLado2() == pontaEsquerda) {
				pontaEsquerda = p.getLado1();
				pecasNaMesa.add(0, p);
				return true;
			}
		}
		if(lado == 'D' || lado == 'd') {
			//D/d == diretita
			if(p.getLado1() == pontaDireita) {
				pontaDireita = p.getLado1();
				pecasNaMesa.add(0, p);
				return true;
			}else if(p.getLado2() == pontaDireita) {
				pontaDireita = p.getLado1();
				pecasNaMesa.add(0, p);
				return true;
			}
		}
		
		*/
		return false;
	}
	
	
	@Override
	public String toString() {
		if(estaVazia()) {
			return "Mesa está vazia";
		}
		
		StringBuilder stringB = new StringBuilder();
		stringB.append("Esquerda ("+pontaEsquerda+") ");
		for(Peca p:pecasNaMesa) {
			stringB.append(p.toString());
		}
		stringB.append(" ("+pontaDireita+") Direita");
		return stringB.toString();
	}
	/*StringBuilder
	 * eh um construtor de Strings mutável
	 * ajuda a reutilizar o texto, no lugar de criar varios(e ocupar memoria)
	 */
	
}


