select count(*) from books;

select * from book_categories;

select * from books;

select name from books
where name = 'Clean Code';

select name, isbn, year, author, description from books
where name = 'Clean Code';

select  b.name, bc.name
from books b inner join book_categories bc on b.book_category_id = bc.id ;

select count(*) from book_categories;

select * from books;

select name from books;


 Display book name and related bookCategory name from library
  Find me how many book we have in each category
 Display most popular book category ( this book needs to borrow to be popular.)
     Display how many books are borrowed not turn back yet