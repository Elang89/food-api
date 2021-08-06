CREATE TABLE IF NOT EXISTS recipes (
    id uuid PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL)
