INSERT INTO app.login (
        login,
        password,
        fio,
        birthday,
        create_at,
        role
    ) VALUES (
        'admin',
        'admin',
        'Admin',
        '1970-01-01',
        CURRENT_TIMESTAMP,
        'ADMIN'
    )
    ON CONFLICT (login) DO NOTHING;;