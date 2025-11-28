package modelo;

//ORDENACAO USANDO COMPARABLE!! - em Peca implementamos Comparable para usar o Colections Sort para ordenar as peças
public class Peca implements Comparable<Peca> {
	//Prof, aqui usamos encapsulamento
	private int lado1;
	private int lado2;
	
	public Peca(int lado1, int lado2) {
		this.lado1 = lado1;
		this.lado2 = lado2;
	}
	
	//gets
	//aqui também faz parte do encapsulamento
	public int getLado1() {
		return lado1;
	}
	public int getLado2() {
		return lado2;
	}
	//nao fazemos set pois n vamos precisar setar, aoenas na criacao
	
	@Override
	public String toString() {
		return "["+ lado1 + "-"+ lado2+"]";
	}

	@Override
	public int compareTo(Peca outraPeca) {
		// TODO Auto-generated method stub
		//aqui é apenas para uma organizacao das pecas
		int valorTotal = this.lado1 + this.lado2;
		int valorTotalOutro = outraPeca.lado1 + outraPeca.lado2;
		
		return Integer.compare(valorTotal,  valorTotalOutro);
		//<0 se menor, =0 se igual e >0 se maior
	}
}


