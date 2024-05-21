UPDATE users
SET category = SUBSTRING(MD5(random()::text) FROM 1 FOR 10)
WHERE category IS NULL;