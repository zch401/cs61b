public class OffByOne implements CharacterComparator{
    /**if x and y are off by 1*/
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        diff = Math.abs(diff);
        return diff == 1;
    }
}
