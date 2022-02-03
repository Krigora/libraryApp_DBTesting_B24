-- Display book name and related bookCategory name from library
select * from books;

select name, book_category_id from books;


select b.name, bc.name
from books b
inner join book_categories bc on b.book_category_id = bc.id;


 -- Find me how many book we have in each category_id

 select book_category_id,count(*) from books

group by book_category_id
order by  2 desc
limit 1;


-- Display most popular book category ( this book needs to borrow to be popular.)
select  bc.name,count(*)
from books b
 inner join book_categories bc on b.book_category_id = bc.id
group by bc.name
order by  2 desc
limit 1;



    -- Display how many books are borrowed not turn back yet

    select bc.name, count(*)
from book_borrow bb
inner join books b on bb.book_id = b.id
inner join book_categories bc on b.book_category_id = bc.id
group by  bc.name
order by 2 desc
limit 1;


