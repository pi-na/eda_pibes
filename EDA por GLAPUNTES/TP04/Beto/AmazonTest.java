import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class AmazonTest {
    public static void main(String[] args) throws IOException {
    ArrayList<Element> products = ReadFile.read();
    Hash<String, String> h = new Hash<>(s -> s.codePointAt(0));
    //for(Element e : products){
      //  h.insert(e.getTitle(), e.getContent());
    //}
    h = new Hash<>(s->{
        int result = 0;
        int i = 0;
        while(i < s.length()){
            result += s.codePointAt(i);
            i++;
        }
        return result;
    });
    for(Element e : products){
        h.insert(e.getTitle(), e.getContent());
    }
    h.dump();

    }
}
