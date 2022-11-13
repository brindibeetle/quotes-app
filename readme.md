# Quotes application
#### Opstarten
```mvn spring-boot:run```

#### Endpoints
De volgende endpoints zijn gemaakt:
- ```<hostname>/v1/quotes```
<br> antwoord is een random quote in json
<br> bijvoorbeeld http://localhost:8080/v1/quotes.

- ```<hostname>/v1/quotes/xml```
<br> antwoord is een random quote in xml

- ```<hostname>/v1/quotes/popular```
<br> antwoord is een lijst met populaire quotes

#### Extra assignment
Vertaalde quotes. De random quote wordt vertaald via een endpoint van Nlp Cloud. 
- ```<hostname>/v1/quotes/translate?language=<code>```
<br> antwoord is een random quote vertaald naar de meegegeven taal.
<br> bijvoorbeeld http://localhost:8080/v1/quotes/translate?language=pap_Latn voor een quote in het Papiamento
, of http://localhost:8080/v1/quotes/translate?language=pes_Arab, voor een Persische quote.
<br> Hiervoor gebruik ik de translation-api van npl cloud, zie https://docs.nlpcloud.com/#translation voor de documentatie en mogelijkheden.
