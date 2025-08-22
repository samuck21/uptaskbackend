package com.samuck21.uptaskbackend.dto.user;

import lombok.Data;

@Data
public class UpdateStoreRequest {
    public String description;
    public String status;
    public String model;
    public String codemd;
    public String supplier;
    public String market;
    public String category;
    public double priceunitwoiva;
    public int minstock;
    public int multiplebuy;
    public int maxstock;
    public float needpermouth;
    public int  stock;
    public String  units;
    public int  end;
    public int  coststock;
    public String  deparment;
}
