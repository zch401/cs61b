public class OffByN implements CharacterComparator {
    private int n;
    /**define off size*/
    public OffByN(int N) {
        n = N;
    }

    /**if two characters are off by n*/
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        diff = Math.abs(diff);
        return diff == n;
    }
}
