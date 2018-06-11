package cobaia.viewHelper;
import java.util.HashMap;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
public class MyPebbleExtensions  extends AbstractExtension {

	
	private Map<String, Filter> filters =
			new HashMap<>();

	public MyPebbleExtensions() {
		filters.put("conversaoMoeda", new CurrencyConverterFilter());
	}
	
	@Override
	public Map<String, Filter> getFilters() {		
		return this.filters;
	}
}
