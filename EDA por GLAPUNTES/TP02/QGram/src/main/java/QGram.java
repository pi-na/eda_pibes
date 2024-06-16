import java.util.*;

public class QGram {
    private final int n;

    public QGram(int n) {
        this.n = n;
    }

    private Map<String,Integer> nGram(String s) {
        for (int i = 0; i < n-1; i++) {
            s="#".concat(s);
            s=s.concat("#");
        }
        Map<String,Integer> ret = new HashMap<>();
        for (int i = 0; i <= s.length() - n; i++) {
            String key = s.substring(i, i + n);
            int val = ret.getOrDefault(key, 0);
            ret.put(key, val+1);
        }
        return ret;
    }

    public void printTokens(String s){
        Map<String,Integer> ret = nGram(s);
        ret.forEach((key, value)-> System.out.println(String.format("QGram: %s Amount: %d",key,value)));
    }

    public double similarity(String s1, String s2) {
        Map<String,Integer> first = nGram(s1);
        Map<String,Integer> second = nGram(s2);
        int firstSize = first.values().stream().mapToInt(x->x).sum();
        int secondSize = second.values().stream().mapToInt(x->x).sum();
        System.out.println(String.format("size1:%d size2:%d",firstSize,secondSize));
        int shared=0;
        for (Map.Entry<String,Integer> firstEntry: first.entrySet()) {
            for (Map.Entry<String,Integer> secondEntry: second.entrySet()) {
                if (firstEntry.getKey().equals(secondEntry.getKey())) {
                    shared+= Math.min(firstEntry.getValue(),secondEntry.getValue()) * 2;
                }
            }
        }
        int total = firstSize + secondSize;
        return  shared / (double) total;
    }
}
