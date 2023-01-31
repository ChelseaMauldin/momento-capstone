BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO posts (username, photo_url, likes, caption)
VALUES ('user', 'https://res.cloudinary.com/depdfgnxo/image/upload/v1675111884/ulbgynnx380mxbesgact.jpg', 3, 'Caption Uno');
INSERT INTO posts (username, photo_url, likes, caption)
VALUES ('admin', 'https://res.cloudinary.com/depdfgnxo/image/upload/v1675111162/cld-sample-5.jpg', 5, 'Caption Dos');


COMMIT TRANSACTION;
