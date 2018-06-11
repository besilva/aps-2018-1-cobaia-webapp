package cobaia.modelo;

import java.util.UUID;



import com.br.validator.annotations.Validate;
import com.br.validator.Negocio.Tester;
import com.br.validator.annotations.Length;
import com.br.validator.annotations.NotEmpty;
import com.br.validator.annotations.PasswordTest;
import com.br.validator.annotations.TypeEnum;

public class Usuario extends AbstractModel {
	public enum Status {
		REGISTRADO, ATIVADO;
	}
	private UsuarioDAO dao;
	@Length(min=10, max=100)
	@NotEmpty()
	private String nome;
	@Validate(type=TypeEnum.EMAIL)
	private String email;
	@Length(min=5, max=10)
	@PasswordTest(needsNumber=true, needsUpperCase=true, needsNonAlphanumeric=true)
	private String senha;
	private String token;
	private Status status;

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.token =  UUID.randomUUID().toString().split("-")[0];;
		this.status = Status.REGISTRADO;
        this.dao = new UsuarioDAO();
        this.validar();
	}

    public Usuario() {

    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public void validar(String senha2) {
		validaSenhas(senha, senha2);
		validar();
	}
	@Override
	public void validar() {
		Tester t = new Tester();
		t.validate(this);
		

	}

	@Override
	protected void doSave() {
	    if (this.getId() != null) dao.update(this);
        else dao.create(this);
	}

    @Override
    public void delete() {
        dao.delete(this.getId());
    }
}
