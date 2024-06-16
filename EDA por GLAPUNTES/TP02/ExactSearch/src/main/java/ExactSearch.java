public class ExactSearch {
    public static int indexOf(char[] target, char[] query) {
//         //Does not work, fix.
//         int j=0, ret = -1;
//         boolean found = false;
//         for (int i = 0; i < target.length && j < query.length; i++) {
//             if (query[j]==target[i]) {
//                 j++;
//                 if (!found) {
//                     found=true;
//                     ret=i;
//                 }
//             }
//         }
//         return ret;

        //De Juli, O((n-m)*m)
        int index = -1;

        for(int i = 0; i < (target.length - query.length + 1) && index == -1; i++) {
            boolean flag = true;
            for(int j = 0; j < query.length && flag; j++) {
                if(target[i + j] != query[j]) {
                    flag = false;
                }
            }
            if(flag) {
                index = i;
            }
        }
        return index;

    }
}
