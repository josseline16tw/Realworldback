create table authors (
    id uuid not null primary key,
    username text not null unique,
    email text not null unique,
    bio text,
    image text -- URL
);

create table tags (
    id uuid not null primary key,
    name text not null
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