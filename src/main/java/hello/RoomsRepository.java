package hello;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoomsRepository extends CrudRepository<Rooms, Integer>  {
}

/*
This is the repository interface, this will be automatically implemented by Spring in a bean.
The bean name will be roomsRepository
 */