package tacos;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
public class Order {

    private Long id;

    private Date placedAt;

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is reqquired")
    private String state;

    @NotBlank(message = "zip code is required")
    private String zip;

 //@CreditCardNumber(message = "Not a valid credit card number")
    @NotBlank(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos= new ArrayList<>();

    public void addDesign(Taco taco){
        tacos.add(taco);
    }

    public Map<String,Object> convertToTableValues(){
        Map<String,Object> tableValues=new HashMap<>();
        tableValues.put("id",this.id);
        tableValues.put("deliveryName",this.name);
        tableValues.put("deliveryStreet",this.street);
        tableValues.put("deliveryCity",this.city);
        tableValues.put("deliveryState",this.state);
        tableValues.put("deliveryZip",this.zip);
        tableValues.put("ccNumber",this.ccNumber);
        tableValues.put("ccExpiration",this.ccExpiration);
        tableValues.put("ccCVV",this.ccCVV);
        tableValues.put("placedAt",this.placedAt);
        return tableValues;
    }
}
