package Customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Could not find customer with the following id: %s", id));
    }
}
