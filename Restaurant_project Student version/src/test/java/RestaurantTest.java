import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest
{
    static Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeAll
    public static void setUp()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Vegetable sizzler", 150);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>ORDER TOTAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //
    @Test
    public void return_total_order_price()
    {
        //Method should take a list of string with the names of items
        //Method should call findItemByName which will take an item from list of string
        //Method should then get the price of the item after we get the item
        //Method should add the prices
        //Method should return the total price
        List<String> items = new ArrayList<>();
        items.add("Sweet corn soup");
        items.add("Vegetable sizzler");

        int price = restaurant.getTotalPrice(items);
        assertEquals(269, price);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time()
    {
        LocalTime inputTime = LocalTime.parse("21:00:00");
        Boolean isOpen = restaurant.isRestaurantOpen(inputTime);
        assertEquals(true, isOpen);
        //WRITE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time()
    {
        LocalTime inputTime = LocalTime.parse("23:00:00");
        Boolean isOpen = restaurant.isRestaurantOpen(inputTime);
        assertEquals(false, isOpen);
        //WRITE UNIT TEST CASE HERE
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1()
    {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException
    {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception()
    {
        assertThrows(itemNotFoundException.class, ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}