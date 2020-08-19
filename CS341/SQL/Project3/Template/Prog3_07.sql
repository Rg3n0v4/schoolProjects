-- 7. For each store, list the percentage of orders which did not ship until after the required_date
-- Retrieve the STORE_NAME, PERCENT_OVERDUE with the greatest first, alphabetic in the case of a tie.
-- EXAMPLE
# STORE_NAME, PERCENT_OVERDUE
# 'Santa Cruz Bikes', '29.3103'
# 'Baldwin Bikes', '27.8134'
# 'Rowlett Bikes', '20.1149'

SELECT store_name AS STORE_NAME,
((SELECT COUNT(*) FROM sales.orders WHERE shipped_date > required_date AND store_id = sales.stores.store_id) 
/ (SELECT COUNT(*) FROM sales.orders WHERE store_id = sales.stores.store_id))* 100 AS PERCENT_OVERDUE 
FROM sales.stores ORDER BY PERCENT_OVERDUE DESC, STORE_NAME ASC;

