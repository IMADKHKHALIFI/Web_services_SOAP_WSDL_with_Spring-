package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import ws.entities.Compte;
import java.util.Arrays;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod(operationName = "ConversionEuroToDH")
    public double conversion(@WebParam(name="Montant") double mt){
        return mt*11;
    }
    // si le nom n'est pas spécifié elle prendra le meme nom que la méthode
    @WebMethod()
    public Compte getCompte(@WebParam(name = "code") int code){
        return new Compte(code, Math.random()*6800, new Date());
    }
    @WebMethod()
    public List<Compte> listComptes() {
        return Arrays.asList(
                new Compte(1, Math.random() * 6800, new Date()),
                new Compte(2, Math.random() * 6800, new Date()),
                new Compte(3, Math.random() * 6800, new Date())
        );
    }
}
