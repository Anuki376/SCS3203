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

@Path("/v1/pets-types")
@Produces("application/json")
public class PetTypeResource {

    //View all pet types
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Type"))) })
    @GET
    public Response getPetTypes() {
        ArrayList<PetType> new_pet_types = PetTypeList.getInstance().getArray();
        return Response.ok(new_pet_types).build();
    }


    //Find pet type from id
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No pet type found for id.") })
    @GET
    @Path("{petTypeId}")
    public Response getPet(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        PetType pet_type = PetTypeList.getInstance().getArrayElement(petTypeId);
        if (pet_type != null) {
            return Response.ok(pet_type).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    //Add pet type
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Pet type added successfully"),
            @APIResponse(responseCode = "409", description = "Pet ID already exists") })
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPetType(PetType newPetType) {
        if(PetTypeList.getInstance().addToArray(newPetType)){
            return Response.status(Status.CREATED).type(MediaType.TEXT_PLAIN).entity("Pet Type Added Successfully!").build();
        }else{
            return Response.status(Status.CONFLICT).build();
        }
    }


    //Update pet type details
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type updated" ),
            @APIResponse(responseCode = "404", description = "No Pet type found for id."),
            @APIResponse(responseCode = "304", description = "Update unsuccessful"),})
    @PUT
    @Path("/update/{petTypeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePet(@PathParam("petTypeId") int petTypeId, PetType petsType) {
        if (petTypeId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        petsType.setPetTypeId(petTypeId);

        if (PetTypeList.getInstance().updateArray(petsType)) {
            return Response.status(Status.OK).type(MediaType.TEXT_PLAIN).entity("Pet Type Updated Successfully!").build();
        } else {
            return Response.status(Status.NOT_MODIFIED).build();
        }
    }


    //delete pet
    @DELETE
    @Path("/delete/{petTypeId}")
    public Response delete(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        if (PetTypeList.getInstance().deleteArrayElement(petTypeId)) {
            return Response.status(Status.OK).type(MediaType.TEXT_PLAIN).entity("Pet Type Deleted Successfully!").build();
        } else {
            return Response.status(Status.NOT_MODIFIED).build();
        }
    }
}
