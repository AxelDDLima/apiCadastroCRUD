package exception;

public class ArquivoException extends RuntimeException{
	
	public ArquivoException(String mensagem) {
		super(mensagem);
	}
	
	public ArquivoException(String mensagem, Throwable exception) {
		super(mensagem, exception);
	}
}
