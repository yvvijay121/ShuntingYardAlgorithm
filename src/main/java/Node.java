import java.util.ArrayList;
import java.util.Iterator;

public class Node<T> {
    T value;
    ArrayList<Node<T>> children;

    public Node(T value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    public void addChild(Node<T> node) {
        children.add(node);
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(value);
        buffer.append('\n');
        for (Iterator<Node<T>> it = children.iterator(); it.hasNext(); ) {
            Node<T> next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
