create table users (
    id uuid not null primary key,
    username text not null,
    email text not null,
    password text not null,
    bio text,
    image text
);

create table authors (
    id uuid not null primary key,
    username text not null unique,
    email text not null unique,
    user_id uuid not null references users(id)
);

create table tags (
    id uuid not null primary key,
    name text unique not null
);

create table articles (
    id uuid not null primary key,
    title text not null,
    slug text,
    description text not null,
    body text not null,
    author_id uuid not null references authors (id),
    created_at date not null default now(),
    updated_at date not null default now(),
    favorited boolean not null default false,
    favorites_count integer not null default 0
);



-- JOIN TABLE
create table articles_tags (
    article_id uuid not null references articles (id),
    tag_id uuid not null references tags (id),
    primary key (article_id, tag_id)
);

-- SEED DATABASE

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into users (id, username, email,password)
values (gen_random_uuid(), 'aloussase', 'alexander.goussas@thoughtworks.com','1'),
       (gen_random_uuid(), 'josseline', 'josseline.a@thoughtworks.com','2'),
       (gen_random_uuid(), 'melissa', 'melissa.ayllon@thoughtworks.com','3');

insert into authors (id, username, email,user_id)
select gen_random_uuid(), users.username, users.email, users.id
from users;

insert into articles (id, title, description, body, author_id)
select gen_random_uuid(), 'Titulo 1', 'Articulo 1', 'Contenido del articulo', a.id
from authors a;

insert into tags (id, name)
values (gen_random_uuid(), 'ia'),
       (gen_random_uuid(), 'chatgpt'),
       (gen_random_uuid(), 'tag'),
       (gen_random_uuid(), 'playa');

insert into articles_tags (article_id, tag_id)
select a.id, t.id
from articles a cross join tags t;

