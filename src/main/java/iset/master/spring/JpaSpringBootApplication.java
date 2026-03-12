package iset.master.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import iset.master.spring.model.Categorie;
import iset.master.spring.model.Produit;
import iset.master.spring.model.Stock;
import iset.master.spring.model.Responsable;

import iset.master.spring.repository.CategorieRepository;
import iset.master.spring.repository.ProduitRepository;
import iset.master.spring.repository.StockRepository;
import iset.master.spring.repository.ResponsableRepository;

@SpringBootApplication
public class JpaSpringBootApplication {

    // Repositories
    static ProduitRepository produitRepos;
    static CategorieRepository categorieRepos;
    static StockRepository stockRepos;
    static ResponsableRepository responsableRepos;

    public static void main(String[] args) {

        ApplicationContext contexte =
                SpringApplication.run(JpaSpringBootApplication.class, args);

        produitRepos = contexte.getBean(ProduitRepository.class);
        categorieRepos = contexte.getBean(CategorieRepository.class);
        stockRepos = contexte.getBean(StockRepository.class);
        responsableRepos = contexte.getBean(ResponsableRepository.class);



        Categorie cat1 = new Categorie("AL", "Alimentaire");
        Categorie cat2 = new Categorie("PL", "Plastique");

        categorieRepos.save(cat1);
        categorieRepos.save(cat2);



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;

        try {
            date1 = sdf.parse("2022-04-15");
            date2 = sdf.parse("2022-02-15");
            date3 = sdf.parse("2022-05-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Produit p1 = new Produit("Yahourt", 0.4, 20, date1, cat1);
        Produit p2 = new Produit("Chocolat", 2000.0, 5, date2, cat1);
        Produit p3 = new Produit("Panier", 1.2, 30, date3, cat2);



        Stock s1 = new Stock("1", "Sfax");
        Stock s2 = new Stock("2", "Tunis");

        // Affectation produits-stocks
        p1.getStocks().add(s1);
        p1.getStocks().add(s2);

        s1.getProduits().add(p1);
        s2.getProduits().add(p1);

        produitRepos.save(p1);
        produitRepos.save(p2);
        produitRepos.save(p3);

        p2.getStocks().add(s1);
        p3.getStocks().add(s2);

        s1.getProduits().add(p2);
        s2.getProduits().add(p3);

        produitRepos.save(p2);
        produitRepos.save(p3);

        afficherTousLesProduits();



        stockRepos.deleteById(s1.getId());



        Responsable r1 = new Responsable("Ben Saleh", "Ali");
        Stock st1 = new Stock("1", "Tunis", r1);
        stockRepos.save(st1);

        Responsable r2 = new Responsable("Ben Ahmed", "Omar");
        Stock st2 = new Stock("2", "Sousse", r2);
        stockRepos.save(st2);

        Responsable r3 = new Responsable("Sallemi", "Samira");
        Stock st3 = new Stock("3", "Sfax", r3);
        stockRepos.save(st3);

        Responsable r4 = new Responsable("Zouari", "Zied");
        responsableRepos.save(r4);

        Stock s4 = new Stock("4", "Gabes");
        stockRepos.save(s4);


        // Ali devient responsable de Gabes
        st1.setResponsable(null);
        stockRepos.save(st1);

        s4.setResponsable(r1);
        stockRepos.save(s4);

        // Zied devient responsable de Tunis
        st1.setResponsable(r4);
        stockRepos.save(st1);


        System.out.println("***********************************************");
        System.out.println("Afficher tous les responsables avec leurs stocks");

        Collection<Responsable> lr = responsableRepos.findAll();

        for (Responsable r : lr) {
            System.out.println(r);
        }

        System.out.println("***********************************************");
    }

    static void afficherTousLesProduits() {

        System.out.println("***************************************");
        System.out.println("Afficher tous les produits...");

        List<Produit> lp = produitRepos.findAll();

        for (Produit p : lp) {
            System.out.println(p);
        }

        System.out.println("***************************************");
    }
}