package hello;

import org.springframework.data.repository.CrudRepository;

public interface BookingsRepository extends CrudRepository<Booking, Integer> {
}
