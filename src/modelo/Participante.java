package modelo;
import java.util.ArrayList;
import java.util.List;


public abstract class Participante implements AcaoJogo {
	protected String nome;
	protected List<Peca> mao;
	
	//Construtor geral
	public Participante(String nome) {
		this.nome = nome;
		this.mao = new ArrayList<>();
	}
	
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
	//metodos abstrato
	public abstract Peca escolhePeca();
	
	@Override
	public String toString() {
		return "Jogador: " + nome + " suas peças são: "+ mao.size();
	}
	
}
