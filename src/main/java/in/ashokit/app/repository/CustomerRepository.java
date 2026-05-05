package in.ashokit.app.repository;

import in.ashokit.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value="SELECT COUNT(*) FROM  CUSTOMER  WHERE  PHONE_NUMBER=?  AND  PASSWORD=?", nativeQuery=true)
    Integer  checkLogin(Long phoneNumber, String password);
}
