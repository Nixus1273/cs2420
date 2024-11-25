package notes;
import java.util.Arrays;

public class BinaryNodeDemo {
    public static void main(String[] args) {
        arrayRepresentationDemo();
    }

    private static void linkedRepresentationDemo() {
        BinaryNode<String> tree = new BinaryNode<String>("cat",
            new BinaryNode<String>("bee",
                new BinaryNode<String>("alligator"),
                new BinaryNode<String>("bird",
                    new BinaryNode<String>("beetle"),
                    null
                )
            ),
            new BinaryNode<String>("dog",
                null,
                new BinaryNode<String>("turtle",
                    new BinaryNode<String>("fish",
                        null,
                        new BinaryNode<String>("kangaroo")
                    ),
                    new BinaryNode<String>("walrus")
                )
            )
        );

        System.out.println("\tpre-order:   \t" + tree.getPreOrder());
        System.out.println("\tpost-order:  \t" + tree.getPostOrder());
        System.out.println("\tin-order:    \t" + tree.getInOrder());
        System.out.println("\tlevel-order: \t" + tree.getLevelOrder());

        System.out.println("DOT representation");
        System.out.println(tree.generateDotString());
    }

    private static void arrayRepresentationDemo() {
        String[] tree = new String[] {
            "cat",
            "bee",                                          "dog",
            "alligator",            "bird",                 null,                   "turtle",
            null,       null,       "beetle",   null,       null,       null,       "fish",           "walrus",
            null, null, null, null, null, null, null, null, null, null, null, null, "kangaroo", null, null, null
        };
                                            
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                System.out.println(tree[i] + ":");
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left < tree.length && tree[left] != null) {
                    System.out.println("\tleft:  " + tree[left]);
                }
                if (right < tree.length && tree[right] != null) {
                    System.out.println("\tright: " + tree[right]);
                }
            }
        }
    }
}
