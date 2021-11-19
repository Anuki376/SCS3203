package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetList {

    private static PetList pet_list_instance = null;
    private static List<Pet> pets;

    private PetList(){
        //initialize list
        pets = new ArrayList<Pet>();

        //Add test data
        Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(2);
		pet1.setPetName("Snoopy");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Tom");
		pet2.setPetType("Cat");

        Pet pet3 = new Pet();
        pet3.setPetId(3);
        pet3.setPetAge(1);
        pet3.setPetName("Tom");
        pet3.setPetType("Horse");

        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
    }

    public static PetList getInstance() {

        if(pet_list_instance == null)
            pet_list_instance = new PetList();

        return pet_list_instance;
    }

    // retrieve pet list
    public ArrayList<Pet> getArray() {
        return (ArrayList<Pet>) pets;
    }


    //Add pet to list
    public boolean addToArray(Pet pet) {

        if (this.getArrayElement(pet.getPetId()) == null){
            pets.add(pet);
            return true;
        }else{
            return false;
        }
    }


    //get pet from id
    public Pet getArrayElement(int petId) {
        for (Pet pet : pets) {
            if (pet.getPetId().equals(petId)) {
                return pet;
            }
        }
        return null;
    }


    //update pet details
    public boolean updateArray(Pet newPet) {

        Pet pet = this.getArrayElement(newPet.getPetId());

        if(pet != null) {
            pet.setPetAge(newPet.getPetAge());
            pet.setPetName(newPet.getPetName());
            pet.setPetType(newPet.getPetType());
            return true;
        }
        return false;
    }


    //delete pet
    public boolean deleteArrayElement(int petId){
        return pets.removeIf(pet -> pet.getPetId().equals(petId));
    }


    //search pet by name
    public ArrayList<Pet> searchArrayElementByName(String petName) {
        ArrayList<Pet> tempList = new ArrayList<Pet>();
        for (Pet pet : pets) {
            if (pet.getPetName().equals(petName)) {
                tempList.add(pet);
            }
        }
        return tempList;
    }


    //search pet by age
    public ArrayList<Pet> searchArrayElementByAge(int petAge) {
        ArrayList<Pet> tempList = new ArrayList<Pet>();
        for (Pet pet : pets) {
            if (pet.getPetAge().equals(petAge)) {
                tempList.add(pet);
            }
        }
        return tempList;
    }

}
