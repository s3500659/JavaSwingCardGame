package controller.playagain;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import model.Player;

public class CompareValues implements Comparator<Map.Entry<Player, Integer>> {



	@Override
	public int compare(Entry<Player, Integer> o1, Entry<Player, Integer> o2) {
		
		return (o2.getValue()).compareTo(o1.getValue()); 
	}

}
