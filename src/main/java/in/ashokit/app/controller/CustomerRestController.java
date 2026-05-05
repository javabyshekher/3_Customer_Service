package in.ashokit.app.controller;

import in.ashokit.app.entity.Customer;
import in.ashokit.app.model.CustomerProfile;
import in.ashokit.app.model.LoginRequest;
import in.ashokit.app.model.PlanData;
import in.ashokit.app.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class CustomerRestController {
    @Autowired
    CustomerService service;

    private static final String PLAN_URL = "http://localhost:8081/api/v1/plans/{id}";
	private static final String FRIEND_URL = "http://localhost:8082/api/v1/friend-contacts/{phoneNumber}";

    @PostMapping(value = "/customer/registration")
    public boolean addCustomer(@RequestBody Customer customer) {
        return service.registerCustomer(customer);
    }

    @PostMapping(value = "/customer/login")
    public boolean loginCustomer(@RequestBody LoginRequest loginRequest) {
        return service.loginCustomer(loginRequest);
    }

    @GetMapping("/customer/profile/{phoneNumber}")
    public ResponseEntity<CustomerProfile> showProfile(@PathVariable Long phoneNumber) {

        RestTemplate restTemplate = new RestTemplate();
        Customer customer = service.readCustomer(phoneNumber);

        CustomerProfile customerProfile = new CustomerProfile();

        BeanUtils.copyProperties(customer, customerProfile);

        // calling plan microservice
        ResponseEntity<PlanData> re = restTemplate.getForEntity(PLAN_URL, PlanData.class,
                customer.getPlanId());

        PlanData planData = re.getBody();
        BeanUtils.copyProperties(planData, customerProfile);

        // calling friend microservice

        ParameterizedTypeReference<List<Object[]>> typeRef = new ParameterizedTypeReference<List<Object[]>>() {
        };

        ResponseEntity<List<Object[]>> re2 = restTemplate.exchange(FRIEND_URL, HttpMethod.GET, null, typeRef, phoneNumber);
        List<Object[]> friendsContactNumbers = re2.getBody();
        customerProfile.setFriendsContactNumbers(friendsContactNumbers);

        return ResponseEntity.ok(customerProfile);
    }
}
