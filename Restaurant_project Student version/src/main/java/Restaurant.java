import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant
{
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime)
    {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen(LocalTime inputTime)
    {
        int compareClosing = closingTime.compareTo(inputTime);
        int compareOpening = openingTime.compareTo(inputTime);
        return compareClosing > 0 && compareOpening < 0;
    }

    public LocalTime getCurrentTime()
    {
        return LocalTime.now();
    }

    public List<Item> getMenu()
    {
        return menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException
    {
        for(Item item: menu)
        {
            if(item.getName().equalsIgnoreCase(itemName))
            {
                return item;
            }
            else
            {
                throw new itemNotFoundException(itemName);
            }
        }
        return null;
    }

    public void addToMenu(String name, int price)
    {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException
    {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
        {
            throw new itemNotFoundException(itemName);
        }
        menu.remove(itemToBeRemoved);
    }

    public void displayDetails()
    {
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());
    }

    public String getName()
    {
        return name;
    }
}
