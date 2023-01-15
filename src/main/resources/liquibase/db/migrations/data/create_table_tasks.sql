CREATE TABLE IF NOT EXISTS tasks (
  task_id INT NOT NULL AUTO_INCREMENT,
  status VARCHAR(50) NULL,
  parent_id int NULL,
  PRIMARY KEY (task_id),
  FOREIGN KEY (parent_id) REFERENCES tasks (task_id)
 );