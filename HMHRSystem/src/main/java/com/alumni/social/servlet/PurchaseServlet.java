package com.alumni.social.servlet;

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        List<Item> basket = (List<Item>) request.getSession().getAttribute("basket");

        double totalAmount = calculateTotalAmount(basket);

        // Check if user has enough balance
        User user = UserDAO.getUserById(userId);
        if (user.getBalance() >= totalAmount) {
            // Create an order and update item quantities
            Order order = OrderDAO.createOrder(userId, basket, totalAmount);
            ItemDAO.updateItemQuantities(basket);
            UserDAO.updateBalance(userId, totalAmount);

            // Clear basket and redirect to order confirmation page
            request.getSession().setAttribute("basket", new ArrayList<Item>());
            response.sendRedirect("orderConfirmation.jsp?orderId=" + order.getOrderId());
        } else {
            response.sendRedirect("checkout.jsp?error=insufficient_balance");
        }
    }

    private double calculateTotalAmount(List<Item> basket) {
        double total = 0.0;
        for (Item item : basket) {
            total += item.getPrice();
        }
        return total;
    }
}
