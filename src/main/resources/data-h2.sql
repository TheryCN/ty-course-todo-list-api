-- Insert TodoList
INSERT INTO todo_list (id, name, created_date, updated_date) VALUES (1, 'Course 02/01/2020', '2019-12-25 00:00:00.00', '2019-12-25 00:00:00.00');

-- Insert Task
INSERT INTO task (id, name, description, priority, start_date, end_date, created_date, updated_date, todo_list_id) VALUES (1, 'Prepare Slides', 'Do it on Google Slide','MEDIUM', '2019-12-25 00:00:00.00', null, '2019-12-25 00:00:00.00', '2019-12-25 00:00:00.00', 1);
INSERT INTO task (id, name, description, priority, start_date, end_date, created_date, updated_date, todo_list_id) VALUES (2, 'Prepare Exercice', 'Do a web project with steps','MEDIUM', '2019-12-25 00:00:00.00', null, '2019-12-25 00:00:00.00', '2019-12-25 00:00:00.00', 1);
INSERT INTO task (id, name, description, priority, start_date, end_date, created_date, updated_date, todo_list_id) VALUES (3, 'Wave up at 8am', 'Be at office on time','HIGH', '2019-12-25 00:00:00.00', null, '2019-12-25 00:00:00.00', '2019-12-25 00:00:00.00', 1);