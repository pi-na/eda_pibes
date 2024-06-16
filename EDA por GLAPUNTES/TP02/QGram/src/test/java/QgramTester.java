public class QgramTester {
    public static void main(String[] args) {
        QGram gram = new QGram(2);
        gram.printTokens("alal");
        // #a 1
        // al 2
        // la 1
        // l# 1
        double value = gram.similarity("salesal","alale");
        System.out.println(value);
        // 0,42857
    }
}
