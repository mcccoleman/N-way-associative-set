import java.util.HashMap;
import java.util.Map;

 public class CacheEntry<K,V> {
   private K key;
   private V value;
   private long currentTimeMillis;
  

   public CacheEntry(K key, V value) {
     this.key = key;
     this.value = value;
     this.currentTimeMillis = System.currentTimeMillis();
   }

   public K getKey() {
     return this.key;
   }

   public V getValue() {
     return this.value;
   }
   
   public long getLastUseTime() {
	   return this.currentTimeMillis;
   }

   public void setValue(V value) {
     this.value = value;
     this.currentTimeMillis = System.currentTimeMillis();
   }

//   public K toString() {
//     return "Key: " + this.key + " Value: "  + this.value.toString();
//   }

 }

//public class CacheEntry<T, U> {
//  private final Map<T, U> map = new HashMap<>();
//
//  public void put(T key, U value) {
//    map.put(key, value);
//  }
//
//  public <T> T getKey() {
//    return (T) map.get(key);
//  }
//  
//  public <T> T getValue() {
//	    return (T) map.get(value);
//	  }
//
// public static class Key<T> {
//  private final String name;
//
//  public Key(String name) {
//    this.name = name;
//  }
//  public String getName() {
//    return name;
//  }
//
//
// }
//
//
//}
