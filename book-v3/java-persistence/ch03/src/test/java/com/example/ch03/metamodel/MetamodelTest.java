package com.example.ch03.metamodel;

import com.example.ch03.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.metamodel.SingularAttribute;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MetamodelTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private EntityManager em;

    @Test
    void metaTest() {
        Metamodel metamodel = emf.getMetamodel();
        Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();
        ManagedType<?> itemType = managedTypes.iterator().next();
        assertThat(managedTypes.size()).isEqualTo(2);

        SingularAttribute<?, ?> idAttribute = itemType.getSingularAttribute("id");
        assertThat(idAttribute.isOptional()).isFalse();
    }

    @Test
    void staticMetaTest() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> query = cb.createQuery(Item.class);
        Root<Item> fromItem = query.from(Item.class);
        query.select(fromItem);
        List<Item> items = em.createQuery(query).getResultList();
        assertThat(items.size()).isEqualTo(2);
    }
}
