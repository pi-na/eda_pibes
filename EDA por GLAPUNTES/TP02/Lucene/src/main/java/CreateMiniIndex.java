
import java.io.IOException; 
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.BytesRef;


public class CreateMiniIndex {
	
	static Directory directory;
		
	static IndexWriter CreateIndex() throws IOException
	{
		StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer); 
		
		//Create a writer
		directory = new ByteBuffersDirectory();
		IndexWriter idxWriter = new IndexWriter(directory, config);

		return idxWriter;
	}
	
	
	static void addDoc(IndexWriter idxWriter, String IsbnValue, String authorValue, String titleValue, String contentValue) throws IOException
	{
		Document aDoc; 
		
		aDoc= new Document();
		
		// no tokenizable: StringField
		// tokenizable: TextField
		
		aDoc.add( new StringField("isbn", IsbnValue, Field.Store.NO));
		aDoc.add( new StringField("author", authorValue, Field.Store.YES));
		aDoc.add( new TextField("title", titleValue, Field.Store.NO));
		aDoc.add( new TextField("content", contentValue, Field.Store.YES));
		
		
		idxWriter.addDocument(aDoc);
		idxWriter.commit();
		// Add Second document, etc.
		
		
	}
	
	

	
	public static void main(String[] args) throws IOException {
		IndexWriter idx = CreateIndex();
		addDoc(idx, "1234", "Leticia Gomez", 
				"Estructura de datos y algoritmos", 
				"Un arreglo es una estructura indexada , bla bla bla" );
	
		addDoc(idx, "5678", "Leticia irene gomez", 
				"algoritmos en Java", 
				"Hashing es una estructura que bla bla bla" );
	
	
	

	
		SearchIndex();
	}
	
	static void SearchIndex() throws IOException
	{
		// Busqueda por termino
//		Query query = new TermQuery(new Term("content", "bla bla"));
		// Busqueda por frase
//		Query query = new PhraseQuery("content","indexada", "estructura");
		// Busqueda por rango
		Query query = new TermRangeQuery("content",new BytesRef("a"),new BytesRef("z") ,false,true);

		//Create a reader
		IndexReader idxReader = DirectoryReader.open(directory);

		IndexSearcher searcher = new IndexSearcher(idxReader);

		TopDocs topDocs = searcher.search(query, 10);

		ScoreDoc[] x = topDocs.scoreDocs;

		System.out.println("Query => " + query);
		System.out.println("Resultset => ");
		for (ScoreDoc aD : x) {
			// print info about finding
			int docID= aD.doc;
			System.out.println(aD);

			// print stored document
			Document aDoc = searcher.doc(docID);
			System.out.println(aDoc);
		}

	}
}
