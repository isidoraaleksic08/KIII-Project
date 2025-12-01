CREATE MATERIALIZED VIEW IF NOT EXISTS books_by_author AS
SELECT a.id AS author_id, a.first_name, a.last_name, COUNT(b.id) AS book_count
FROM authors a
         LEFT JOIN books b ON a.id = b.author_id
GROUP BY a.id;


CREATE UNIQUE INDEX idx_books_by_author ON books_by_author(author_id);


REFRESH MATERIALIZED VIEW books_by_author;

select * from books_by_author
