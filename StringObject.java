
/**
 * 
 * @author saulo
 *
 */
public class StringObject extends CounterObject {

	private String string;
	
	public StringObject(String aValue) {
		this.string = aValue;
		this.addOccurrence();
	}
	
	@Override
	public boolean lessThan(CounterObject other) {
		// TODO Auto-generated method stub
		return this.string.compareToIgnoreCase((String)other.getValue()) < 0;
	}

	@Override
	public boolean greaterThan(CounterObject other) {
		// TODO Auto-generated method stub
		return  this.string.compareToIgnoreCase((String)other.getValue()) > 0;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.string;
	}

}
