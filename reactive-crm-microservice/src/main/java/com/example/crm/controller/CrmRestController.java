package com.example.crm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.ReactiveCustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//curl -X POST http://localhost:8100/customers -d "{\"identity\": \"1\", \"fullname\": \"jack bauer\", \"email\": \"jack@example.com\", \"sms\": \"+905555555\",\"birthYear\": 1965, \"addresses\" : [{\"city\": \"istanbul\", \"country\": \"turkey\", \"line1\": \"levent\", \"line2\": \"\"}]}" -H "Content-Type: application/json" -H "Accept: application/json"
//curl -X POST http://localhost:8100/customers -d "{\"identity\": \"2\", \"fullname\": \"kate austen\", \"email\": \"katea@example.com\", \"sms\": \"+905557777\",\"birthYear\": 1985, \"addresses\" : [{\"city\": \"istanbul\", \"country\": \"turkey\", \"line1\": \"kadikoy\", \"line2\": \"\"}]}" -H "Content-Type: application/json" -H "Accept: application/json"
//curl -X PUT http://localhost:8100/customers/1 -d "{\"identity\": \"1\", \"fullname\": \"jack bauer\", \"email\": \"jack.bauer@example.com\", \"sms\": \"+905556666\",\"birthYear\": 1965, \"addresses\" : [{\"city\": \"istanbul\", \"country\": \"turkey\", \"line1\": \"4. levent\", \"line2\": \"\"}]}" -H "Content-Type: application/json" -H "Accept: application/json"
//curl -X GET http://localhost:8100/customers/1 -H "Accept: application/json"
//curl -X GET "http://localhost:8100/customers?no=0&size=10" -H "Accept: application/json"
//curl -X DELETE http://localhost:8100/customers/1 -H "Accept: application/json"

@RestController
@RequestMapping("customers")
@CrossOrigin
public class CrmRestController {
	private ReactiveCustomerService customerService;

	public CrmRestController(ReactiveCustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{identity}")
	public Mono<CustomerDocument> findCustomerByIdentity(@PathVariable String identity) {
		return customerService.findById(identity);
	}

	@GetMapping(params = { "no", "size" })
	public Flux<CustomerDocument> findCustomersByPage(@RequestParam int no, @RequestParam int size) {
		return customerService.findCustomersByPage(no, size);
	}

	@PostMapping
	public Mono<CustomerDocument> addCustomer(@RequestBody CustomerDocument customer) {
		return customerService.addCustomer(customer);
	}

	@PutMapping("/{identity}")
	public Mono<CustomerDocument> updateCustomer(@PathVariable String identity,
			@RequestBody CustomerDocument customer) {
		return customerService.updateCustomer(identity, customer);
	}

	@DeleteMapping("/{identity}")
	public Mono<CustomerDocument> removeCustomerByIdentity(@PathVariable String identity) {
		return customerService.removeCustomerById(identity);
	}
}
