package beetle.brindi.quotes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private String author;

    private Long id;

    private String quote;

    private String permalink;

}
