package beetle.brindi.quotes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Translation {

    private String text;

    private String source;

    private String target;

}
