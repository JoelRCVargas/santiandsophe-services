package com.beecode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beecode.entity.Order;
import com.beecode.entity.Person;
import com.beecode.entity.ProductOrder;
import com.beecode.interfaces.OrderService;
import com.beecode.projection.OrderProjection;
import com.beecode.projection.OrderTotalProjection;
import com.beecode.repository.IOrderRepository;
import com.beecode.repository.IPersonRepository;

import io.conekta.Conekta;
import io.conekta.Customer;
import io.conekta.Error;
import io.conekta.ErrorList;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final double IVA = 0.18;
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private IPersonRepository personRepo;
	
	@Override
	public Order createOrder(Order order, Person person) {
		order.setPerson(person);
		if(!order.getTypePay().equals(Order.PAYPAL)) {
			createOrderConekta(person,order);
		}
		return orderRepo.save(order);
	}

	@Override
	public Order updateOrder(Order order, int dues, String token, String typePay) {
		int total_dues = order.getDues() - dues;
		double totalPay = order.getTotal();
		Optional<Person> person = personRepo.findById(order.getPerson().getId());
		if(typePay.equals(Order.CREDIt_CARD)) {
			updateOrderConekta(person, order, dues, token);
		}
		order.setDues(total_dues);
		order.setTotal(totalPay + (order.getDuesTotal() * dues));
		if(total_dues == 0) {
			order.setDuesTotal(0);
		}
		order.setTokenPay(token);
		order.setTypePay(typePay);
		order.setProductOrder(null);
		return orderRepo.save(order);
	}

	@Override
	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) {
		orderRepo.deleteById(orderId);
	}

	@Override
	public List<Optional<OrderProjection>> getOrderProjectionByPersonId(Long id) {
		List<Optional<OrderProjection>> orderProjection = orderRepo.findAllByPersonId(id);
		if(!orderProjection.isEmpty()) {
			return orderProjection;
		}
		return null;
	}
	
	public void createOrderConekta(Person person, Order order){
		Conekta.setApiKey("key_s51tE5a6sqgyXjyZw3bFxA");
		Conekta.apiVersion = "2.0.0";
		
		//Create customer send conekta
		JSONObject customerJSON = new JSONObject("{"
			    + "'name': '"+person.getFirstName() +" "+person.getLastName()+"', "
			    + "'email': 'example@gmail.com',"
			    + "'phone': '"+person.getPhone()+"',"
			    + "'payment_sources': [{"
			        + "'token_id': '"+order.getTokenPay()+"',"
			        + "'type': 'card'"
			    + "}]"
			+ "}");

		
		Customer customer;
		try {
			customer = Customer.create(customerJSON);
			System.out.println(customer);
		} catch (Error | ErrorList e) {
			e.printStackTrace();
		}
		
		
		// Create order send conekta
		List<ProductOrder> productsOrder = order.getProductOrder();
		List<JSONObject> line_items = new ArrayList<JSONObject>();
		JSONObject item;
		for (ProductOrder productOrder : productsOrder) {
				Long totalIVA = (long) ((productOrder.getUnitPrice() * IVA) * 100);
				System.out.println("IVA :" + totalIVA);
				item = new JSONObject();
				item.put("name", productOrder.getProductName());
				item.put("description", productOrder.getProductSku());
				item.put("unit_price", (productOrder.getUnitPrice() * 100) + totalIVA);
				item.put("quantity" , productOrder.getQuantity());
				System.out.println(productOrder.getUnitPrice() * 100);
				line_items.add(item);
		}
		System.out.println("JsonObject : " + line_items);
		JSONObject completeOrderJSON = new JSONObject("{" +
		        "'currency': 'mxn'," +
		        "'metadata': {" +
		        "    'test': true"+
		        "}," +	
		        "'line_items': "+line_items+"," +
		        "'customer_info': { " +
		        "    'name': '"+person.getFirstName()+" "+person.getLastName()+"'," +
		        "    'phone': '"+person.getPhone()+"'," +
		        "    'email': 'conektaPay@conekta.com'" +
		        "}," +
		        "'charges': [{" +
		        "    'payment_method': {" +
		        "        'type': 'card'," +
		        "        'token_id': 'tok_test_visa_4242'" +
		        "    } " +
		        "}]" +
		        "}");
				
		io.conekta.Order completeOrder;
		try {
			completeOrder = io.conekta.Order.create(completeOrderJSON);
			System.out.println(completeOrder.charges.get(0));
		} catch (ErrorList | Error e) {
			e.printStackTrace();
		}	
	}
	
	public void updateOrderConekta(Optional<Person> person, Order order, int dues , String token) {
		Conekta.setApiKey("key_s51tE5a6sqgyXjyZw3bFxA");
		Conekta.apiVersion = "2.0.0";
		Long total = (long) ((dues * order.getDuesTotal()) * 100);
		
		//Create customer send conekta
		JSONObject customerJSON = new JSONObject("{"
			    + "'name': '"+person.get().getFirstName() +" "+person.get().getLastName()+"', "
			    + "'email': 'example@gmail.com',"
			    + "'phone': '"+person.get().getPhone()+"',"
			    + "'payment_sources': [{"
			        + "'token_id': '"+token+"',"
			        + "'type': 'card'"
			    + "}]"
			+ "}");

		
		Customer customer;
		try {
			customer = Customer.create(customerJSON);
			System.out.println(customer);
		} catch (Error | ErrorList e) {
			e.printStackTrace();
		}
		
		
		JSONObject completeOrderJSON = new JSONObject("{" +
		        "'currency': 'mxn'," +
		        "'metadata': {" +
		        "    'test': true"+
		        "}," +	
		        "'line_items': [{"
		          + "'name': 'Pago de cuota ("+ dues +")',"
		          + "'unit_price': "+ total +","
		          + "'quantity': 1"
		          + "}],"+
		        "'customer_info': { " +
		        "    'name': '"+person.get().getFirstName()+" "+person.get().getLastName()+"'," +
		        "    'phone': '"+person.get().getPhone()+"'," +
		        "    'email': 'conektaPay@conekta.com'" +
		        "}," +
		        "'charges': [{" +
		        "    'payment_method': {" +
		        "        'type': 'card'," +
		        "        'token_id': 'tok_test_visa_4242'" +
		        "    } " +
		        "}]" +
		        "}");
				
		io.conekta.Order completeOrder;
		try {
			completeOrder = io.conekta.Order.create(completeOrderJSON);
			System.out.println(completeOrder.charges.get(0));
		} catch (ErrorList | Error e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<OrderProjection> getAllOrderProjection() {
		List<OrderProjection> orderProjection = orderRepo.findAllProjetedBy();
		if(!orderProjection.isEmpty()) {
			return orderProjection;
		}
		return null;
	}

	@Override
	public Order changeDeliveryStatusOrder(Order order) {
		boolean status = !order.isDeliveryStatus();
		order.setDeliveryStatus(status);
		return orderRepo.save(order);
	}

	@Override
	public Long getCountOrder() {
		return orderRepo.count();
	}

	@Override
	public List<OrderTotalProjection> getOrderTotalProjection() {
		return orderRepo.findTotalProjectedBy();
	}
	
}
