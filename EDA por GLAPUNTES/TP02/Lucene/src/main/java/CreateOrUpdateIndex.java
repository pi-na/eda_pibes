
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CreateOrUpdateIndex {

	public static void main(String[] args) throws IOException {
		createIndex();
		System.out.println("Fin del proceso");
	}
	
	public static void createIndex() throws IOException 	{
		String databaseDir = Utils.getDir();

		Directory indexDir = FSDirectory.open( Paths.get(databaseDir + "/index/"));

		// generar indice de todos los archivos. Podria poner un filtro. Ej: solo los txt y pdf
		final List<String> stop_Words = Arrays.asList("el", "los", "la", "las");
		final CharArraySet stopSet = new CharArraySet(stop_Words, true);
		
		
		IndexWriterConfig indexConf = new IndexWriterConfig(new StandardAnalyzer(stopSet));

		
		// optional
		indexConf.setOpenMode(OpenMode.CREATE);  // other options are available

		final IndexWriter index = new IndexWriter(indexDir, indexConf);

		// agrego los documentos
		Path docsDir = Paths.get(databaseDir + "/docs/");
		FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				Document aDoc = new Document();
				aDoc.add(new TextField("owner", "leticia gomez", Field.Store.YES));

				aDoc.add(new StringField("path", file.toAbsolutePath().toString(),
						Field.Store.YES));

				
				InputStream theFile = Files.newInputStream(file);
				aDoc.add(new TextField("content",  new BufferedReader(
						new InputStreamReader(theFile, StandardCharsets.UTF_8))));


				index.addDocument(aDoc);
				return FileVisitResult.CONTINUE;
			}
		};

		Files.walkFileTree(docsDir, fv);
		index.close();  
	}
}