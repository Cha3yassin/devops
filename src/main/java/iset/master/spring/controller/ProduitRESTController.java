package iset.master.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import iset.master.spring.model.Produit;
import iset.master.spring.repository.ProduitRepository;

@RestController
@RequestMapping("/produits")
public class ProduitRESTController {

    @Autowired
    private ProduitRepository produitRepos;

    // =============================
    // Message d'accueil
    // =============================
    // http://localhost:8080/produits/index
    @GetMapping("/index")
    public String accueil() {
        return "BienVenue au service Web REST 'produits'.....";
    }

    // =============================
    // Afficher tous les produits
    // =============================
    // http://localhost:8080/produits/
    @GetMapping(
            value = "/",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public List<Produit> getAllProduits() {
        return produitRepos.findAll();
    }

    // =============================
    // Afficher produit par id
    // =============================
    // http://localhost:8080/produits/{id}
    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Produit getProduit(@PathVariable Long id) {
        return produitRepos.findById(id).get();
    }

    // =============================
    // Supprimer produit par id (GET)
    // =============================
    // http://localhost:8080/produits/delete/{id}
    @GetMapping("/delete/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitRepos.deleteById(id);
    }

    // =============================
    // Ajouter produit (POST)
    // =============================
    // http://localhost:8080/produits/ (POST)
    @PostMapping(
            value = "/",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public Produit saveProduit(@RequestBody Produit p) {
        return produitRepos.save(p);
    }

    // =============================
    // Modifier produit (PUT)
    // =============================
    // http://localhost:8080/produits/ (PUT)
    @PutMapping(
            value = "/",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Produit updateProduit(@RequestBody Produit p) {
        return produitRepos.save(p);
    }

    // =============================
    // Supprimer produit (DELETE)
    // =============================
    // http://localhost:8080/produits/ (DELETE)
    @DeleteMapping(value = "/")
    public void deleteProduit(@RequestBody Produit p) {
        produitRepos.delete(p);
    }
}