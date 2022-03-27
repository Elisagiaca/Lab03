package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.math.*;

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
	
	
	//metodo ricerca lineare
	public List<RichWord> spellCheckTextLinear(List<String> inputTeList){
		List<RichWord> listaLinear = new LinkedList<>();
		List<RichWord> listaSbagliateLinear = new LinkedList<>();
		
		//mappo tutto nella lista di RichWord
		for (String s : inputTeList) {
			listaLinear.add(new RichWord(s, false));
		}
		
		
		
		for (RichWord rW: listaLinear) {
			for (String sD : listaParole) {
				if (rW.getParola().compareTo(sD)==0) {
					rW.setCorretta(true);
					
				}

			}			
		}
		
		for (RichWord rrww : listaLinear) {
			if (rrww.isCorretta()==false) {
				listaSbagliateLinear.add(rrww);
			}
		}
		
		return listaSbagliateLinear;
	}
	
	//metodo ricerca dicotomica
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTeList){
		List<RichWord> listaDichotomic = new LinkedList<>();
		List<RichWord> listaSbagliateDichotomic = new LinkedList<>();

		//mappo nella lista di RichWord
		for (String s : inputTeList) {
			listaDichotomic.add(new RichWord(s, false));
		}
		
		//ricerca dicotomica
		for(RichWord rw : listaDichotomic) {
			int indiceInizio = 0;
			int indiceFine = listaParole.size()-1;
			int indiceCentrale = ((indiceInizio+indiceFine) / 2);		
			 
			while(indiceInizio <= indiceFine) {
				//se è uguale
				if (rw.getParola().compareTo(listaParole.get(indiceCentrale))==0) {
					rw.setCorretta(true);
					break;
				}
				//se viene dopo
				else if(rw.getParola().compareTo(listaParole.get(indiceCentrale)) > 0) {
					indiceInizio = indiceCentrale+1;
					indiceCentrale = (indiceFine+indiceInizio)/2;
				}
				//se viene prima
				else {
					indiceFine = indiceCentrale-1;
					indiceCentrale = (indiceInizio+indiceFine) / 2;
				}
			}
		}
		
		for (RichWord rrww : listaDichotomic) {
			if (rrww.isCorretta()==false) {
				listaSbagliateDichotomic.add(rrww);
			}
		}
		
		return listaSbagliateDichotomic;
	}
}
