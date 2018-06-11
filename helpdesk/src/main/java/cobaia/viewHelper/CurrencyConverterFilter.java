package cobaia.viewHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;

import cobaia.REST.CurrencyAPI;

public class CurrencyConverterFilter implements Filter {
	CurrencyAPI api = new CurrencyAPI();
	@Override
	public List<String> getArgumentNames() {
		// TODO Auto-generated method stub
		List<String> arguments = new ArrayList();
		arguments.add("actualCurrency");
		arguments.add("target");
		return arguments;
	}

	@Override
	public Object apply(Object o, Map<String, Object> args) {
		// TODO Auto-generated method stub
		String actualCurrency =(String) args.get(getArgumentNames().get(0));
		String targetCurrency =(String) args.get(getArgumentNames().get(1));
		double valor = Double.parseDouble(o.toString());
		valor = valor/api.getCurrencyDifference(actualCurrency);
		valor*= api.getCurrencyDifference(targetCurrency);
		System.out.println(actualCurrency + " para "+targetCurrency +":"+ valor);
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(valor);
	}

}
