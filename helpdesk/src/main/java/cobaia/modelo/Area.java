package cobaia.modelo;

import java.util.Map;

import com.br.validator.Negocio.Tester;
import com.br.validator.annotations.Length;
import com.br.validator.annotations.NotEmpty;

import com.br.validator.annotations.*;

public class Area extends AbstractModel{
	
	private Integer id;
	@NotEmpty()
	@Length(max = 100 , min = 5)
	private String nome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public Area(String nome) {
		super();
		this.nome = nome;
		this.validar();
	}
	@Override
	public void validar() {
		// TODO Auto-generated method stub
		Tester t = new Tester();
		t.validate(this);
		
	}

    @Override
    public void delete() {

    }

    @Override
	public Map<String, String> getErros() {
		return super.getErros();
	}

	@Override
	protected void doSave() {

	}
}
