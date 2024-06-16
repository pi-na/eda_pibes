public class StackOverflowErrorExample {

        public static void recursivePrint(int num) {
            System.out.println("Number: " + num);

            if (num == 0)
                return;
            else
                recursivePrint(++num);
        }

        public static void main(String[] args) {
            try {
                StackOverflowErrorExample.recursivePrint(1);
            } catch (StackOverflowError ex){
                System.out.println("\n"+ex.getClass());
            }
        }
}
