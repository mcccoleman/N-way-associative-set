public class SetAssociativeCache {
  private final int numSets;
  private final int sizeOfSet;

  private CacheEntry[][] cacheEntries;

  public SetAssociativeCache(int numSets, int sizeOfSet) {
    this.numSets = numSets;
    this.sizeOfSet = sizeOfSet;
    this.cacheEntries = new CacheEntry[numSets][sizeOfSet];
  }

}
