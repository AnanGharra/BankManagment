package com.group.BankManager.BL;

import com.group.BankManager.beans.Customer;
import com.group.BankManager.dao.CustomerDAO;
import com.group.BankManager.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerBL {

    @Autowired
    private CustomerDAO customerDAO;

    public void addCustomer(Customer customer){
        customerDAO.save(customer);
    }


    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        if(!customerDAO.existsById(id)){
            throw new CustomerNotFoundException("Customer Not Found!");
        }
        customerDAO.deleteById(id);
    }


    public void updateCustomer(Customer customer) throws CustomerNotFoundException {
        if(!customerDAO.existsById(customer.getCustomerID())){
            throw new CustomerNotFoundException("Customer Not Found!");
        }
        customerDAO.save(customer);
    }
}
