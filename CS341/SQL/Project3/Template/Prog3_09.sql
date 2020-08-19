-- 9. List information about each order.
-- Retrieve NAME, ORDER_ID, TOTAL_PRICE_BEFORE_DISCOUT, TOTAL_DISCOUNT
-- NAME includes both first and last customer name appended with with a space character in between. 
-- TOTAL_PRICE_BEFORE_DISCOUNT is the number of items multiplied by the list prices of those items, added up over all items in the order
-- TOTAL_DISCOUNT is the sum of the discount applied item by item to the total prices calculated in the prior linee
-- Order results by order_id
-- EXAMPLE 
-- 1615 rows reutrned
-- first row
# NAME, ORDER_ID, TOTAL_PRICE_BEFORE_DISCOUT, TOTAL_DISCOUNT
# 'Johnathan Velazquez', '1', '11397.94', '1166.8936'

SELECT concat(sales.customers.first_name, ' ', sales.customers.last_name) AS `NAME`,
sales.orders.order_id AS ORDER_ID,
SUM(sales.order_items.quantity*sales.order_items.list_price) AS TOTAL_PRICE_BEFORE_DISCOUNT,
ROUND(SUM(sales.order_items.quantity*sales.order_items.list_price*sales.order_items.discount),2) AS TOTAL_DISCOUNT
FROM sales.orders 
JOIN sales.customers ON sales.customers.customer_id = sales.orders.customer_id
JOIN sales.order_items ON sales.order_items.order_id = sales.orders.order_id
GROUP BY sales.orders.order_id ORDER BY sales.orders.order_id ASC;
