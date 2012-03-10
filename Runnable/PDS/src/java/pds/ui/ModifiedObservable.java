package pds.ui;

import java.util.Observable;

public class ModifiedObservable extends Observable{
	
	public void setChanged(){
		super.setChanged();
		
	}
	
	public void clearChanged(){
		super.clearChanged();
	}
	
	

}
