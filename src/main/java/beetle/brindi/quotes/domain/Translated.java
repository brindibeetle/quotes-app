package beetle.brindi.quotes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Translated {

    @JsonProperty("translation_text")
    private String translationText;

}
