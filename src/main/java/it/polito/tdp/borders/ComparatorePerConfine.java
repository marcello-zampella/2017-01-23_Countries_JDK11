package it.polito.tdp.borders;

import java.util.Comparator;

import it.polito.tdp.borders.model.Confine;

public class ComparatorePerConfine implements Comparator {
	public int compare (Object o1, Object o2) {
		Confine a1=(Confine) o1;
		Confine a2 =(Confine) o2;
		if(a1.getConfinanti()>(a2.getConfinanti())){
			return -1;
		} if(a1.getConfinanti()<(a2.getConfinanti())) {
		return 1;
	}
		return 0;

}


}
