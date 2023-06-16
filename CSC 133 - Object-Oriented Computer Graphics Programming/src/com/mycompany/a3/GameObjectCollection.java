package com.mycompany.a3;
import java.util.*;
public class GameObjectCollection implements ICollection {
	
	private ArrayList<GameObject> collectionOfObjects;
	
	public GameObjectCollection()
	{
		collectionOfObjects = new ArrayList<GameObject>();
	}
	public void add(Object newObject)
	{
		collectionOfObjects.add((GameObject)newObject); //test if able to cast properly
	}
	public IIterator getIterator()
	{
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator
	{
		private int indexElement;
		public GameObjectIterator()
		{
			indexElement = -1;
		}
		// check if there is another element
		public boolean hasNext()
		{
			if(collectionOfObjects.size() <= 0) // collection contains no elements
				return false;
			if(indexElement == collectionOfObjects.size()-1) // index is at the last element
				return false;
			return true;
		}
		// moves the index 1 up and gets that object
		public Object getNext()
		{
			indexElement++;
			return collectionOfObjects.get(indexElement);
		}
	}
}
