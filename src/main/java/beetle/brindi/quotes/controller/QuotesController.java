package beetle.brindi.quotes.controller;

import beetle.brindi.quotes.domain.Quote;
import beetle.brindi.quotes.exception.QuotesException;
import beetle.brindi.quotes.exception.TranslateException;
import beetle.brindi.quotes.service.QuotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/quotes")
@RequiredArgsConstructor
public class QuotesController {

    private final QuotesService quotesService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote getQuoteJson() throws QuotesException {
        return quotesService.getRandomQuote();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Quote getQuoteXml() throws QuotesException {
        return quotesService.getRandomQuote();
    }

    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote[] getPopularQuotes() throws QuotesException {
        return quotesService.getPopularQuotes();
    }

    @GetMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote getTranslated(@RequestParam String language) throws QuotesException, TranslateException {
        return quotesService.getTranslatedQuote(language);
    }

}
