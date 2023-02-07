BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO posts (username, photo_url, likes, caption)
VALUES ('user', 'https://res.cloudinary.com/depdfgnxo/image/upload/v1675111884/ulbgynnx380mxbesgact.jpg', 3, 'Caption Uno');
INSERT INTO posts (username, photo_url, likes, caption)
VALUES ('admin', 'https://res.cloudinary.com/depdfgnxo/image/upload/v1675111162/cld-sample-5.jpg', 5, 'Caption Dos');

INSERT INTO comments (post_id, commenter, comment)
VALUES (1, 'admin', 'Great picture, Rick. Love the happy smile');
INSERT INTO comments (post_id, commenter, comment)
VALUES (1, 'admin', 'Wow, love the laptop. What coffee are you drinking?');

INSERT INTO comments (post_id, commenter, comment)
VALUES(2, 'user', 'Omg, what a cool shoe. What brand is it?');

INSERT INTO comments (post_id, commenter, comment)
VALUES (2, 'user', 'We are going to steal your shoes...jk....or not...wink wink');

INSERT INTO profiles (user_id, email, name, profile_image, profile_bio)
VALUES (1, 'fakeemail1@yahoo.com', 'RickUser', 'https://res.cloudinary.com/depdfgnxo/image/upload/v1675111884/ulbgynnx380mxbesgact.jpg', 'I like laptops');

INSERT INTO profiles (user_id, email, name, profile_image, profile_bio)
VALUES (2, 'fakeemail2@yahoo.com', 'NikeLover', 'https://res.cloudinary.com/depdfgnxo/image/upload/v1675111162/cld-sample-5.jpg', 'I like shoes');
COMMIT TRANSACTION;

INSERT INTO ratings (rating_id, post_id, rating, rater)
VALUES (1, 1, 5, 'admin');