-- 3. Filter out only the active orders.
-- Since we want to optimize the delivery services, 
-- from the orders table retrieve all the orders with 'Pending' and 'Processing' status 
-- and return a table with fields ORDER_ID, STATUS, STORE_ZIPCODE, CUSTOMER_ZIPCODE, REQUIRED_DATE. 
-- Order the results by STORE_ZIPCODE (ascending) and then by REQUIRED_DATE (earliest first), then by ORDER_ID.
-- The order statuses are as follows
-- Pending: 1 
-- Processing: 2
-- Rejected: 3
-- Completed : 4
-- EXAMPLE
-- 186 rows returned
-- first row
# ORDER_ID, STATUS, STORE_ZIPCODE, CUSTOMER_ZIPCODE, REQUIRED_DATE
# '1431', '2', '11432', '14580', '2018-03-12'
SELECT 
order_id AS ORDER_ID, 
order_status AS STATUS, 
(SELECT zip_code FROM sales.stores WHERE store_id = sales.orders.store_id) AS STORE_ZIPCODE,
(SELECT zip_code FROM sales.customers WHERE customer_id = sales.orders.customer_id) AS CUSTOMER_ZIPCODE,
required_date FROM sales.orders WHERE order_status = 1 OR order_status = 2 ORDER BY STORE_ZIPCODE ASC, REQUIRED_DATE ASC, ORDER_ID ASC;
