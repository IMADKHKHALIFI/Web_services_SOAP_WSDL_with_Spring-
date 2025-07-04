package net.imad;

import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BanqueService proxy = new BanqueWS().getBanqueServicePort();
        System.out.println(proxy.conversionEuroToDH(90));
        Compte compte = proxy.getCompte(4);
        System.out.println("---------------------");
        System.out.println(compte.getCode());
        System.out.println(compte.getDateCreation());
        System.out.println(compte.getSolde());
        System.out.println("---------------------");
        proxy.listComptes().forEach(cp ->{
            System.out.println("---------------------");
            System.out.println(cp.getCode());
            System.out.println(cp.getDateCreation());
            System.out.println(cp.getSolde());
        } );
    }
}