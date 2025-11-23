package com.oliveira.meucaixa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Query("SELECT * FROM users WHERE nomeCompleto = :nome AND dataNascimento = :dataNascimento LIMIT 1")
    User findUser(String nome, String dataNascimento);

    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    LiveData<User> getLastUser();
}
