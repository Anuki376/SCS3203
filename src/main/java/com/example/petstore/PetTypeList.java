package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetTypeList {

    private static PetTypeList pet_type_list_instance = null;
    private static List<PetType> pet_types;

    private PetTypeList(){
        //initialize list
        pet_types = new ArrayList<PetType>();

        PetType type1 = new PetType();
        type1.setPetTypeId(1);
        type1.setPetTypeName("Dog");

        PetType type2 = new PetType();
        type2.setPetTypeId(2);
        type2.setPetTypeName("Cat");

        PetType type3 = new PetType();
        type3.setPetTypeId(3);
        type3.setPetTypeName("Horse");

        pet_types.add(type1);
        pet_types.add(type2);
        pet_types.add(type3);
    }

    public static PetTypeList getInstance() {

        if(pet_type_list_instance == null)
            pet_type_list_instance = new PetTypeList();

        return pet_type_list_instance;
    }


    // retrieve pet types list
    public ArrayList<PetType> getArray() {
        return (ArrayList<PetType>) pet_types;
    }


    //get pet type from id
    public PetType getArrayElement(int petTypeId) {
        for (PetType pet_type : pet_types) {
            if (pet_type.getPetTypeId().equals(petTypeId)) {
                return pet_type;
            }
        }
        return null;
    }


    //search pet by name
    public PetType searchArrayElementByName(String petTypeName) {
        for (PetType pet_type : pet_types) {
            if (pet_type.getPetTypeName().equals(petTypeName)) {
                return pet_type;
            }
        }
        return null;
    }


    //Add pet type to list
    public boolean addToArray(PetType newPetType) {

        if(this.searchArrayElementByName(newPetType.getPetTypeName())==null) {
            int newId = pet_types.size() + 1;
            newPetType.setPetTypeId(newId);
            pet_types.add(newPetType);
            return true;
        }

        return false;
    }


    //update pet type details
    public boolean updateArray(PetType newPetType) {

        if(this.searchArrayElementByName(newPetType.getPetTypeName())==null) {
            int index = pet_types.indexOf(newPetType);
            if (index >= 0) {
                pet_types.set(index, newPetType);
                return true;
            }
        }
        return false;
    }


    //delete pet
    public boolean deleteArrayElement(int petTypeId){
        return pet_types.removeIf(pet_type -> pet_type.getPetTypeId().equals(petTypeId));
    }

}
