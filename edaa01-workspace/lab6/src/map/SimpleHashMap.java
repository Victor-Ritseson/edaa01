package map;

import java.util.Random;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int size;
	
		public SimpleHashMap() {
			this.table = (Entry<K,V>[]) new Entry[16];
			this.size = 0;	
		}
		public SimpleHashMap(int capacity) {
			table = (Entry<K,V>[]) new Entry[capacity];
			this.size = 0;
		}
		
		public String show() {
			StringBuilder xs = new StringBuilder();
			
			for(int i = 0; i < table.length; i++) {
				if(table[i] != null) {
					xs.append(i + "  " + table[i].toString());
						Entry<K, V> temp = table[i];
						while(temp.next != null) {
							xs.append(i + "  " + temp.next.toString());
							temp = temp.next;
						}
						xs.append("\n");
				} else {
					xs.append(i + "  null \n");
				}
			}
			return xs.toString();
		}
	
	
		@SuppressWarnings("unchecked")
		@Override
		public V get(Object arg0) {	
		
		Entry<K,V>	x = find(index((K) arg0), (K) arg0);		
		
		if(x != null) {
			return x.value;
		} else {
			return null;
		}
		
		}

		@Override
		public boolean isEmpty() {	
			return (size == 0);
		}

		@Override

			public V put(K key, V value) {
			Entry<K,V> e = find(index(key), key);
			
			if(e == null){
				e = table[index(key)];
				
				if(e == null){
					table[index(key)] = new Entry<K,V>(key, value);
				}else{
					while(e.next != null)e = e.next;
					e.next = new Entry<K,V>(key, value);
				}
			}else{
				V old_value = e.value;
				e.value = value;
				return old_value;
			}		
			
			size++;
			
			// Check load factor
			if((double) size/(double) table.length >= 0.75){
				rehash();
			}
			
			
			return null;	
			
			
			}
		
		
		private void rehash() {
		
		if((double) size /(double) table.length > 0.75) {
			size = 0;
			Entry<K, V>[] tempTable = (Entry<K, V>[]) new Entry[table.length * 2];
			Entry<K, V>[] oldTable = table;
			table = tempTable;
			
			for (int i = 0; i < oldTable.length; i++) {
				Entry<K, V> temp = oldTable[i];
				if (temp != null) {
					while (temp.next != null) {
						put(temp.getKey(), temp.getValue());
						temp = temp.next;
					}
					put(temp.getKey(), temp.getValue());
				}
			}
			oldTable = null;
		}

		}

		@Override
		public V remove(Object arg0) {
			int indexObj =  index((K) arg0);
			Entry<K, V> list = table[indexObj];


				while(list != null) {
					if(list.key.equals((K) arg0)) {
						table[indexObj] = list.next;
						size--;
						return list.value;
					} else if(list.next != null && list.next.key.equals((K) arg0)){
							V value = list.next.value;
							list.next = list.next.next;
							size --;
							return value;
					} 	
					list = list.next;
				}
			return null;
		}

		@Override
		public int size() {
			return size;
		}
		
		private int index(K key) {
			return Math.abs(key.hashCode() % table.length);
		}
		
		private Entry<K, V> find(int index, K key){
			Entry<K, V> first = table[index];
			while(first != null) {
				if(first.getKey().equals(key)) {
					return first;
				} else {
				first = first.next;	
				}
			}
				return null;
		}
		
		
		
		
		private static class Entry<K, V> implements Map.Entry<K, V> {
			private K key;
			private V value;
			private Entry<K, V> next;
			
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
			
			
			@Override
			public K getKey() {
				
				return key;
			}

			@Override
			public V getValue() {
				 
				return value;
			}

			@Override
			public V setValue(V value) {
				this.value = value;
				return value;
			}
			
			public String toString() {
				return key + "=" + value;
			}
			
			
			
		}
		
		public static void main(String[] args) {
			SimpleHashMap<Integer, Integer> map = new SimpleHashMap(10);
			
			final int n = 10;
			
			Random random = new Random();
			
			for(int i = 0; i < n; i++) {
				int x = random.nextInt(2000) - 500; 
				map.put(x, x);
			}
			
			System.out.println(map.show());
		}
		
		
		
	
	}

	



