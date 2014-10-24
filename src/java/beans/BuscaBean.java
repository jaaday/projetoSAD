package beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.netbeans.j2ee.wsdl.buscape.ObjectFactory;
import org.netbeans.j2ee.wsdl.buscape.OfferType;
import org.netbeans.j2ee.wsdl.buscape.Result;

public class BuscaBean {

    private String nome;
    private Result result;
    private List<OfferType> resultados;
    private JAXBContext jc;
    private Unmarshaller u;

    public BuscaBean() {
        resultados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void buscar() {
        try {
            jc = JAXBContext.newInstance(ObjectFactory.class);
            u = jc.createUnmarshaller();
            result = (Result) u.unmarshal(new URL("http://sandbox.buscape.com/service/findOfferList/564771466d477a4458664d3d/?keyword="+nome));
            resultados = result.getOffer();
        } catch (JAXBException | MalformedURLException e) {
        }
    }

    public List<OfferType> getResultados() {
        return resultados;
    }

    public void setResultados(List<OfferType> resultados) {
        this.resultados = resultados;
    }

}
