package FinalExamPractice;

import java.util.ArrayList;
import java.util.Random;

public class OpenHashTable{
	private ArrayList<ArrayList<KVPair>> map;
	private int maxSize;
	
	public OpenHashTable(int initSize) {
		map = new ArrayList<ArrayList<KVPair>>(initSize);
		maxSize = initSize;
		for(int i = 0; i < initSize; i++)
			map.add(new ArrayList<KVPair>());
	}
	
	public void put(int key, String value) {
		map.get(hash(key)).add(new KVPair(key, value));
	}
	public int hash(int key) {
		// System.out.println(key + " % " + maxSize + " = " + key % maxSize);
		return key % maxSize;
	}
	
	public String get(int key) {
		if(containsKey(key))
			return findValueIn(map.get(hash(key)), key);
		
		return "";
	}
	public String findValueIn(ArrayList<KVPair> KVs, int key) {
		for(int i = 0; i < KVs.size(); i++) {
			if(KVs.get(i).getKey() == key)
				return KVs.get(i).getValue();
		}
		return "";
	}
	
	public boolean containsKey(int key) {
		return map.get(hash(key)) != null;
	}
	
	public String toString() {
		return map.toString();
	}
	
	public static void main(String[] args) {
		Random gen = new Random();
		
		OpenHashTable hash = new OpenHashTable(10);
		for(int i = 0; i < 100; i++){
			hash.put(gen.nextInt(100), "" + (char)(97 + gen.nextInt(26)) + "" + (char)(97 + gen.nextInt(26)) + "" + (char)(97 + gen.nextInt(26)));
		}
		
		System.out.println(hash);
		int key;
		for(int i = 0; i < 100; i++) {
			key = gen.nextInt(100);
			System.out.println(key + ": " + ((hash.get(key) != "") ? hash.get(key) : "not in table"));
			// System.out.println(hash.map.get(hash.hash(key)));
		}
	}
}

class KVPair{
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
}