package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@Schema(name = "PetsType")
public class PetType {

    @JsonProperty("pet_type_id")
    private Integer petTypeId;

    @Schema(required = true, description = "Type name")
    @JsonProperty("pet_type_name")
    private String petTypeName;

    //default constructor
    public PetType() {
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetTypeName() {
        return petTypeName;
    }

    public void setPetTypeName(String petTypeName) {
        this.petTypeName = petTypeName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + petTypeId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PetType other = (PetType) obj;
        if (!Objects.equals(petTypeId, other.petTypeId))
            return false;

        return true;
    }

}
