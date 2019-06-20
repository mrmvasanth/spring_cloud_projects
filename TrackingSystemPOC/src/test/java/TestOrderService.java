import com.packs.TrackingSystemPOC.entity.Orders;
import com.packs.TrackingSystemPOC.repositories.OrdersRepository;
import com.packs.TrackingSystemPOC.services.OrdersService;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderService {

    @Mock
    Orders order;

    @InjectMocks
    OrdersService ordersService;

    @Mock
    OrdersRepository ordersRepository;

    int orderId,userId;

    String status;

    List<Orders> ordersList;

    @Test
    public void placeOrderTest(){
        Mockito.when(ordersRepository.save(order)).thenReturn(order);
        Assert.assertThat(ordersService.placeOrder(order), Is.is(order));
        Mockito.verify(ordersRepository,Mockito.times(1)).save(order);
    }

    @Test
    public void deleteOrderByIdTest(){
        Assert.assertThat(ordersService.deleteOrderById(orderId),Is.is(orderId));
        Mockito.verify(ordersRepository,Mockito.times(1)).deleteById(orderId);
    }

    @Test
    public void getAllOrdersTest(){
        Mockito.when(ordersRepository.findAll()).thenReturn(ordersList);
        Assert.assertThat(ordersService.getAllOrders(),Is.is(ordersList));
        Mockito.verify(ordersRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void getOrderByOrderIdTest(){
        Mockito.when(ordersRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));
        Assert.assertThat(ordersService.getOrderByOrderId(orderId),Is.is(java.util.Optional.of(order)));
        Mockito.verify(ordersRepository,Mockito.times(1)).findById(orderId);
    }

    @Test
    public void getOrdersByUserIdTest(){
        Mockito.when(ordersRepository.findOrdersByUserId(userId)).thenReturn(ordersList);
        Assert.assertThat(ordersService.getOrdersByUserId(userId),Is.is(ordersList));
        Mockito.verify(ordersRepository,Mockito.times(1)).findOrdersByUserId(userId);
    }

    @Test
    public void getOrderByStatusTest() {
        Mockito.when(ordersRepository.findOrdersByStatus(status)).thenReturn(ordersList);
        Assert.assertThat(ordersService.getOrderByStatus(status),Is.is(ordersList));
        Mockito.verify(ordersRepository,Mockito.times(1)).findOrdersByStatus(status);
    }
}
