package net.swanndolia;

import net.swanndolia.entity.Animal;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.swanndolia.IHM.requestInputFromUser;

public class Repository {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_jpa");
    public static EntityManager entityManager = emf.createEntityManager();

    public static void addAnimal() {
        String            dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dtf        = DateTimeFormatter.ofPattern(dateFormat);

        Animal animal = Animal.builder().age(Integer.parseInt(requestInputFromUser("Entrez l'age de l'animal"))).name(
                requestInputFromUser("Entrez le nom de l'animal")).dateArrival(
                LocalDate.parse(requestInputFromUser("Entrez la date d'arrivée au zoo de l'animal au format " + dateFormat), dtf)).foodHabits(
                IHM.requestUserChoiceFromEnum("Sélectionner le régime alimentaire de l'animal")).build();
        entityManager.persist(animal);
        entityManager.getTransaction().commit();
    }

    public static Animal searchAnimalByID(int id) {
        return entityManager.find(Animal.class, id);
    }

    public static List<Animal> searchAnimalByName(String name) {
        TypedQuery<Animal> query = entityManager.createQuery("select a from Animal a where name = ?1", Animal.class);
        query.setParameter(1, name);
        return query.getResultList();
    }

    public static List<Animal> searchAnimalByFoodHabits(FoodHabits foodHabits) {
        TypedQuery<Animal> query = entityManager.createQuery("select a from Animal a where foodHabits = ?1", Animal.class);
        query.setParameter(1, foodHabits);
        return query.getResultList();
    }

    public static void closeEntityManager() {
        entityManager.close();
        emf.close();
    }
}
