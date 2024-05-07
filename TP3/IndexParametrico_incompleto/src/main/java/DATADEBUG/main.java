package DATADEBUG;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException {
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
            IdxRecord<Double, Long> currentIdxRecord = new IdxRecord<Double, Long>(Double.valueOf(record.get("daily_max_8_hour_co_concentration")), currentRowId);
            System.out.println("trying to insert " + currentIdxRecord);
            IndexByPollution.insert(currentIdxRecord);
            IndexByPollution.sortedPrint();

        }

        in.close();

        //querys
        System.out.println(IndexByPollution.search(new IdxRecord<Double, Long>(2.8)));

        Double minValue = IndexByPollution.getMin().getKey();
        IdxRecord<Double, Long> minValueIdxRecord = new IdxRecord<Double, Long>(minValue);
        IdxRecord<Double, Long>[] minimalRecordValues = IndexByPollution.range(minValueIdxRecord, minValueIdxRecord, true, true);
        for(IdxRecord<Double, Long> record : minimalRecordValues){
            System.out.println(record);
            System.out.println(data.get(record.getRow()).toString());
        }
        IndexByPollution.sortedPrint();
    }
}
