import java.util.Random;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

class MyHashTable {

	Node<String, Integer>[] words;
	PriorityQueue<Integer> frequencies;
	int size;
	int nodeIndex;
	ArrayList<Integer> indexSet;

	MyHashTable(int size) {
		this.size = size;
		words = new Node[size];
		frequencies = new PriorityQueue<Integer>(size,Collections.reverseOrder());
		indexSet = new ArrayList<Integer>();
		nodeIndex = 0;
	}

	public void insert(String word) {
		if (!containsKey(word)) {
			Node<String, Integer> newNode = new Node();
			newNode.setKey(word);
			newNode.setValue(1);
			int ind = hashFunction();
			indexSet.add(ind);
			words[ind] = newNode;
			nodeIndex++;
		} else {
			int value = get(word) + 1;
			words[getIndex(word)].put(word,value);
		}
	}

	public boolean containsKey(String word) {
		if (nodeIndex > 0) {
			for (int i = 0; i < indexSet.size(); i++) {
				if (words[indexSet.get(i)].getKey().compareTo(word) == 0) {
					return true;
				}
			}	
		}
		return false;
	}

	public Integer get(String word) {
		for (int i = 0; i < indexSet.size(); i++) {
			if (words[indexSet.get(i)].getKey().equals(word)) {
				return words[indexSet.get(i)].getValue();
			}
		}
		return null;
	}

	public Integer getIndex(String word) {
		for (int i = 0; i < indexSet.size(); i++) {
			if (words[indexSet.get(i)].getKey().compareTo(word) == 0) {
				return indexSet.get(i);
			}
		}
		return null;
	}

	public int hashFunction() {
		Random randomInt = new Random();
		int index = randomInt.nextInt(((size - 1) - 0) + 1) + 0;
		return index;
	}

	public void insertFrequencyToHeap() {
		for (int i = 0; i < indexSet.size(); i++) {
			if (!frequencies.contains(words[indexSet.get(i)].getValue())) {
				frequencies.add(words[indexSet.get(i)].getValue());
			}		
		}
	}

	public void getHighFrequencyWords() {
		int i = 0;
		int frequency;
		while (i < 100 && frequencies.peek() != null) {
			frequency = frequencies.peek();
			for (int k = 0; k < indexSet.size(); k++) {
				String word = words[indexSet.get(k)].getKey();
				if (frequency == get(word)) {
					System.out.println(word + frequency);
				}
			}
			frequencies.remove(frequencies.peek());
			i++;
		}
	}

	public static void main(String[] args) {
		MyHashTable hash = new MyHashTable(1000);
		Scanner sc = new Scanner(System.in);
		String para = sc.nextLine();
		StringTokenizer st = new StringTokenizer(para, ". !?,/{[(*';:]})-_\"");
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			hash.insert(s);
		}
		hash.insertFrequencyToHeap();
		hash.getHighFrequencyWords();
	}

}
@SuppressWarnings("unchecked")

class Node<T , E> {
	T key;
	E value;
	public void setKey(T key) {
		this.key = key;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public void put(T key, E value) {
		this.key = key;
		this.value = value;
	}

	public T getKey() {
		return this.key;
	}

	public E getValue() {
		return this.value;
	}

	// public int compareTo(Node node) {
	// 	if (this.getValue().compareTo(node.getValue()) > 0) {
	// 		return 1;
	// 	} else if (this.getValue().compareTo(node.getValue()) < 0) {
	// 		return -1;
	// 	}
	// 	return 0;
	// }
}