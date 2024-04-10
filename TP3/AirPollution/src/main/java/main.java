import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        Map<Long, CSVRecord> data = new HashMap<>();
        IndexServiceParam<IdxRecord<Double, Long>> IndexByPollution = new IndexWithDuplicatesParam<>(IdxRecord.class);

        String fileName= "co_1980_alabama.csv";
        InputStream is = DataAnalysis.class.getClassLoader().getResourceAsStream(fileName);
        Reader in = new InputStreamReader(is);

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(in);

        for (CSVRecord record : records) {
            Long currentRowId = record.getRecordNumber();
            data.put(currentRowId, record);
            IndexByPollution.insert(new IdxRecord<Double, Long>(Double.valueOf(record.get("daily_max_8_hour_co_concentration")), currentRowId));

//            String value = record.get("daily_max_8_hour_co_concentration");
//            System.out.println(String.format("%s, %s", value, record.toString()));

        }

        in.close();
    }
}
