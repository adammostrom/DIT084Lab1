### BUGS 

Changed the method as below due to a bug where the function adds an element twice due to the final insert statement after the loop breaks for the first if case. If the element is larger in the set than the inserted element, the if statement is true, the element gets inserted and then after the loop breaks the element gets inserted again.



Changed break statements to return statements due to duplicate bug, in the first if statement.

```java
public void insert(int x){
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > x) {
                a.add(i, x);
                break;
            } else {
                if (a.get(i) == x) {
                    break;
                }
            }
        }
        a.add(x);
    }
```

to:

```java
    public void insert(int x) {
        if(a.size() == 0) {
            a.add(x);
            return;
        }
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > x) {
                a.add(i, x);
                break;
            } else {
                if (a.get(i) == x || a.get(i) < x) {
                    a.add(x);
                    break;
                }
            }
        }
    }
```