select count(*) from books;

select name from book_categories;

select * from books;

select name from books
where name = 'Clean Code';

select name, isbn, year, author, description from books
where name = 'Clean Code';