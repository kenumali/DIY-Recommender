
public class MinutesFilter implements Filter {
	
	private int minMinutes;
	private int maxMinutes;
	
	public MinutesFilter(int minMins, int maxMins) {
		minMinutes = minMins;
		maxMinutes = maxMins;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes; 
	}

}
