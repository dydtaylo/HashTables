package FinalExamPractice;

import java.util.ArrayList;
import java.util.Random;

public class ClosedHashTable {
	private ArrayList<KVPair> map;
	private int maxSize;
	
	public ClosedHashTable(int initSize) {
		map = new ArrayList<KVPair>();
		maxSize = initSize;
		for(int i = 0; i < maxSize; i++)
			map.add(null);
	}
	
	public boolean put(int key, String value) {
		if(map.get(hash(key)) == null)
			map.set(hash(key), new KVPair(key, value));
		else {
			int index = 0;
			int origHash = hash(key);
			boolean maxCapacityReached = false;
			while(map.get(origHash + index) != null && !maxCapacityReached) {
				index++;
				if(origHash + index >= maxSize)
					index -= (origHash + index);
				if(origHash + index == origHash)
					maxCapacityReached = true;
			}
			if(maxCapacityReached)
				return false;
			else
				map.set(origHash + index , new KVPair(key, value));
		}
		
		return true;
	}
	
	public String get(int key) {
		if(map.get(hash(key)).getKey() == key)
			return map.get(hash(key)).getValue();
		else {
			int index = 0;
			int origHash = hash(key);
			boolean maxCapacityReached = false;
			while((map.get(origHash + index) != null && !maxCapacityReached) || map.get(origHash + index) == null) {
				index++;
				if(origHash + index >= maxSize)
					index -= (origHash + index);
				if(origHash + index == origHash)
					maxCapacityReached = true;
			}
			
			if(!maxCapacityReached)
				return map.get(hash(key) + index).getValue();
		}
		
		return "no value found";
	}
	
	public boolean containsKey(int key) {
		int origHash = hash(key);
		int index = 0;
		boolean notInTable = false;
		while(map.get(origHash + index) != null && !notInTable) {
			if(map.get(origHash + index).getKey() == key)
				return true;
			else {
				index++;
				if(origHash + index >= maxSize)
					index -= (origHash + index);
				
				if(index + origHash == origHash)
					notInTable = true;
			}
		}
		
		return false;
	}
	
	public int hash(int key) {
		return key % maxSize;
	}
	
	public String toString() {
		return map.toString();
	}
	
	public static void main(String [] args) {
		ClosedHashTable map = new ClosedHashTable(10);
		System.out.println(map.put(3, "three"));
		System.out.println(map.put(13,  "thirteen"));
		System.out.println(map.put(8, "eight"));
		System.out.println(map.put(9, "nine"));
		System.out.println(map.put(18, "eighteen"));
		System.out.println(map.put(19, "nineteen"));
		System.out.println(map);
		for(int i = 10; i < 20; i++)
			System.out.println(map.containsKey(i));
	}
}

// defined in other file (OpenHashTable.java)
// uncomment if other file not found
/*class KVPair{
	private int key;
	private String value;
	
	public KVPair(int k, String v) {
		key = k;
		value = v;
	}
	
	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}*/