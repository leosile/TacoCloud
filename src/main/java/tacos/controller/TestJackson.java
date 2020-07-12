package tacos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

class Stock{
    String stockName;
    double price;
    List<String> subZQDMS;

    public Stock(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addSubZQDM(String subZQDM){
        subZQDMS.add(subZQDM);
    }
}

public class TestJackson {


    public static void main(String[] args) {
       Stock stock1=new Stock("600000",66.88);
       stock1.addSubZQDM("aaaaa");
       //if a filed like List<Object> is not empty, it will throws nullpointException
        // when using convertValue
       ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> values=objectMapper.convertValue(stock1,Map.class);
        System.out.println(values);

    }

}
