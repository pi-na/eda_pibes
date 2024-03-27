package core;

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
		 
		// SimpleAnalyzer
		// StandardAnalyzer
		// WhitespaceAnalyzer
		// StopAnalyzer
		// SpanishAnalyzer
		
	//	CharArraySet sw = StopFilter.makeStopSet("de", "y");
	
//	      Analyzer a = new StandardAnalyzer();
//		  Analyzer a = new SimpleAnalyzer();
//		  Analyzer a = new WhitespaceAnalyzer();
		  Analyzer a = new StandardAnalyzer();

		  String fieldValue= "Estructura de datos. Y algoritmos; 2021-Q1  en eda.itba.edu";
		  
		  TokenStream tokenStream = a.tokenStream("fieldName", fieldValue);
		  CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
		  tokenStream.reset();
		  while(tokenStream.incrementToken()) {
		       System.out.println(attr.toString());
		  }       
	}
	
 }
 
