package beetle.brindi.quotes.service;

import beetle.brindi.quotes.domain.Translation;
import beetle.brindi.quotes.domain.Quote;
import beetle.brindi.quotes.domain.Translated;
import beetle.brindi.quotes.exception.QuotesException;
import beetle.brindi.quotes.exception.TranslateException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class QuotesService {

    @Value("${quotes.popular.url}")
    private String popularQuotes;

    @Value("${quotes.random.url}")
    private String randomQuote;

    @Value("${translate.api.url}")
    private String translateUrl;

    @Value("${translate.api.token}")
    private String translateToken;

    public Quote[] getPopularQuotes() throws QuotesException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        Quote[] quotes = null;
        try {
            json = IOUtils.toString(URI.create(popularQuotes), StandardCharsets.UTF_8);
            quotes = objectMapper.readValue(json, Quote[].class);
        } catch (IOException e) {
            throw new QuotesException(e.getMessage());
        }
        return quotes;
    }

    public Quote getRandomQuote() throws QuotesException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        Quote quote = null;
        try {
            json = IOUtils.toString(URI.create(randomQuote), StandardCharsets.UTF_8);
            quote = objectMapper.readValue(json, Quote.class);
        } catch (IOException e) {
            throw new QuotesException(e.getMessage());
        }
        return quote;
    }

    public Quote getTranslatedQuote(String language) throws QuotesException, TranslateException {
        ObjectMapper objectMapper = new ObjectMapper();
        Quote quote = getRandomQuote();

        HttpPost request = new HttpPost(translateUrl);
        request.setHeader("Authorization", "Token " + translateToken);
        request.setHeader("Content-Type", "application/json");

        Translation translation = new Translation(quote.getQuote(), "eng_Latn", language);
        String translationJson = null;
        try {
            translationJson = objectMapper.writeValueAsString(translation);
        } catch (JsonProcessingException e) {
            throw new TranslateException(e.getMessage());
        }

        StringEntity translationEntity = new StringEntity(translationJson);
        request.setEntity(translationEntity);

        String result = null;
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
                objectMapper = new ObjectMapper();
                if (response.getCode() == HttpStatus.SC_OK) {
                    Translated translated = objectMapper.readValue(result, Translated.class);
                    quote.setQuote(translated.getTranslationText());
                } else {
                    throw new TranslateException(result);
                }
            }

        } catch (IOException | ParseException e) {
            throw new QuotesException(e.getMessage());
        }
        return quote;
    }

}
