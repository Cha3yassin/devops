package iset.master.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Responsable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String nom;

    @Column(length = 50)
    private String prenom;

    @OneToOne(mappedBy = "responsable")
    private Stock stock;

    public Responsable() {
        super();
    }

    public Responsable(String nom, String prenom) {
        super();
        this.nom = nom;
        this.prenom = prenom;
    }

    public Responsable(String nom, String prenom, Stock stock) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Stock getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Responsable [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}