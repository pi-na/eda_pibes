package BinaryTree;

public class BTreeMain {
    public static void main(String[] args) {
        BTree<Integer> st = new BTree<>(2);
        st.add(0);
        st.add(8);
        st.add(109);
        st.add(220);
        st.add(222);
        st.add(241);
        st.add(149);
        st.add(107);
        st.add(75);
        st.add(248);
        st.add(254);
        st.add(140);
        st.add(16);
        st.add(66);
        st.add(74);
        st.add(21);
        st.add(211);
        st.add(47);
        st.add(80);
        st.add(242);
        System.out.println( st.toString() );
        System.out.println("....");
        st.remove(66);
        System.out.println(st.toString());
        st.remove(21);
        st.remove(109);
        st.remove(241);
        st.remove(149);
        st.remove(140);
        System.out.println(st.toString());
        st.remove(211);
        System.out.println(st.toString());
        st.remove(220);
        st.remove(242);
        System.out.println(st.toString());


    }
}
