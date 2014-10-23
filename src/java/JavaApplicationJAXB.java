/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.netbeans.j2ee.wsdl.buscape.ObjectFactory;
import org.netbeans.j2ee.wsdl.buscape.OfferType;
import org.netbeans.j2ee.wsdl.buscape.Result;
import org.netbeans.j2ee.wsdl.buscape.SellerType;

/**
 *
 * @author LABORDOC
 */
public class JavaApplicationJAXB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Inicio");
            JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);

            Unmarshaller u = jc.createUnmarshaller();
            
            Result result;
            result = (Result) u.unmarshal(new URL("http://sandbox.buscape.com/service/findOfferList/564771466d477a4458664d3d/?keyword=pc"));
            
            System.out.println(result.getDetails().getStatus());
            for (OfferType offer : result.getOffer()) {
                System.out.println("Nome vendedor: " + offer.getOfferName());
            }
            System.out.println("Fim");
        } catch (JAXBException | MalformedURLException e) {
        }
    }

}
