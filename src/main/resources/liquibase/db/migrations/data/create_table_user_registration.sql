CREATE TABLE IF NOT EXISTS user_registration (
  user_registration_id INT NOT NULL AUTO_INCREMENT,
  user_id INT DEFAULT NULL,
  building_id INT DEFAULT NULL,
  registered_at TIMESTAMP NOT NULL,
  PRIMARY KEY (user_registration_id),
  FOREIGN KEY (user_id)
    REFERENCES user(user_id),
  FOREIGN KEY (building_id)
    REFERENCES building(building_id)
 );