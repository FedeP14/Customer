package Customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer")
    List<Customer> all(){
        return repository.findAll();
    }

    @GetMapping("/customer/{id}")
     Customer get(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PostMapping("/customer")
    Customer save(@RequestBody Customer newCustomer){
        return repository.save(newCustomer);
    }

    @DeleteMapping("/customer/{id}")
    void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/customer/{id}")
    Customer update(@RequestBody Customer newCustomer, @PathVariable Long id){
        return repository.findById(id).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setEmail(newCustomer.getEmail());
            return repository.save(customer);
        }).orElseGet(() -> {
            newCustomer.setId(id);
            return repository.save(newCustomer);
        });
    }
}
