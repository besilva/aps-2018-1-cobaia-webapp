package cobaia;

import cobaia.REST.CurrencyAPI;
import cobaia.modelo.Area;
import cobaia.modelo.Usuario;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Area a = new Area("teste");
		Usuario u = new Usuario("tdsadsdadsadsa", "email@email.com", "Senha@1");
		System.out.println("Fim");
		CurrencyAPI cAPI = new CurrencyAPI();
		
	}

}
