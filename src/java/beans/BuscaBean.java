package beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.netbeans.j2ee.wsdl.buscape.ObjectFactory;
import org.netbeans.j2ee.wsdl.buscape.OfferType;
import org.netbeans.j2ee.wsdl.buscape.Result;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Comparador;
import org.primefaces.event.SlideEndEvent;

@ManagedBean
@RequestScoped
public class BuscaBean implements Serializable {

    private String nome;
    private Result result;
    private List<OfferType> resultados;
    private List<Comparador> comparadores;
    private JAXBContext jc;
    private Unmarshaller u;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6 = 30;
    private int number7 = 80;

    public BuscaBean() {
        resultados = new ArrayList<>();
        comparadores = new ArrayList<>();
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
            result = (Result) u.unmarshal(new URL("http://sandbox.buscape.com/service/findOfferList/564771466d477a4458664d3d/?categoryId=3482&keyword=" + nome));
            resultados = result.getOffer();
            getBestOption();
        } catch (JAXBException | MalformedURLException e) {
        }
    }

    public List<OfferType> getResultados() {
        return resultados;
    }

    public void getBestOption() {
        Double preco;
        Double rating;
        Comparador comp;
        for (OfferType atual : resultados) {
            comp = new Comparador();
            comp.setNomeProduto(atual.getOfferName());
            preco = Double.parseDouble(atual.getPrice().getValue());
            comp.setValorProduto(preco);
            comp.setLojaVenda(atual.getSeller().getSellerName());
            try {
                rating = Double.parseDouble(atual.getSeller().getRating().getUserAverageRating().getRating());
                comp.setRating(rating);
            } catch (Exception e) {
            }
            comp.setPorcentagem(0.0);
            comparadores.add(comp);
        }

        Double maxRating;
        Double minPrice;
        Double calculo;

        maxRating = ratingMaximo();
        minPrice = priceMinimo();

        for (int i = 0; i < comparadores.size(); i++) {
            calculo = ((minPrice * 100.0) / (comparadores.get(i).getValorProduto())) * (getNumber2()/100.0);
            if (comparadores.get(i).getRating() > 0) {
                calculo += (((comparadores.get(i).getRating()) * 100.0) / maxRating) * (getNumber3()/100.0);
            }

            comparadores.get(i).setPorcentagem(calculo);
        }
        
        if (comparadores.size() > 0) {
            Comparador maxi;
            maxi = comparadores.get(0);
            for (Comparador atual : comparadores) {
                if (atual.getPorcentagem() > maxi.getPorcentagem()) {
                    maxi = atual;
                }
            }
            comparadores = new ArrayList<>();
            comparadores.add(maxi);
        } 

    }


    public Double ratingMaximo() {
        Double maxi = 0.0;
        for (Comparador atual : comparadores) {
            if (atual.getRating() > maxi) {
                maxi = atual.getRating();
            }
        }
        return maxi;
    }

    public Double priceMinimo() {
        if (comparadores.size() > 0) {
            Double mini = comparadores.get(0).getValorProduto();
            for (Comparador atual : comparadores) {
                if (atual.getValorProduto() < mini) {
                    mini = atual.getValorProduto();
                }
            }
            return mini;
        } else {
            return null;
        }

    }

    public void setResultados(List<OfferType> resultados) {
        this.resultados = resultados;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumber4() {
        return number4;
    }

    public void setNumber4(int number4) {
        this.number4 = number4;
    }

    public int getNumber5() {
        return number5;
    }

    public void setNumber5(int number5) {
        this.number5 = number5;
    }

    public int getNumber6() {
        return number6;
    }

    public void setNumber6(int number6) {
        this.number6 = number6;
    }

    public int getNumber7() {
        return number7;
    }

    public void setNumber7(int number7) {
        this.number7 = number7;
    }

    public void onSlideEnd(SlideEndEvent event) {
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Comparador> getComparadores() {
        return comparadores;
    }

    public void setComparadores(List<Comparador> comparadores) {
        this.comparadores = comparadores;
    }

}
