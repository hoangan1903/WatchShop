package com.seuit.spring.watchshop.rest;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.seuit.spring.watchshop.entity.Order;
import com.seuit.spring.watchshop.service.OrderService;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/rest")
public class OrderRestController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/order/{id}")
	private String createOrder(@PathVariable(name = "id") Integer idPayment) {
		boolean boo = orderService.createOrder(idPayment);
		if (boo == true) {
			return "ok";
		} else {
			return "false";
		}
	}

	@GetMapping(value="/order")
	private List<Order> listAllOrder(
			@RequestParam(required = false, defaultValue = "0") Integer orderStatus,
			@RequestParam(required = false, defaultValue = "0") Integer orderCreateStatus,
			@RequestParam(required = false, defaultValue = "0") Integer orderPriceStatus,
			@RequestParam(required = false, defaultValue = "0") Integer orderPaymentStatus) {
		System.out.println(orderStatus);
		return orderService.listOrder(orderStatus, orderCreateStatus, orderPriceStatus, orderPaymentStatus);
	}

	@GetMapping("/order/detail/{id}")
	private Map<String, Object> getOrderDetailByIdOrder(@PathVariable(name = "id") Integer idOrder) {
		return orderService.listOrderDetailByIdOrder(idOrder);
	}

	@PutMapping("/order/{id}/{isSuccess}")
	private String updateStatusOrder(@PathVariable(name = "id") Integer idOrder,
			@PathVariable(name = "isSuccess") boolean isSuccess) throws NotFoundException {
		return orderService.updateStatusOrder(idOrder, isSuccess);
	}
}
