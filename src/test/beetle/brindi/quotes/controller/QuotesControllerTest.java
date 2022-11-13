package beetle.brindi.quotes.controller;

import beetle.brindi.quotes.controller.QuotesController;
import beetle.brindi.quotes.domain.Quote;
import beetle.brindi.quotes.exception.QuotesException;
import beetle.brindi.quotes.exception.TranslateException;
import beetle.brindi.quotes.service.QuotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuotesControllerTest {

    @InjectMocks
    private QuotesController quotesController;

    @Mock
    private QuotesService quotesService;

    @Test
    public void testGetQuoteJson() throws QuotesException {
        Quote quote = new Quote("author", 1L, "quote", "link");
        when(quotesService.getRandomQuote()).thenReturn(quote);
        Quote quote1 = null;
        quote1 = quotesController.getQuoteJson();

        assert(quote1.equals(quote));
    }

    @Test
    public void testGetQuoteXml() throws QuotesException {
        Quote quote = new Quote("author", 1l, "quote", "link");
        when(quotesService.getRandomQuote()).thenReturn(quote);
        Quote quote1 = quotesController.getQuoteXml();

        assert(quote1.equals(quote));
    }

    @Test
    public void testGetTranslated() throws QuotesException, TranslateException {
        Quote quote = new Quote("author", 1l, "quote", "link");
        when(quotesService.getTranslatedQuote("language")).thenReturn(quote);
        Quote quote1 = quotesController.getTranslated("language");

        assert(quote1.equals(quote));
    }

}
