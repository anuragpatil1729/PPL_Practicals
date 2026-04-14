import java.util.*;

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

// Shared Order Queue

class OrderQueue {

    private Queue<Order> orders = new LinkedList<>();

    public synchronized void addOrder(Order order) {

        orders.add(order);

        System.out.println("✅ Order Placed: #" + order.getOrderId() +

                " | " + order.getFoodItem() +

                " | Customer: " + order.getCustomerName());

        notify();

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

// Delivery Agent Thread

class DeliveryAgent extends Thread {

    private String agentName;

    private OrderQueue orderQueue;

    public DeliveryAgent(String agentName, OrderQueue orderQueue) {

        this.agentName = agentName;

        this.orderQueue = orderQueue;

    }

    @Override

    public void run() {

        while (true) {

            Order order = orderQueue.getOrder();

            deliver(order);

        }

    }

    private void deliver(Order order) {

        System.out.println("🚴 " + agentName + " picked Order #" + order.getOrderId());

        try {

            Thread.sleep(3000); // simulate delivery

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

    // Menu

    static String[] menu = {

            "Pizza",

            "Burger",

            "Pasta",

            "Biryani",

            "Sandwich"

    };

    public static void showMenu() {

        System.out.println("\n🍔 FOOD MENU");

        for (int i = 0; i < menu.length; i++) {

            System.out.println((i + 1) + ". " + menu[i]);

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        OrderQueue queue = new OrderQueue();

        // Start delivery agents

        new DeliveryAgent("Agent A", queue).start();

        new DeliveryAgent("Agent B", queue).start();

        new DeliveryAgent("Agent C", queue).start();

        System.out.println("=== 🍽️ Online Food Delivery CLI ===");

        while (true) {

            System.out.println("\n1. Place Order");

            System.out.println("2. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            sc.nextLine(); // consume newline

            if (choice == 1) {

                System.out.print("Enter Customer Name: ");

                String name = sc.nextLine();

                showMenu();

                System.out.print("Select food item: ");

                int itemChoice = sc.nextInt();

                if (itemChoice < 1 || itemChoice > menu.length) {

                    System.out.println("❌ Invalid choice!");

                    continue;

                }

                String food = menu[itemChoice - 1];

                Order order = new Order(orderCounter++, name, food);

                queue.addOrder(order);

            } else if (choice == 2) {

                System.out.println("Exiting system...");

                System.exit(0);

            } else {

                System.out.println("❌ Invalid option!");

            }

        }

    }

}