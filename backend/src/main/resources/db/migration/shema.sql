CREATE MATERIALIZED VIEW authors_by_country_view AS
SELECT c.id AS country_id, c.name AS country_name, COUNT(a.id) AS total_authors
FROM authors a
         JOIN countries c ON a.country_id = c.id
GROUP BY c.id, c.name;


REFRESH MATERIALIZED VIEW authors_by_country_view;

SELECT * FROM authors_by_country_view;




