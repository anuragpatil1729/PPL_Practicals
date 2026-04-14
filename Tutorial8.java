import java.util.*;

// Order Class
class Order {
    private int orderId;
    private String customerName;
    private String foodItem;

    public Order(int orderId, String customerName, String foodItem) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.foodItem = foodItem;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getFoodItem() {
        return foodItem;
    }
}

// Shared Queue
class OrderQueue {
    private Queue<Order> orders = new LinkedList<>();

    public synchronized void addOrder(Order order) {
        orders.add(order);
        System.out.println("✅ Order Placed: #" + order.getOrderId() +
                " | " + order.getFoodItem() +
                " | Customer: " + order.getCustomerName());
        notifyAll();
    }

    public synchronized Order getOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return orders.poll();
    }
}

// Delivery Agent
class DeliveryAgent extends Thread {
    private String agentName;
    private OrderQueue queue;

    public DeliveryAgent(String agentName, OrderQueue queue) {
        this.agentName = agentName;
        this.queue = queue;
    }

    public void run() {
        while (true) {
            Order order = queue.getOrder();
            deliver(order);
        }
    }

    private void deliver(Order order) {
        System.out.println("🚴 " + agentName + " picked Order #" + order.getOrderId());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("📦 " + agentName + " delivered " + order.getFoodItem() +
                " to " + order.getCustomerName());
    }
}

// Main Class
public class Tutorial8 {

    static int orderCounter = 1;

    static String[] menu = {
            "Pizza", "Burger", "Pasta", "Biryani", "Sandwich"
    };

    static List<DeliveryAgent> agents = new ArrayList<>();
    static boolean deliveryStarted = false;

    public static void showMenu() {
        System.out.println("\n🍔 FOOD MENU");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderQueue queue = new OrderQueue();

        System.out.println("=== 🍽️ Online Food Delivery CLI ===");

        while (true) {

            System.out.println("\n1. Place Order");
            System.out.println("2. Add Delivery Agent");
            System.out.println("3. Start Delivery");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    showMenu();

                    System.out.print("Select food item: ");
                    int itemChoice = sc.nextInt();

                    if (itemChoice < 1 || itemChoice > menu.length) {
                        System.out.println("Invalid choice!");
                        continue;
                    }

                    String food = menu[itemChoice - 1];
                    Order order = new Order(orderCounter++, name, food);
                    queue.addOrder(order);
                    break;

                case 2:
                    System.out.print("Enter Agent Name: ");
                    String agentName = sc.nextLine();

                    DeliveryAgent agent = new DeliveryAgent(agentName, queue);
                    agents.add(agent);

                    System.out.println("Agent added: " + agentName);
                    break;

                case 3:
                    if (deliveryStarted) {
                        System.out.println("Delivery already started!");
                        break;
                    }

                    if (agents.isEmpty()) {
                        System.out.println("Add at least one delivery agent!");
                        break;
                    }

                    for (DeliveryAgent a : agents) {
                        a.start();
                    }

                    deliveryStarted = true;
                    System.out.println("Delivery Started with " + agents.size() + " agents!");
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option!");
            }

        }

    }

}