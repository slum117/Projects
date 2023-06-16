package com.mycompany.a3;

public interface ICollection {
	// adds a new element to the collection
	public void add(Object newObject);
	// returns an iterator
	public IIterator getIterator();
}
