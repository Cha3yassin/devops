package iset.master.spring.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import iset.master.spring.model.Produit;
import jakarta.transaction.Transactional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("select p from Produit p where p.designation like %:x%")
    List<Produit> findByDesignation(@Param("x") String mc);

    @Query("update Produit p set p.designation = :designation where p.id = :id")
    @Modifying
    @Transactional
    int mettreAJourDesignation(@Param("designation") String designation, @Param("id") Long idProduit);

    List<Produit> findByPrixGreaterThan(double prixMin);

    List<Produit> findAllByOrderByPrixAsc();

    List<Produit> findByDateAchatAfter(Date dateAchat);

    List<Produit> findByCategorieId(Long categorieId);
}