package com.hamidou.gestiondestock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Cette classe permet de configurer l'API Flickr pour l'envoi des images sur le serveur de Flickr 
 * @Value("${flickr.apiKey}") : permet de récupérer la clé de l'API Flickr
 * @Value("${flickr.apiSecret}") : permet de récupérer le secret de l'API Flickr
 * @Bean : permet de créer un bean Flickr
 * @return : retourne un objet Flickr
 * @throws IOException : exception d'entrée/sortie
 * @throws ExecutionException : exception d'exécution
 * @throws InterruptedException : exception d'interruption
 * @throws FlickrException : exception Flickr
 */

@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;

    //On ajoute l'annotation Bean pour qu'au démarage de l'application, on puisse récupérer l'objet Flickr c'est à dire exécuter la méthode
    //@Bean
    /*
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {

        // Création d'un objet Flickr
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        // Création d'un objet OAuth10aService pour l'authentification de l'utilisateur
        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));

        final Scanner scanner = new Scanner(System.in);

        // Récupération du token de l'utilisateur pour l'authentification de l'utilisateur
        final OAuth1RequestToken request = service.getRequestToken();

        // Récupération de l'URL d'authentification de l'utilisateur pour l'envoi des images sur le serveur de Flickr
        final String authUrl = service.getAuthorizationUrl(request);

        System.out.println(authUrl);
        System.out.println("Past it here >>");

        // Récupération du token de l'utilisateur pour l'authentification de l'utilisateur 
        final String authVerifier = scanner.nextLine();

        // Récupération de l'access token de l'utilisateur pour l'authentification de l'utilisateur
        OAuth1AccessToken accessToken = service.getAccessToken(request, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        // Vérification du token de l'utilisateur pour l'authentification de l'utilisateur
        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("---------------------------------------------");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
    }
     */

    @Bean
    public Flickr getFlickr(){
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        Auth auth = new Auth();

        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);

        return flickr;
    }

}
