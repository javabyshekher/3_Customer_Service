package in.ashokit.app.service;

import in.ashokit.app.entity.Customer;
import in.ashokit.app.model.LoginRequest;
import in.ashokit.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public boolean registerCustomer(Customer customer) {

        if (!repository.existsById(customer.getPhoneNumber())) {
            repository.save(customer);
            return true;
        } else {
            return false;
        }
    }

    public boolean loginCustomer(LoginRequest loginRequest) {

        if (repository.checkLogin(loginRequest.getPhoneNumber(), loginRequest.getPassword()) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Customer readCustomer(Long phoneNumber) {

        return repository.findById(phoneNumber).get();
    }
}
