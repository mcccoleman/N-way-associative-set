import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class SetAssociativeCache<T, U> {
	
   private final int numSets;
   private final int sizeOfSet;
   private final ReplacementPolicy replacementPolicy;
   public enum ReplacementPolicy { MRU, LRU };
	
  private CacheEntry<T,U>[][] cacheEntries;

  public SetAssociativeCache(int numSets, int sizeOfSet, ReplacementPolicy replacementPolicy) {
    this.numSets = numSets;
    this.sizeOfSet = sizeOfSet;
    this.cacheEntries = new CacheEntry[numSets][sizeOfSet];
    this.replacementPolicy = replacementPolicy;
  }

  public final Object get(T key) {
    int set = key.hashCode() % numSets;

    for( CacheEntry entry : cacheEntries[set]) {
      if( entry != null ) {
        if( entry.getKey() == key) {
          return entry.getValue();
        }
      }
    }
    return null;
  }

  public void put(T key, U value) {
	  // what occurs if there's already an entry in the cacheEntry
    int set = key.hashCode() % numSets;
    for( CacheEntry entry : cacheEntries[set]) {
      if( entry != null ) {
        if( entry.getKey() == key) {
          entry.setValue(value);
          return;
        }
      }
    }
    // what occurs if there's not already an entry n the cacheEntry
    CacheEntry cacheEntry = new CacheEntry<T, U>(key,value);
    for( int i = 0; i < this.sizeOfSet; i++) {
      if( cacheEntries[set][i] == null ) {
        cacheEntries[set][i] = cacheEntry;
        return;
      }
    }
    
    if(this.replacementPolicy == ReplacementPolicy.MRU) {
    CacheEntry<T,U> minimumCacheEntry = cacheEntries[set][0];
    int indexOfMin = 0;
    for( int i = 1; i < this.sizeOfSet; i++) {
        if( cacheEntries[set][i].getLastUseTime() < minimumCacheEntry.getLastUseTime() ) {
        	minimumCacheEntry = cacheEntries[set][i];
        	indexOfMin = i;
        }
      }
    cacheEntries[set][indexOfMin] = cacheEntry;
    
    } else if (this.replacementPolicy == ReplacementPolicy.LRU) {
    	CacheEntry<T,U> minimumCacheEntry = cacheEntries[set][0];
        int indexOfMin = 0;
        
        for( int i = 1; i < this.sizeOfSet; i++) {
            if( cacheEntries[set][i].getLastUseTime() < minimumCacheEntry.getLastUseTime() ) {
            	minimumCacheEntry = cacheEntries[set][i];
            	indexOfMin = i;
            }
    }
    } else {
    	// custom replacement policy goes here
    }
  }
  
  public String toString() {
	  return Arrays.deepToString(cacheEntries);
  }

  public static void main(String args[]) {
	  SetAssociativeCache cache = new SetAssociativeCache<String, Integer>(2,3,ReplacementPolicy.LRU);
//	  cache.put("hey","hello!");
//	  cache.put(1,1);
	  cache.put("hello", 1);
	  System.out.println(cache.get("hello"));
  }
}
