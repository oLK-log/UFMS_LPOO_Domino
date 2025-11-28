package excecoes;
/*Pelo código inteiro usando a classe JogadaInvalidaException
usamos tanto para erro de jogo(jogadaInvalida) quanto para erros genéricos.
Usamos também para fazer o usuário digitar um valor numerico
*/

public class JogadaInvalidaException extends Exception{
	public JogadaInvalidaException(String mensagem) {
		super(mensagem);
	}
}


