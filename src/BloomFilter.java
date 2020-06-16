import java.util.BitSet;

public class BloomFilter {
    public int filter_len;
    public BitSet bitSet;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        // создаём битовый массив длиной f_len ...
        bitSet = new BitSet(f_len);
    }

    // хэш-функции
    public int hash1(String str1) {
        // 17
        int res = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            res = res * 17 + code;
        }
        // реализация ...
        if (res < 0) res *= -1;
        return res % filter_len;
    }

    public int hash2(String str1) {
        // 223
        // реализация ...
        int random = 223;
        int shift = 8;
        int res = str1.charAt(0);
        for (int i = 1; i < str1.length(); i++) {
            res |= str1.charAt(i) << shift;
            shift += 8;
        }
        res *= random;
        if (res < 0) res *= -1;
        return res % filter_len;
    }

    public void add(String str1) {
        // добавляем строку str1 в фильтр
        int index1 = hash1(str1);
        int index2 = hash2(str1);
        bitSet.set(index1);
        bitSet.set(index2);
    }

    public boolean isValue(String str1) {
        // проверка, имеется ли строка str1 в фильтре
        int index1 = hash1(str1);
        int index2 = hash2(str1);
        return bitSet.get(index1) && bitSet.get(index2);
    }
}