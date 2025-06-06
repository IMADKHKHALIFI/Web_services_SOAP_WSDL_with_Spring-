
# Web_services_SOAP_WSDL_with_Spring-

# WS SOAP

* **Nom :** EL KHELYFY  
* **Prénom :** Imad  
* **Filière :** Master en Intelligence Artificielle et Sciences de Données  
* **Université :** Faculté des Sciences, Université Moulay Ismail Meknès  

---

## **1- Introduction :**

Dans le cadre de ce projet, nous avons développé un service web SOAP en Java permettant de convertir des montants en euros vers le dirham marocain, ainsi que de consulter un compte bancaire ou une liste de comptes. Le service a été déployé à l’aide d’un serveur JAX-WS simple. Nous avons analysé le fichier WSDL généré via un navigateur HTTP, puis testé les opérations du service avec l’outil SoapUI. Enfin, nous avons créé un client Java utilisant le stub généré à partir du WSDL pour interagir avec le service web de manière programmatique.

`JAX-WS (Java API for XML Web Services)` est une API Java qui permet de créer et d'implémenter des services web `SOAP` de manière simple et standardisée. Elle fait partie de la plateforme `Java EE` (anciennement J2EE) et facilite le développement de services web basés sur le protocole SOAP.

**Fonctionnalités principales de JAX-WS :**
- Création facile de services web SOAP en ajoutant des annotations Java.
- Génération automatique du `WSDL (Web Services Description Language)`.
- Gestion des échanges de messages SOAP via des méthodes Java.
- Support de la sécurité, des sessions, et des handlers pour personnaliser les requêtes/réponses.

---

## **2- Énoncé :**

1. Créer un Web service qui permet de :
   - Convertir un montant de l’euro en dirham (DH)
   - Consulter un Compte
   - Consulter une Liste de comptes  
2. Déployer le Web service avec un simple Serveur JAX-WS  
3. Consulter et analyser le WSDL avec un navigateur HTTP  
4. Tester les opérations du web service avec un outil comme SoapUI  
5. Créer un Client SOAP Java :
   - Générer le Stub à partir du WSDL
   - Créer un client SOAP pour le web service  

---

## **3- Implémentation :**

### 1. La classe `Compte.java`

```java
package ws.entities;

import java.util.Date;

public class Compte {
    private int code;
    private double solde;
    private Date dateCreation;

    public Compte(int code, double solde, Date dateCreation) {
        this.code = code;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

    public Compte() {}

    public void setCode(int code) { this.code = code; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    public void setSolde(double solde) { this.solde = solde; }

    public int getCode() { return code; }
    public double getSolde() { return solde; }
    public Date getDateCreation() { return dateCreation; }
}
```

### Explication :

- code : identifiant du compte
- solde : montant disponible
- dateCreation : date de création du compte

---

### 2. La classe `BanqueService.java`

```java
package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import ws.entities.Compte;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod(operationName = "ConversionEuroToDH")
    public double conversion(@WebParam(name="Montant") double mt){
        return mt * 11;
    }

    @WebMethod()
    public Compte getCompte(@WebParam(name = "code") int code){
        return new Compte(code, Math.random() * 6800, new Date());
    }

    @WebMethod()
    public List<Compte> listComptes(){
        return Arrays.asList(
            new Compte(1, Math.random() * 6800, new Date()),
            new Compte(2, Math.random() * 6800, new Date()),
            new Compte(3, Math.random() * 6800, new Date())
        );
    }
}
```

---

### 3. La classe `ServerJWS.java`

```java
package ws;

import jakarta.xml.ws.Endpoint;

public class ServerJWS {
    public static void main(String[] args) {
        String url = "http://0.0.0.0:9090/";
        Endpoint.publish(url, new BanqueService());
        System.out.println("Web service déployé sur l’URL " + url);
    }
}
```

---

### 4. Le client Java `Main.java` (dans module client-soap-java)

```java
package ws;

import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;

public class Main {
    public static void main(String[] args) {
        BanqueService proxy = new BanqueWS().getBanqueServicePort();
        System.out.println("Méthode conversion EuroToDH : " + proxy.conversionEuroToDH(98));

        System.out.println("*************** Consulter un compte ***************");
        Compte compte = proxy.getCompte(1);
        System.out.println(compte.getCode());
        System.out.println(compte.getSolde());
        System.out.println(compte.getDateCreation());

        System.out.println("*************** Liste des comptes ***************");
        proxy.listComptes().forEach(c -> {
            System.out.println("Code : " + c.getCode() + " | Solde : " + c.getSolde() + " | Date : " + c.getDateCreation());
        });
    }
}
```

---

### 5- Conclusion :

Ce travail pratique a permis de mettre en œuvre un service web SOAP avec JAX-WS, incluant la conversion de devises et la gestion des comptes bancaires. Le déploiement sur un serveur intégré, l’analyse du WSDL et les tests avec SoapUI ont validé le bon fonctionnement du service. La création d’un client Java à partir du WSDL a également été réalisée, assurant ainsi une intégration complète et fonctionnelle du système.

---

### 6- Auteur :

**EL KHELYFY Imad**

---

### 7- Captures d’écran :
- ![](Screene/getcompte .png)
- ![](Screene/listecompte.png)
- ![](Screene/localhost wsdl.png)
- ![](Screene/PROXEY .png)
- ![](Screene/Services web .png)
- ![](Screene/SOUP interafec , convert euro to mad.png)
- ![](Screene/proxey consuler client ....png)
