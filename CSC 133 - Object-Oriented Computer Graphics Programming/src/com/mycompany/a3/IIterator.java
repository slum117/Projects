package com.mycompany.a3;

public interface IIterator {
	// used to display if the next object exists
	// if it does then is returned with getNext
	public boolean hasNext();
	public Object getNext();
}
