insert into user_table (username, password, role)
values ('John Doe', '123', 'admin'),
       ('Jane Doe', '456', 'user'),
       ('Jonny Depp', '789', 'user'),
       ('Ana de Armas', '147', 'user');

insert into project (name, description, created_date)
values ('spring', 'some text', '2024-09-23'),
       ('homework-1', 'some text', '2024-09-20'),
       ('homework-2', 'some text', '2024-09-15'),
       ('homework-3', 'some text', '2024-09-10');

insert into users_project (project_id, user_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (3, 3),
       (2, 4),
       (4, 4);
