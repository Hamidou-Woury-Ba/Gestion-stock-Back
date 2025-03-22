
# 📦 Backend - Gestion de Stock

Ce projet est une API REST développée avec **Spring Boot** pour la gestion de stock. Il inclut les fonctionnalités suivantes :
- 📸 Intégration de **Flickr** pour le stockage des images
- 📘 Documentation interactive de l’API avec **Swagger (OpenAPI)**

---

## 🚀 Prérequis

Avant de commencer, assurez-vous d’avoir installé :
- Java 17 ou version compatible
- Maven
- Un IDE comme IntelliJ IDEA ou VS Code
- Postman (pour tester les endpoints si besoin)

---

## ⚙️ Installation et Configuration du projet

### 1️⃣ Cloner le projet

```bash
git clone https://github.com/ton-utilisateur/gestion-stock-back.git
cd gestion-stock-back
```

### 2️⃣ Configuration de Flickr (Stockage d’images)

1. Créez un compte sur [Flickr](https://www.flickr.com/).
2. Allez dans la section développeur de Flickr pour **créer une application**.
3. Une fois l’application créée, vous obtiendrez :
   - une **API Key**
   - une **API Secret**
4. Ajoutez ces clés dans le fichier `application.yml` :

```yaml
flickr:
  apiKey: VOTRE_API_KEY
  apiSecret: VOTRE_API_SECRET
```

5. Une **classe de configuration Flickr** est déjà présente dans le projet pour initialiser automatiquement l’API avec vos clés.

### 3️⃣ Configuration de Swagger (Documentation de l’API)

1. Le projet utilise **Springdoc OpenAPI** pour générer automatiquement la documentation des endpoints.
2. La dépendance est déjà ajoutée dans le fichier `pom.xml` :

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.8.4</version>
</dependency>
```

3. Une classe de configuration `OpenApiConfig` est déjà fournie :

```java
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Gestion de stock REST API")
                .version("1.0")
                .description("Documentation de l'API pour la gestion de stock"));
    }
}
```

4. Après le démarrage de l’application, la documentation sera disponible à l’adresse :
```
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Démarrer l’application

1. Compilez et lancez le projet :

```bash
mvn spring-boot:run
```

2. L’API sera accessible sur :  
```
http://localhost:8080
```

---

## 📁 Exemple du fichier `application.yml`

```yaml
server:
  port: 8080

flickr:
  apiKey: VOTRE_API_KEY
  apiSecret: VOTRE_API_SECRET
```

---

## 📬 Accès API Documentation (Swagger)

- URL : `http://localhost:8080/swagger-ui.html`
- Interface interactive pour tester tous les endpoints

---

