package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {
 
	private CustomerDocumentRepository custRepo;
	
	public ReactiveCustomerService(CustomerDocumentRepository custRepo) {
		this.custRepo = custRepo;
	}

	public Mono<CustomerDocument> findById(String identity) {
		return custRepo.findById(identity);
	}

	public Flux<CustomerDocument> findCustomersByPage(int no, int size) {
		return custRepo.findAllByPage(PageRequest.of(no, size));
	}

	public Mono<CustomerDocument> addCustomer(CustomerDocument customer) {
		return custRepo.insert(customer);
	}

	public Mono<CustomerDocument> updateCustomer(String identity, CustomerDocument customer) {
		return custRepo.save(customer);
	}

	public Mono<CustomerDocument> removeCustomerById(String identity) {
		Mono<CustomerDocument> cust = custRepo.findById(identity);
		cust.subscribe(customer ->{
			custRepo.delete(customer).subscribe(System.err::println);
		});
		return cust;
	}

}
