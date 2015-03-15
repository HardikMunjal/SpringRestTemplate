package com.draka.hardi;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalog {

	
    public String sku;
    public String price;
    
    
    @JsonProperty("sku")
    public String getLoginToken() {
        return sku;
    }

    public void setLoginToken(String sku) {
        this.sku = sku;
    }
    
    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}