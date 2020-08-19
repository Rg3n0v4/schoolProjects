-- 6.  For each store, report the Average turnaround (time from order to shipment)
-- Retrieve the STORE_NAME, AVG_TURNAROUND ordered by the fastest stores first, alphabetic in the case of a tie.
-- EXAMPLE
# STORE_NAME, AVG_TURNAROUND
# 'Rowlett Bikes', '1.9203'
# 'Baldwin Bikes', '1.9766'
# 'Santa Cruz Bikes', '2.0399'
SELECT store_name AS STORE_NAME,
(SELECT avg(DATEDIFF(shipped_date, order_date)) FROM sales.orders WHERE store_id = sales.stores.store_id) 
AS AVG_TURNAROUND 
FROM sales.stores ORDER BY AVG_TURNAROUND ASC;