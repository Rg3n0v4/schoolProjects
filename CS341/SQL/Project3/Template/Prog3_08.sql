-- 8. Each store decides to give away free coupons to the customers 
-- who have ordered items from their store more than once. 
-- Retrieve STORE_NAME, CUSTOMER_NAME, NUM_ORDERS
-- CUSTOMER_NAME includes both first and last name appended with with a space character in between. 
-- Only keep the store-customer pairs where more than one order has been made.
-- Sort the results by store name alphabetic, then in decreasing order of NUM_ORDERS, 
-- and alphabetic by last name in the case of ties..
-- EXAMPLE
-- 132 rows returned
-- first row
# STORE_NAME, CUSTOMER_NAME, NUM_ORDERS
# 'Baldwin Bikes', 'Genoveva Baldwin', '3'

SELECT sales.stores.store_name AS STORE_NAME, 
concat(sales.customers.first_name, ' ', sales.customers.last_name) AS CUSTOMER_NAME,
(SELECT count(*) FROM sales.orders WHERE sales.customers.customer_id = customer_id AND sales.stores.store_id = store_id) AS NUM_ORDERS
FROM sales.stores, sales.customers HAVING NUM_ORDERS > 1 ORDER BY STORE_NAME ASC, NUM_ORDERS DESC, sales.customers.last_name ASC;
