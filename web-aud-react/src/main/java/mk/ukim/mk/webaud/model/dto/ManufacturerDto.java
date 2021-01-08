package mk.ukim.mk.webaud.model.dto;

import lombok.Data;

@Data
public class ManufacturerDto {

    private String name;
    private String address;

    public ManufacturerDto() {
    }

    public ManufacturerDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
