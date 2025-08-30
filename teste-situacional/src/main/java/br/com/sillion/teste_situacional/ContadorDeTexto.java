package br.com.sillion.teste_situacional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContadorDeTexto {
	
	public int contarFrase(String texto, String frase) {
        if (texto == null || frase == null || texto.isEmpty() || frase.isEmpty()) {
            return 0;
        }
        Pattern padrao = Pattern.compile("\\b" + Pattern.quote(frase.toLowerCase()) + "\\b");
        
        Matcher buscador = padrao.matcher(texto.toLowerCase());
        int contagem = 0;
        while (buscador.find()) {
            contagem++;
        }
        return contagem;
    }

	public Map<String, Integer> contarPalavrasNaFrase(String texto, String frase) {
	    Map<String, Integer> contagemDasPalavras = new LinkedHashMap<>();
	    if (texto == null || frase == null || texto.isEmpty() || frase.isEmpty()) {
	        return contagemDasPalavras;
	    }

	    String[] palavras = frase.toLowerCase().split("\\s+");
	    for (String palavra : palavras) {
	        contagemDasPalavras.put(palavra, contarFrase(texto, palavra));
	    }
	    return contagemDasPalavras;
	}
}
