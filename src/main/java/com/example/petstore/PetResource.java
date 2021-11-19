package com.example.petstore;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	//View all pets
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		ArrayList<Pet> new_pets = PetList.getInstance().getArray();
		return Response.ok(new_pets).build();

	}


	//Find pet from id
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Pet pet = PetList.getInstance().getArrayElement(petId);
		if (pet != null) {
			return Response.ok(pet).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}


	//Add pet
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Pet added successfully"),
			@APIResponse(responseCode = "409", description = "Pet ID already exists") })
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet(Pet newPet) {
		if(PetList.getInstance().addToArray(newPet)){
			return Response.status(Status.CREATED).type(MediaType.TEXT_PLAIN).entity("Pet Added Successfully!").build();
		}else{
			return Response.status(Status.CONFLICT).build();
		}

	}


	//Update pet details
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet details updated" ),
			@APIResponse(responseCode = "404", description = "No Pet found for the id."),
			@APIResponse(responseCode = "304", description = "Update unsuccessful"),})
	@PUT
	@Path("/update/{petId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePet(@PathParam("petId") int petId, Pet pet) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		pet.setPetId(petId);

		if (PetList.getInstance().updateArray(pet)) {
			return Response.status(Status.OK).type(MediaType.TEXT_PLAIN).entity("Update Successful!").build();
		} else {
			return Response.status(Status.NOT_MODIFIED).build();
		}
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet deleted" ),
			@APIResponse(responseCode = "404", description = "No Pet found for the id."),
			@APIResponse(responseCode = "304", description = "Deletion unsuccessful"),})
	//delete pet
	@DELETE
	@Path("/delete/{petId}")
	public Response delete(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		if (PetList.getInstance().deleteArrayElement(petId)) {
			return Response.status(Status.OK).type(MediaType.TEXT_PLAIN).entity("Pet deleted successfully").build();
		} else {
			return Response.status(Status.NOT_MODIFIED).build();
		}
	}


	//search for pet by name
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Matching Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/name/{petName}")
	public Response searchPetsByName(@PathParam("petName") String petName) {
		ArrayList<Pet> temp_pets = PetList.getInstance().searchArrayElementByName(petName);
		return Response.ok(temp_pets).build();
	}


	//search for pet by age
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Matching Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/age/{petAge}")
	public Response searchPetsByAge(@PathParam("petAge") int petAge) {
		ArrayList<Pet> temp_pets = PetList.getInstance().searchArrayElementByAge(petAge);
		return Response.ok(temp_pets).build();
	}



}
