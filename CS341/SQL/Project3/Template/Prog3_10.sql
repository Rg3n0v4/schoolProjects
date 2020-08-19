-- 10. Identify whether there may be some correlation between
-- the amount ordered and the time it takes to ship the order
-- Compute the average and standard deviation of the time per quantity of each order
-- EXAMPLE
# AVG, STDDEV
# '0.64447607', '0.5703154314602293'

SELECT AVG(X) as AVG, STDDEV(X) AS STDDEV FROM
(SELECT (DATEDIFF(shipped_date, order_date)) / sum(quantity) AS X FROM sales.order_items, sales.orders 
WHERE sales.orders.order_id = sales.order_items.order_id GROUP BY sales.orders.order_id) AS TEMP;