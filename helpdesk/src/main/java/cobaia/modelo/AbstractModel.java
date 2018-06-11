package cobaia.modelo;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractModel {
	private Map<String, String> erros = new HashMap();
	private Integer id;

	public Map<String, String> getErros() {
		return erros;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	
	protected void resetErros() {
		this.erros = new HashMap();
	}
	protected void addErro(String campo, String mensagem) {
		erros.put(campo, mensagem);
	}
	
	public abstract void validar();
	
	public boolean isValido() {
		return erros.isEmpty();
	}
	public void validaLength(String campo, String valor, int min, int max) {
		if (valor.length() < min || valor.length() > max) {
			addErro("erros", campo + " deve ter entre 3 e 50 caracteres");
		}
	}
	
	public void validaEmail(String email ) {
		if (!email.matches("[\\w._]+@\\w+(\\.\\w+)+")) {
			addErro("erros", "E-mail inválido, ele deve ter o formato de usuario@provedor");

		}
	}
	public void validaSenhas(String senha1, String senha2) {
		if (!senha1.equals(senha2)) {
			addErro("erros", "As senhas não conferem");
			
		}
	}

	public void save(){
		if(isValido()){
			doSave();
		}else{
			System.out.println("Usuario invalido");
		}
	}
	public  abstract void delete();
	protected abstract void doSave();
}
