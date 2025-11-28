package modelo;
import java.util.ArrayList;
import java.util.List;

//CLASSE ABSTRATA!!! ela é um molde para os Jogadores- todo participante tem um nome e uma mao(lista de Pecas)
public abstract class Participante implements AcaoJogo {
	protected String nome;
	//LIST/ARRAYLIST!!! - Usamos uma List e ArrayList par criar a mao do Participante
	protected List<Peca> mao;
	
	//Construtor geral
	public Participante(String nome) {
		this.nome = nome;
		this.mao = new ArrayList<>();
	}
	
	//ENCAPSULAMENTO!!!
	//gets
	//Não queremos permitir o set(possibilidade de alterar mao)
	public List<Peca> getMao(){
		return mao;
	}
	public String getNome() {
		return nome;
	}
	public void adicionarPeca(Peca p) {
		this.mao.add(p);
	}
	//METODO ABSTRATO!!! - quem herda vai ter que ter uma logida de decisão de pecas
	public abstract Peca escolhePeca();
	
	@Override
	public String toString() {
		return "Jogador: " + nome + " suas peças são: "+ mao.size();
	}
	
}


