package it.polito.tdp.borders;

import java.util.Comparator;


public class ComparatoreNumeri implements Comparator {
	public int compare (Object o1, Object o2) {
		Integer a1=(Integer) o1;
		Integer a2 =(Integer) o2;
		if(a1>a2){
			return -1;
		} if(a1<a2) {
		return 1;
	}
		return 0;

}


}
