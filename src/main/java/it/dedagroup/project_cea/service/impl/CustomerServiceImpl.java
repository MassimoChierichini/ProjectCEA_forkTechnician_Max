package it.dedagroup.project_cea.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Role;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.repository.BillRepository;
import it.dedagroup.project_cea.repository.CustomerRepository;
import it.dedagroup.project_cea.repository.InterventionRepository;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;

@Service
public class CustomerServiceImpl implements CustomerServiceDef{
	
	@Autowired
	CustomerRepository customerRepo;         //istanziato le repository(contenenti i metodi find)
	@Autowired
	InterventionRepository interventionRepo;
	@Autowired
	BillRepository billRepo;
	@Autowired
	ApartmentRepository apartmentRepo;
	
	@Override
	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void modifyCustomer(Customer customer) {
		Customer customerModify = findCustomerById(customer.getId());
		customerModify.setName(customer.getName());
		customerModify.setSurname(customer.getSurname());
		customerModify.setUsername(customer.getUsername());
		customerModify.setRole(Role.CUSTOMER);
		customerRepo.save(customer);
	}

	@Override
	public void deleteCustomer(long id_customer) {
		Customer customer = findCustomerById(id_customer);
		customer.setAvailable(false);
		customerRepo.save(customer);
	}
	
	@Override
	public Intervention bookIntervention(long id_user, long id_apartment, LocalDate interventionDate) {
		Apartment customerApart = apartmentRepo.findApartmentByCustomer_id(id_user).orElseThrow(() -> new NotValidDataException("Apartment not found to book an intervention with user id: "+id_user));
		Intervention intervention=new Intervention();
		intervention.setApartment(customerApart);
		intervention.setStatus(StatusIntervention.PENDING);
		intervention.setType(TypeOfIntervention.FIXING_UP);
		return interventionRepo.save(intervention);
	}

	@Override
	public Bill payBill(long id_bill, LocalDate paymentDate) {
		Bill bill=billRepo.findById(id_bill).orElseThrow();
		bill.setPaymentDay(paymentDate);
		return billRepo.save(bill);
	}

	@Override
	public Scan autoScan(long id_apartment,double mcLiter) {
		Scan scan=new Scan();
		scan.setMcLiter(mcLiter);
		Apartment apartment=apartmentRepo.findById(id_apartment).orElseThrow(()-> new NotValidDataException("Apartment not found with apartment id: "+id_apartment));
		apartment.getScans().add(scan);
		return scan;
	}
	
	@Override
	public Customer findCustomerById(long id_customer) {
		return customerRepo.findById(id_customer).orElseThrow(() -> new NotValidDataException("Customer not found with id: "+id_customer));
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepo.findAll();
	}

	@Override
	public Customer findCustomerByUsernameAndPassword(String username, String password) {
		return customerRepo.findCustomerByUsernameAndPassword(username, password).orElseThrow(() -> new NotValidDataException("Customer's username and/or password invalid"));
	}
	
	@Override
	public Customer findCustomerByUsername(String username) {
		return customerRepo.findCustomerByUsername(username).orElseThrow(() -> new NotValidDataException("Customer not found with username: "+username));
	}

	@Override
	public Customer findCustomerByTaxCode(String taxCode) {
		return customerRepo.findCustomerByTaxCode(taxCode).orElseThrow(() -> new NotValidDataException("Customer not found with tax code: "+taxCode));
	}

	@Override
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname) {
		return customerRepo.findAllCustomerByNameAndSurname(name, surname);
	}

	@Override
	public Customer findCustomerByApartments_Id(long id_apartment) {
		return customerRepo.findCustomerByApartments_Id(id_apartment)
				.orElseThrow(() -> new NotValidDataException("Customer not found with Apartment's id: "+id_apartment));
	}
}
