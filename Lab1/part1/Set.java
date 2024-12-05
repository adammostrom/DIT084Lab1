package part1;

import java.util.ArrayList;
import java.util.function.IntBinaryOperator;

public class Set {
    private ArrayList<Integer> a;

    public Set() {
        a = new ArrayList<Integer>();
    }

    public int[] toArray() {
        int[] ia = new int[a.size()];
        for (int i = 0; i < ia.length; i++) {
            ia[i] = a.get(i);
        }
        return ia;
    }

    public void insert(int x) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > x) {
                a.add(i, x);
                return; /** Changed / fixed bug  */
            } else {
                if (a.get(i) == x) {
                    return; /** Changed / fixed bug  */
                }
            }
        }
        a.add(x);
    }

    public boolean member(int x) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > x) {
                return false;
            } else {
                if (a.get(i) == x) {
                    return true;
                }
            }
        }
        return false;
    }

    public void intersect(Set s) {
        for (int i = 0, j = 0; i < a.size();) {
            if (j == s.a.size()) {
                a.subList(j, a.size()).clear();
                break;
            }

            if (a.get(i).equals(s.a.get(j))) {
                i++;
                j++;
            } else {
                if (a.get(i) < s.a.get(j)) {
                    a.remove(i);
                    /** Removed i++ , iterated one too many*/
                } else {
                    j++;
                }
            }
        }
        

        // stops iterating when reaching the end of `s`, leaving elements in `a` that shouldn't be there
        // test {0, 1} {0}




    }

    // Try with:
    // (a, b) -> a + b;
    // (a, b) -> a - b;
    public boolean distinctClosed(IntBinaryOperator f) {
        int vi, vj;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                vi = a.get(i);
                vj = a.get(j);
                if (!(member(f.applyAsInt(vi, vj)) || vi == vj))
                    return false;
            }
        }
        return true;
    }
}