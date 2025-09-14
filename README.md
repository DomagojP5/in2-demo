# Spring Boot web app

Demo Spring Boot aplikacija koja prikazuje temperature gradova.

## Funkcionalnosti
- Dohvat podataka preko OpenWeatherMap API-ja
- Pohrana u H2 in-memory bazu
- Prikaz podataka u tablici pomoću Thymeleaf-a
- Automatsko ažuriranje podataka putem Spring Scheduler-a
- Basic Authentication (admin / admin123)

## Pokretanje
1. Klonirati repo ili raspakirati ZIP
2. Postaviti `.env` varijablu: `WEATHER_API_KEY`
3. Pokrenuti aplikaciju:
   ```bash
   mvn spring-boot:run
4. Otvoriti aplikaciju u web pregledniku na adresi: http://localhost:8080/