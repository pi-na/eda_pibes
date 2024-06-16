import java.io.*;
import java.util.*;


import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.*;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;


public class TestAnalyzer  {
 
	public static void main(String[] args) throws IOException {

//		Analyzer a= new SimpleAnalyzer(); // Elimina signos de puntacuion y numeros, luego pasa a minuscula.
//		Analyzer a= new StandardAnalyzer(); // Elimina signos de puntacuion (excepto el . que lo tokeniza por no estar espaciado), luego pasa a minuscula.
//		Analyzer a= new WhitespaceAnalyzer(); // Elimina unicamente espacios en blanco.
		List<String> sw = new ArrayList<>();
		sw.add("de");
		sw.add("y");
		CharArraySet stw= new CharArraySet(sw,false);
//		Analyzer a= new StopAnalyzer(stw); // rango de y
//		Analyzer a = new SpanishAnalyzer();
		Analyzer a = CustomAnalyzer.builder()
				.withTokenizer("standard")
				.addTokenFilter("lowercase")
				.addTokenFilter("stop")
				.addTokenFilter("porterstem")   // familia de palabras
				.addTokenFilter("capitalization")
				.build();

		String fieldValue= "Estructura de datos. com y algoritmos; 2020-Q1";
		  
		TokenStream tokenStream = a.tokenStream("fieldName", fieldValue);
		CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while(tokenStream.incrementToken()) {
		       System.out.println(attr.toString());
		  }       
	}
	
 }
 
