package iset.master.spring.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String designation;

    private double prix;
    private int quantite;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @ManyToOne
    private Categorie categorie;

    // ✅ Cascade PERSIST
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Stock> stocks = new ArrayList<>();

    public Produit() {}

    public Produit(String designation, double prix, int quantite) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(String designation, double prix, int quantite, Date dateAchat) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.dateAchat = dateAchat;
    }

    public Produit(String designation, double prix, int quantite, Date dateAchat, Categorie categorie) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.dateAchat = dateAchat;
        this.categorie = categorie;
    }

    public Produit(Long id, String designation, double prix, int quantite) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Date getDateAchat() { return dateAchat; }
    public void setDateAchat(Date dateAchat) { this.dateAchat = dateAchat; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    public Collection<Stock> getStocks() { return stocks; }
    public void setStocks(Collection<Stock> stocks) { this.stocks = stocks; }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", designation=" + designation +
                ", prix=" + prix + ", quantite=" + quantite +
                ", dateAchat=" + dateAchat + ", categorie=" + categorie + "]";
    }
}