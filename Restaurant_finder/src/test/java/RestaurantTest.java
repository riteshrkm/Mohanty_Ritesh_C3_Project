import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    @BeforeEach
    public void setup()
    {
    	 LocalTime openingTime = LocalTime.parse("10:30:00");
         LocalTime closingTime = LocalTime.parse("22:00:00");
         restaurant.addToMenu("Sweet corn soup",119);
         restaurant.addToMenu("Vegetable lasagne", 269);
    	restaurant=Mockito.spy(new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime));
    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
       Mockito.doReturn(LocalTime.parse("14:00:00")).when(restaurant).getCurrentTime();
       assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        
    	Mockito.doReturn(LocalTime.parse("24:00:00")).when(restaurant).getCurrentTime();
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
       

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        
        assertThrows(itemNotFoundException.class,
                new Executable() {
					public void execute() throws Throwable {
						restaurant.removeFromMenu("French fries");
					}
				});
    }
    @Test
    public void calculate_cost_of_selected_items_to_be_total_of_prices() {
    	assertEquals(119+269,restaurant.calculateSelectedItems("Sweet corn soup","Vegetable lasagne"));
    }
  
    
}