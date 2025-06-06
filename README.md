
# Web_services_SOAP_WSDL_with_Spring-

## WS SOAP

**Nom :** EL KHELYFY  
**Prénom :** Imad  
**Filière :** Master en Intelligence Artificielle et Sciences de Données  
**Université :** Faculté des Sciences, Université Moulay Ismail Meknès  

---

## 1- Introduction

Ce projet démontre la création d’un service web SOAP en Java avec JAX-WS. Il convertit un montant en euros vers le dirham marocain et permet la consultation de comptes bancaires (unitaire ou liste).

**JAX-WS (Java API for XML Web Services)** permet de :
- Créer facilement des services SOAP via des annotations Java.
- Générer automatiquement un fichier `WSDL`.
- Manipuler les messages SOAP avec des objets Java (marshalling/unmarshalling).
- Faciliter le client via la génération de STUB (proxy).

---

## 2- Objectifs

1. Créer un service Web qui permet de :
   - ✅ Convertir Euro → Dirham
   - ✅ Consulter un compte bancaire
   - ✅ Afficher une liste de comptes
2. Déployer avec un serveur JAX-WS
3. Analyser le WSDL dans un navigateur
4. Tester le service avec SoapUI
5. Générer un client Java SOAP via le WSDL

---

## 3- Architecture générale

- 📦 `ws.soap` → Backend Java avec `BanqueService`
- 💻 `client-soap-java` → Client SOAP qui consomme le service via les classes STUB
- 🌐 `http://localhost:9090/BanqueService?wsdl` → WSDL généré automatiquement

---

## 4- Concepts techniques

### ✅ WSDL
Un document XML qui **décrit les services Web** : types, opérations, messages, bindings et endpoint.

### ✅ STUB / SKELETON
- **STUB** : Code généré côté client qui simule le serveur pour interagir facilement.
- **SKELETON** : (ancien concept côté serveur) utilisé pour interpréter l'appel SOAP et invoquer les méthodes.

### ✅ JAXB (Java Architecture for XML Binding)
Permet de **lier automatiquement les classes Java ↔ XML** (marshalling/unmarshalling).

#### ⚙️ Quelques annotations JAXB utiles :
| Annotation         | Rôle |
|--------------------|------|
| `@XmlRootElement`  | Associe une classe à un élément XML |
| `@XmlElement`      | Marque une propriété comme élément XML |
| `@XmlAttribute`    | Marque une propriété comme attribut XML |
| `@XmlTransient`    | Exclut un champ de la sérialisation |
| `@XmlSchema`       | Définit un espace de nommage global |
| `@XmlAccessorType` | Contrôle comment les propriétés sont exposées |

### ✅ JAX-WS
API pour créer des services Web SOAP en Java via des annotations comme :
- `@WebService`
- `@WebMethod`
- `@WebParam`

---

## 5- Extrait de Code

### 🧮 Service : BanqueService.java
```java
@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod
    public double conversion(@WebParam(name="Montant") double mt) {
        return mt * 11;
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "code") int code) {
        return new Compte(code, Math.random() * 6800, new Date());
    }

    @WebMethod
    public List<Compte> listComptes() {
        return Arrays.asList(
            new Compte(1, Math.random() * 6800, new Date()),
            new Compte(2, Math.random() * 6800, new Date())
        );
    }
}
```

### 🧪 Test client : Main.java
```java
BanqueService proxy = new BanqueWS().getBanqueServicePort();
System.out.println(proxy.conversionEuroToDH(90));
Compte c = proxy.getCompte(4);
System.out.println(c.getCode() + " " + c.getSolde());
```

---

## 6- Déploiement et Consommation

- 🌐 Lancement via `ServerJWS.java`
- 🔗 Accès WSDL : `http://localhost:9090/BanqueService?wsdl`
- 🧪 Test via SoapUI
- 🛠️ Génération du STUB via `wsimport` ou Maven `jaxws-maven-plugin`

---

## 7- Captures d’écran

### ✔️ Interface SOAPUI : Méthode getCompte
![getCompte](Screene/getcompte .png)

### ✔️ Interface SOAPUI : Liste comptes
![listeComptes](Screene/listecompte.png)

### ✔️ Visualisation du WSDL
![WSDL](Screene/localhost wsdl.png)

### ✔️ Code du STUB Proxy
![proxy](Screene/PROXEY .png)

### ✔️ Interface Web de test
![web](Screene/Services web .png)

### ✔️ Conversion Euro → MAD
![conversion](Screene/SOUP interafec , convert euro to mad.png)

### ✔️ Client Java
![client](Screene/proxey consuler client ....png)

---

## 8- Conclusion

Ce mini-projet présente une implémentation complète d’un **web service SOAP en Java**. Il met en pratique les concepts de **WSDL**, **JAX-WS**, **JAXB** et **SOAP** avec un client Java et des tests manuels. Il illustre clairement l'interopérabilité offerte par SOAP dans un contexte métier.

---

## Auteur

**EL KHELYFY Imad**  
Master IASD – Faculté des Sciences, Meknès  
