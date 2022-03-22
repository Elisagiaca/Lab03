package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

	List<String> listaParole = new LinkedList<>();
	List<RichWord> listaRichWord = new LinkedList<>();
	
	public void loadDictionary(String language) {
		listaParole.clear();
		String filename = "C:\\Users\\39345\\OneDrive\\Desktop\\WorkspaceTDP\\Lab03\\src\\main\\resources\\"+language+".txt";
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				listaParole.add(word);
			}
			br.close();
		}catch (IOException e) {
			System.out.println("Errore nella lettura del file.");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTeList){
		List<RichWord> listaSbagliate = new LinkedList<>();
		
		
		for (String s : inputTeList) {
			if (listaParole.contains(s)==false) {
				RichWord richwordTemp = new RichWord(s, false);
    			listaSbagliate.add(richwordTemp);
			}
		}
    	
		
		return listaSbagliate;
	}
	
	
}
