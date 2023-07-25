package com.altenproducts.Back.controller;

import com.altenproducts.Back.domain.service.AltenProductsService;
import com.altenproducts.Back.form.AltenProductsForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "PRODUCTS", description = "Gestion des produits")
@RequestMapping("v1/products")
public class AltenProductsController {

    Logger logger = LoggerFactory.getLogger(AltenProductsController.class);
    String loggerProduitIntrouvable = "[controller] produit introuvable";
    String loggerSuppressionImpossible = "Impossible de supprimer un produit utilisé";

    private final AltenProductsService altenProductsService;


    public AltenProductsController(AltenProductsService altenProductsService) {
        this.altenProductsService = altenProductsService;
    }


    @Operation(summary = "Créer un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = @Content),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content)})
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> registerProducts(@Valid @RequestBody AltenProductsForm altenProductsForm) {
        logger.debug("[Controller] Appel methode register");

        List<AltenProductsForm> altenProductsFormList = altenProductsService.getAllData();
        for (AltenProductsForm altenProductsFormVerif : altenProductsFormList) {
            if (altenProductsFormVerif.getCode().equals(altenProductsForm.getCode())) {
                return new ResponseEntity<>("Donnéee présente dans la BDD", HttpStatus.CONFLICT);
            }
        }

        if (altenProductsService.register(altenProductsForm) != null) {
            logger.info("produit cree");
            return new ResponseEntity<>("Le produit a été correctement créé", HttpStatus.CREATED);
        }

        logger.error("[controller] erreur lors de l'enregistrement de votre produit");
        return new ResponseEntity<>("Une erreur est servenue lors de la création de votre produit ", HttpStatus.NOT_ACCEPTABLE);

    }

    @Operation(summary = "Trouver un produit par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content)})
    @GetMapping("/{idproduit}")
    @ResponseBody
    public ResponseEntity<AltenProductsForm> getServiceById(@Parameter(description = "Id du produit. Ne peut pas être nul.", required = true) @PathVariable("idproduit") int idproduit) {
        logger.debug("[service] Appel methode GetOne ");

        AltenProductsForm altenProductsForm = altenProductsService.getDataById(idproduit);
        if (altenProductsForm == null) {
            logger.error(loggerProduitIntrouvable);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("[controller] produit est bien dans la BDD");
        return new ResponseEntity<>(altenProductsForm, HttpStatus.OK);
    }

    @Operation(summary = "Trouver tous les produits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content)})
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AltenProductsForm>> getAllproducts() {
        logger.debug("[controller] Appel methode getAll");
        List<AltenProductsForm> altenProductsFormList = altenProductsService.getAllData();

        logger.info("[controller] recuperation de tout les produits");
        return new ResponseEntity<>(altenProductsFormList, HttpStatus.OK);
    }

    @Operation(summary = "Modifier un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content),
            @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = @Content),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content)})
    @PatchMapping
    @ResponseBody
    public ResponseEntity<String> updateProduit(@Valid @RequestBody AltenProductsForm altenProductsForm) {
        logger.debug("[controller] Appel methode update");

        if (altenProductsService.update(altenProductsForm) == null) {
            logger.info("[controller] on peut pas modifier un produit qui n'existe pas");
            return new ResponseEntity<>("on peut pas modifier un produit qui n'existe pas", HttpStatus.NOT_FOUND);
        }

        logger.info("[controller] produit modifié ");
        return new ResponseEntity<>("produit modifié", HttpStatus.CREATED);
    }

    @Operation(summary = "Supprimer un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content)})
    @DeleteMapping("/{idproduit}")
    @ResponseBody
    public ResponseEntity<String> deleteProduit(@Parameter(description = "Id du produit. Ne peut pas être nul.", required = true) @PathVariable("idproduit") int idproduit) {
        logger.debug("[controller] Appel methode Delete");

        AltenProductsForm altenProductsForm  = altenProductsService.getDataById(idproduit);

        if(altenProductsForm == null){
            logger.error(loggerProduitIntrouvable);
            return new ResponseEntity<>("products not Found",HttpStatus.NOT_FOUND);
        }

        if(altenProductsService.deleteOne(idproduit)){
            logger.info("[controller] le produits vient d'etre supprimer de la BDD");
            return new ResponseEntity<>("le produit vient d'etre supprimer",HttpStatus.OK);
        }
        logger.error("[controller] erreur");
        return new ResponseEntity<>("une erreur est servenue lors de la suppression de votre produit",HttpStatus.FORBIDDEN);

    }
}
