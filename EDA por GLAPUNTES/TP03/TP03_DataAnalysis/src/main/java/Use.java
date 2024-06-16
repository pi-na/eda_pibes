import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class Use {
    public static void main(String[] args) throws IOException {
        IndexParametricService<IdxRecord<Double, CSVRecord>> myIndex = new IndexParametricImpl<>();
        String fileName= "/co_1980_alabama.csv";
        InputStream is = DataAnalysis.class.getResourceAsStream(fileName);
        Reader in = new InputStreamReader(is);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
            myIndex.insert(new IdxRecord<>(Double.valueOf(record.get("daily_max_8_hour_co_concentration"))
                    , record));
        }
        IdxRecord<Double,CSVRecord> rec = myIndex.getMin();
        Comparable<IdxRecord<Double,CSVRecord>>[] rango = myIndex.range(rec,rec,true,true);
        for (Comparable<IdxRecord<Double,CSVRecord>> idxRecord: rango)
            System.out.println(((IdxRecord) idxRecord).getRow());
        in.close();
    }
}
