package trs.someluigi.slperiph.helper;

public class CCCHelper {
	
	public static Integer getInt(Object param) throws Exception {
		//throw new NumberFormatException("? " + param.getClass().getName());
		//System.out.println(param.getClass().getName());
		
		if (param instanceof Integer) {
			return (Integer) param;
		}
		if (param instanceof Double) {
			return ((Double) param).intValue();
		}
		if (param instanceof Long) {
			return ((Long) param).intValue();
		}
		if (param instanceof Float) {
			return ((Float) param).intValue();
		}
		
		throw new NumberFormatException("? " + param.getClass().getName());
		
		//return -1;
	}
	
}
