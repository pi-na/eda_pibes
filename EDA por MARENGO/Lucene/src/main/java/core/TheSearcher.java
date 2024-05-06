package core;

import org.apache.lucene.search.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;


public class TheSearcher {
	
	private static IndexReader getIndexReader() throws IOException {
		
		// target index directory
		Directory indexDir = FSDirectory.open( Paths.get(Utils.getPrefixDir() + "/index/"));
		
		return DirectoryReader.open( indexDir );
	}

    public static void main( String[] args ) {
        try {
           	IndexReader index = getIndexReader();
        	IndexSearcher searcher= new IndexSearcher(index);
        	searcher.setSimilarity(new ClassicSimilarity());

        	// field of interest
        	String fieldName = "content";
        	String queryStr= "*";
        	
        	Term myTerm = new Term(fieldName, queryStr);
        	Query query= new WildcardQuery(myTerm);

        	// run the query
        	long startTime = System.currentTimeMillis();       	
        	TopDocs topDocs = searcher.search(query, 20);
        	long endTime = System.currentTimeMillis();
        	
			// show the resultset
			System.out.println(String.format("Query=> %s\n", query));
			System.out.println(String.format("%d topDocs documents found in %d ms.\n", topDocs.totalHits,
					(endTime - startTime) ) );
        	
			ScoreDoc[] orderedDocs = topDocs.scoreDocs;

			int position= 1;
			System.out.println("Resultset=> \n");
			
			for (ScoreDoc aD : orderedDocs) {
				// print info about finding
				int docID= aD.doc;
				double score = aD.score;
				System.out.println(String.format("position=%-10d  score= %10.7f", position, score ));
				
				// print docID, score
				System.out.println(aD);
				
				// obtain ALL the stored fields
				Document aDoc = searcher.doc(docID);
				System.out.println("Store values " + aDoc);
				 
	//			Explanation rta = searcher.explain(query, docID);
	//            System.out.println(rta);
	         
	            position++;
	            System.out.println();
			}

			index.close();
        } 
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    

}