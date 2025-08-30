package br.com.sillion.teste_situacional;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner leitor = new Scanner(System.in)) {
        	
            boolean continuar;
            
            do {
            	
                String url;
                
                do {
                    System.out.println("Por favor, insira um URL válido:");
                    url = leitor.nextLine().trim();
                    if (url.isEmpty()) {
                        System.out.println("A URL não pode estar vazia. Tente novamente.");
                    }
                } while (url.isEmpty());
                
                String frase;
                
                do {
                    System.out.println("Por favor, insira a frase para buscar:");
                    frase = leitor.nextLine().trim();
                    if (frase.isEmpty()) {
                        System.out.println("A frase não pode estar vazia. Tente novamente.");
                    }
                } while (frase.isEmpty());

                ExtratorDeDadosWeb extrator = new ExtratorDeDadosWeb();
                ContadorDeTexto contador = new ContadorDeTexto();

                try {
                    String textoDoCorpo = extrator.obterEExtrairTexto(url);

                    if (textoDoCorpo != null) {
                        int contagemDaFrase = contador.contarFrase(textoDoCorpo, frase);
                        System.out.println("\n--- Resultados ---");
                        
                        boolean eFrase = frase.split("\\s+").length > 1;

                        if (eFrase)
                        	System.out.printf("Frase: \"%s\" => repete %d vezes\n", frase, contagemDaFrase);
                        else
                        	System.out.printf("Palavra: \"%s\" => repete %d vezes\n", frase, contagemDaFrase);
                        

                        if (eFrase) {
                            System.out.println("\n--- Contagem de palavras na frase ---");
                            Map<String, Integer> contagemDasPalavras = contador.contarPalavrasNaFrase(textoDoCorpo, frase);
                            contagemDasPalavras.forEach((palavra, contagem) -> System.out.printf("\"%s\" => repete %d vezes\n", palavra, contagem));
                        }
                        
                    } else {
                        System.out.println("Não foi possível obter o conteúdo da URL.");
                    }
                } catch (IOException | IllegalArgumentException e) {   
                    System.err.println("Erro: " + e.getMessage());
                }
                
                System.out.println("\nDeseja realizar uma nova busca? (S/N)");
                String resposta = leitor.nextLine().toUpperCase();
                continuar = resposta.equals("S");

            } while (continuar);

            System.out.println("Programa encerrado.");
        }
    }
}