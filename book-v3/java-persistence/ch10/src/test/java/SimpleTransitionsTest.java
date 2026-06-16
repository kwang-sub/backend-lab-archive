import org.assertj.core.api.Assertions;
import org.example.entity.Item;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.*;

public class SimpleTransitionsTest {

    @Test
    void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Item item = new Item();
        item.setName("Some Item");
        em.persist(item);
        Long itemId = item.getId();
        assertThat(itemId).isNotNull();

        Item findItemB = em.find(Item.class, itemId);
        if (findItemB != null) {findItemB.setName("New Name");}
        Item findItemA = em.find(Item.class, itemId);

        assertThat(findItemA.getName()).isEqualTo("New Name");
        assertThat(findItemA).isEqualTo(findItemB);
        em.detach(findItemA);
        assertThat(em.contains(findItemA)).isFalse();

        em.merge(findItemA);
        assertThat(findItemA.getId()).isNotNull();
        em.close();
    }
}
