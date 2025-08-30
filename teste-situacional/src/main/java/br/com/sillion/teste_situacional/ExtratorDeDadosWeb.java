package br.com.sillion.teste_situacional;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ExtratorDeDadosWeb {
    public String obterEExtrairTexto(String url) throws IOException {
    	
        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(globalConfig)
                .build()) {

            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/91.0.4472.124 Safari/537.36");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new IOException("Requisição falhou com o código: " + statusCode);
                }

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String htmlContent = EntityUtils.toString(entity);
                    Document doc = Jsoup.parse(htmlContent);
                    //System.out.println(doc.body().text().toLowerCase());
                    return doc.body().text().toLowerCase();
                }
            }
        }
        return null;
    }
}
